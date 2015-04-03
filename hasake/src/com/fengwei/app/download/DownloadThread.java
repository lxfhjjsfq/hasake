package com.fengwei.app.download;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadThread extends Thread {

	private final String TAG = "DownloadThread";
	
	private DownloadBean mDownloadBean;
	private DownloadListener mDownloadListener;
	private int brocastCount = 0;
	private int BROCAST_MAX = 20;
	private boolean isPause = false;
	private boolean isWait = false;
	private boolean isFinish = false;
	private final int BUFFER = 4096;

	public DownloadThread(DownloadBean downBean) {
		super();
		mDownloadBean = downBean;
	}

	@Override
	public void run() {

		intDownloadBean();
		if (mDownloadBean.getState() == UDownloadManager.DOWN_RUNNING) {
			downloading();
		} else {
			// 更新广播
			if (mDownloadListener != null) {
				mDownloadListener.finish(mDownloadBean);
			}
		}
		//下载结束
		isFinish = true;
		DownloadTestUnit.d(TAG, "-----------isFinish");
	}

	public void pauseDownload(boolean pause) {
		isPause = pause;
	}

	public void waitDownload(boolean wait) {
		isWait = wait;
	}

	public void setDownloadListener(DownloadListener listener) {
		mDownloadListener = listener;
	}

	private void updateDownloadBean(int length) {
		int cur = mDownloadBean.getCurLength();
		cur = cur + length;
		mDownloadBean.setCurLength(cur);
		int percent = 0;
		try {
			percent = cur * 100 / mDownloadBean.getTotalLength();
			mDownloadBean.setPercent(percent);
		} catch (Exception e) {
//			mDownloadBean.setPercent(percent);
		}
		DownloadTestUnit.d(TAG, "DownloadBean length : " + cur);
	}

	/**
	 * 初始化下载对象（获取下载长度和状态，如果已经下载则更新为完成状态）　
	 */
	public void intDownloadBean() {
		DownloadTestUnit.d(TAG, "intDownloadBean");
		final String path = DownloadUtil.getFileNameFromUrl(mDownloadBean.getUrl());

		mDownloadBean.setPath(DownloadUtil.FILE_ROOT + path);
		// mDownloadBean.setPercent(0);

		try {
			URL url = new URL(mDownloadBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				// 服务器返回数据的长度　实际文件长度
				int length = conn.getContentLength();
				DownloadTestUnit.d(TAG, "total : " + length);
				mDownloadBean.setTotalLength(length);
				// 判断是否已经下载此文件
				File file = new File(DownloadUtil.FILE_ROOT + path);
				if (file.exists()) {
					// 如果长度等于下载完成长度，则发送下载完成广播
					int fileLength = (int) file.length();
					if (fileLength == length) {
						mDownloadBean.setPercent(100);
						mDownloadBean.setState(UDownloadManager.DOWN_COMPLITE);
					} else {
						// 断点续传(flv不支持断点续传)
						if(path.endsWith(".flv")){
							file.delete();
							mDownloadBean.setCurLength(0);
							mDownloadBean.setPercent(0);
						}
						else{
							mDownloadBean.setCurLength(fileLength);
							mDownloadBean.setPercent(fileLength * 100 / length);
						}
						mDownloadBean.setState(UDownloadManager.DOWN_RUNNING);
					}
				} else {
					mDownloadBean.setState(UDownloadManager.DOWN_RUNNING);
				}
				conn.disconnect();
				if (isPause) {
					DownloadTestUnit.d(TAG, "DOWN_PAUSE before downing! ");
					mDownloadBean.setState(UDownloadManager.DOWN_PAUSE);
				}
				if (isWait) {
					DownloadTestUnit.d(TAG, "DOWN_WAIT before downing! ");
					mDownloadBean.setState(UDownloadManager.DOWN_WAIT);
				}
			} else {
				// 服务器返回错误
				mDownloadBean.setState(UDownloadManager.DOWN_FAIL);
			}

		} catch (MalformedURLException e) {
			mDownloadBean.setState(UDownloadManager.DOWN_FAIL);
		} catch (IOException e) {
			mDownloadBean.setState(UDownloadManager.DOWN_FAIL);
		}
	}

	/**
	 * 下载文件
	 */
	private void downloading() {
		DownloadTestUnit.d(TAG, "downloading");
		try {
			// mDownloadBean.setState(UDownloadManager.DOWN_RUNNING);
			URL url = new URL(mDownloadBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			// 请求服务器下载的部分制定文件的位置
			conn.setRequestProperty("Range",
					"bytes=" + mDownloadBean.getCurLength() + "-"
							+ mDownloadBean.getTotalLength());
			int code = conn.getResponseCode();// 从服务器请求全部资源　200　ok
												// 如果从服务器请求部分资源　206 ok

			if (code == 200 || code == 206) {

				InputStream is = conn.getInputStream();
				// 在客户端创建一个RandomAccessFile文件
				RandomAccessFile raf = new RandomAccessFile(
						mDownloadBean.getPath(), "rw");
				raf.seek(mDownloadBean.getCurLength());// 定位文件

				int len = 0; // 写入文件长度
				int total = 0; // 已经下载的数据长度
				byte[] buffer = new byte[BUFFER];

				while ((len = is.read(buffer)) != -1) {
					brocastCount ++;
					// 可以中断，暂停或变成等待
					if (isPause) {
						DownloadTestUnit.d(TAG, "DOWN_PAUSE before write! ");
						mDownloadBean.setState(UDownloadManager.DOWN_PAUSE);
						break;
					}
					if (isWait) {
						DownloadTestUnit.d(TAG, "DOWN_WAIT before write! ");
						mDownloadBean.setState(UDownloadManager.DOWN_WAIT);
						break;
					}
					raf.write(buffer, 0, len);
					// 可以中断，暂停或变成等待
					if (isPause) {
						DownloadTestUnit.d(TAG, "DOWN_PAUSE after write! ");
						mDownloadBean.setState(UDownloadManager.DOWN_PAUSE);
						break;
					}
					if (isWait) {
						DownloadTestUnit.d(TAG, "DOWN_WAIT after write! ");
						mDownloadBean.setState(UDownloadManager.DOWN_WAIT);
						break;
					}
					total += len;
					// 设置当前下载长度
					updateDownloadBean(len);
					// 更新广播
					if (mDownloadListener != null && brocastCount >= BROCAST_MAX) {
						brocastCount = 0;
						mDownloadListener.running(mDownloadBean);
					}
					DownloadTestUnit.d(TAG, "while continue! ");
					Thread.sleep(10);
					DownloadTestUnit.d(TAG, "sleep end! ");
				}
				DownloadTestUnit.d(TAG, "break while");
				is.close();
				raf.close();
				if (!isPause && !isWait) {
					mDownloadBean.setPercent(100);
					mDownloadBean.setState(UDownloadManager.DOWN_COMPLITE);
				}
			} else {
				// 下载失败
				mDownloadBean.setState(UDownloadManager.DOWN_FAIL);
			}
		} catch (MalformedURLException e) {
			DownloadTestUnit.d(TAG, "break MalformedURLException");
			mDownloadBean.setState(UDownloadManager.DOWN_FAIL);
		} catch (IOException e) {
			DownloadTestUnit.d(TAG, "break IOException");
			mDownloadBean.setState(UDownloadManager.DOWN_FAIL);
		} catch (InterruptedException e) {
			DownloadTestUnit.d(TAG, "break InterruptedException");
			mDownloadBean.setState(UDownloadManager.DOWN_FAIL);
		} catch(Exception e){
			DownloadTestUnit.d(TAG, "break Exception");
			mDownloadBean.setState(UDownloadManager.DOWN_FAIL);
		}
		finally {
			// 更新广播
			if (mDownloadListener != null) {
				DownloadTestUnit.d(TAG, " finally mDownloadListener");
				mDownloadListener.finish(mDownloadBean);
			}
		}
	}

	//是否下载结束
	public boolean isFinish(){
		return isFinish;
	}
	
}

package com.fengwei.app.download;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

public class UDownloadManager implements DownloadInterface {

	private final String TAG = "UDownloadManager";
	
	public static final String DOWN_RECEIVER_ACTION = "com.tingshu.hasake.downloader.receiver";
	public static final String DOWN_RECEIVER_KEY = "download";
	
	/**
	 * 新加入的下载任务
	 */
	public static final int DOWN_NEW = 0;
	/**
	 * 下载等待
	 */
	public static final int DOWN_WAIT = 1;
	/**
	 * 下载中
	 */
	public static final int DOWN_RUNNING = 3;
	/**
	 * 下载暂停
	 */
	public static final int DOWN_PAUSE = 2;
	/**
	 * 下载失败
	 */
	public static final int DOWN_FAIL = 4;
	/**
	 * 下载完成
	 */
	public static final int DOWN_COMPLITE = 5;

	private static Context mContext;
	private static UDownloadManager instance;
	private static DownloadQueue mDownloadQueue;
	private static DownloadThread mDownloadThread;
	private static Handler sendBrocaseHandler;

	private static DownloadListener mDownloadListener = new DownloadListener() {
		public void running(DownloadBean downBean) {
			// 由handler发送更新下载进度广播
			Message msg = sendBrocaseHandler.obtainMessage();
			msg.obj = downBean;
			sendBrocaseHandler.sendMessage(msg);
		}

		public void finish(DownloadBean downBean) {
			// 发送下载结束(可能失败或暂停，等待)广播
			Message msg = sendBrocaseHandler.obtainMessage();
			msg.obj = downBean;
			sendBrocaseHandler.sendMessage(msg);
			// 删除下载队列中的下载任务
			if (downBean.getState() == DOWN_COMPLITE) {
				mDownloadQueue.removeDownloadBean(downBean);
			}

			// 检查是否有可以下载的任务
			// checkDownloadQueue();
		}
	};

	private UDownloadManager() {
	}

	public static void init(Context context){
		if (instance == null) {
			mContext = context;
			instance = new UDownloadManager();
			mDownloadQueue = new DownloadQueue();
			initHandler();
		}
	}
	
	public static UDownloadManager getInstance() {
		if(instance == null){
			throw new IllegalArgumentException("没有初始化UDownloadManager，请调用UDownloadManager.init");
		}
		return instance;
	}

	@Override
	public synchronized void addDownload(String url) {
		if(TextUtils.isEmpty(url)){
			return;
		}
		DownloadBean down = new DownloadBean();
		down.setUrl(url);
		down.setState(DOWN_NEW);
		// 加入下载队列
		mDownloadQueue.addQueue(down);
		// 检查下载队列是否有已经结束的下载任务
		checkDownloadQueue();
	}

	@Override
	public synchronized void startDownload(String url) {

		DownloadBean bean = mDownloadQueue.getDownloadBean(url);
		if (bean == null) {
			DownloadBean down = new DownloadBean();
			down.setUrl(url);
			down.setState(DOWN_NEW);
			// 加入下载队列
			mDownloadQueue.addQueue(down);
			bean = down;
		}

		if (mDownloadThread != null && !mDownloadThread.isFinish()) {
			mDownloadThread.waitDownload(true);
			while (!mDownloadThread.isFinish()) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		mDownloadThread = new DownloadThread(bean);
		mDownloadThread.setDownloadListener(mDownloadListener);
		mDownloadThread.start();
	}

	@Override
	public synchronized void pauseDownload(String url) {
		DownloadBean bean = mDownloadQueue.getDownloadBean(url);
		if (bean == null || bean.getState() != DOWN_RUNNING) {
			return;
		}

		if (mDownloadThread != null && !mDownloadThread.isFinish()) {
			mDownloadThread.pauseDownload(true);
			while (!mDownloadThread.isFinish()) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			

			}
		}
		// 检查是否有可以下载的任务
		checkDownloadQueue();
	}

	/**
	 * 继续下载任务（如果之前没有下载，则加入到下载列表并下载）
	 */
	@Override
	public synchronized void continueDownload(String url) {
		DownloadBean bean = mDownloadQueue.getDownloadBean(url);
		if (bean == null){
			DownloadBean down = new DownloadBean();
			down.setUrl(url);
			down.setState(DOWN_NEW);
			// 加入下载队列
			mDownloadQueue.addQueue(down);
			bean = down;
		}
		else if(bean.getState() == DOWN_RUNNING) {
			return;
		}
		if (mDownloadThread != null && !mDownloadThread.isFinish()) {
			mDownloadThread.waitDownload(true);
			while (!mDownloadThread.isFinish()) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			

			}
		}
		// 检查是否有可以下载的任务
		mDownloadThread = new DownloadThread(bean);
		mDownloadThread.setDownloadListener(mDownloadListener);
		mDownloadThread.start();
	}
	@Override
	public void waitDownload(String url) {
		// DownloadBean bean = mDownloadQueue.getDownloadBean(url);
		// if(bean == null || bean.getState() != DOWN_RUNNING){
		// return;
		// }
		//
		// if(mDownloadThread != null && !mDownloadThread.isFinish()){
		// mDownloadThread.waitDownload(true);
		// while(!mDownloadThread.isFinish()){
		//
		// }
		// }
		// //检查是否有可以下载的任务
		// checkDownloadQueue();
	}

	@Override
	public synchronized void deleteDownload(String url) {
		DownloadBean bean = mDownloadQueue.getDownloadBean(url);
		if (bean == null) {
			//下载完成的
			String path = DownloadUtil.getFileNameFromUrl(url);
//			TestUnit.test(TAG, "path: " + path);
			deleteFile(DownloadUtil.FILE_ROOT + path);
			return;
		}
		// 如果正在下载，暂停任务
		if (bean.getState() == DOWN_RUNNING) {
			mDownloadThread.pauseDownload(true);
//			while (!mDownloadThread.isFinish()) {
//				try {
//					Thread.sleep(30);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			
//
//			}
		}
		// 删除任务
//		File file = new File("/storage/emulated/0/utv");
//		String[] fs = file.list();
//		for(String str : fs){
//			DownloadTestUnit.d(TAG, str);
//		}
		deleteFile(bean.getPath());
		
		mDownloadQueue.removeDownloadBean(bean);
		// 检查是否有可以下载的任务
//		checkDownloadQueue();
	}

	/**
	 * 检查是否有可以下载的任务
	 */
	public void checkDownloadQueue() {
		if (mDownloadThread == null || mDownloadThread.isFinish()) {
			DownloadBean bean = mDownloadQueue.getCurWaitDownload();
			if(bean != null){
				mDownloadThread = new DownloadThread(bean);
				mDownloadThread.setDownloadListener(mDownloadListener);
				mDownloadThread.start();
			}
		}
	}

	private static void initHandler() {
		sendBrocaseHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				
				DownloadBean bean = (DownloadBean) msg.obj;
				Intent broadcast = new Intent(DOWN_RECEIVER_ACTION);
				broadcast.putExtra(DOWN_RECEIVER_KEY, bean);
				mContext.sendOrderedBroadcast(broadcast, null);
				
				if(mDownloadCallback != null && isCallback){
					mDownloadCallback.onReceive(bean);
				}
			}
		};
	}

	private void deleteFile(final String path){
		if(path != null){
			new Thread(){
				public void run(){
					File f1 = 	new File(path);
//					TestUnit.test(TAG, "delete before file");
					if(f1.exists()){
						DownloadTestUnit.d(TAG, "delete file ing");
						f1.delete();
					}
				}
			}.start();
		}
	}
	
	private static DownloadCallback mDownloadCallback;
	private static boolean isCallback = false;
	public void setDownloadCallback(DownloadCallback callback){
		mDownloadCallback = callback;
		isCallback = true;
	}
	public void clearDownloadCallback(){
		isCallback = false;
	}
	
}

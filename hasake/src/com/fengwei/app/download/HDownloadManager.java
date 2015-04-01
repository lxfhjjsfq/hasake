package com.fengwei.app.download;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.tingshu.hasake.database.DownloadDao;


public class HDownloadManager{
	
	private static HttpHandler<?> mHttpHandler;
	private static DownloadDao mDownloadDao;
	
	public static void startDown(DownloadDao dao){
		
		//检查是否已经有下载任务，如果有，先暂停上一个下载任务
		if(mHttpHandler != null){
			mHttpHandler.pause();
			mHttpHandler = null;
			mDownloadDao = null;
		}
		
		//获取路径
		String target = HDownloadUtil.getFileNameFromUrl(dao.getDownUrl());
		dao.setPath(target);
		mDownloadDao = dao;
		//开始下载任务
		HttpUtils httpUtils = new HttpUtils();
		mHttpHandler = httpUtils.download(dao.getDownUrl(), target, true, new RequestCallBack<File>() {
			public void onStart() {
				if(mDownCallback != null){
					mDownCallback.onStart();
				}
			}
			public void onLoading(long total, long current, boolean isUploading) {
				mDownloadDao.setCurLength(current);
				mDownloadDao.setTotalLength(total);
				if(mDownCallback != null){
					mDownCallback.onLoading(total, current);
				}
			}
			public void onSuccess(ResponseInfo<File> arg0) {
				//更新数据库
				if(mDownCallback != null){
					mDownCallback.onSuccess();
				}
			}
			public void onFailure(HttpException arg0, String arg1) {
				//更新数据库
				if(mDownCallback != null){
					mDownCallback.onFailure();
				}
			}
		});
		
		//设置DownloadDao
		
		//
	}
	
	
	public static void pauseDown(String url){
		
		//如果正在下载，则暂停下载任务
		if(mHttpHandler != null){
			mHttpHandler.pause();
		}
	}
	
	public void setDownCallback(DownCallback callback){
		mDownCallback = callback;
	}
	private static DownCallback mDownCallback;
	public interface DownCallback{
		public void onStart();
		public void onLoading(long total, long current);
		public void onSuccess();
		public void onFailure();
	}
}

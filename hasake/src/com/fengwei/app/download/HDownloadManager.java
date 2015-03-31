package com.fengwei.app.download;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.tingshu.hasake.database.DownloadDao;


public class HDownloadManager{
	
	private static HttpHandler mHttpHandler;
	
	public static void startDown(DownloadDao dao){
		
		//检查是否已经有下载任务，如果有，先暂停上一个下载任务
		if(mHttpHandler != null){
			mHttpHandler.pause();
			mHttpHandler = null;
		}
		
		//获取路径
		String target = HDownloadUtil.getFileNameFromUrl(dao.getDownUrl());
		dao.setPath(target);
		//开始下载任务
		HttpUtils httpUtils = new HttpUtils();
		mHttpHandler = httpUtils.download(dao.getDownUrl(), target, true, null);
		
		//设置DownloadDao
		
		//
	}
	
	
	public static void pauseDown(String url){
		
		//如果正在下载，则暂停下载任务
		if(mHttpHandler != null){
			mHttpHandler.pause();
		}
	}
	
	class HDownCallback extends RequestCallBack<File>{

		@Override
		public void onStart() {
			super.onStart();
		}
		
		@Override
		public void onLoading(long total, long current, boolean isUploading) {
			super.onLoading(total, current, isUploading);
		}
		
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			
		}

		@Override
		public void onSuccess(ResponseInfo arg0) {
			
		}
		
	}
}

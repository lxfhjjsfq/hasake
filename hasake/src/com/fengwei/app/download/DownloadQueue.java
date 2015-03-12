package com.fengwei.app.download;


import java.util.ArrayList;

import android.text.TextUtils;

public class DownloadQueue {

	private ArrayList<DownloadBean> mDownQueue = new ArrayList<DownloadBean>();
	
	public DownloadQueue(){}
	
	/**
	 * 将下载任务加入的队列里
	 * @param downBean
	 */
	public synchronized void addQueue(DownloadBean downBean){
		
		for(DownloadBean bean : mDownQueue){
			if(bean.getUrl().equals(downBean.getUrl())){
				return;
			}
		}
		mDownQueue.add(downBean);
	}
	
	/**
	 * 移除队列里对应的下载任务
	 * @param downBean
	 * @return
	 */
	public synchronized boolean removeDownloadBean(DownloadBean downBean){
		for(DownloadBean bean : mDownQueue){
			if(bean.getUrl().equals(downBean.getUrl())){
				mDownQueue.remove(bean);
				return true;
			}
		}
		return false;
	}
	
	public synchronized DownloadBean getCurWaitDownload(){
		for(DownloadBean bean : mDownQueue){
			if(bean.getState() == UDownloadManager.DOWN_NEW || bean.getState() == UDownloadManager.DOWN_WAIT){
				return bean;
			}
		}
		return null;
	}
	
	public synchronized DownloadBean getDownloadBean(String url){
		for(DownloadBean bean : mDownQueue){
			if(bean.getUrl().equals(url)){
				return bean;
			}
		}
		return null;
	}
	
	/**
	 * 获取全部下载任务
	 * @return
	 */
	public synchronized ArrayList<DownloadBean> getDownList(){
		return mDownQueue;
	}
	
	/**
	 * 判断下载队列里面是否有对应下载任务
	 * @param url
	 * @return
	 */
	public synchronized boolean isExistDownloadBean(String url){
		for(DownloadBean bean : mDownQueue){
			if(bean.getUrl().equals(url)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取下载列队长度
	 * @return
	 */
	public synchronized int size(){
		return mDownQueue.size();
	}
	
}

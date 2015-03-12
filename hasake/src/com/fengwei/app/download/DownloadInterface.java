package com.fengwei.app.download;


public interface DownloadInterface {

	/**
	 * 添加下载
	 * @param url
	 */
	public void addDownload(String url);
	/**
	 * 开始下载
	 * @param url
	 */
	public void startDownload(String url);
	/**
	 * 暂停下载
	 * @param url
	 */
	public void pauseDownload(String url);
	/**
	 *继续下载 
	 * @param url
	 */
	public void continueDownload(String url);
	/**
	 * 等待下载
	 * @param url
	 */
	public void waitDownload(String url);
	/**
	 * 删除下载
	 * @param url
	 */
	public void deleteDownload(String url);
	
}

package com.fengwei.app.download;

public interface DownloadListener {

	/**
	 * 下载中
	 * @param downBean
	 */
	public void running(DownloadBean downBean);
	/**
	 * 下载结束
	 * @param downBean
	 */
	public void finish(DownloadBean downBean);
	
}

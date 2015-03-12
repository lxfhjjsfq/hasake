package com.tingshu.hasake;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setcontentView();
		initView();
		initListener();
		initData();
	}
	/**
	 * 加载试图
	 */
	protected abstract void setcontentView();

	/**
	 * 初始化view
	 */
	protected abstract void initView();

	/***
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 添加监听
	 */
	protected abstract void initListener();

}

package com.tingshu.hasake;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

public abstract class BaseActivity extends Activity {

	protected Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		context = this;
		setcontentView();
		initView();
		initListener();
		initData();
	}

	/**
	 * 加载布局文件
	 */
	protected abstract void setcontentView();

	/**
	 * 加载view
	 */
	protected abstract void initView();

	/***
	 * 加载数据
	 */
	protected abstract void initData();

	/**
	 * 绑定监听
	 */
	protected abstract void initListener();

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}

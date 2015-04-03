package com.tingshu.hasake;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends FragmentActivity {

	protected Context context;
	protected HaskApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		context = this;
		application=(HaskApplication) getApplication();
		setcontentView();
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.activity_title_view);
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

	public void setTitleContent(String title) {
		TextView tv_title = (TextView) findViewById(R.id.act_tiltle_text);
		tv_title.setText(title);
	}

	public void toast(String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}

	public void log(String content) {
		Log.d("hask", content);
	}
	
	public void getNetData(){
		
	}

}

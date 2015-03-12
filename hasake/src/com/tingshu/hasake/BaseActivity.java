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
	 * ������ͼ
	 */
	protected abstract void setcontentView();

	/**
	 * ��ʼ��view
	 */
	protected abstract void initView();

	/***
	 * ��ʼ������
	 */
	protected abstract void initData();

	/**
	 * ��Ӽ���
	 */
	protected abstract void initListener();

}

package com.tingshu.hasake;

import com.tingshu.hasake.utils.SfpUtils;

import android.app.Application;

public class HaskApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
	}

	public boolean isLogin() {
		return SfpUtils.getIntDataToSp(this, SfpUtils.USER_ID, -1) != -1;
	}
}

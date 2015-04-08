package com.tingshu.hasake;

import com.tingshu.hasake.config.HasakeConfig;
import com.tingshu.hasake.database.DatabaseUtil;
import com.tingshu.hasake.utils.SfpUtils;

import android.app.Application;

public class HaskApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		
		//初始化数据库
//		DatabaseUtil.initDatabase(this);
		HasakeConfig.initSubType(this);
		
	}

	public boolean isLogin() {
		return SfpUtils.getIntDataToSp(this, SfpUtils.USER_ID, -1) != -1;
	}

	public int getUserId() {
		return SfpUtils.getIntDataToSp(this, SfpUtils.USER_ID, -1);
	}
}

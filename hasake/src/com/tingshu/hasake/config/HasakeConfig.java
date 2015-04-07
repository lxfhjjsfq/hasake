package com.tingshu.hasake.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import android.content.Context;
import android.util.Log;

public class HasakeConfig {
	private final static String TAG = "HasakeConfig";
	
	public static String home_tv_wenxue;
	public static String home_tv_yule;
	public static String home_tv_lishi;
	public static String home_tv_yinyue;
	public static String home_tv_ertong;
	public static String home_tv_jiankang;
	
	/**
	 * 初始化首页分类菜单
	 */
	public static void initHomeType(Context context){
		Properties properties = new Properties();
		try {
			InputStream is = context.getAssets().open("hanyu/home.properties");
			properties.load(is);
			
//			home_tv_wenxue = new String(String.valueOf(properties.get("home_tv_wenxue")).getBytes("ISO-8859-1"),"UTF-8");
//			home_tv_wenxue = properties.getProperty("home_tv_wenxue");
//			home_tv_yule = properties.getProperty("home_tv_yule");
//			home_tv_lishi = properties.getProperty("home_tv_lishi");
//			home_tv_yinyue = properties.getProperty("home_tv_yinyue");
//			home_tv_ertong = properties.getProperty("home_tv_ertong");
//			home_tv_jiankang = properties.getProperty("home_tv_jiankang");
			
			home_tv_wenxue = getPropertyValue(properties, "home_tv_wenxue");
			home_tv_yule = getPropertyValue(properties, "home_tv_yule");
			home_tv_lishi = getPropertyValue(properties, "home_tv_lishi");
			home_tv_yinyue = getPropertyValue(properties, "home_tv_yinyue");
			home_tv_ertong = getPropertyValue(properties, "home_tv_ertong");
			home_tv_jiankang = getPropertyValue(properties, "home_tv_jiankang");
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String getPropertyValue(Properties properties, String key){
		String ret = "";
		try {
			ret = new String(String.valueOf(properties.get(key)).getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
			Log.e(TAG, "初始化properties失败");
		}
		return ret;
	}
}

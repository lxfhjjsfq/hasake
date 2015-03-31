package com.tingshu.hasake.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SfpUtils {

	public static final String USER_NAM = "user_name";
	public static final String USER_ID = "user_id";

	public static void saveStringDataToSp(Context context, String tag,
			String content) {
		SharedPreferences preferences = getSp(context);
		Editor editor = preferences.edit();
		editor.putString(tag, content);
		editor.commit();
	}

	public static void saveIntDataToSp(Context context, String tag, int content) {
		SharedPreferences preferences = getSp(context);
		Editor editor = preferences.edit();
		editor.putInt(tag, content);
		editor.commit();
	}

	private static SharedPreferences getSp(Context context) {
		return context.getSharedPreferences("hask", 0);
	}

	public static int getIntDataToSp(Context context, String key,
			int defaultVlue) {
		return context.getSharedPreferences("hask", 0).getInt(key, defaultVlue);
	}

}

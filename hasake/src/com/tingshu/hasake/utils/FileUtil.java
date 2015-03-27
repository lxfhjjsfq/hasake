package com.tingshu.hasake.utils;

import android.os.Environment;

public class FileUtil {

	public static String getPath(){
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
		path += "/hasake/";
		return path;
	}
	
}

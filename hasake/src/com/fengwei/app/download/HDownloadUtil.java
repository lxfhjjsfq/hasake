package com.fengwei.app.download;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HDownloadUtil {

	/**
	 * 获取文件名
	 * @param url
	 * @return
	 */
	public static String getFileNameFromUrl(String url) {

		String extName = "";
		String filename;
		int index = url.lastIndexOf('?');
		if (index > 1) {
			extName = url.substring(url.lastIndexOf('.') + 1, index);
		} else {
			extName = url.substring(url.lastIndexOf('.') + 1);
		}
		filename = hashKeyForDisk(url) + "." + extName;
		return filename;
	}

	public static String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}
	private static String bytesToHexString(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++)
		{
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1)
			{
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}

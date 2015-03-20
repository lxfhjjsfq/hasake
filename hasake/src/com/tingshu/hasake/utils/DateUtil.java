package com.tingshu.hasake.utils;


import java.util.Date;

/**
 * 获取日期字符串类
 * 
 * @author lxf
 * 
 */
public class DateUtil {

	// private static Date mDate;

	/*
	 * public DateUtil(long time) { mDate = new Date(time); }
	 */

	public static String getDateOfMonth(long time) {
		Date mDate = new Date(time);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append((mDate.getMonth() + 1));
		stringBuilder.append("月");
		stringBuilder.append(mDate.getDate());
		return stringBuilder.toString();
	}

	public String getDateOfYear(String time) {
		Date mDate = new Date(time);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(((mDate.getYear() + 1900)));
		stringBuilder.append("年");
		stringBuilder.append((mDate.getMonth() + 1));
		stringBuilder.append("月");
		stringBuilder.append(mDate.getDate());
		return stringBuilder.toString();
	}

	/**
	 * 使用给定的标记连接年，月，日（如：mark为＂－＂，yyyy-mm-dd）
	 * 
	 * @param mark
	 * @return
	 */
	public static String getDateOfYearByChar(long time, String mark) {
		Date mDate = new Date(time);
		StringBuilder stringBuilder = new StringBuilder();
		String year = (mDate.getYear() + 1900) + "";
		String month = (mDate.getMonth() + 1) + "";
		String date = mDate.getDate() + "";
		if (month.length() == 1) {
			month = "0" + month;
		}
		if (date.length() == 1) {
			date = "0" + date;
		}
		stringBuilder.append(year);
		stringBuilder.append(mark);
		stringBuilder.append(month);
		stringBuilder.append(mark);
		stringBuilder.append(date);
		return stringBuilder.toString();
	}

	public String getDayOfWeek(long time) {
		Date mDate = new Date(time);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("周");
		String day = "日";
		switch (mDate.getDay()) {
		case 1:
			day = "一";
			break;
		case 2:
			day = "二";
			break;
		case 3:
			day = "三";
			break;
		case 4:
			day = "四";
			break;
		case 5:
			day = "五";
			break;
		case 6:
			day = "六";
			break;
		}
		stringBuilder.append(day);
		return stringBuilder.toString();
	}

	/**
	 * 获取当天时间（hh:mm:ss）
	 * 
	 * @param time
	 * @return
	 */
	public static String getTimeOfDay(long time) {
		Date mDate = new Date(time);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getNumByXX(mDate.getHours()));
		stringBuilder.append(":");
		stringBuilder.append(getNumByXX(mDate.getMinutes()));
		stringBuilder.append(":");
		stringBuilder.append(getNumByXX(mDate.getSeconds()));
		return stringBuilder.toString();
	}

	/**
	 * 获得一天中指定的时间（hh:mm:ss方式）
	 * 
	 * @param time
	 *            　时间
	 * @param length
	 *            　时长（时间间隔）
	 * @return
	 */
	public static String getTimeOfDay(String time, int length) {

		if (time.length() > 8) {// 去除年，月，日多余信息
			time = time.substring(time.length() - 8, time.length());
		}
		if (length <= 0) {
			return time;
		}
		// 分别计算时，分，秒差值
		int second = length % 60;
		int minute = length / 60;
		int hour = minute / 60;
		minute = minute % 60;

		String temp = time.replace("：", ":");
		String[] timeArray = temp.split(":");

		second = Integer.parseInt(timeArray[2]) + second;
		if (second > 59) {
			minute = minute + 1;
			second = second % 60;
		}
		minute = Integer.parseInt(timeArray[1]) + minute;
		if (minute > 59) {
			hour = hour + 1;
			minute = minute % 60;
		}
		hour = Integer.parseInt(timeArray[0]) + hour;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getNumByXX(hour));
		stringBuilder.append(":");
		stringBuilder.append(getNumByXX(minute));
		stringBuilder.append(":");
		stringBuilder.append(getNumByXX(second));
		return stringBuilder.toString();
	}

	/**
	 * 获得一天中指定的时间（hh:mm:ss方式）
	 * 
	 * @param time
	 *            时间
	 * @param length
	 *            时长（时间间隔）
	 * @return
	 */
	public static String getTimeOfDay(String time, String length) {
		int len = Integer.parseInt(length);
		return getTimeOfDay(time, len);
	}

	/**
	 * 判断是否到达给定时间
	 * 
	 * @param endTime
	 *            　 给定时间　
	 * @param time
	 *            　 当前时间
	 * @return
	 */
	public static boolean isBeforeTheTime(String endTime, long time) {

		String curTemp = getTimeOfDay(time).replace("：", ":").replace(":", "");
		String endTemp = getTimeOfDay(endTime, "0").replace("：", ":").replace(
				":", "");

		int cur = Integer.parseInt(curTemp);
		int end = Integer.parseInt(endTemp);

		if (cur > end) {
			return false;
		}
		return true;
	}

	/**
	 * 根据给定的时间点，获取当前过去的时间毫秒数
	 * 
	 * @param startTime
	 *            　给定的时间点（要计算当前与之时间差）
	 * @return 时间差
	 */
	public static int getLastTime(String startTime) {

		Date mDate = new Date(System.currentTimeMillis());
		int hour = mDate.getHours();
		int minute = mDate.getMinutes();
		int second = mDate.getSeconds();

		if (startTime.length() > 8) {// 去除年，月，日多余信息
			startTime = startTime.substring(startTime.length() - 8,
					startTime.length());
		}

		String[] timeArray = startTime.replace("：", ":").split(":");
		hour = hour - Integer.parseInt(timeArray[0]);
		minute = minute - Integer.parseInt(timeArray[1]);
		second = second - Integer.parseInt(timeArray[2]);

		return hour * 3600 + minute * 60 + second;
	}

	/**
	 * 获取两个时间差值
	 * 
	 * @param firstTime
	 *            　第一个时间（要求hh:mm:ss）
	 * @param secondTime
	 *            第二个时间（要求hh:mm:ss）
	 * @return
	 */
	public static int getDurationFromTime(String firstTime, String secondTime) {
		if (firstTime.length() > 8) {// 去除年，月，日多余信息
			firstTime = firstTime.substring(firstTime.length() - 8,
					firstTime.length());
		}
		if (secondTime.length() > 8) {// 去除年，月，日多余信息
			secondTime = secondTime.substring(secondTime.length() - 8,
					secondTime.length());
		}

		String[] firstArray = firstTime.replace("：", ":").split(":");
		String[] secondArray = secondTime.replace("：", ":").split(":");

		int hour = Integer.parseInt(firstArray[0])
				- Integer.parseInt(secondArray[0]);
		int minute = Integer.parseInt(firstArray[1])
				- Integer.parseInt(secondArray[1]);
		int second = Integer.parseInt(firstArray[2])
				- Integer.parseInt(secondArray[2]);

		return hour * 3600 + minute * 60 + second;
	}

	/**
	 * 从时间秒数得到时间形式的表示（如：２４１，　６：０１）
	 * 
	 * @param time
	 *            　时间数(秒数)
	 * @return
	 */
	public static String getFormatTime(String time) {
		return getFormatTime(Integer.parseInt(time));
	}

	/**
	 * 从时间秒数得到时间形式的表示（如：241，6:01）
	 * 
	 * @param time
	 *            时间数(秒数)
	 * @param formatNum
	 *            需要显示时间格式的位数（如formatNum＝5，241-->06:01）
	 * @return
	 */
	public static String getFormatTime(String time, int formatNum) {
		return getFormatTime(Integer.parseInt(time), formatNum);
	}

	/**
	 * 从时间秒数得到时间形式的表示（如：２４１，　６：０１）
	 * 
	 * @param time
	 *            　时间数(秒数)
	 * @return
	 */
	public static String getFormatTime(int time) {

		if (time <= 0) {
			return "00:00";
		}
		StringBuffer ret = new StringBuffer();
		// 分别计算时，分，秒差值
		int second = time % 60;
		int minute = time / 60;
		int hour = minute / 60;
		minute = minute % 60;

		if (hour > 0) {
			ret.append(getNumByXX(hour) + ":");
		}
		if (minute >= 0) {
			// if (hour > 0) {
			ret.append(getNumByXX(minute) + ":");
			// } else {
			// ret.append(minute + ":");
			// }
		}
		if (second >= 0) {
//			if (hour > 0 || minute > 0) {
				ret.append(getNumByXX(second));
			// } else {
			// ret.append(second);
			// }
		}
		return ret.toString();
	}

	/**
	 * 从时间秒数得到时间形式的表示（如：２４１，　６：０１）
	 * 
	 * @param time
	 *            时间数(秒数)
	 * @param formatNum
	 *            需要显示时间格式的位数（如formatNum＝４，２４１－－＞０６：０１　）
	 * @return
	 */
	public static String getFormatTime(int time, int formatNum) {

		if (time <= 0) {
			return "00:00";
		}
		StringBuffer ret = new StringBuffer();
		// 分别计算时，分，秒差值
		int second = time % 60;
		int minute = time / 60;
		int hour = minute / 60;
		minute = minute % 60;

		if (formatNum == 8) {// 00:00:00
			ret.append(getNumByXX(hour) + ":");
		} 
//		else {
//			ret.append(hour + ":");
//		}
		if (formatNum >= 5) {// 00:00
			ret.append(getNumByXX(minute) + ":");
		} 
//		else {
//			ret.append(minute + ":");
//		}
		if (formatNum >= 1) {
			ret.append(getNumByXX(second));
		} 
//		else {
//
//			ret.append(second);
//		}
		return ret.toString();
	}

	protected static String getNumByXX(String params) {
		if (params.length() == 1)
			return "0" + params;
		return params;
	}

	protected static String getNumByXX(int params) {
		if (params < 10)
			return "0" + params;
		return params + "";
	}
}

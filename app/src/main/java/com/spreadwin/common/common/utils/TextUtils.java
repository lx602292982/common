package com.spreadwin.common.common.utils;


import com.spreadwin.common.constant.Constant;

public class TextUtils {

	public static boolean isEmpty(CharSequence str) {
		return (str == null || str.length() == 0 || str.equals("null")
				|| str.equals("") || str.equals("[]") || str.equals(" "));
	}

	public static boolean isEquals(Object actual, Object expected) {
		return actual == expected
				|| (actual == null ? expected == null : actual.equals(expected));
	}

	public static String getSimpleLocation(String location) {
		if (isEmpty(location)) {
			return "";
		}
		int length = location.length();
		for (int i = 1; i < length; i++) {
			String splitStr = location.substring(length - i, length);
			if (isEquals(splitStr, "省") || isEquals(splitStr, "市")
					|| isEquals(splitStr, "县") || isEquals(splitStr, "地区")) {
				return location.substring(0, length - i);
			}
		}
		return location;
	}
	
	public static String isHttpHost(String url) {
		if (isEmpty(url)) {
			return "";
		}
		String host = url.substring(0, 4);
		return host.equals("http") ? url : addSpriet(url);
	}

	public static String addSpriet(String url) {
		String spriet = url.substring(0, 1);
		return spriet.equals("/") ? Constant.img + url : Constant.img + "/"
				+ url;
	}
	
	public static boolean isQiniuHttp(String url) {
		String http = url.substring(0, Constant.img.length());
		return http.equals(Constant.img);
	}

	public static char ascii2Char(int ASCII) {
		return (char) ASCII;
	}

}

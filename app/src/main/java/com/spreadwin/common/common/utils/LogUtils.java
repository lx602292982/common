package com.spreadwin.common.common.utils;

import android.util.Log;

public class LogUtils {
	public static boolean isDebug = true;


	// public static boolean allowE = true;
	// public static boolean allowI = true;
	// public static boolean allowV = true;
	// public static boolean allowW = true;
	// public static boolean allowWtf = true;
	
	public static void d(String content) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.d(tag, content);
	}

	public static void d(String content, Throwable tr) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.d(tag, content, tr);
	}

	public static void e(String content) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.e(tag, content);
	}

	public static void e(String content, Throwable tr) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.e(tag, content, tr);
	}

	public static void i(String content) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.i(tag, content);
	}

	public static void i(String content, Throwable tr) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.i(tag, content, tr);
	}

	public static void v(String content) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.v(tag, content);
	}

	public static void v(String content, Throwable tr) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.v(tag, content, tr);
	}

	public static void w(String content) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.w(tag, content);
	}

	public static void w(String content, Throwable tr) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.w(tag, content, tr);
	}

	public static void w(Throwable tr) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.w(tag, tr);
	}

	public static void wtf(String content) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.wtf(tag, content);
	}

	public static void wtf(String content, Throwable tr) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.wtf(tag, content, tr);
	}

	public static void wtf(Throwable tr) {
		if (!isDebug) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.wtf(tag, tr);
	}

	// 跟踪到调用该日志的方法
	private static StackTraceElement getCallerMethodName() {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		return stacks[4];
	}

	// 规范TAG格式：类名[方法名， 调用行数]
	private static String generateTag(StackTraceElement caller) {
		String tag = "%s[%s, %d]";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName
				.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(),
				caller.getLineNumber());
		return tag;
	}

}

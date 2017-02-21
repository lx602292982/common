package com.spreadwin.common.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

@SuppressLint("NewApi")
public class DeviceUtils {
	public static final int APP_STATUS_KILL = 0;
	public static final int APP_STATUS_BACKGROUND = 1;
	public static final int APP_STATUS_FOREGROUND = 2;
	public static final int APP_STATUS_SERVIES = 3;
	/**
	 * @Title: getVersionName
	 * @Description: TODO(获取版本名称)
	 * @param @param context
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getVersionName(Context context) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
			return "1.0";
		}
	}

	/**
	 * @Title: getVersionCode
	 * @Description: TODO(获取版本号)
	 * @param @param context
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return pi.versionCode;
		} catch (NameNotFoundException e) {
			return 0;
		}
	}

	public static boolean isValidContext(Context c) {
		Activity a = (Activity) c;
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
			if (a.isDestroyed() || a.isFinishing()) {
				return false;
			} else {
				return true;
			}
		} else {
			if (a.isFinishing()) {
				return false;
			} else {
				return true;
			}
		}

	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.d("WifiPreference IpAddress", ex.toString());
		}
		return "";
	}

	public static boolean isAvilible(Context context, String packageName) {
		final PackageManager packageManager = context.getPackageManager();
		// 获取所有已安装程序的包信息
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
		for (int i = 0; i < pinfo.size(); i++) {
			if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
				return true;
		}
		return false;
	}
	public static int getAppStatus(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
            	System.out.println("importance-------------"+appProcess.importance);
                if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return APP_STATUS_FOREGROUND;
                } else if(appProcess.importance == RunningAppProcessInfo.IMPORTANCE_SERVICE){
                	return APP_STATUS_SERVIES;
                } else {
                    return APP_STATUS_BACKGROUND;
                }
            }
        }
        return APP_STATUS_KILL;  
    }  
}

package com.spreadwin.common.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by lixiang on 2016/12/31.
 */

public class AppUtils {

    /**
     * 判断网络是否可用
     *
     * @param context Context对象
     */
    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return (current.isAvailable());
    }
}

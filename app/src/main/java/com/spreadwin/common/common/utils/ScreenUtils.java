package com.spreadwin.common.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Field;

public class ScreenUtils {
	/**
	 * dp2px
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * px2dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * px2sp
	 * 
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * sp2px
	 * 
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(localDisplayMetrics);
		Rect frame = new Rect();
		((Activity) context).getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(frame);
		return localDisplayMetrics.heightPixels - frame.top;
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(localDisplayMetrics);
		return localDisplayMetrics.widthPixels;
	}
	public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0; 
        try { 
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance(); 
            field = c.getField("status_bar_height"); 
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);  
        } catch (Exception e1) {
            e1.printStackTrace(); 
        }  
        return statusBarHeight; 
    }
	public static boolean hideSoftInput(Activity activity, EditText editText) {
		InputMethodManager imm = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		return imm.hideSoftInputFromWindow(editText.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static boolean showSoftInput(Activity activity, EditText et) {
		InputMethodManager imm = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		return imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
	}

	public static boolean hideSoftInput(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		return imm.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
}

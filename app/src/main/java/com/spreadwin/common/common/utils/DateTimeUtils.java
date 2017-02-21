package com.spreadwin.common.common.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateTimeUtils {
	public static final String UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'";
	public static final String YEAR = "yyyy-MM-dd";
	public static final String MONTH = "MM";
	public static final String DAY = "dd";
	public static final String MONTH_DAY = "MM-dd";
	public static final String HOURS_MINUTE = "HH:mm";
	public static final String MONTH_DAY_HOURS = "MM-dd HH:mm";
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String CHAT_TIME = "MM月dd HH:mm";

	public static String getStringByDate(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date);
	}

	public static String getCurrentTimeInString(String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return getTime(getCurrentTimeInLong(), sdf);
	}
	public static String getStringTimeByFormat(long timeInMillis, String formatType){
		return getTime(timeInMillis, new SimpleDateFormat(formatType));
	}
	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}
	public static Date getDateByString(String date, String formatType){
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date(System.currentTimeMillis());
	}
	public static Date getDateByUtc(String utc) {
		SimpleDateFormat sdf = new SimpleDateFormat(UTC);
		Date dt = null;
		try {
			dt = sdf.parse(utc);
		} catch (ParseException e) {
			dt = new Date(System.currentTimeMillis());
		}
		return dt;
	}

	public static String getStringByUtc(String utc) {
		SimpleDateFormat sdf = new SimpleDateFormat(UTC);
		Date dt = null;
		try {
			dt = sdf.parse(utc);
		} catch (ParseException e) {
			dt = new Date(System.currentTimeMillis());
		}
		return getPushTimeByDate(dt);
	}

	public static String getPushTimeByDate(Date date) {
		SimpleDateFormat year = new SimpleDateFormat(YEAR);
		SimpleDateFormat day = new SimpleDateFormat(MONTH_DAY);

		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		Date yestDay = c.getTime();

		if (year.format(now).equals(year.format(date))) {
			return "今天";
		} else if (year.format(date).equals(year.format(yestDay.getTime()))) {
			return "昨天";
		} else {
			return day.format(date);
		}
	}
	public static String getStartTime(String date){
		SimpleDateFormat day = new SimpleDateFormat(YEAR);
		SimpleDateFormat month = new SimpleDateFormat(MONTH_DAY_HOURS);
		SimpleDateFormat hours = new SimpleDateFormat(HOURS_MINUTE);
		Date cdate = getDateByString(date, DEFAULT_DATE_FORMAT);
		//今天
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		//明天
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
		Date yestDay = c.getTime();
		
		if(day.format(cdate).equals(day.format(now))){
			return "今天 " + hours.format(cdate);
		}else if(day.format(cdate).equals(day.format(yestDay))){
			return "明天 " + hours.format(cdate);
		}else{
			return month.format(cdate);
		}
	}
	public static String getCreateAtByUtc(String utc){
		return getCreateAtByFormat(UTC,utc);
	}
	public static String getCreateAtByFormat(String format, String date) {
		SimpleDateFormat fu = new SimpleDateFormat(format);
		SimpleDateFormat sdf = new SimpleDateFormat(HOURS_MINUTE);
		SimpleDateFormat day = new SimpleDateFormat(MONTH_DAY);
		SimpleDateFormat year = new SimpleDateFormat(YEAR);
		try {
			Date dt = fu.parse(date);

			Calendar c = Calendar.getInstance();
			long time = c.getTimeInMillis() - dt.getTime();
			c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
			Date yestDay = c.getTime();
			if (time / 1000 / 60 <= 1) {
				return "片刻之前";
			} else if (time / 1000 / 60 <= 60) {
				return time / 1000 / 60 + "分钟前";
			} else if (year.format(dt).equals(year.format(yestDay.getTime()))) {
				return "昨天";
			} else if (time / 1000 / 60 / 60 <= 24) {
				return sdf.format(dt);
			} else {
				return day.format(dt);
			}
		} catch (ParseException e) {
			return "";
		}
	}

	public static String getDoubleDate(int date) {
		if (date < 10) {
			return "0" + date;
		} else {
			return date + "";
		}
	}

	/**
	 * 时间对比
	 * @param dateString
	 * @return
	 */
	public static Date stringToDate(String dateString) {
	        ParsePosition position = new ParsePosition(0);
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	        Date dateValue = simpleDateFormat.parse(dateString, position);
	        return dateValue;  
	    } 
	
	public static String getIllegalDate(String date) {
		String kk = null;
		if (date.length() == 13) {
			long l = Long.parseLong(date);
			Date date_1 = new Date(l);
			SimpleDateFormat k = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
			kk = k.format(date_1);
		} else {
			long l = Long.parseLong(date + "000");
			Date date_1 = new Date(l);
			SimpleDateFormat k = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
			kk = k.format(date_1);
		}
		return kk;
	}
	
	
	
	  public static String getWeekOfDate(Date date) {
		    String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		    Calendar calendar = Calendar.getInstance();
		    if(date != null){        
		         calendar.setTime(date);      
		    }        
		    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		    if (w < 0){        
		        w = 0;      
		    }      
		    return weekOfDays[w];    
		}
	
	
}

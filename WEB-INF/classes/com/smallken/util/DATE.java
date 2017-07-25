package com.smallken.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DATE {
	// 用來全域控制 上一周，本周，下一周的周數變化
	private int weeks = 0;
	private int MaxDate;// 一月最大天數
	private int MaxYear;// 一年最大天數

//	public static void main(String[] args) {
//		DATE tt = new DATE();
//		System.out.println("獲取當天日期:" + tt.getNowTime("yyyy-MM-dd"));
//		System.out.println("獲取本週一日期:" + tt.getMondayOFWeek());
//		System.out.println("獲取本周日的日期~:" + tt.getCurrentWeekday());
//		System.out.println("獲取上週一日期:" + tt.getPreviousWeekday());
//		System.out.println("獲取上周日日期:" + tt.getPreviousWeekSunday());
//		System.out.println("獲取下週一日期:" + tt.getNextMonday());
//		System.out.println("獲取下周日日期:" + tt.getNextSunday());
//		System.out.println("獲得相應周的週六的日期:" + tt.getNowTime("yyyy-MM-dd"));
//		System.out.println("獲取本月第一天日期:" + tt.getFirstDayOfMonth());
//		System.out.println("獲取本月最後一天日期:" + tt.getDefaultDay());
//		System.out.println("獲取上月第一天日期:" + tt.getPreviousMonthFirst());
//		System.out.println("獲取上月最後一天的日期:" + tt.getPreviousMonthEnd());
//		System.out.println("獲取下月第一天日期:" + tt.getNextMonthFirst());
//		System.out.println("獲取下月最後一天日期:" + tt.getNextMonthEnd());
//		System.out.println("獲取本年的第一天日期:" + tt.getCurrentYearFirst());
//		System.out.println("獲取本年最後一天日期:" + tt.getCurrentYearEnd());
//		System.out.println("獲取去年的第一天日期:" + tt.getPreviousYearFirst());
//		System.out.println("獲取去年的最後一天日期:" + tt.getPreviousYearEnd());
//		System.out.println("獲取明年第一天日期:" + tt.getNextYearFirst());
//		System.out.println("獲取明年最後一天日期:" + tt.getNextYearEnd());
//		System.out.println("獲取本季度第一天到最後一天:" + tt.getThisSeasonTime(11));
//		System.out.println("獲取兩個日期之間間隔天數2008-12-1~2008-9.29:"
//				+ DATE.getTwoDay("2008-12-1", "2008-9-29"));
//	}
	
	/**
	 * 2015-07-08 Chi - Hsin Huang 
	 * 
	 * 	日期加減 
	 * @param cal
	 * @param y 加減年
	 * @param m 加減月
	 * @param d 加減日
	 * @return Calendar
	 */
	public static Calendar addYearMonthDay(Calendar cal, int  y,int m, int d) {
		cal.add(Calendar.YEAR, y);
		cal.add(Calendar.MONTH, m);
		cal.add(Calendar.DAY_OF_MONTH, d);		
		return cal;	
	}
	
	/**
	 * 2015-07-08 Chi - Hsin Huang 
	 * 日期加減 ( 傳入日期自訂格式) ex "yyyyMMdd"
	 * 
	 * @param str_date
	 * @param dateformat
	 * @param y
	 * @param m
	 * @param d
	 * @return Calendar
	 */
	public static Calendar addYearMonthDay(String str_date,String dateformat, int  y,int m, int d) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(dateformat);
		Calendar cal =  Calendar.getInstance();
		try {
			java.util.Date date = myFormatter.parse(str_date);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.YEAR, y);
		cal.add(Calendar.MONTH, m);
		cal.add(Calendar.DAY_OF_MONTH, d);		
		return cal;	
	}
	
	/**
	 * 2015-07-08 Chi - Hsin Huang 
	 * 民國年日期轉西元年日期 ex 1040703 → 20150703
	 * 
	 * @param yyymmdd
	 * @return String
	 */
	public static String toWDate(String yyymmdd) {
		String  mmdd = yyymmdd.substring(3,7);
		int  yyyy =  Integer.parseInt(yyymmdd.substring(0,3) )+ 1911;
		return String.valueOf(yyyy) + mmdd;
	}
	

	/**
	 * 2015-07-21 Chi - Hsin Huang 
	 * 西元年日期轉民國年日期 ex  2015-07-03 → 104/07/03 
	 * 
	 * @param yyymmdd
	 * @return String
	 */
	public static String toFDate(String yyyymmdd) {
		String  yyyy = yyyymmdd.substring(0,4);		
		int  yyy_i =  Integer.parseInt(yyyy)  - 1911;		
		String  mm = yyyymmdd.substring(5,7);	
		String  dd = yyyymmdd.substring(8,10);
		return String.valueOf(yyy_i) + "/" + mm + "/" + dd;
	}
	
	/**
	 * 2015-07-21 Chi - Hsin Huang 
	 * 西元年日期轉民國年日期 ex   104/07/03  → 2015-07-03 
	 * 
	 * @param yyymmdd
	 * @return String
	 */
	public static String toF2Date(String yyymmdd) {
		String  yyy = yyymmdd.substring(0,3);		
		int  yyyy_i =  Integer.parseInt(yyy)  + 1911;		
		String  mm = yyymmdd.substring(4,6);	
		String  dd = yyymmdd.substring(7,9);
		return String.valueOf(yyyy_i) + "-" + mm + "-" + dd;
	}
	
	
	
    /**
     * 2015-07-08 Chi - Hsin Huang 
     * 回傳民國年格式 ex 10407
     * 
     * @param cal
     * @return  String
     */
	public static String getROCYearMonth(Calendar cal) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String yyyymmdd = sdf.format(cal.getTime()).toString();
		String yyy = String.valueOf(Integer.parseInt(yyyymmdd.substring(0,4)) - 1911);
		String mm = yyyymmdd.substring(4,6);
		return yyy+mm;	
	}
	
   /**
    * 比對日期相差天數
    * @param sj1
    * @param sj2
    * @return
    */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	public static String getWeek(String sdate) {
		// 再轉換為時間
		Date date = DATE.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期幾了，其範圍 1~7
		// 1=星期日 7=星期六，其他類推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 轉換為標準時間
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	// 計算當月最後一天,返回字串
	public String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 設為當前月的1號
		lastDate.add(Calendar.MONTH, 1);// 加一個月，變為下月的1號
		lastDate.add(Calendar.DATE, -1);// 減去一天，變為當月最後一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 上月第一天
	public String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 設為當前月的1號
		lastDate.add(Calendar.MONTH, -1);// 減一個月，變為下月的1號
		// lastDate.add(Calendar.DATE,-1);//減去一天，變為當月最後一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 獲取當月第一天
	public String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 設為當前月的1號
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 獲得本周星期日的日期
	public String getCurrentWeekday() {
		weeks = 0;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 獲取當天時間
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}

	// 獲得當前日期與本周日相差的天數
	private int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 獲得今天是一周的第幾天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因為按中國禮拜一作為第一天所以這裡減1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 獲得本週一的日期
	public String getMondayOFWeek() {
		weeks = 0;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 獲得相應周的週六的日期
	public String getSaturday() {
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 獲得上周星期日的日期
	public String getPreviousWeekSunday() {
		weeks = 0;
		weeks--;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 獲得上周星期一的日期
	public String getPreviousWeekday() {
		weeks--;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 獲得下周星期一的日期
	public String getNextMonday() {
		weeks++;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 獲得下周星期日的日期
	public String getNextSunday() {
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	private int getMonthPlus() {
		Calendar cd = Calendar.getInstance();
		int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
		cd.set(Calendar.DATE, 1);// 把日期設置為當月第一天
		cd.roll(Calendar.DATE, -1);// 日期回滾一天，也就是最後一天
		MaxDate = cd.get(Calendar.DATE);
		if (monthOfNumber == 1) {
			return -MaxDate;
		} else {
			return 1 - monthOfNumber;
		}
	}

	// 獲得上月最後一天的日期
	public String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 減一個月
		lastDate.set(Calendar.DATE, 1);// 把日期設置為當月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滾一天，也就是本月最後一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 獲得下個月第一天的日期
	public String getNextMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 減一個月
		lastDate.set(Calendar.DATE, 1);// 把日期設置為當月第一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 獲得下個月最後一天的日期
	public String getNextMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一個月
		lastDate.set(Calendar.DATE, 1);// 把日期設置為當月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滾一天，也就是本月最後一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 獲得明年最後一天的日期
	public String getNextYearEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一個年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.roll(Calendar.DAY_OF_YEAR, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 獲得明年第一天的日期
	public String getNextYearFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一個年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 獲得本年有多少天
	private int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期設為當年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滾一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	private int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 獲得當天是一年中的第幾天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期設為當年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滾一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	// 獲得本年第一天的日期
	public String getCurrentYearFirst() {
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	// 獲得本年最後一天的日期 *
	public String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	// 獲得上年第一天的日期 *
	public String getPreviousYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "-1-1";
	}

	// 獲得上年最後一天的日期
	public String getPreviousYearEnd() {
		weeks--;
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus + MaxYear * weeks
				+ (MaxYear - 1));
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		getThisSeasonTime(11);
		return preYearDay;
	}

	// 獲得本季度
	public String getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = years_value + "-" + start_month + "-" + start_days
				+ ";" + years_value + "-" + end_month + "-" + end_days;
		return seasonDate;
	}

	private int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	public boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}



}
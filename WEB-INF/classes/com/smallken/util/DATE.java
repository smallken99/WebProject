package com.smallken.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DATE {
	// �Ψӥ��챱�� �W�@�P�A���P�A�U�@�P���P���ܤ�
	private int weeks = 0;
	private int MaxDate;// �@��̤j�Ѽ�
	private int MaxYear;// �@�~�̤j�Ѽ�

//	public static void main(String[] args) {
//		DATE tt = new DATE();
//		System.out.println("�����Ѥ��:" + tt.getNowTime("yyyy-MM-dd"));
//		System.out.println("������g�@���:" + tt.getMondayOFWeek());
//		System.out.println("������P�骺���~:" + tt.getCurrentWeekday());
//		System.out.println("����W�g�@���:" + tt.getPreviousWeekday());
//		System.out.println("����W�P����:" + tt.getPreviousWeekSunday());
//		System.out.println("����U�g�@���:" + tt.getNextMonday());
//		System.out.println("����U�P����:" + tt.getNextSunday());
//		System.out.println("��o�����P���g�������:" + tt.getNowTime("yyyy-MM-dd"));
//		System.out.println("�������Ĥ@�Ѥ��:" + tt.getFirstDayOfMonth());
//		System.out.println("�������̫�@�Ѥ��:" + tt.getDefaultDay());
//		System.out.println("����W��Ĥ@�Ѥ��:" + tt.getPreviousMonthFirst());
//		System.out.println("����W��̫�@�Ѫ����:" + tt.getPreviousMonthEnd());
//		System.out.println("����U��Ĥ@�Ѥ��:" + tt.getNextMonthFirst());
//		System.out.println("����U��̫�@�Ѥ��:" + tt.getNextMonthEnd());
//		System.out.println("������~���Ĥ@�Ѥ��:" + tt.getCurrentYearFirst());
//		System.out.println("������~�̫�@�Ѥ��:" + tt.getCurrentYearEnd());
//		System.out.println("����h�~���Ĥ@�Ѥ��:" + tt.getPreviousYearFirst());
//		System.out.println("����h�~���̫�@�Ѥ��:" + tt.getPreviousYearEnd());
//		System.out.println("������~�Ĥ@�Ѥ��:" + tt.getNextYearFirst());
//		System.out.println("������~�̫�@�Ѥ��:" + tt.getNextYearEnd());
//		System.out.println("������u�ײĤ@�Ѩ�̫�@��:" + tt.getThisSeasonTime(11));
//		System.out.println("�����Ӥ���������j�Ѽ�2008-12-1~2008-9.29:"
//				+ DATE.getTwoDay("2008-12-1", "2008-9-29"));
//	}
	
	/**
	 * 2015-07-08 Chi - Hsin Huang 
	 * 
	 * 	����[�� 
	 * @param cal
	 * @param y �[��~
	 * @param m �[���
	 * @param d �[���
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
	 * ����[�� ( �ǤJ����ۭq�榡) ex "yyyyMMdd"
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
	 * ����~�����褸�~��� ex 1040703 �� 20150703
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
	 * �褸�~��������~��� ex  2015-07-03 �� 104/07/03 
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
	 * �褸�~��������~��� ex   104/07/03  �� 2015-07-03 
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
     * �^�ǥ���~�榡 ex 10407
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
    * ������ۮt�Ѽ�
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
		// �A�ഫ���ɶ�
		Date date = DATE.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour���s���N�O�P���X�F�A��d�� 1~7
		// 1=�P���� 7=�P�����A��L����
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
		// �ഫ���зǮɶ�
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

	// �p����̫�@��,��^�r��
	public String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// �]����e�몺1��
		lastDate.add(Calendar.MONTH, 1);// �[�@�Ӥ�A�ܬ��U�몺1��
		lastDate.add(Calendar.DATE, -1);// ��h�@�ѡA�ܬ����̫�@��
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// �W��Ĥ@��
	public String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// �]����e�몺1��
		lastDate.add(Calendar.MONTH, -1);// ��@�Ӥ�A�ܬ��U�몺1��
		// lastDate.add(Calendar.DATE,-1);//��h�@�ѡA�ܬ����̫�@��
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// ������Ĥ@��
	public String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// �]����e�몺1��
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// ��o���P�P���骺���
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

	// �����Ѯɶ�
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// �i�H��K�a�ק����榡
		String hehe = dateFormat.format(now);
		return hehe;
	}

	// ��o��e����P���P��ۮt���Ѽ�
	private int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// ��o���ѬO�@�P���ĴX�ѡA�P����O�Ĥ@�ѡA�P���G�O�ĤG��......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // �]��������§���@�@���Ĥ@�ѩҥH�o�̴�1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// ��o���g�@�����
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

	// ��o�����P���g�������
	public String getSaturday() {
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// ��o�W�P�P���骺���
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

	// ��o�W�P�P���@�����
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

	// ��o�U�P�P���@�����
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

	// ��o�U�P�P���骺���
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
		cd.set(Calendar.DATE, 1);// �����]�m�����Ĥ@��
		cd.roll(Calendar.DATE, -1);// ����^�u�@�ѡA�]�N�O�̫�@��
		MaxDate = cd.get(Calendar.DATE);
		if (monthOfNumber == 1) {
			return -MaxDate;
		} else {
			return 1 - monthOfNumber;
		}
	}

	// ��o�W��̫�@�Ѫ����
	public String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// ��@�Ӥ�
		lastDate.set(Calendar.DATE, 1);// �����]�m�����Ĥ@��
		lastDate.roll(Calendar.DATE, -1);// ����^�u�@�ѡA�]�N�O����̫�@��
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// ��o�U�Ӥ�Ĥ@�Ѫ����
	public String getNextMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// ��@�Ӥ�
		lastDate.set(Calendar.DATE, 1);// �����]�m�����Ĥ@��
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// ��o�U�Ӥ�̫�@�Ѫ����
	public String getNextMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// �[�@�Ӥ�
		lastDate.set(Calendar.DATE, 1);// �����]�m�����Ĥ@��
		lastDate.roll(Calendar.DATE, -1);// ����^�u�@�ѡA�]�N�O����̫�@��
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// ��o���~�̫�@�Ѫ����
	public String getNextYearEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// �[�@�Ӧ~
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.roll(Calendar.DAY_OF_YEAR, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// ��o���~�Ĥ@�Ѫ����
	public String getNextYearFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// �[�@�Ӧ~
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// ��o���~���h�֤�
	private int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// �����]����~�Ĥ@��
		cd.roll(Calendar.DAY_OF_YEAR, -1);// �����^�u�@�ѡC
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	private int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// ��o��ѬO�@�~�����ĴX��
		cd.set(Calendar.DAY_OF_YEAR, 1);// �����]����~�Ĥ@��
		cd.roll(Calendar.DAY_OF_YEAR, -1);// �����^�u�@�ѡC
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	// ��o���~�Ĥ@�Ѫ����
	public String getCurrentYearFirst() {
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	// ��o���~�̫�@�Ѫ���� *
	public String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// �i�H��K�a�ק����榡
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	// ��o�W�~�Ĥ@�Ѫ���� *
	public String getPreviousYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// �i�H��K�a�ק����榡
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "-1-1";
	}

	// ��o�W�~�̫�@�Ѫ����
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

	// ��o���u��
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// �i�H��K�a�ק����榡
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
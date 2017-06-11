package com.lorne.core.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Title: 日期工具<br>
 * Description: <br>
 * Project: 外勤E通 <br>
 * Company: Finalist IT Group <br>
 * Copyright: 2010 www.finalist.cn Inc. All rights reserved. <br>
 * Date: Apr 22, 2010
 *
 * @author Neil,yuliang
 * @version 1.0
 */
public final class DateUtil {

    /**
     * default date format pattern
     */
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String MONTH_DATE_FORMAT = "MM-dd";
    public final static String YEAR_WEEK_FORMAT = "yyyy-ww";
    public final static String YEAR_MONTH_FORMAT = "yyyy-MM";
    public final static String YEAR_WEEK_FORMAT_SHORT = "yy-ww";
    public final static String YEAR_MONTH_FORMAT_SHORT = "yy-MM";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public final static String FULL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String TIME_FORMAT = "HH:mm";
    public final static String MONTH_DAY_HOUR_MINUTE_FORMAT = "MM-dd HH:mm";
    public final static String LOCATE_DATE_FORMAT = "yyyyMMddHHmmss";

    private static final int DAYS_OF_A_WEEK = 7;

    private DateUtil() {
    }

    /**
     * parse date with the default pattern
     *
     * @param date string date
     * @return the parsed date
     */
    public static Date parseDate(String date) throws ParseException {
        return parseDate(date, DATE_FORMAT);
    }

    public static Date parseDate(String date, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(date);
    }

    /**
     * 获取增加小时后的 Date
     *
     * @param date
     * @param i
     * @return squall add 20100225
     */
    public static Date addHour(Date date, int i) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, i);
        return calendar.getTime();
    }

    /**
     * format date with the default pattern
     *
     * @param date the date that want to format to string
     * @return the formated date
     */
    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

    public static String formatTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        return format.format(date);
    }

    /**
     * format date with the given pattern
     *
     * @param date    the date that want to format to string
     * @param pattern the formated pattern
     * @return the formated date
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * get current date
     *
     * @return the string of current date
     */
    public static String getCurrentDateFormat() {
        return formatDate(new Date());
    }

    public static String getCurrentTimeFormat() {
        return formatTime(new Date());
    }

    /**
     * get number of days between the two given date
     *
     * @param fromDate the begin date
     * @param endDate  the end date
     * @return the number of days between the two date
     */
    public static int getDateNum(Date fromDate, Date endDate) {
        long days = (endDate.getTime() - fromDate.getTime())
                / (1000 * 60 * 60 * 24);
        return (int) days;
    }

    /**
     * add day to the date
     *
     * @param date   the added date
     * @param number the number to add to the date
     * @return the added date
     */
    public static Date addDate(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, number);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, number);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, number);
        return calendar.getTime();
    }

    /**
     * get the default calendar
     *
     * @return the calendar instance
     */
    public static Calendar getDefaultCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar;
    }

    /**
     * format the date into string value
     *
     * @param calendar the formated calendar
     * @return the string date
     */
    public static String getStringDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + getNiceString(month) + "-" + getNiceString(day);
    }

    /**
     * according to the pattern yyyy-MM-dd
     *
     * @param value the value
     * @return the formated value
     */
    public static String getNiceString(int value) {
        String str = "00" + value;
        return str.substring(str.length() - 2, str.length());
    }

    /**
     * get calendar from date
     *
     * @param date the passing date
     * @return the calendar instance
     */
    public static Calendar getCalendarFromDate(Date date) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static String getInterval(Date startDate, Date endDate) {
        long intervalTime = endDate.getTime() - startDate.getTime();
        return getInterval(intervalTime);
    }

    public static int getIntervalMinute(Date startDate, Date endDate) {
        long intervalTime = endDate.getTime() - startDate.getTime();
        return (int) (intervalTime / (1000 * 60));
    }

    public static String getInterval(long intervalTime) {
        int hour = (int) (intervalTime / (1000 * 60 * 60));
        int minute = (int) (intervalTime / (1000 * 60) - hour * 60);
        int second = (int) ((intervalTime / 1000) - hour * 60 * 60 - minute * 60);
        if (hour > 0) {
            return hour + "小时 " + minute + "分 " + second + "秒";
        } else if (minute > 0) {
            return minute + "分钟 " + second + "秒";
        } else {
            return second + "秒";
        }
    }

    public static int getYear(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date now) {
        Calendar calendar = getCalendarFromDate(now);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getWeekOfYear(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR) - 1;
    }

    public static Date getCurrentDate() {
        Calendar calendar = getCalendarFromDate(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    public static String getCurrentDateStr() {
        return formatDate(new Date());
    }

    public static Date getNextDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCurrentDate());
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 一周的日期
     *
     * @param date
     * @return
     */
    public static List<Date> getWeekDayOfYear(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);

        List<Date> result = new ArrayList<Date>();
        result.add(getDateOfYearWeek(year, week, Calendar.MONDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.TUESDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.WEDNESDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.THURSDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.FRIDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.SATURDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.SUNDAY));
        return result;
    }

    /**
     * 获取一年中某周,星期几的日期
     *
     * @param yearNum
     * @param weekNum
     * @param dayOfWeek
     * @return
     */
    private static Date getDateOfYearWeek(int yearNum, int weekNum,
                                          int dayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        cal.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        /*
         * cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
		 * cal.set(Calendar.SECOND, 0);
		 */
        return cal.getTime();
    }

    /**
     * 获取指定日期是一周的第几天,星期日是第一天
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

//	public static Date parseDateE(String date, String pattern) {
//		SimpleDateFormat format = new SimpleDateFormat(pattern);
//		try {
//			return format.parse(date);
//		} catch (ParseException e) {
//			throw new RuntimeException("date pattern should be " + pattern);
//		}
//	}

    public static Date getDefaultDate() {
        Calendar calendar = getCalendarFromDate(new Date());
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date addSecond(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, number);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, number);
        return calendar.getTime();
    }

    /**
     * 清空日期的时间
     *
     * @param date
     * @return
     */
    public static Date clearTime(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static boolean betweenHour(Date date, int hour) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.add(Calendar.HOUR, hour);
        Calendar now = getDefaultCalendar();
        return calendar.after(now);
    }

    public static boolean betweenMinute(Date date, int minute) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.add(Calendar.MINUTE, minute);
        Calendar now = getDefaultCalendar();
        return calendar.after(now);
    }

    public static boolean betweenSecond(Date date, int second) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.add(Calendar.SECOND, second);
        Calendar now = getDefaultCalendar();
        return calendar.after(now);
    }


    /**
     * +周
     *
     * @param date
     * @param i
     * @return
     */
    public static Date addWeek(Date date, int i) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, i);
        return calendar.getTime();
    }

    /**
     * 本周指定星期几的日期
     *
     * @param date
     * @param monday
     * @return
     */
    public static String getCurrentWeekDay(Date date, int day) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);

        return formatDate(getDateOfYearWeek(year, week, day));
    }

    /**
     * 指定年范围
     *
     * @param fromYear
     * @param toYear
     * @return
     */
    public static List<String> getYearRange(int fromYear, int toYear) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i <= toYear - fromYear; i++) {
            result.add("" + (toYear - i));
        }
        return result;
    }

    /**
     * 获得指定年月的所有日期
     *
     * @param year
     * @param month
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<String> getMonthRange(int year, int month) {
        Calendar calendar = getDefaultCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        List<String> result = new ArrayList<String>();
        Date date = calendar.getTime();
        while (date.getMonth() == calendar.get(Calendar.MONTH)) {
            result.add(formatDate(date));
            date = addDate(date, 1);
        }
        return result;
    }

    public static List<String> getDateRange(String strBeginDate,
                                            String strEndDate, String pattern) throws ParseException {
        List<String> ret = new ArrayList<String>();
        Date beginDate = parseDate(strBeginDate);
        Date endDate = parseDate(strEndDate);
        ret.add(formatDate(beginDate, pattern));
        while (!beginDate.equals(endDate)) {
            beginDate = addDate(beginDate, 1);
            ret.add(formatDate(beginDate, pattern));
        }
        return ret;
    }

    public static boolean before(Date beginDate, Date endDate) {
        return beginDate.compareTo(endDate) <= 0;
    }

    public static List<String> getWeekRange(String strBeginDate,
                                            String strEndDate, String pattern) throws ParseException {
        List<String> ret = new ArrayList<String>();
        Date beginDate = parseDate(strBeginDate);
        Date endDate = parseDate(strEndDate);

        String beginFormat = formatDate(beginDate, pattern);
        String endFormat = formatDate(endDate, pattern);
        ret.add(beginFormat);

        while (!beginFormat.equals(endFormat)) {
            beginDate = addWeek(beginDate, 1);
            beginFormat = formatDate(beginDate, pattern);
            ret.add(beginFormat);
        }
        return ret;
    }

    public static List<String> getMonthRange(String strBeginDate,
                                             String strEndDate, String pattern) throws ParseException {
        List<String> ret = new ArrayList<String>();
        Date beginDate = parseDate(strBeginDate, pattern);
        Date endDate = parseDate(strEndDate, pattern);
        String beginFormat = formatDate(beginDate, pattern);
        String endFormat = formatDate(endDate, pattern);
        ret.add(beginFormat);
        while (!beginFormat.equals(endFormat)) {
            beginDate = addMonth(beginDate, 1);
            beginFormat = formatDate(beginDate, pattern);
            ret.add(beginFormat);
        }
        return ret;
    }

    public static float getIntervalHour(Date startDate, Date endDate) {
        long intervalTime = endDate.getTime() - startDate.getTime();
        return intervalTime * 1f / (1000 * 60 * 60);
    }


    public static String getCurrentDateTime() {
        return formatDate(new Date(), LOCATE_DATE_FORMAT);
    }

    //根据日期取得星期几
    public static String getWeek(Date date) {
        String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    public static List<String> getFullYear() {
        List<String> list = new ArrayList<>();
        Date date = new Date();
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;
        for (int i = 0; i < 12; i++) {
            if (month == 0) {
                month = 12;
                year--;
            }
            String temp = month < 10 ? "0" + month : month + "";
            list.add(year + "-" + temp);
            month--;
        }
        return list;
    }

}

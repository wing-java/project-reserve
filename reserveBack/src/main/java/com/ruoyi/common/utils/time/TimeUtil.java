package com.ruoyi.common.utils.time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class TimeUtil {
	
	public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	public static SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd"); 
	
	public static SimpleDateFormat format2 = new SimpleDateFormat("HHmmss"); 
	
	public static SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static SimpleDateFormat format5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static SimpleDateFormat format6 = new SimpleDateFormat("MM/dd/yyyy"); 
	
	public static SimpleDateFormat format7 = new SimpleDateFormat("yyyyMMddHHmm"); 
	
	public static SimpleDateFormat format8 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public static SimpleDateFormat format9 = new SimpleDateFormat("yyyyMM"); 
	
	
	
	//========================================直接将时间转换为特定格式的字符串===========================
	/**
	 * 获取当时的日期，格式例如：20181115
	 * @return
	 */
	public static String getDayString() {
		Date date=new Date();
		return format1.format(date);
	}
	
	
	/**
	 * 获取当时的日期，格式例如：20181115
	 * @return
	 */
	public static String getMonthString() {
		Date date=new Date();
		return format9.format(date);
	}
	
	
	/**
	 * 获取当时的时间点，格式形如： HHmmss
	 * @return
	 */
	public static String getTimeString(){
		return format2.format(new Date());
	}
	
	
	/**
	 * 获取当时的指定格式字符串
	 * @param returnFormat：返回的时间格式
	 * @return
	 */
	public static String getDayFormat(SimpleDateFormat returnFormat){
		Date date=new Date();
		return returnFormat.format(date);
	}
	
	
	/**
	 * 获取指定时间的指定格式的字符串
	 * @param date
	 * @param returnFormat：返回的时间格式
	 * @return
	 */
	public static String getDayFormat(Date date,SimpleDateFormat returnFormat){
		return returnFormat.format(date);
	}
	
	
	/**
	 * 获取当时的日期，格式例如：2018-11-15
	 * @return
	 */
	public static String getDayFormat3(){
		Date date=new Date();
		return format3.format(date);
	}
	
	
	/**
	 * 获取指定时间的日期，格式例如：2018-11-15
	 * @param date
	 * @return
	 */
	public static String getDayFormat3(Date date){
		return format3.format(date);
	}
	
	
	/**
	 * 获取指定格式字符串时间，格式例如：yyyyMMddHHmmss
	 * @return
	 */
	public static String getDayFormat4() {
		Date date=new Date();
		return format4.format(date);
	}
	
	/**
	 * 获取当前月份，格式例如：yyyyMM
	 * @return
	 */
	public static String getDayFormat9() {
		Date date=new Date();
		return format9.format(date);
	}
	
	
	/**
	 * 获取某一天的某个具体时间，精确到秒，格式例如：yyyyMMddHHmmss
	 * @return
	 */
	public static String getDayFormat4(Date date) {
		return format4.format(date);
	}
	
	
	/**
	 * 获取当时的某个具体时间，精确到秒，格式例如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String get_format5(){
		Date date=new Date();
		return format5.format(date);
	}
	
	
	/**
	 * 获取指定时间的某个具体时间，精确到秒，格式例如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String get_format5(Date date){
		return format5.format(date);
	}
	
	
	/**
	 * 获取当时的某个具体时间，格式例如：MM/dd/yyyy
	 * @param date
	 * @return
	 */
	public static String get_format6(){
		Date date=new Date();
		return format6.format(date);
	}
	
	
	/**
	 * 获取指定时间的某个具体时间，格式例如：MM/dd/yyyy
	 * @param date
	 * @return
	 */
	public static String get_format6(Date date){
		return format6.format(date);
	}
	
	
	/**
	 * 获取当时的某个具体时间，精确到分，格式例如：yyyyMMddHHmm
	 * @return
	 */
	public static String get_format7() {
		Date date=new Date();
		return format7.format(date);
	}
	
	
	/**
	 * 获取指定时间的某个具体时间，精确到分，格式例如：yyyyMMddHHmm
	 * @return
	 */
	public static String get_format7(Date date) {
		return format7.format(date);
	}
	
	
	/**
	 * 获取当时的日期，格式形如：MM/dd/yyyy HH:mm:ss
	 * @return
	 */
	public static String getDayFormat8(){
		Date date=new Date();
		return format8.format(date);
	}
	
	
	/**
	 * 获取指定时间的日期，格式形如：MM/dd/yyyy HH:mm:ss
	 * @return
	 */
	public static String getDayFormat8(Date date){
		return format8.format(date);
	}
	
	
	//============================================字符串转换为时间戳=====================================
	/**
	 * 字符串时间20190410000000转换为时间戳
	 * @param strDate，格式：20190410000000
	 * @return
	 */
	public static final long getTimeString(String strDate) {
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = format4.parse(strDate, pos);
		return strtodate.getTime();
	}
	
	
	//============================================字符串转换为时间类型==================================
	/**
	 * 把字符串时间转换为date类型
	 * @param strDate：请求参数，字符串时间
	 * @param requestFormat，字符串时间的格式
	 * @return
	 */
	public static Date strToDate(String strDate, SimpleDateFormat requestFormat) {
		 ParsePosition pos = new ParsePosition(0);
		 Date strtodate = requestFormat.parse(strDate, pos);
		 return strtodate;
	}
	
	
	/**
	 * 把字符串时间转换为date类型
	 * @param strDate，格式：2019-06-25 00:00:00
	 * @return
	 */
	public static Date strToDate_format5(String strDate) {
		 ParsePosition pos = new ParsePosition(0);
		 Date strtodate = format5.parse(strDate, pos);
		 return strtodate;
	}
	
	
	
	//======================================获取某一天的开始时间（返回字符串）================================
	/**
	 * 获取某一天的开始时间
	 * @param strDate：字符串时间格式
	 * @param requestFormat：请求字符串的时间格式
	 * @param returnFormat：转换之后的时间字符串格式
	 * @return
	 */
	public static String dateStart(String strDate, SimpleDateFormat requestFormat, SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(strToDate(strDate,requestFormat));
    	cal0.set(Calendar.HOUR_OF_DAY, 0);
    	cal0.set(Calendar.MINUTE, 0);
    	cal0.set(Calendar.SECOND, 0);
    	return returnFormat.format(cal0.getTime());
	}
	
	
	/**
	 * 获取某一天的开始时间，格式例如：2018-11-15 00:00:00
	 * @param date：日期，事件类型
	 * @param returnFormat：返回的时间格式
	 * @return
	 */
	public static String dateStart(Date date, SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(date);
    	cal0.set(Calendar.HOUR_OF_DAY, 0);
    	cal0.set(Calendar.MINUTE, 0);
    	cal0.set(Calendar.SECOND, 0);
    	return returnFormat.format(cal0.getTime());
	}
	
	
	/**
	 * 获取当天的开始时间，格式例如：2018-11-15 00:00:00
	 * @param returnFormat：返回的时间格式
	 * @return
	 */
	public static String dateStart(SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date());
    	cal0.set(Calendar.HOUR_OF_DAY, 0);
    	cal0.set(Calendar.MINUTE, 0);
    	cal0.set(Calendar.SECOND, 0);
    	return returnFormat.format(cal0.getTime());
	}
	
	
	/**
	 * 获取当天的开始时间，格式例如：2018-11-15 00:00:00
	 * @return
	 */
	public static String dateStart_format5(){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date());
    	cal0.set(Calendar.HOUR_OF_DAY, 0);
    	cal0.set(Calendar.MINUTE, 0);
    	cal0.set(Calendar.SECOND, 0);
    	return format5.format(cal0.getTime());
	}
	
	
	
	/**
	 * 获取一天的开始时间
	 * @param strDate，请求格式为：06/01/2019
	 * @return
	 */
	public static String dateStart_format5(String strDate){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date(strDate));
    	return format5.format(cal0.getTime());
	}
	
	
	/**
	 * 获取一天的开始时间
	 * @param strDate，请求格式为：06/01/2019
	 * @param returnFormat：返回时间格式
	 * @return
	 */
	public static String dateStart_format5(String strDate, SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date(strDate));
    	return returnFormat.format(cal0.getTime());
	}
	
	
	
	
	//======================================获取某一天的开始时间（返回Timestamp或者date）================================
	/**
	 * 获取某个日期的开始时间
	 * @param d
	 * @return
	 */
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar=Calendar.getInstance();
        if(null!=d){
        	calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), 
        calendar.get(Calendar.MONTH),    
        calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    
    
    /**
	 * 获取当天开始时间
	 * @return
	 */
	public static Date getDayBegin(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);//0点
		cal.set(Calendar.MINUTE, 0);//0分
		cal.set(Calendar.SECOND, 0);//0秒
		cal.set(Calendar.MILLISECOND, 0);//0毫秒
		return cal.getTime();
	}
	
	
	/**
	 * 获取昨天开始时间
	 * @return
	 */
	public static Date getBeginDayOfYesterday(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(getDayBegin());//当天开始时间
		cal.add(Calendar.DAY_OF_MONTH, -1);//当天月份天数减1
		return cal.getTime();
	}
	
	
	/**
	 * 获取明天开始时间
	 * @return
	 */
	public static Date getBeginDayOfTomorrow(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(getDayBegin());//当天开始时间
		cal.add(Calendar.DAY_OF_MONTH, 1);//当天月份天数加1
		return cal.getTime();
	}
	
	
	/**
     * 获取本周的开始时间
     * @return
     */
	@SuppressWarnings("unused")
	public static Date getBeginDayOfWeek(){
		Date date=new Date();
		if(date==null){
			return null;
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek==1){
			dayOfWeek+=7;
		}
		cal.add(Calendar.DATE, 2-dayOfWeek);
		return getDayStartTime(cal.getTime());
	}
	
	
	/**
     * 获取上周开始时间
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek() {
        Date date=new Date();
        if (date==null) {
            return null;
        }
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int dayofweek=cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek==1) {
            dayofweek+=7;
        }
        cal.add(Calendar.DATE, 2-dayofweek-7);
        return getDayStartTime(cal.getTime());
    }
    
    
    /**
     * 获取本月的开始时间
     * @return
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth()-1, 1);
        return getDayStartTime(calendar.getTime());
    }
    
    
    /**
     * 获取上月的开始时间
     * @return
     */
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth()-2, 1);
        return getDayStartTime(calendar.getTime());
    }
    
    
    /**
     * 获得指定月的开始时间
     * @param month
     * @return
     */
    public static Date getBeginDayOfOneMonth(int year,int month) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(year, month-1, 1);
        return getDayStartTime(calendar.getTime());
    }
    
    
    /**
     * 获取本年的开始时间
     * @return
     */
    public static java.util.Date getBeginDayOfYear() {
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }

	
	
	
	
	//============================================获取某一天的结束时间（返回String）===================================
	/**
	 * 获取某一天的结束时间
	 * @param strDate：字符串时间格式
	 * @param requestFormat：请求字符串的时间格式
	 * @param returnFormat：转换之后的时间字符串格式
	 * @return
	 */
	public static String dateEnd(String strDate, SimpleDateFormat requestFormat, SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(strToDate(strDate,requestFormat));
    	cal0.set(Calendar.HOUR_OF_DAY, 23);
    	cal0.set(Calendar.MINUTE, 59);
    	cal0.set(Calendar.SECOND, 59);
    	return returnFormat.format(cal0.getTime());
	}
	
	
	/**
	 * 获取某一天的结束时间，格式例如：2018-11-15 00:00:00
	 * @param date：日期，事件类型
	 * @param returnFormat：返回的时间格式
	 * @return
	 */
	public static String dateEnd(Date date, SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(date);
    	cal0.set(Calendar.HOUR_OF_DAY, 23);
    	cal0.set(Calendar.MINUTE, 59);
    	cal0.set(Calendar.SECOND, 59);
    	return returnFormat.format(cal0.getTime());
	}
	
	
	/**
	 * 获取当天的结束时间，格式例如：2018-11-15 00:00:00
	 * @param returnFormat：返回的时间格式
	 * @return
	 */
	public static String dateEnd(SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date());
    	cal0.set(Calendar.HOUR_OF_DAY, 23);
    	cal0.set(Calendar.MINUTE, 59);
    	cal0.set(Calendar.SECOND, 59);
    	return returnFormat.format(cal0.getTime());
	}
	
	
	/**
	 * 获取一天的结束时间，格式例如：2018-11-15 23:59:59
	 * @param date
	 * @return
	 */
	public static String dateEnd_format5(){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date());
    	cal0.set(Calendar.HOUR_OF_DAY, 23);
    	cal0.set(Calendar.MINUTE, 59);
    	cal0.set(Calendar.SECOND, 59);
    	return format5.format(cal0.getTime());
	}
	
	
	/**
	 * 获取某一天的结束时间，请求格式为：06/01/2019
	 * @param strDate
	 * @return
	 */
	public static String dateEnd_format5(String strDate){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date(strDate));
    	cal0.set(Calendar.HOUR_OF_DAY, 23);
    	cal0.set(Calendar.MINUTE, 59);
    	cal0.set(Calendar.SECOND, 59);
    	return format5.format(cal0.getTime());
	}
	
	
	/**
	 * 获取某一天的结束时间，请求格式为：06/01/2019
	 * @param strDate，时间字符串，格式为：06/01/2019
	 * @param returnFormat：返回的时间格式
	 * @return
	 */
	public static String dateEnd_format5(String strDate, SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date(strDate));
    	cal0.set(Calendar.HOUR_OF_DAY, 23);
    	cal0.set(Calendar.MINUTE, 59);
    	cal0.set(Calendar.SECOND, 59);
    	return returnFormat.format(cal0.getTime());
	}
	
	
	
	//============================================获取某一天的结束时间（返回Timestamp或者date）===================================
	 /**
     * 获取某个日期的结束时间
     * @param d
     * @return
     */
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar=Calendar.getInstance();
        if(null!=d){
        	calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), 
        calendar.get(Calendar.MONTH),    
        calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }
	
	
	/**
	 * 获取当天结束时间
	 * @return
	 */
	public static Date getDayEnd(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);//23点
		cal.set(Calendar.MINUTE, 59);//59分
		cal.set(Calendar.SECOND, 59);//59秒
		return cal.getTime();
	}
	
	/**
	 * 获取一天的开始时间，格式例如：2018-11-15 00:00:00
	 * @param date
	 * @return
	 */
	public static String dayStart(){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date());
    	cal0.set(Calendar.HOUR_OF_DAY, 0);
    	cal0.set(Calendar.MINUTE, 0);
    	cal0.set(Calendar.SECOND, 0);
    	return format5.format(cal0.getTime());
	}
	
	
	/**
	 * 获取昨天结束时间
	 * @return
	 */
	public static Date getEndDayOfYesterday(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(getDayEnd());//当天结束时间
		cal.add(Calendar.DAY_OF_MONTH, -1);//当天月份天数减1
		return cal.getTime();
	}
	
	
	/**
	 * 获取明天结束时间
	 * @return
	 */
	public static Date getEndDayOfTomorrow(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(getDayEnd());//当天结束时间
		cal.add(Calendar.DAY_OF_MONTH, 1);//当天月份天数加1
		return cal.getTime();
	}
	
	
	/**
	 * 获取本周的结束时间
	 * @return
	 */
    public static Date getEndDayOfWeek(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }
	
    
    /**
     * 获取上周的结束时间
     * @return
     */
    public static Date getEndDayOfLastWeek(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }
    
    
    /**
     * 获取本月的结束时间
     * @return
     */
    public static Date getEndDayOfMonth() {
    	Calendar calendar=Calendar.getInstance();
    	calendar.set(getNowYear(), getNowMonth()-1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth()-1, day);
        return getDayEndTime(calendar.getTime());
    }
    
    
    /**
     * 获取上月的结束时间
     * @return
     */
    public static Date getEndDayOfLastMonth() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth()-2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth()-2, day);
        return getDayEndTime(calendar.getTime());
    }
	
    
    /**
     * 获得指定月的结束日期
     * @return
     */
    public static Date getEndDayOfOneMonth(int year,int month) {
    	Calendar calendar=Calendar.getInstance();
    	calendar.set(year, month-1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(year, month-1, day);
        return getDayEndTime(calendar.getTime());
    }
    
    
    /**
     * 获取本年的结束时间
     * @return
     */
    public static java.util.Date getEndDayOfYear() {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }
	
	
	//==========================================获取今年和今月是哪年哪月=========================
	/**
     * 获取今年是哪一年
     * @return
     */
    public static Integer getNowYear(){
    	Date date = new Date();
    	GregorianCalendar gc=(GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }
    
    
    /**
     * 获取本月是哪一月
     * @return
     */
    public static Integer getNowMonth() {
    	Date date = new Date();
    	GregorianCalendar gc=(GregorianCalendar)Calendar.getInstance();
    	gc.setTime(date);
        return gc.get(2) + 1;
    }
    
    
    //======================================获取指定年月的第一天和最后一天的日期，若未指定，则默认查询当月的第一天日期===========================
    /**
	 * 获得指定年月的第一天日期，若未指定则默认查当月的第一天日期
	 * @param month
	 * @return
	 */
	public static String getOneMonthBeginDateString(String year,String month){
		//如果年份为空，则默认查所有
		if(StringUtils.isEmpty(year)) {
			year=getNowYear().toString();//默认本年
		}
		//如果月份为空，则默认第一月
		if(StringUtils.isEmpty(month)) {
			month=getNowMonth().toString();//默认当月
		}
		return format1.format(getBeginDayOfOneMonth(Integer.parseInt(year),Integer.parseInt(month)));
	}
	
	
	/**
	 * 获得指定年月的最后一天日期，若未指定则默认查当月的最后一天日期
	 * @param month
	 * @return
	 */
	public static String getOneMonthEndDateString(String year,String month){
		//如果年份为空，则默认所有
		if(StringUtils.isEmpty(year)) {
			year=getNowYear().toString();//默认本年
		}
		//如果月份为空，则默认最后一个月
		if(StringUtils.isEmpty(month)) {
			month=getNowMonth().toString();//默认当月
		}
		return format1.format(getEndDayOfOneMonth(Integer.parseInt(year),Integer.parseInt(month)));
	}
	
	
	
	//====================================获取搜索的时间范围=============================
	/**
	 * 获取搜索的时间范围
	 * @param map,其中selectTime格式为：06/01/2019 - 06/30/2019
	 * @return
	 */
	public static Map<String, Object> getSearchTime(Map<String, Object> map){
		if(null!=(map.get("selectTime"))) {
        	String[] times= map.get("selectTime").toString().split(" - ");
        	map.put("beginTime",dateStart_format5(times[0]));
        	map.put("endTime",dateEnd_format5(times[1]));
        }
		return map;
	}
	
	
	
	//=================================获取某个月的第一天========================
	/**
	 * 获得某一个月的第一天
	 * @param date
	 * @return
	 */
	public static String firstDayOfMonth(){
		 Calendar cal_1=Calendar.getInstance();//获取当前日期 
	     cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	     String firstDay = format6.format(cal_1.getTime());
	     return firstDay;
	}
	
	
	/**
	 * 获得某一个月的第一天
	 * @param date
	 * @return
	 */
	public static String firstDayOfMonth(Date date, SimpleDateFormat returnFormat){
		 Calendar cal_1=Calendar.getInstance();//获取当前日期 
	     cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	     String firstDay = returnFormat.format(cal_1.getTime());
	     return firstDay;
	}
	
	
	/**
	 * 获取某个日期之后多少个月的第一天日期
	 * @param strt_date
	 * @param month_num
	 * @return
	 */
	public static String getAfterMonthBeginDate(String strt_date, int month_num){
		Date date = strToDate(getCountDay(strt_date,1,format1, format1),format1);
		Calendar cal0 = Calendar.getInstance();
		cal0.setTime(date);
		cal0.add(Calendar.MONTH, month_num);
		cal0.set(Calendar.DAY_OF_MONTH, 1);
		return format1.format(cal0.getTime());
	}
	
	
	/**
	 * 获取某个日期的月份的第一天
	 * @param strt_date
	 * @return
	 */
	public static String getMonthFirstDate(String strt_date) {
		Date date = strToDate(getCountDay(strt_date,1,format1, format1),format1);
		Calendar cal0 = Calendar.getInstance();
		cal0.setTime(date);
		cal0.set(Calendar.DAY_OF_MONTH, 1);
		return format1.format(cal0.getTime());
	}
	
	/**
	 * 获取某个日期的月份的最后一天
	 * @param strt_date
	 * @return
	 */
	public static String getMonthLastDate(String strt_date) {
		Date date = strToDate(getCountDay(strt_date,1,format1, format1),format1);
		Calendar cal0 = Calendar.getInstance();
		cal0.setTime(date);
		cal0.set(Calendar.DAY_OF_MONTH, cal0.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format1.format(cal0.getTime());
	}
	
	
	//======================================获取指定日期往前往后的日期================================
	/**
	 * 计算日期加减天数后的日期
	 * @param date：请求日期字符串
	 * @param num：计算天数
	 * @param requestFormat：请求日期格式
	 * @param returnFormat：返回日期格式
	 * @return
	 */
	public static String getCountDay(String date, int day_num, SimpleDateFormat requestFormat, SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	try {
			cal0.setTime(requestFormat.parse(date));
			cal0.add(Calendar.DAY_OF_MONTH, day_num); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return returnFormat.format(cal0.getTime());
	}
	
	
	public static void main(String[] args) {
		String str = getMonthLastDate("20200714");
		System.out.println(str);
	}
	
	
	/**
	 * 得到第二天凌晨几点的时间
	 * @param end_date
	 * @param i
	 * @return
	 */
	public static Date getOneDayOneTime(String end_date, int hour_num) {
		Date date = strToDate(getCountDay(end_date,1,format1, format1),format1);
		Calendar cal0 = Calendar.getInstance();
		cal0.setTime(date);
		cal0.set(Calendar.HOUR, hour_num);
		return cal0.getTime();
	}
	
	
	/**
	 * 获取一天的凌晨一点时间，格式例如：20181115010000
	 * @param date
	 * @return
	 */
	public static String todayOneTime(){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date());
    	cal0.set(Calendar.HOUR_OF_DAY, 1);
    	cal0.set(Calendar.MINUTE, 0);
    	cal0.set(Calendar.SECOND, 0);
    	return format4.format(cal0.getTime());
	}
	
	
	
	/**
	 * 返回昨天日期
	 * @return yyyyMMdd
	 */
	public static String getYesterday(){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date());
    	cal0.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
    	return format1.format(cal0.getTime());
	}
	
	
	/**
	 * 返回昨天的日期
	 * @param returnFormat：返回的时间格式
	 * @return
	 */
	public static String getYesterday(SimpleDateFormat returnFormat){
    	Calendar cal0 = Calendar.getInstance();
    	cal0.setTime(new Date());
    	cal0.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
    	return returnFormat.format(cal0.getTime());
	}
	
	
	/**
	 * 返回前几天或者后几天时间
	 * @param day_num：天数
	 * @param returnFormat：返回时间格式
	 * @return
	 */
	public static String getCountDay(int day_num, SimpleDateFormat returnFormat){
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(c.DAY_OF_YEAR, day_num);
        return returnFormat.format(c.getTime());
	}
	
	
	/**
	 * 返回指定时间前几天或者后几天时间====》返回时间类型
	 * @param date
	 * @return
	 */
	public static Date getSevenDayBefore(Date date, int day_num){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(c.DAY_OF_YEAR,day_num);
        return TimeUtil.getDayEndTime(c.getTime());
    }
	
	
	/**
     * 得到一个时间延后或前移几天的时间
     * @param nowdate：时间，格式为：2019-01-11
     * @param delay：前移或后延的天数
     * @return
     */
    public static String getNextDay(String nowdate, String delay) {
    	try{
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		String mdate = "";
    		Date d = strToDate(nowdate);
    		long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
    		d.setTime(myTime * 1000);
    		mdate = format.format(d);
    		return mdate;
    	}catch(Exception e){
    		return "";
    	}
    }
    
    
    
    
    //======================================================获得多少天之后的时间戳=================================
    /**
	 * 获得多少天之后的时间戳
	 * @param date
	 * @param day
	 * @return
	 */
	public static long getAfterDayChuo(Date date, int day) {
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(c.DAY_OF_YEAR, day);
	    return new Timestamp(c.getTimeInMillis()).getTime();
	}
	
	
	/**
	 * 获得多少天之后的时间戳
	 * @param strDate：字符串时间
	 * @param requestFormat：字符串时间的格式
	 * @param day：天数
	 * @return
	 */
	public static long getAfterDayChuo(String strDate, SimpleDateFormat requestFormat, int day) {
		Calendar c = Calendar.getInstance();
	    c.setTime(strToDate(strDate, requestFormat));
	    c.add(c.DAY_OF_YEAR, day);
	    return new Timestamp(c.getTimeInMillis()).getTime();
	}
	
	
	
	/**
     * 获得当前的时间戳
     * @return
     */
    public static Timestamp nowTimeStamp() {
    	return new Timestamp(nowTimeMillis());
    }
	
	
	/**
	 * 审核时间间隔
	 * @param auditDate
	 * @param minu
	 * @return
	 */
	public static boolean TimeCompare(Date auditDate,Integer minu) {
		//将审核时间date类型转换为Calendar并且加上minu分钟
		Calendar cal=Calendar.getInstance();
		cal.setTime(auditDate);
		cal.add(Calendar.MINUTE, minu);
		//再将更改后的审核时间还原为date类型
		Date auditDate1=cal.getTime();
		//当前时间
		Date currentDate=new Date();
		//当前时间小于审核时间加上的分钟
		if(currentDate.before(auditDate1)) {
			return false;
		}else {
			return true;
		}
	}
	
	
	/**
	 * 判断time是否在from，to之内
	 * @param time：
	 * @param from：起始时间
	 * @param to：结束时间
	 * @return
	 */
	public static boolean belongCalendar(Date time, Date from, Date to) {
		if (time.getTime()==from.getTime()||time.getTime()==to.getTime()) {
            return true;
        }
		//当前时间
        Calendar date = Calendar.getInstance();
        date.setTime(time);
        //起始时间
        Calendar after = Calendar.getInstance();
        after.setTime(from);
        //结束时间
        Calendar before = Calendar.getInstance();
        before.setTime(to);
        //判断
        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }
	
	
	/**
	 * 把字符串时间转换为date类型
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		 ParsePosition pos = new ParsePosition(0);
		 Date strtodate = format5.parse(strDate, pos);
		 System.out.println(strtodate);
		 return strtodate;
	}
	
	
	/**
	 * 时间处理，将20180101101201换为2018/01/01 10:12:01
	 * @param timeStr
	 * @return
	 */
	public static String timeDeal(String timeStr) {
		String pattern = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
		String timeType="$1-$2-$3 $4:$5:$6";
		return timeStr.replaceFirst(pattern, timeType);
	}
	
    
    /**
     * 两个日期相减得到的天数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffDays(Date beginDate, Date endDate) {
        if(beginDate==null||endDate==null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff=(endDate.getTime()-beginDate.getTime())/(1000*60*60*24);
        int days = new Long(diff).intValue();
        return days;
    }
    
    
    /**
     * 两个日期相减得到的毫秒数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms=beginDate.getTime();
        long date2ms=endDate.getTime();
        return date2ms-date1ms;
    }
    
    
    /**
     * 获取两个日期中的最大日起
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date max(Date beginDate, Date endDate) {
        if(beginDate==null) {
            return endDate;
        }
        if(endDate==null) {
            return beginDate;
        }
        if(beginDate.after(endDate)) {//beginDate日期大于endDate
           return beginDate;
        }
        return endDate;
    }
    
    
    /**
     * 获取两个日期中的最小日期
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date min(Date beginDate, Date endDate) {
        if(beginDate==null) {
            return endDate;
        }
        if(endDate==null) {
            return beginDate;
        }
        if(beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }
    
    
    /**
     * 获取某月该季度的第一个月
     * @param date
     * @return
     */
    public static Date getFirstSeasonDate(Date date) {
    	final int[] SEASON={ 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(date);
    	int sean = SEASON[cal.get(Calendar.MONTH)];
    	cal.set(Calendar.MONTH, sean*3-3);
    	return cal.getTime();
    }
    
    
    /**
   * 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
   * @param beginYear
   * @param beginMonth
   * @param k
   * @return
   */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List getTimeList(int beginYear, int beginMonth, int k) {
    	List list = new ArrayList();
        Calendar begincal=new GregorianCalendar(beginYear,beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
        	list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }
    
    
    /**
     * 获取某年某月到某年某月按天的切片日期集合（间隔天数的集合）
     * @param beginYear
     * @param beginMonth
     * @param endYear
     * @param endMonth
     * @param k
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getTimeList(int beginYear,int beginMonth,int endYear,int endMonth, int k) {
    	List list = new ArrayList();
    	if (beginYear==endYear){
    		for(int j=beginMonth;j<=endMonth;j++){
    			list.add(getTimeList(beginYear,j,k));
    		}
    	}else{
    			{
    				for(int j=beginMonth;j<12;j++){
    					list.add(getTimeList(beginYear,j,k));
    				}
                    for(int i=beginYear+1;i<endYear;i++) {
                    	for (int j=0; j<12; j++) {
                    		list.add(getTimeList(i,j,k));
                    	}
                    }
                    for (int j=0;j <= endMonth; j++) {
                    	list.add(getTimeList(endYear, j, k));
                    }
    			}
    		}
    	return list;
    }
    
    
    /**
     * 将"2015-08-31 21:08:06"型字符串转化为Date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String str) throws ParseException{
    	SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	SimpleDateFormat formatter2=new SimpleDateFormat("yyyyMMddHHmmss");
    	str=formatter1.format(formatter2.parse(str)); 
        Date date = (Date) formatter1.parse(str);
        return date;
    }
    
    
    /**
     * 将long类型转化为Date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date LongToDare(long str) throws ParseException{      
        return new Date(str * 1000);  
    }
    

    /**
     * 获得当前的日期毫秒
     * @return
     */
    public static long nowTimeMillis() {
    	return System.currentTimeMillis();
    }
    
    
    /**
     * 判断当前日期是否在[startDate, endDate]区间
     * 
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @author jqlin
     * @return
     */
    public static boolean isEffectiveDate(Date startDate, Date endDate){
        if(startDate == null || endDate == null){
            return false;
        }
        long currentTime = new Date().getTime();
        if(currentTime >= startDate.getTime() 
                && currentTime <= endDate.getTime()){
            return true;
        }
        return false;
    }
    
    
    /**
     * 得到二个日期间的间隔天数
     * @param secondString：后一个日期
     * @param firstString：前一个日期
     * @return
     */
    public static String getTwoDay(String secondString, String firstString) {
    	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	long day = 0;
    	try {
    		java.util.Date secondTime = myFormatter.parse(secondString);
    		java.util.Date firstTime = myFormatter.parse(firstString);
    		day = (secondTime.getTime() - firstTime.getTime()) / (24 * 60 * 60 * 1000);
    	} catch (Exception e) {
    		return "";
    	}
    	return day + "";
    }
    
    
    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     * @param StringTime：时间
     * @param minute：分钟（有正负之分）
     * @return
     */
    public static String getPreTime(String StringTime, String minute) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String mydate1 = "";
    	try {
    		Date date1 = format.parse(StringTime);
    		long Time = (date1.getTime() / 1000) + Integer.parseInt(minute) * 60;
    		date1.setTime(Time * 1000);
    		mydate1 = format.format(date1);
    	} catch (Exception e) {
    		return "";
    	}
    	return mydate1;
    }
    
    
    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd 
     * 
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	ParsePosition pos = new ParsePosition(0);
    	Date strtodate = formatter.parse(strDate, pos);
    	return strtodate;
    }
    
    
    /**
     * 判断是否闰年
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {
    	/**
    	  * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
    	  * 3.能被4整除同时能被100整除则不是闰年
    	 */
    	Date d = strToDate(ddate);
    	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
    	gc.setTime(d);
    	int year = gc.get(Calendar.YEAR);
    	if ((year % 400) == 0){
    		return true;
    	}else if ((year % 4) == 0){
    	   if ((year % 100) == 0){
    		   return false;
    	   }else{
    		   return true;
    	   }
    	}else{
    		return false;
    	}
    }
    
    
    /**
     * 返回美国时间格式
     * @param str
     * @return
     */
    public static String getEDate(String str) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	ParsePosition pos = new ParsePosition(0);
    	Date strtodate = formatter.parse(str, pos);
    	String j = strtodate.toString();
    	String[] k = j.split(" ");
    	return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    
    /**
     * 判断二个时间是否在同一个周
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
    	Calendar cal1 = Calendar.getInstance();
    	Calendar cal2 = Calendar.getInstance();
    	cal1.setTime(date1);
    	cal2.setTime(date2);
    	int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
    	if(0 == subYear) {
    		if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
    			return true;
    		}
    	}else if(1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
    		// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
    		if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
    			return true;
    		}
    	}else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
    		if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
    			return true;
    		}
    	}
    	return false;
    }
    
    
    
    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     * @return
     */
    public static String getSeqWeek() {
    	Calendar c = Calendar.getInstance(Locale.CHINA);
    	String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
    	if (week.length() == 1)
    	week = "0" + week;
    	String year = Integer.toString(c.get(Calendar.YEAR));
    	return year +"年第"+ week+"周";
    }
    
    
    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     * @param sdate：日期
     * @param num：星期几（星期天是一周的第一天）
     * @return
     */
    public static String getWeek(String sdate, String num) {
    	// 再转换为时间
    	Date dd = strToDate(sdate);
    	Calendar c = Calendar.getInstance();
    	c.setTime(dd);
    	if (num.equals("1")) // 返回星期一所在的日期
    	c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	else if (num.equals("2")) // 返回星期二所在的日期
    	c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
    	else if (num.equals("3")) // 返回星期三所在的日期
    	c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
    	else if (num.equals("4")) // 返回星期四所在的日期
    	c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
    	else if (num.equals("5")) // 返回星期五所在的日期
    	c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
    	else if (num.equals("6")) // 返回星期六所在的日期
    	c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    	else if (num.equals("0")) // 返回星期日所在的日期
    	c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }
    
    
    /**
     * 根据一个日期，返回是星期几的字符串
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
    	// 再转换为时间
    	Date date = strToDate(sdate);
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	// int hour=c.get(Calendar.DAY_OF_WEEK);
    	// hour中存的就是星期几了，其范围 1~7
    	// 1=星期日 7=星期六，其他类推
    	return new SimpleDateFormat("EEEE").format(c.getTime());
    }
    
    
    /**
     * 根据一个日期，返回是星期几的字符串
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate){
    	String str = "";
    	str = getWeek(sdate);
    	if("1".equals(str)){
    		str = "星期日";
    	}else if("2".equals(str)){
    		str = "星期一";
    	}else if("3".equals(str)){
    		str = "星期二";
    	}else if("4".equals(str)){
    		str = "星期三";
    	}else if("5".equals(str)){
    		str = "星期四";
    	}else if("6".equals(str)){
    		str = "星期五";
    	}else if("7".equals(str)){
    		str = "星期六";
    	}
    	return str;
    }
    
    
    /**
     * 两个时间之间的天数
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
    	if (date1 == null || date1.equals(""))
    		return 0;
    	if (date2 == null || date2.equals(""))
    		return 0;
    	// 转换为标准时间
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
    
    
    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate) {
    	// 取该时间所在月的一号
    	sdate = sdate.substring(0, 8) + "01";

    	// 得到这个月的1号是星期几
    	Date date = strToDate(sdate);
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int u = c.get(Calendar.DAY_OF_WEEK);
    	String newday = getNextDay(sdate, (1 - u) + "");
    	return newday;
    }
    
    
    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写
     * @param sformat
     * @return
     */
    public static String getUserDate(String sformat) {
    	Date currentTime = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat(sformat);
    	String dateString = formatter.format(currentTime);
    	return dateString;
    }
    
    
    /**
     * 返回一个i位数的随机数
     * @param i
     * @return
     */
    public static String getRandom(int i) {
    	Random jjj = new Random();
    	// int suiJiShu = jjj.nextInt(9);
    	if (i == 0)
    		return "";
    	String jj = "";
    	for (int k = 0; k < i; k++) {
    		jj = jj + jjj.nextInt(9);
    	}
    	return jj;
    }
    
    
    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     * @param k：表示是取几位随机数，可以自己定
     * @return
     */
    public static String getNo(int k) {
    	return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }
    
    /**
     * 日期格式转换
     * yyyy-MM-dd HH:mm:ss 转换成 yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String get_format4(String str){
    	Date date;
		try {
			date = format5.parse(str);
			return format4.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    /**
	 * 获取某一天时间,格式例如：yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String get_format4(Date date){
	     String firstDay = format4.format(date);
	     return firstDay;
	}
	
    
    /**
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
    
    /**
     * 计算周一与周日所在日期
     * @param date
     * @return
     */
    public static String getMondayAndSundayDate(String date){
		 try {
			   SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式  
			   Calendar cal = Calendar.getInstance();  
			   Date time=sdf.parse(date);
			   cal.setTime(time);  
			   //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
			   int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
			   if(1 == dayWeek) {  
			      cal.add(Calendar.DAY_OF_MONTH, -1);  
			   }  
			  cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
			  int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
			  cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
			  String monday = sdf.format(cal.getTime()).toString();
			  cal.add(Calendar.DATE, 6);
			  String sunday = sdf.format(cal.getTime()).toString();
			  return monday + "-" + sunday;
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		 return null;
	}
    
    /**
     * 计算当前日期所属的年周数
     * @param date
     * @return
     */
    public static int getWeekOfYearOrder(String date){
    	Calendar calendar = null;
    	try {  
    		calendar = Calendar.getInstance();  
        	calendar.setFirstDayOfWeek(Calendar.MONDAY);  
        	calendar.setTime(format1.parse(date));  
    	} catch (ParseException e) {  
    	    e.printStackTrace();  
    	}  
    	  return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
	

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }
    
    /**
     * 获取秒级的时间戳
     * @return
     */
    public static Long getNowTimestampSecond(){
    	String timestamp = String.valueOf(new Date().getTime());  
    	int length = timestamp.length();  
    	if (length > 3) {  
    		return Long.valueOf(timestamp.substring(0,length-3));  
    	} else {  
    		return 0l;  
    	}  
    }

    /**
	 * 当前时间之前的时间与当前时间相差多少秒
	 * @param date1-date2
	 * @return
	 */
	public static Long calLastedTime(String date1,String date2, String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date dt1 = sf.parse(date1);
			Date dt2 = sf.parse(date2);
			long diffSeconds = (long) ((dt1.getTime() - dt2.getTime()) / 1000);
			return diffSeconds;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
    
}

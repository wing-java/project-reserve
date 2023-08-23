package com.example.longecological.utils.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;

/**
 * 获取指定几个月的月初、月末的工具类（可用于按月查询数据）
 * @author fcy
 *
 */
public class MonthDateUtil {
    
	
	/**
	 * 获得月份时间范围
	 * @param num：往前推算的月份数量
	 * @param endTimeString：结束时间字符串
	 * @return
	 */
    public static Map<String, Object> getMonthAreaMap(Integer num, String endTimeString){
    	String pattern = "(\\d{4})(\\d{2})(\\d{2})";
 		String timeType="$1-$2-$3";
 		LocalDateTime dateTime = LocalDateTime.parse(endTimeString.replaceFirst(pattern, timeType));
 		List<Map<String, Object>> monthList=new ArrayList<>();
 		String groupParam="";
 		for(int i=0;i<num;i++) {
 			LocalDateTime firstDayOfPreviousMonth = dateTime.minusMonths(i).dayOfMonth().withMinimumValue(); 
 	    	LocalDateTime lastDayOfPreviousMonth = dateTime.minusMonths(i).dayOfMonth().withMaximumValue(); 
 	    	groupParam = groupParam + "WHEN cre_date BETWEEN '"+firstDayOfPreviousMonth.toString("yyyyMMdd")+"' AND '"+lastDayOfPreviousMonth.toString("yyyyMMdd")+"' THEN '"+firstDayOfPreviousMonth.toString("yyyyMMdd")+"-"+lastDayOfPreviousMonth.toString("yyyyMMdd")+"' ";
 	    	Map<String, Object> oneMonthMap=new HashMap<>();
 	    	oneMonthMap.put("dateArea", firstDayOfPreviousMonth.toString("yyyyMMdd")+"-"+lastDayOfPreviousMonth.toString("yyyyMMdd"));//时间范围
 	    	monthList.add(oneMonthMap);
 		}
 		
 		Map<String, Object> monthAreaMap = new HashMap<>();
 		monthAreaMap.put("endTime", endTimeString);//最后一天
 		monthAreaMap.put("beginTime", dateTime.minusMonths(num-1).dayOfMonth().withMinimumValue().toString("yyyyMMdd"));//最开始的一天：往前推的最后一个月的月初
 		monthAreaMap.put("groupParam", groupParam);//时间范围sql语句处理
 		monthAreaMap.put("monthList", monthList);//每一周的时间范围
 	   	return monthAreaMap;
   }
    
    
   /**
    * 获得20181025类型数据的前一个月
    * @param createDate
    * @return
    */
   public static String getLastMonth(String createDate) {
	   //时间格式转换
	   String pattern = "(\\d{4})(\\d{2})(\\d{2})";
	   String timeType="$1-$2-$3";
	   LocalDateTime dateTime = LocalDateTime.parse(createDate.replaceFirst(pattern, timeType));
	   
	   //查看前一个月的年月份
	   return dateTime.minusMonths(1).toString("yyyy年MM月");
   }
    
   
   
    public static void main(String[] args) {
    	Map<String, Object> map=new HashMap<>();
    	System.out.println(getMonthAreaMap(2,"20181025").toString());
    	System.out.println(getLastMonth("20181025"));
    }
}
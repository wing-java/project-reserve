package com.example.longecological.utils.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;

/**
 * 获取指定几年的年初、年末的工具类（可用于按年查询数据）
 * @author fcy
 *
 */
public class YearDateUtil {
    
	
	/**
	 * 获得年份时间范围
	 * @param num
	 * @param endTimeString
	 * @param map
	 * @return
	 */
    public static Map<String, Object> getYearAreaMap(Integer num, String endTimeString){
    	String pattern = "(\\d{4})(\\d{2})(\\d{2})";
 		String timeType="$1-$2-$3";
 		LocalDateTime dateTime = LocalDateTime.parse(endTimeString.replaceFirst(pattern, timeType));
 		List<Map<String, Object>> yearList=new ArrayList<>();
 		String groupParam="";
 		for(int i=0;i<num;i++) {
 			LocalDateTime firstDayOfPreviousMonth = dateTime.minusYears(i).dayOfYear().withMinimumValue(); 
 	    	LocalDateTime lastDayOfPreviousMonth = dateTime.minusYears(i).dayOfYear().withMaximumValue(); 
 	    	groupParam = groupParam + "WHEN cre_date BETWEEN '"+firstDayOfPreviousMonth.toString("yyyyMMdd")+"' AND '"+lastDayOfPreviousMonth.toString("yyyyMMdd")+"' THEN '"+firstDayOfPreviousMonth.toString("yyyyMMdd")+"-"+lastDayOfPreviousMonth.toString("yyyyMMdd")+"' ";
 	    	Map<String, Object> oneYearMap=new HashMap<>();
 	    	oneYearMap.put("dateArea", firstDayOfPreviousMonth.toString("yyyyMMdd")+"-"+lastDayOfPreviousMonth.toString("yyyyMMdd"));//时间范围
 	    	yearList.add(oneYearMap);
 		}
 		
 		Map<String, Object> yearAreaMap = new HashMap<>();
 		yearAreaMap.put("endTime", endTimeString);//最后一天
 		yearAreaMap.put("beginTime", dateTime.minusYears(num-1).dayOfYear().withMinimumValue().toString("yyyyMMdd"));//最开始的一天：往前推的最后一个月的月初
 		yearAreaMap.put("groupParam", groupParam);//时间范围sql语句处理
 		yearAreaMap.put("yearList", yearList);//每一周的时间范围
 	   	return yearAreaMap;
    }
    
    public static void main(String[] args) {
    	Map<String, Object> map=new HashMap<>();
    	System.out.println(getYearAreaMap(2,"20181025").toString());
    }
}
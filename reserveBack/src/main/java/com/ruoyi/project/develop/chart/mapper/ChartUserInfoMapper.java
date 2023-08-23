package com.ruoyi.project.develop.chart.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ChartUserInfoMapper {

	/**
	 * 查询父级链
	 * @param user_id
	 * @return
	 */
	String getUserParentChain(@Param("user_id") String user_id);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int getSummaryNum1(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int getSummaryNum2(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int getSummaryNum3(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int getSummaryNum4(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	String getSummaryNum5(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> getSummaryNum_6_7_8(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> getSummaryNum_9_10_11(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	String getSummaryNum12(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	String getSummaryNum13(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> getSummaryNum_14_15_16(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> getSummaryNum_17_18_19(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int getSummaryNum20(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	String getSummaryNum21(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	String getSummaryNum22(@Param("map") Map<String, Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	String getSummaryNum23(@Param("map") Map<String, Object> map);
}

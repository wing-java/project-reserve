package com.example.longecological.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserSignMapper {

	/**
	 * 
	 * @param user_id
	 * @param month
	 * @return
	 */
	List<Map<String, Object>> getUserSignList(@Param("user_id") String user_id, @Param("month") String month);
	
	/**
	 * 
	 * @param user_id
	 * @param date
	 * @return
	 */
	Map<String, Object> getUserSignByDate(@Param("user_id") String user_id, @Param("date") String date);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int addUserSign(@Param("map") Map<String, Object> map);
}

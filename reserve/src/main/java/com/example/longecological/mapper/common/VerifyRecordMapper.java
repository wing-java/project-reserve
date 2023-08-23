package com.example.longecological.mapper.common;

import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface VerifyRecordMapper {

	/**
	 * 拿到发送账号在当前系统  最后发送的验证码
	 * @param map
	 * @return
	 */
	Map<String, Object> getInfolast(@Param("map")Map<String, Object> map); 
	
	
	/**
	 * 将验证码标记为已被验证
	 * @param entity
	 * @return
	 */
	int update(@Param("map")Map<String, Object> map);
	
	
	/**
	 * 拿到一个小时的发送数量
	 * @param map
	 * @return
	 */
	int getPeriodCount(@Param("map")Map<String, Object> map);


	/**
	 * 短信验证码发送
	 * @param verifyRecordMap
	 * @return
	 */
	int addVerifyRecord(@Param("map")Map<String, Object> verifyRecordMap);

}

package com.example.longecological.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户意见反馈管理
 * @author Administrator
 *
 */
public interface UserFeedBackMapper {

	/**
	 * 新增用户意见反馈
	 * @param map
	 * @return
	 */
	int addUserFeedBack(@Param("map") Map<String, Object> map);

	
	/**
	 * 查询意见反馈列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserFeedBackList(@Param("map") Map<String, Object> map);


	/**
	 * 查询意见反馈详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserFeedBackDetail(@Param("map") Map<String, Object> map);

}

package com.example.longecological.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户线下充值记录
 * @author Administrator
 *
 */
public interface UserRechargeOfflineMapper {

	
	/**
	 * 保存用户线下充值记录
	 * @param alipayInfo
	 * @return
	 */
	int insertUserRechargeOffline(@Param("map") Map<String, Object> alipayInfo);

	
	/**
	 * 查询用户线下充值记录列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserRechargeOfflineList(@Param("map") Map<String, Object> map);


	/**
	 * 查询用户线下充值记录详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserRechargeOfflineDetail(@Param("map") Map<String, Object> map);
	
}

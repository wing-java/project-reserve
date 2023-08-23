package com.example.longecological.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户线上充值记录
 * @author Administrator
 *
 */
public interface UserRechargeOnlineMapper {

	
	/**
	 * 保存线上充值支付订单记录
	 * @param alipayInfo
	 * @return
	 */
	int insertUserRechargeOnline(@Param("map") Map<String, Object> alipayInfo);
	
	/**
	 * 查询用户线下充值记录列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserRechargeOnlineList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询用户线上充值详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserRechargeOnlineDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询充值次数
	 * @param user_id
	 * @return
	 */
	int getUserRechargeNum(@Param("user_id") String user_id);
	
}

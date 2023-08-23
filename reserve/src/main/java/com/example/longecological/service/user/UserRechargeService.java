package com.example.longecological.service.user;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 用户余额充值相关
 * @author Administrator
 *
 */
public interface UserRechargeService {

	
	/**
	 * 查询用户充值列表
	 * @param map
	 * @return
	 */
	R getUserRechargeList(Map<String, Object> map);
	/**
	 * 查询用户充值详情
	 * @param map
	 * @return
	 */
	R getUserRechargeDetail(Map<String, Object> map);
	
	
	/**
	 * 用户线下充值
	 * @param map
	 * @return
	 */
	R userRechargeOffLine(Map<String, Object> map);
	/**
	 * 用户线上充值（APP端）
	 * @param map
	 * @return
	 */
	R userRechargeOnLineApp(Map<String, Object> map);


}

package com.ruoyi.project.develop.trade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.trade.domain.UserRechargeOffline;



/**
 * 用户线下充值信息管理
 * @author Administrator
 *
 */
public interface UserRechargeOfflineMapper {


	/**
	 * 查询用户线下充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRechargeOfflineList(@Param("map") Map<String, Object> params);
	/**
	 * 导出用户线下充值列表
	 * @param params
	 * @return
	 */
	List<UserRechargeOffline> selectUserRechargeOfflineList(@Param("map") Map<String, Object> params);
	/**
	 * 统计充值信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserRechargeOfflineList(@Param("map") Map<String, Object> params);


	/**
	 * 根据充值编号查询线下充值信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserRechargeOfflineById(@Param("recharge_id") String recharge_id);
	/**
	 * 更新用户线下充值状态
	 * @param rechargeMap
	 * @return
	 */
	int updateUserRechargeOfflineStatus(@Param("map") Map<String, Object> rechargeMap);
	
	

}

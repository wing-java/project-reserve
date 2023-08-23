package com.ruoyi.project.develop.trade.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.trade.domain.UserRechargeOnline;
import com.ruoyi.project.develop.trade.domain.UserRechargeRecordDetail;

/**
 * 用户线上充值管理
 * @author Administrator
 *
 */
public interface UserRechargeOnlineService {

	
	/**
	 * 查询用户线上充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRechargeOnlineList(Map<String, Object> params);
	/**
	 * 导出用户线上充值列表
	 * @param params
	 * @return
	 */
	List<UserRechargeOnline> selectUserRechargeOnlineList(Map<String, Object> params);
	/**
	 * 统计充值信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserRechargeOnlineList(Map<String, Object> params);

}
	
	

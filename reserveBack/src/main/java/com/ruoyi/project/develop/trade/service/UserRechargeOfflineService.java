package com.ruoyi.project.develop.trade.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.trade.domain.UserRechargeOffline;

/**
 * 用户线下充值管理
 * @author Administrator
 *
 */
public interface UserRechargeOfflineService {

	
	/**
	 * 查询用户线下充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRechargeOfflineList(Map<String, Object> params);
	/**
	 * 导出用户线下充值列表
	 * @param params
	 * @return
	 */
	List<UserRechargeOffline> selectUserRechargeOfflineList(Map<String, Object> params);
	/**
	 * 统计充值信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserRechargeOfflineList(Map<String, Object> params);


	/**
	 * 批量审核用户线下充值记录
	 * @param params
	 * @return
	 */
	R batchSysAuditUserRechargeOffline(Map<String, Object> params);
	

}
	
	

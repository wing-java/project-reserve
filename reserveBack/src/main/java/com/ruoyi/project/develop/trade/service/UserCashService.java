package com.ruoyi.project.develop.trade.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.trade.domain.UserCash;

/**
 * 代理======用户取现记录管理
 * @author Administrator
 *
 */
public interface UserCashService {

	
	/**
	 * 查询用户取现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserCashList(Map<String, Object> params);
	/**
	 * 统计取现信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserCashList(Map<String, Object> params);
	/**
	 * 导出用户取现记录信息
	 * @param params
	 * @return
	 */
	List<UserCash> selectUserCashList(Map<String, Object> params);
	/**
	 * 查询取现记录详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserCashDetailList(Map<String, Object> params);


	/**
	 * 导出用户待处理取现记录
	 * @param params
	 * @return
	 */
	List<UserCash> selectWaitUserCashList(Map<String, Object> params);


	/**
	 * 批量审核处理处理中的兑币记录
	 * @param params
	 * @return
	 */
	R batchSysAuditUserCash(Map<String, Object> params);
	

}
	
	

package com.ruoyi.project.develop.trade.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.trade.domain.UserRoll;

/**
 * 充币日志管理
 * @author Administrator
 *
 */
public interface UserRollService {

	
	/**
	 * 查询用户转账日志列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRollList(Map<String, Object> params);
	
	
	/**
	 * 汇总数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserRollList(Map<String, Object> params);

	
	/**
	 * 导出用户转账日志列表
	 * @param params
	 * @return
	 */
	List<UserRoll> selectUserRollList(Map<String, Object> params);

}
	
	

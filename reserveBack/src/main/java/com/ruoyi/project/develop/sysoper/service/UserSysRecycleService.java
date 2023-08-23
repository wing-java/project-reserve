package com.ruoyi.project.develop.sysoper.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.sysoper.domain.UserSysRecycle;

/**
 * 公司扣款管理
 * @author Administrator
 *
 */
public interface UserSysRecycleService {

	
	/**
	 * 查询公司扣款列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserSysRecycleList(Map<String, Object> params);
	
	
	/**
	 * 汇总数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserSysRecycleList(Map<String, Object> params);

	
	/**
	 * 导出公司扣款列表
	 * @param params
	 * @return
	 */
	List<UserSysRecycle> selectUserSysRecycleList(Map<String, Object> params);


	/**
	 * 新增公司扣款
	 * @param params
	 * @return
	 */
	R batchAddUserSysRecycle(Map<String, Object> params);
	
}
	
	

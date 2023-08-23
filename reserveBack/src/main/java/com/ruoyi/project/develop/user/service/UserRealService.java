package com.ruoyi.project.develop.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.user.domain.UserReal;

public interface UserRealService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRealList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserReal> selectUserRealList(Map<String, Object> params);
	
	/**
	 * 详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getUserRealById(String id);
	
	/**
	 * 审核
	 * @param params
	 * @return
	 */
	R check(Map<String, Object> params);
}

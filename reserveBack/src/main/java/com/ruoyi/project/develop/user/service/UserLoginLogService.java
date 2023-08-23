package com.ruoyi.project.develop.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.user.domain.UserLoginLog;

/**
 * 用户登录日志管理
 * @author Administrator
 *
 */
public interface UserLoginLogService {

	
	/**
	 * 查询登录日志列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserLoginLogList(Map<String, Object> params);

	
	/**
	 * 导出用户登录日志列表
	 * @param params
	 * @return
	 */
	List<UserLoginLog> selectUserLoginLogList(Map<String, Object> params);

}
	
	

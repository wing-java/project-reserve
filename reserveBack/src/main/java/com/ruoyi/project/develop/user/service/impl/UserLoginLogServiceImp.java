package com.ruoyi.project.develop.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.user.domain.UserLoginLog;
import com.ruoyi.project.develop.user.mapper.UserLoginLogMapper;
import com.ruoyi.project.develop.user.service.UserLoginLogService;


/**
 * 用户登录日志管理
 * @author Administrator
 *
 */
@Service
public class UserLoginLogServiceImp implements UserLoginLogService {
	
	@Autowired
	private UserLoginLogMapper userLoginLogMapper;

	
	/**
	 * 查询用户登录日志列表
	 */
	@Override
	public List<Map<String, Object>> getUserLoginLogList(Map<String, Object> params) {
		return userLoginLogMapper.getUserLoginLogList(params);
	}

	
	/**
	 * 导出用户登录日志列表
	 */
	@Override
	public List<UserLoginLog> selectUserLoginLogList(Map<String, Object> params) {
		return userLoginLogMapper.selectUserLoginLogList(params);
	}

}
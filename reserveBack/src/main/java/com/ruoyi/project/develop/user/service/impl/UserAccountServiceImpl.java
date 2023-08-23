package com.ruoyi.project.develop.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.user.domain.UserAccount;
import com.ruoyi.project.develop.user.mapper.UserAccountMapper;
import com.ruoyi.project.develop.user.service.UserAccountService;


/**
 * 用户收款账户管理
 * @author Administrator
 *
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	private UserAccountMapper userAccountMapper;

	
	/**
	 * 查询用户收货地址列表
	 */
	@Override
	public List<Map<String, Object>> getUserAccountList(Map<String, Object> params) {
		return userAccountMapper.getUserAccountList(params);
	}

	
	/**
	 * 导出用户收货地址列表
	 */
	@Override
	public List<UserAccount> selectUserAccountList(Map<String, Object> params) {
		return userAccountMapper.selectUserAccountList(params);
	}

}
package com.ruoyi.project.develop.trade.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.trade.domain.UserRoll;
import com.ruoyi.project.develop.trade.mapper.UserRollMapper;
import com.ruoyi.project.develop.trade.service.UserRollService;


/**
 * 用户转账日志管理
 * @author Administrator
 *
 */
@Service
public class UserRollServiceImpl implements UserRollService {

	@Autowired
	private UserRollMapper userRollMapper;

	
	/**
	 * 查询用户转账日志列表
	 */
	@Override
	public List<Map<String, Object>> getUserRollList(Map<String, Object> params) {
		return userRollMapper.getUserRollList(params);
	}
	
	
	/**
	 * 汇总数据
	 */
	@Override
	public Map<String, Object> summaryUserRollList(Map<String, Object> params) {
		return userRollMapper.summaryUserRollList(params);
	}

	
	/**
	 * 导出用户转账日志列表
	 */
	@Override
	public List<UserRoll> selectUserRollList(Map<String, Object> params) {
		return userRollMapper.selectUserRollList(params);
	}

}

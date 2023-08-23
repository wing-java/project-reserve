package com.ruoyi.project.develop.trade.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.pay.domain.UserTradeAlipay;
import com.ruoyi.project.develop.pay.domain.UserTradeWechatpay;
import com.ruoyi.project.develop.trade.domain.UserRechargeOnline;
import com.ruoyi.project.develop.trade.domain.UserRechargeRecordDetail;
import com.ruoyi.project.develop.trade.mapper.UserRechargeOnlineMapper;
import com.ruoyi.project.develop.trade.service.UserRechargeOnlineService;


/**
 * 用户线上充值管理
 * @author Administrator
 *
 */
@Service
public class UserRechargeOnlineServiceImpl implements UserRechargeOnlineService {
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	private UserRechargeOnlineMapper userRechargeOnlineMapper;

	
	/**
	 * 查询用户线上充值列表
	 */
	@Override
	public List<Map<String, Object>> getUserRechargeOnlineList(Map<String, Object> params) {
		return userRechargeOnlineMapper.getUserRechargeOnlineList(params);
	}
	/**
	 * 导出用户线上充值列表
	 */
	@Override
	public List<UserRechargeOnline> selectUserRechargeOnlineList(Map<String, Object> params) {
		return userRechargeOnlineMapper.selectUserRechargeOnlineList(params);
	}
	/**
	 * 统计充值信息
	 */
	@Override
	public Map<String, Object> summaryUserRechargeOnlineList(Map<String, Object> params) {
		return userRechargeOnlineMapper.summaryUserRechargeOnlineList(params);
	}

}

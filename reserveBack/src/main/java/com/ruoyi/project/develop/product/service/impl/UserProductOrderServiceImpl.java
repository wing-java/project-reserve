package com.ruoyi.project.develop.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.RedisNameVersionConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.product.domain.UserProductOrder;
import com.ruoyi.project.develop.product.mapper.UserProductOrderMapper;
import com.ruoyi.project.develop.product.service.UserProductOrderService;


/**
 * 订单信息管理
 * @author Administrator
 *
 */
@Service
public class UserProductOrderServiceImpl implements UserProductOrderService {
	
	@Autowired
	private UserProductOrderMapper userProductOrderMapper;
	@Autowired
	RedisUtils redisUtils;
	

	/**
	 * 查询订单列表
	 */
	@Override	
	public List<Map<String, Object>> getUserProductOrderList(Map<String, Object> params) {
		return userProductOrderMapper.getUserProductOrderList(params);
	}
	/**
	 * 导出订单
	 */
	@Override
	public List<UserProductOrder> selectUserProductOrderList(Map<String, Object> params) {
		return userProductOrderMapper.selectUserProductOrderList(params);
	}
	/**
	 * 根据编号查询订单详情
	 */
	@Override
	public Map<String, Object> getUserProductOrderById(String id) {
		return userProductOrderMapper.getUserProductOrderById(id);
	}
	/**
	 * 根据订单号查询订单详情
	 */
	@Override
	public Map<String, Object> getUserProductOrderByProductOrderId(String ProductOrder_id) {
		return userProductOrderMapper.getUserProductOrderByProductOrderId(ProductOrder_id);
	}
	
}
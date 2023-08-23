package com.example.longecological.service.product.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.alibaba.fastjson.JSON;
import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.constant.RedisNameVersionConstants;
import com.example.longecological.constant.RocketMqConstants;
import com.example.longecological.constant.SysFunctionLockParamConstants;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.SysProductOrderCodeConstant;
import com.example.longecological.constant.msgcode.TradeInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.async.AsyncBenefitMapper;
import com.example.longecological.mapper.product.SysProductOrderMapper;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.common.SysFunctionLockParamService;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.product.SysProductCacheService;
import com.example.longecological.service.product.SysProductOrderService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class SysProductOrderServiceImpl implements SysProductOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysProductOrderServiceImpl.class);
	
	@Autowired
	SysProductOrderMapper sysProductOrderMapper;
	@Autowired
	UserInfoCacheService userInfoCacheService;
	@Autowired
	SysProductCacheService sysProductCacheService;
	@Autowired
	SysParamService sysParamService;
	@Autowired
	RedisUtils redisUtils;
	@Autowired
	VerifyUserService verifyUserService;
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	UserWalletMapper userWalletMapper;
	@Autowired
	AsyncBenefitMapper asyncBenefitMapper;
	@Autowired
	RocketMQTemplate rocketMQTemplate;
	@Autowired
	SysFunctionLockParamService sysFunctionLockParamService;

	/**
	 * 提交订单
	 */
	@Override
	@Transactional
	public R submitOrder(Map<String, Object> map) {
		int i=0;
		try {
			//（1）余额功能是否开放
			String userBuyProductLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userBuyProductLock);
			if(!TypeStatusConstant.sys_function_lock_param_lock_1.equals(userBuyProductLock)) {
				return R.error(CommonCodeConstant.COMMON_CODE_999981, CommonCodeConstant.COMMON_MSG_999981);
			}
			//校验订单信息
			R checkSubmitOrder = checkSubmitOrder(map);
			if(!Boolean.valueOf(checkSubmitOrder.get(R.SUCCESS_TAG).toString())) {
				return checkSubmitOrder;
			}
			//校验商品信息
			Map<String, Object> product = sysProductCacheService.getSysProductById(map);
			if(product == null) {
				return R.error(SysProductOrderCodeConstant.COMMON_CODE_999198, SysProductOrderCodeConstant.COMMON_MSG_999198);
			}
			//查询用户
			Map<String, Object> user = userInfoMapper.getRealUserInfoById(StringUtil.getMapValue(map, "sys_user_id"));
			//处理参数
			map.put("goods_num", 1);//默认数量为1
			BigDecimal cash_num = new BigDecimal(StringUtil.getMapValue(product, "goods_price")).multiply(new BigDecimal(StringUtil.getMapValue(map, "goods_num")));
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			String order_no = StringUtil.getDateTimeAndRandomForID();
			//处理产品库存
			Map<String, Object> edit_product = new HashMap<String, Object>();
			edit_product.put("product_id", StringUtil.getMapValue(product, "product_id"));
			edit_product.put("goods_num", StringUtil.getMapValue(map, "goods_num"));
			edit_product.put("up_date", TimeUtil.getDayString());
			edit_product.put("up_time", TimeUtil.getTimeString());
			i = sysProductOrderMapper.updateProductStockAndSales(edit_product);
			if(i != 1) {
				return R.error(SysProductOrderCodeConstant.COMMON_CODE_999197, SysProductOrderCodeConstant.COMMON_MSG_999197);
			}
			//更新用户账户余额：余额账户
			Map<String, Object> edit_user = new HashMap<>();
			edit_user.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));//用户编号
			edit_user.put("op_order_no", order_no);//订单号
			edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_02);//操作类型：购买产品
			edit_user.put("balance_num", cash_num.negate());//支付余额账户
			edit_user.put("sharestock_num", StringUtil.getMapValue(product, "sharestock_num"));
			edit_user.put("up_date", TimeUtil.getDayString());//更新日期
			edit_user.put("up_time", TimeUtil.getTimeString());//更新时间
			i = userWalletMapper.updateUserWalletNum(edit_user);
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(TradeInfoCodeConstant.TRADE_INFO_CODE_999599,"账户余额不足，请充值");
			}
			
			synchronized ("submitOrder") {
				//查询股权编号
				int rank = sysProductOrderMapper.getSysSharestock();
				String sharestock_no = this.getSharestockNo(rank);
				//处理订单信息
				Map<String, Object> record = new HashMap<>();
				record.put("order_id", null);
				record.put("order_no", order_no);
				record.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
				record.put("goods_num", StringUtil.getMapValue(map, "goods_num"));
				record.put("cash_num", cash_num);
				record.put("cre_date", date);
				record.put("cre_time", time);
				record.put("product_id", StringUtil.getMapValue(map, "product_id"));
				record.put("goods_price", StringUtil.getMapValue(product, "goods_price"));
				record.put("goods_name", StringUtil.getMapValue(product, "goods_name"));
				record.put("goods_show", StringUtil.getMapValue(product, "goods_show"));
				record.put("sharestock_num", StringUtil.getMapValue(product, "sharestock_num"));
				record.put("sharestock_no", sharestock_no);
				record.put("real_name", StringUtil.getMapValue(user, "real_name"));
				record.put("status", "09");
				i = sysProductOrderMapper.addUserProductOrderInfo(record);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(SysProductOrderCodeConstant.COMMON_CODE_999196, SysProductOrderCodeConstant.COMMON_MSG_999196);
				}
				//更新股权数
				sysProductOrderMapper.updateSysSharestock();
				//提交事务
				TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
					@Override
					public void afterCommit() {
						rocketMQTemplate.syncSend(RocketMqConstants.order_reward_topic,JSON.toJSONString(record));
					}
				});
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("SysProductOrderServiceImpl -- submitOrder方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}finally {
			redisUtils.updateVersion(RedisNameVersionConstants.user_product_order_list_version+StringUtil.getMapValue(map, "sys_user_id"));
		} 
	}
	
	public String getSharestockNo(int rank) {
		int len = String.valueOf(rank).length();
		if(len<8) {
			return "PG1"+String.format("%07d", rank);
		}else {
			return "PG"+String.valueOf(rank);
		}
	}

	/**
	 * 校验订单信息
	 * @param map
	 * @return
	 */
	public R checkSubmitOrder(Map<String, Object> map) {
		//校验用户信息
		if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
			return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
		}
		//校验产品信息
		if(StringUtil.isEmpty(StringUtil.getMapValue(map, "product_id"))) {
			return R.error(SysProductOrderCodeConstant.COMMON_CODE_999198, SysProductOrderCodeConstant.COMMON_MSG_999198);
		}
		//校验支付密码
		R checkPayPassword = verifyUserService.checUserPayPass(map);
		if(!Boolean.valueOf(checkPayPassword.get(R.SUCCESS_TAG).toString())) {
			return checkPayPassword;
		}
		//校验实名认证
		R checUserAuthSatus = verifyUserService.checUserAuthStatus(map);
		if(!Boolean.valueOf(checUserAuthSatus.get(R.SUCCESS_TAG).toString())) {
			return checUserAuthSatus;
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999978, CommonCodeConstant.COMMON_MSG_999978);
	}


	/**
	 * 查询产品订单详情
	 */
	@Override
	public R getUserProductOrderDetail(Map<String, Object> map) {
		try {
			Map<String, Object> userProductOrder = sysProductCacheService.getUserProductOrderInfoById(map);
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userProductOrder", userProductOrder);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("SysProductOrderServiceImpl -- getUserProductOrderDetail方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询产品订单列表
	 */
	@Override
	public R getSysProductOrderList(Map<String, Object> map) {
		try {
			map.put("lastIdKey", "".equals(StringUtil.getMapValue(map, "last_id")) ? "0" : StringUtil.getMapValue(map, "last_id"));
			List<Map<String, Object>> sysProductOrderList = sysProductCacheService.getSysProductOrderList(map);
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("sysProductOrderList", sysProductOrderList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		}catch(Exception e) {
			LOGGER.error("SysProductOrderServiceImpl -- getSysProductOrderList方法执行异常:"+ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	@Override
	@Transactional
	public R receiveUnclaimedBenefit(Map<String, Object> map) {
		int i=0;
		try {
			//校验用户信息
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//查询订单信息
			Map<String, Object> order = sysProductCacheService.getUserProductOrderInfoById(map);
			if(order == null || !StringUtil.getMapValue(map, "sys_user_id").equals(StringUtil.getMapValue(order, "user_id"))) 
				return R.error(SysProductOrderCodeConstant.COMMON_CODE_999185, SysProductOrderCodeConstant.COMMON_MSG_999185);
			BigDecimal unclaimed_benefit = new BigDecimal(StringUtil.getMapValue(order, "unclaimed_benefit"));
			if(unclaimed_benefit.compareTo(BigDecimal.ZERO)<=0) 
				return R.error(SysProductOrderCodeConstant.COMMON_CODE_999184, SysProductOrderCodeConstant.COMMON_MSG_999184);
			//处理参数
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			//更新用户账户余额：余额账户
			String order_no = StringUtil.getDateTimeAndRandomForID();
			Map<String, Object> edit_user = new HashMap<>();
			edit_user.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));//用户编号
			edit_user.put("op_order_no", order_no);//订单号
			edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_10);
			edit_user.put("balance_num", unclaimed_benefit);//支付余额账户
			edit_user.put("total_benefit", unclaimed_benefit);//支付余额账户
			edit_user.put("up_date", TimeUtil.getDayString());//更新日期
			edit_user.put("up_time", TimeUtil.getTimeString());//更新时间
			i = userWalletMapper.updateUserWalletNum(edit_user);
			if(i != 1){
				return R.error(SysProductOrderCodeConstant.COMMON_CODE_999183, SysProductOrderCodeConstant.COMMON_MSG_999183);
			}
			//记录收益日志
			Map<String, Object> record = new HashMap<>();
			record.put("order_no", order_no);
			record.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
			record.put("benefit", unclaimed_benefit);
			record.put("buy_order_no", StringUtil.getMapValue(order, "order_no"));
			record.put("buy_order_id", StringUtil.getMapValue(order, "order_id"));
			record.put("days", StringUtil.getMapValue(order, "unclaimed_days"));
			record.put("rate", StringUtil.getMapValue(order, "unclaimed_rate"));
			record.put("cre_date", date);
			record.put("cre_time", time);
			i = asyncBenefitMapper.addUserRewardToDay(record);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(SysProductOrderCodeConstant.COMMON_CODE_999183, SysProductOrderCodeConstant.COMMON_MSG_999183);
			}
			//更新领取信息
			Map<String, Object> edit_order = new HashMap<>();
			edit_order.put("id", StringUtil.getMapValue(order, "order_id"));
			edit_order.put("old_unclaimed_benefit", StringUtil.getMapValue(order, "unclaimed_benefit"));
			edit_order.put("new_unclaimed_benefit", BigDecimal.ZERO);
			edit_order.put("total_benefit2", unclaimed_benefit);
			edit_order.put("init_date2", date);
			edit_order.put("init_time2", time);
			edit_order.put("unclaimed_days", 0);
			edit_order.put("unclaimed_rate", 0);
			edit_order.put("date", date);
			edit_order.put("time", time);
			i = asyncBenefitMapper.updateOrderUnclaimedBenefit(edit_order);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(SysProductOrderCodeConstant.COMMON_CODE_999183, SysProductOrderCodeConstant.COMMON_MSG_999183);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("SysProductOrderServiceImpl -- receiveUnclaimedBenefit方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}

}

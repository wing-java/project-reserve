package com.example.longecological.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.longecological.service.common.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.OperParamConstant;
import com.example.longecological.constant.RedisNameVersionConstants;
import com.example.longecological.constant.SysFunctionLockParamConstants;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.OrderInfoConstant;
import com.example.longecological.constant.msgcode.RechargeRecordConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.system.SysChannelMapper;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.mapper.user.UserRechargeOfflineMapper;
import com.example.longecological.mapper.user.UserRechargeOnlineMapper;
import com.example.longecological.service.system.SysInfoCacheService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.service.user.UserRechargeService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.ParamValidUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户余额充值相关
 * @author Administrator
 *
 */
@Service
public class UserRechargeServiceImpl implements UserRechargeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRechargeServiceImpl.class);

	@Autowired
	RedisUtils redisUtils;
	@Autowired
	UserInfoCacheService userInfoCacheService;
	@Autowired
	SysInfoCacheService sysInfoCacheService;
	@Autowired
	VerifyUserService verifyUserService;
	@Autowired
	SysFunctionLockParamService sysFunctionLockParamService;
	@Autowired
	UserRechargeOfflineMapper userRechargeOfflineMapper;
	@Autowired
	UserRechargeOnlineMapper userRechargeOnlineMapper;
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	ChuanJunService chuanJunService;
	@Autowired
	JinShunService jinShunService;
	@Autowired
	CikeService cikeService;
	@Autowired
	SysChannelMapper sysChannelMapper;
	@Autowired
	DementedGodService dementedGodService;
	
	
	
	/**
	 * 查询用户充值记录列表
	 */
	@Override
	public R getUserRechargeList(Map<String, Object> map) {
		try {
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			map.put("userIdKey", StringUtil.getMapValue(map, "sys_user_id"));
			map.put("lastIdKey", "".equals(StringUtil.getMapValue(map, "last_id")) ? "0" : StringUtil.getMapValue(map, "last_id"));
			List<Map<String, Object>> rechargeRecordList = new ArrayList<Map<String,Object>>();
			//线上充值
			if(TypeStatusConstant.user_recharge_line_type_01.equals(StringUtil.getMapValue(map, "line_type"))) {
				rechargeRecordList = userRechargeOfflineMapper.getUserRechargeOfflineList(map);
			//线下充值
			}else if(TypeStatusConstant.user_recharge_line_type_02.equals(StringUtil.getMapValue(map, "line_type"))) {
				rechargeRecordList = userRechargeOnlineMapper.getUserRechargeOnlineList(map);
			}else {
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			respondMap.put("rechargeRecordList", rechargeRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserRechargeServiceImpl -- getRechargeRecordList方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	/**
	 * 查询充值记录详情
	 */
	@Override
	public R getUserRechargeDetail(Map<String, Object> map) {
		try {
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			Map<String, Object> rechargeRecordDetail = new HashMap<>();
			//线下充值
			if(TypeStatusConstant.user_recharge_line_type_01.equals(StringUtil.getMapValue(map, "line_type"))) {
				rechargeRecordDetail = userRechargeOfflineMapper.getUserRechargeOfflineDetail(map);
			//线上充值
			}else if(TypeStatusConstant.user_recharge_line_type_02.equals(StringUtil.getMapValue(map, "line_type"))) {
				rechargeRecordDetail = userRechargeOnlineMapper.getUserRechargeOnlineDetail(map);
			}else {
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			respondMap.put("rechargeRecordDetail", rechargeRecordDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserRechargeServiceImpl -- getUserRechargeDetail方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	
	/**
	 * 用户线下充值
	 */
	@Override
	@Transactional
	public R userRechargeOffLine(Map<String, Object> map) {
		int num=0;
		try {
			//（1）用户线下充值功能是否开放
			String userRechargeOffLineLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userRechargeOffLineLock);
			if(!TypeStatusConstant.sys_function_lock_param_lock_1.equals(userRechargeOffLineLock)) {
				return R.error(CommonCodeConstant.COMMON_CODE_999981, CommonCodeConstant.COMMON_MSG_999981);
			}
			//（2）线下充值基本信息校验
			R checkRechargeResult = this.checkUserRechargeOffLine(map);
			if(!Boolean.valueOf(checkRechargeResult.get(R.SUCCESS_TAG).toString())) {
				return checkRechargeResult;
			}
			//（3）保存线下充值记录
			map.put("order_id", StringUtil.getDateTimeAndRandomForID());//订单号
			map.put("user_id", map.get("sys_user_id").toString());//用户编号
			map.put("status", TypeStatusConstant.user_recharge_info_status_04);//状态：待审核
			map.put("cre_date", TimeUtil.getDayString());//创建时间
			map.put("cre_time", TimeUtil.getTimeString());//创建时间
			num = userRechargeOfflineMapper.insertUserRechargeOffline(map);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OrderInfoConstant.ORDER_INFO_CODE_999286, OrderInfoConstant.ORDER_INFO_MSG_999286);
			}
			//（4）清除缓存
			redisUtils.updateVersion(RedisNameVersionConstants.user_recharge_offline_list_version+StringUtil.getMapValue(map, "sys_user_id"));
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("OrderInfoServiceImpl -- userRechargeBankCard方法执行异常:"+ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	/**
	 * 校验线下充值信息
	 * @param map
	 * @param userRechargeRecordRechargeType03
	 * @return
	 */
	private R checkUserRechargeOffLine(Map<String, Object> map) {
		//（1）前端必须把充值类型（支付宝、微信、银行卡）参数传过来
		R checkRechargeTypeResult = ParamValidUtil.checkSpecifyParam(map, "recharge_type", OperParamConstant.USER_RECHARGE_OFFLINE_RECHARGE_TYPE);
		if(!Boolean.valueOf(checkRechargeTypeResult.get(R.SUCCESS_TAG).toString())) {
			return checkRechargeTypeResult;
		}
		//（2）前端必须把账户类型（余额）参数传过来
		R checkAccountTypeResult = ParamValidUtil.checkSpecifyParam(map, "account_type", OperParamConstant.USER_RECHARGE_OFFLINE_ACCOUNT_TYPE);
		if(!Boolean.valueOf(checkAccountTypeResult.get(R.SUCCESS_TAG).toString())) {
			return checkAccountTypeResult;
		}
		//（3）校验上传的凭证
		if(StringUtils.isEmpty(StringUtil.getMapValue(map, "recharge_voucher"))) {
			return R.error(RechargeRecordConstant.RECHARGE_RECORD_CODE_999698,RechargeRecordConstant.RECHARGE_RECORD_MSG_999698);
		}
		//（4）校验充值数量
		if(!StringUtil.isValidLargeBigDecimal0(StringUtil.getMapValue(map, "recharge_num"))){
			return R.error(CommonCodeConstant.COMMON_CODE_999982,CommonCodeConstant.COMMON_MSG_999982);
		}
		//（5）校验支付密码
		R checkPaypassword = verifyUserService.checUserPayPass(map);
		if(!Boolean.valueOf(checkPaypassword.get(R.SUCCESS_TAG).toString())) {
			return checkPaypassword;
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
	}
	
	/**
	 * 线上充值信息校验
	 * @param map
	 * @return
	 */
	private R checkUserRechargeOnLine(Map<String, Object> map) {
		System.out.println("参数信息======="+map);
		R checkPaypassword = verifyUserService.checUserAuthStatus(map);
		if(!Boolean.valueOf(checkPaypassword.get(R.SUCCESS_TAG).toString())) {
			return checkPaypassword;
		}
		//（1）前端必须把充值类型（支付宝、微信）参数传过来
		R checkRechargeTypeResult = ParamValidUtil.checkSpecifyParam(map, "recharge_type", OperParamConstant.USER_RECHARGE_ONLINE_RECHARGE_TYPE);
		if(!Boolean.valueOf(checkRechargeTypeResult.get(R.SUCCESS_TAG).toString())) {
			return checkRechargeTypeResult;
		}
		//（2）前端必须把账户类型（余额）参数传过来
		R checkAccountTypeResult = ParamValidUtil.checkSpecifyParam(map, "account_type", OperParamConstant.USER_RECHARGE_ONLINE_ACCOUNT_TYPE);
		if(!Boolean.valueOf(checkAccountTypeResult.get(R.SUCCESS_TAG).toString())) {
			return checkAccountTypeResult;
		}
		//（3）校验充值数量
		if(!StringUtil.isValidLargeBigDecimal0(StringUtil.getMapValue(map, "recharge_num"))){
			return R.error(CommonCodeConstant.COMMON_CODE_999982,CommonCodeConstant.COMMON_MSG_999982);
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
	}
	/**
	 * 川军支付
	 */
	public R userRechargeChuanJun(Map<String, Object> map) {
		int num = 0;
		try {
			//（1）用户线上支付宝充值功能是否开放
			String userRechargeChuanJunLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userRechargeChuanJunLock);
			if(!TypeStatusConstant.sys_function_lock_param_lock_1.equals(userRechargeChuanJunLock)) {
				return R.error(CommonCodeConstant.COMMON_CODE_999981, CommonCodeConstant.COMMON_MSG_999981);
			}
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			String order_no = StringUtil.getDateTimeAndRandomForID();
			R chuanJunResult = chuanJunService.dealChuanJunTrade(StringUtil.getMapValue(map, "sys_user_id"), map.get("recharge_num").toString(), StringUtil.getMapValue(map, "callbackurl"), "01", order_no, date, time, StringUtil.getMapValue(map, "bankcode"));
			if(!Boolean.valueOf(chuanJunResult.get(R.SUCCESS_TAG).toString())) {
				return chuanJunResult;
			}
			//查询是否已充值
			int count = userRechargeOnlineMapper.getUserRechargeNum(map.get("sys_user_id").toString());
			//（4）保存线上充值支付宝支付订单记录
			Map<String, Object> record = new HashMap<>();
			record.put("order_no", order_no);//订单号
			record.put("recharge_type", map.get("recharge_type").toString());//充值类型
			record.put("account_type", map.get("account_type").toString());//充值账户类型
			record.put("user_id", map.get("sys_user_id").toString());//用户编号
			record.put("recharge_num", map.get("recharge_num").toString());//充值数量
			record.put("status", TypeStatusConstant.user_recharge_info_status_00);//状态：充值中，待支付
			record.put("is_first", count > 0 ? '0' : '1');
			record.put("channel_type", StringUtil.getMapValue(map, "channel_type"));
			record.put("cre_date", date);
			record.put("cre_time", time);
			num = userRechargeOnlineMapper.insertUserRechargeOnline(record);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OrderInfoConstant.ORDER_INFO_CODE_999286, OrderInfoConstant.ORDER_INFO_MSG_999286);
			}
			//（5）返回数据处理
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("recharge_url", chuanJunResult.get("data"));
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, respondMap);
		}catch(Exception e) {
			LOGGER.error("UserRechargeServiceImpl -- userRechargeChuanJun方法执行异常:"+ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999285, OrderInfoConstant.ORDER_INFO_MSG_999285);
		}finally {
			redisUtils.updateVersion(RedisNameVersionConstants.user_recharge_online_list_version+StringUtil.getMapValue(map, "sys_user_id"));
		}
	}
	
	/**
	 * 川军支付
	 */
	public R userRechargeJinShun(Map<String, Object> map) {
		int num = 0;
		try {
			//（1）用户线上支付宝充值功能是否开放
			String userRechargeChuanJunLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userRechargeChuanJunLock);
			if(!TypeStatusConstant.sys_function_lock_param_lock_1.equals(userRechargeChuanJunLock)) {
				return R.error(CommonCodeConstant.COMMON_CODE_999981, CommonCodeConstant.COMMON_MSG_999981);
			}
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			String order_no = StringUtil.getDateTimeAndRandomForID();
			R jinShunResult = jinShunService.dealJinShunTrade(StringUtil.getMapValue(map, "sys_user_id"), map.get("recharge_num").toString(), "01", order_no, date, time, StringUtil.getMapValue(map, "product"));
			if(!Boolean.valueOf(jinShunResult.get(R.SUCCESS_TAG).toString())) {
				return jinShunResult;
			}
			//查询是否已充值
			int count = userRechargeOnlineMapper.getUserRechargeNum(map.get("sys_user_id").toString());
			//（4）保存线上充值支付宝支付订单记录
			Map<String, Object> record = new HashMap<>();
			record.put("order_no", order_no);//订单号
			record.put("recharge_type", map.get("recharge_type").toString());//充值类型
			record.put("account_type", map.get("account_type").toString());//充值账户类型
			record.put("user_id", map.get("sys_user_id").toString());//用户编号
			record.put("recharge_num", map.get("recharge_num").toString());//充值数量
			record.put("status", TypeStatusConstant.user_recharge_info_status_00);//状态：充值中，待支付
			record.put("is_first", count > 0 ? '0' : '1');
			record.put("channel_type", StringUtil.getMapValue(map, "channel_type"));
			record.put("cre_date", date);
			record.put("cre_time", time);
			num = userRechargeOnlineMapper.insertUserRechargeOnline(record);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OrderInfoConstant.ORDER_INFO_CODE_999286, OrderInfoConstant.ORDER_INFO_MSG_999286);
			}
			//（5）返回数据处理
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("recharge_url", jinShunResult.get("data"));
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, respondMap);
		}catch(Exception e) {
			LOGGER.error("UserRechargeServiceImpl -- userRechargeJinShun方法执行异常:"+ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999285, OrderInfoConstant.ORDER_INFO_MSG_999285);
		}finally {
			redisUtils.updateVersion(RedisNameVersionConstants.user_recharge_online_list_version+StringUtil.getMapValue(map, "sys_user_id"));
		}
	}
	
	/**
	 * 刺客支付
	 */
	public R userRechargeCike(Map<String, Object> map) {
		int num = 0;
		try {
			//（1）用户线上支付宝充值功能是否开放
			String userRechargeChuanJunLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userRechargeChuanJunLock);
			if(!TypeStatusConstant.sys_function_lock_param_lock_1.equals(userRechargeChuanJunLock)) {
				return R.error(CommonCodeConstant.COMMON_CODE_999981, CommonCodeConstant.COMMON_MSG_999981);
			}
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			String order_no = StringUtil.getDateTimeAndRandomForID();
			R jinShunResult = cikeService.dealCikeTrade(StringUtil.getMapValue(map, "sys_user_id"), map.get("recharge_num").toString(), StringUtil.getMapValue(map, "callbackurl"), "01", order_no, date, time, StringUtil.getMapValue(map, "payType"), StringUtil.getMapValue(map, "user_ip"));
			if(!Boolean.valueOf(jinShunResult.get(R.SUCCESS_TAG).toString())) {
				return jinShunResult;
			}
			//查询是否已充值
			int count = userRechargeOnlineMapper.getUserRechargeNum(map.get("sys_user_id").toString());
			//（4）保存线上充值支付宝支付订单记录
			Map<String, Object> record = new HashMap<>();
			record.put("order_no", order_no);//订单号
			record.put("recharge_type", map.get("recharge_type").toString());//充值类型
			record.put("account_type", map.get("account_type").toString());//充值账户类型
			record.put("user_id", map.get("sys_user_id").toString());//用户编号
			record.put("recharge_num", map.get("recharge_num").toString());//充值数量
			record.put("status", TypeStatusConstant.user_recharge_info_status_00);//状态：充值中，待支付
			record.put("is_first", count > 0 ? '0' : '1');
			record.put("channel_type", StringUtil.getMapValue(map, "channel_type"));
			record.put("cre_date", date);
			record.put("cre_time", time);
			num = userRechargeOnlineMapper.insertUserRechargeOnline(record);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OrderInfoConstant.ORDER_INFO_CODE_999286, OrderInfoConstant.ORDER_INFO_MSG_999286);
			}
			//（5）返回数据处理
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("recharge_url", jinShunResult.get("data"));
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, respondMap);
		}catch(Exception e) {
			LOGGER.error("UserRechargeServiceImpl -- userRechargeJinShun方法执行异常:"+ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999285, OrderInfoConstant.ORDER_INFO_MSG_999285);
		}finally {
			redisUtils.updateVersion(RedisNameVersionConstants.user_recharge_online_list_version+StringUtil.getMapValue(map, "sys_user_id"));
		}
	}

	/**
	 * 狂神支付
	 */
	public R userRechargeDementedGod(Map<String, Object> map) {
		int num = 0;
		try {
			//（1）用户线上支付宝充值功能是否开放
			String userRechargeChuanJunLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userRechargeChuanJunLock);
			if(!TypeStatusConstant.sys_function_lock_param_lock_1.equals(userRechargeChuanJunLock)) {
				return R.error(CommonCodeConstant.COMMON_CODE_999981, CommonCodeConstant.COMMON_MSG_999981);
			}
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			String order_no = StringUtil.getDateTimeAndRandomForID();
			R jinShunResult = dementedGodService.dealDementedGodTrade(StringUtil.getMapValue(map, "sys_user_id"), map.get("recharge_num").toString(),
					"01", order_no, date, time, StringUtil.getMapValue(map, "payType"),"123",StringUtil.getMapValue(map, "user_ip"));
			if(!Boolean.valueOf(jinShunResult.get(R.SUCCESS_TAG).toString())) {
				return jinShunResult;
			}
			//查询是否已充值
			int count = userRechargeOnlineMapper.getUserRechargeNum(map.get("sys_user_id").toString());
			//（4）保存线上充值支付宝支付订单记录
			Map<String, Object> record = new HashMap<>();
			record.put("order_no", order_no);//订单号
			record.put("recharge_type", map.get("recharge_type").toString());//充值类型
			record.put("account_type", map.get("account_type").toString());//充值账户类型
			record.put("user_id", map.get("sys_user_id").toString());//用户编号
			record.put("recharge_num", map.get("recharge_num").toString());//充值数量
			record.put("status", TypeStatusConstant.user_recharge_info_status_00);//状态：充值中，待支付
			record.put("is_first", count > 0 ? '0' : '1');
			record.put("channel_type", StringUtil.getMapValue(map, "channel_type"));
			record.put("cre_date", date);
			record.put("cre_time", time);
			num = userRechargeOnlineMapper.insertUserRechargeOnline(record);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(OrderInfoConstant.ORDER_INFO_CODE_999286, OrderInfoConstant.ORDER_INFO_MSG_999286);
			}
			//（5）返回数据处理
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("recharge_url", jinShunResult.get("data"));
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, respondMap);
		}catch(Exception e) {
			LOGGER.error("UserRechargeServiceImpl -- userRechargeDementedGod 方法执行异常:"+ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999285, OrderInfoConstant.ORDER_INFO_MSG_999285);
		}finally {
			redisUtils.updateVersion(RedisNameVersionConstants.user_recharge_online_list_version+StringUtil.getMapValue(map, "sys_user_id"));
		}
	}

	/**
	 * 用户线上充值APP端
	 */
	@Override
	@Transactional
	public R userRechargeOnLineApp(Map<String, Object> map) {
		//（1）线上充值基本信息校验
		R checkRechargeResult = this.checkUserRechargeOnLine(map);
		if(!Boolean.valueOf(checkRechargeResult.get(R.SUCCESS_TAG).toString())) {
			return checkRechargeResult;
		}
		//查询充值渠道
		Map<String, Object> channleMap = sysChannelMapper.getSysChannelById(StringUtil.getMapValue(map, "channel_id"));
		if(channleMap == null) {
			return R.error(CommonCodeConstant.COMMON_CODE_999953,CommonCodeConstant.COMMON_MSG_999953);
		}
		//（2）线上支付宝app支付
		if(TypeStatusConstant.user_recharge_online_channel_type_01.equals(channleMap.get("channel_type").toString())) {
			map.put("channel_type", channleMap.get("channel_type").toString());
			map.put("bankcode", StringUtil.getMapValue(channleMap, "product"));
			return this.userRechargeChuanJun(map);
		}else if(TypeStatusConstant.user_recharge_online_channel_type_02.equals(channleMap.get("channel_type").toString())){
			map.put("channel_type", channleMap.get("channel_type").toString());
			map.put("product", StringUtil.getMapValue(channleMap, "product"));
			return this.userRechargeJinShun(map);
		}else if(TypeStatusConstant.user_recharge_online_channel_type_03.equals(channleMap.get("channel_type").toString())){
			map.put("channel_type", channleMap.get("channel_type").toString());
			map.put("payType", StringUtil.getMapValue(channleMap, "product"));
			return this.userRechargeCike(map);
		}else if(TypeStatusConstant.user_recharge_online_channel_type_04.equals(channleMap.get("channel_type").toString())
				|| TypeStatusConstant.user_recharge_online_channel_type_05.equals(channleMap.get("channel_type").toString())
				|| TypeStatusConstant.user_recharge_online_channel_type_06.equals(channleMap.get("channel_type").toString())){
			LOGGER.info("****************************************余额充值：狂神的充值被调用******************************");
			map.put("channel_type", channleMap.get("channel_type").toString());
			map.put("payType", StringUtil.getMapValue(channleMap, "product"));
			LOGGER.info("参数：" + map.toString());
			return this.userRechargeDementedGod(map);
		}else {
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999289, OrderInfoConstant.ORDER_INFO_MSG_999289);
		}
	}
	
}

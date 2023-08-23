package com.example.longecological.service.wallet.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.constant.SysFunctionLockParamConstants;
import com.example.longecological.constant.SysParamCodeConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.TradeInfoCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.mapper.wallet.UserRollMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.common.SmsCodeService;
import com.example.longecological.service.common.SysFunctionLockParamService;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.wallet.WalletRollService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户互转相关
 * @author Administrator
 *
 */
@Service
public class WalletRollServiceImpl implements WalletRollService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WalletRollServiceImpl.class);
	
	@Autowired
	SmsCodeService smsCodeService;
	@Autowired
	SysParamService sysParamService;
	@Autowired
	SysFunctionLockParamService sysFunctionLockParamService;
	@Autowired
	VerifyUserService verifyUserService;
	
	
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	UserWalletMapper userWalletMapper;
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	UserRollMapper userRollMapper;

	
	/**
	 * 互转页面信息加载
	 */
	@Override
	public R getRollPageInfo(Map<String, Object> map) {
		try {
			//（1）查询用户信息
			Map<String, Object> userInfo = userInfoMapper.getUserWalletInfo(map.get("sys_user_id").toString());
			String rollMinNumber = null, rollMinCahrgeNum = null, rollSingleCahrgeRate = null;
			if(TypeStatusConstant.user_roll_log_type_01.equals(StringUtil.getMapValue(map, "roll_type"))) {
				//（2）//查询互转参数信息
				rollMinNumber = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_balanceRollMinNumber);
				rollMinCahrgeNum = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_balanceRollMinCahrgeNum);
				rollSingleCahrgeRate = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_balanceRollSingleCahrgeRate);
			}
			

			Map<String, Object> userRollInfo = new HashMap<>();
			userRollInfo.put("rollMinNumber", rollMinNumber);
			userRollInfo.put("rollMinCahrgeNum", rollMinCahrgeNum);
			userRollInfo.put("rollSingleCahrgeRate", rollSingleCahrgeRate);
			
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userInfo", userInfo);
			respondMap.put("userRollInfo", userRollInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	/**
	 * 用户申请互转
	 */
	@Override
	@Transactional
	public R userApplyRoll(Map<String, Object> map) {
		if(TypeStatusConstant.user_roll_log_type_01.equals(StringUtil.getMapValue(map, "roll_type"))) {
			return this.userApplyRollBalance(map);
		}else {
			return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
		}
	}


	/**
	 * 用户申请互转余额
	 * @param map
	 * @return
	 */
	private R userApplyRollBalance(Map<String, Object> map) {
		try {
			//（1）余额功能是否开放
			String userBalanceRollLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userBalanceRollLock);
			if(!TypeStatusConstant.sys_function_lock_param_lock_1.equals(userBalanceRollLock)) {
				return R.error(CommonCodeConstant.COMMON_CODE_999981, CommonCodeConstant.COMMON_MSG_999981);
			}
			//（2）校验基本参数信息
			R checkRollParamResult = this.checkRollParam(map);
			if(!Boolean.valueOf(checkRollParamResult.get(R.SUCCESS_TAG).toString())) {
				return checkRollParamResult;
			}
			//（3）转入人信息
			Map<String, Object> rollUserMap = (Map<String, Object>) checkRollParamResult.get("data");
			//（4）查询系统设置参数：手续费信息
			BigDecimal roll_num = new BigDecimal(map.get("roll_num").toString());//转账数量
			BigDecimal balanceRollMinNumber = new BigDecimal(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_balanceRollMinNumber));
			BigDecimal balanceRollMinCahrgeNum = new BigDecimal(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_balanceRollMinCahrgeNum));
			BigDecimal balanceRollSingleCahrgeRate = new BigDecimal(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_balanceRollSingleCahrgeRate));
			if(roll_num.compareTo(balanceRollMinNumber) < 0){
				return R.error(CommonCodeConstant.COMMON_CODE_999964, CommonCodeConstant.COMMON_MSG_999964);
			}
			BigDecimal single_rate_charge = roll_num.multiply(balanceRollSingleCahrgeRate);//单笔比例手续费
			BigDecimal single_min_charge = balanceRollMinCahrgeNum;//单笔固定手续费
			BigDecimal roll_charge = (single_rate_charge.compareTo(single_min_charge)>0) ? single_rate_charge : single_min_charge;//USDT转账手续费
			BigDecimal real_roll_in_num = roll_num.subtract(roll_charge).setScale(SysParamConstant.decimal_precision, BigDecimal.ROUND_DOWN);//用户实际到账数量
			//（5）订单信息
			int i=0;
			String order_id = StringUtil.getDateTimeAndRandomForID();//订单ID
			Map<String, Object> rollOrderMap = new HashMap<>();
			rollOrderMap.put("op_order_no", order_id);//订单号
			rollOrderMap.put("roll_type", TypeStatusConstant.user_roll_log_type_01);//转账类型：积分互转
			rollOrderMap.put("out_user_id", map.get("sys_user_id"));//转出用户ID
			rollOrderMap.put("in_user_id", rollUserMap.get("id"));//转入用户id
			rollOrderMap.put("in_user_account", map.get("in_user_account"));//转入用户账号
			rollOrderMap.put("roll_num", map.get("roll_num"));//转出数量
			rollOrderMap.put("roll_charge", roll_charge);//转出手续费
			rollOrderMap.put("charge_rate", single_rate_charge);//转账手续费比例
			rollOrderMap.put("charge_min", single_min_charge);//转账最低手续费
			rollOrderMap.put("up_date", TimeUtil.getDayString());//创建日期
			rollOrderMap.put("up_time", TimeUtil.getTimeString());//创建时间
			//（6）更新转出用户资产
			rollOrderMap.put("user_id", map.get("sys_user_id"));//用户ID
			rollOrderMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_11);//操作类型：积分转出
			rollOrderMap.put("balance_num", "-"+map.get("roll_num").toString());
			i = userWalletMapper.updateUserWalletNum(rollOrderMap);
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(TradeInfoCodeConstant.TRADE_INFO_CODE_999599,TradeInfoCodeConstant.TRADE_INFO_MSG_999599);
			}
			//（7）更新转入用户资产
			rollOrderMap.put("user_id", rollUserMap.get("id"));//用户ID
			rollOrderMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_12);//操作类型：积分转入
			rollOrderMap.put("balance_num", real_roll_in_num);
			i = userWalletMapper.updateUserWalletNum(rollOrderMap);
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(TradeInfoCodeConstant.TRADE_INFO_CODE_999598,TradeInfoCodeConstant.TRADE_INFO_MSG_999598);
			}
			//（8）记录转账订单
			i = userRollMapper.addUserRoll(rollOrderMap);
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(TradeInfoCodeConstant.TRADE_INFO_CODE_999598,TradeInfoCodeConstant.TRADE_INFO_MSG_999598);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("WalletRollServiceImpl -- userApplyRollScore方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
		}
	}
	
	/**
	 * 校验基本转账参数信息
	 * @param map
	 * @return
	 */
	private R checkRollParam(Map<String, Object> map) {
		//（1）输入数量校验
		if(!StringUtil.isValidLargeBigDecimal0(StringUtil.getMapValue(map, "roll_num"))){
			return R.error(CommonCodeConstant.COMMON_CODE_999982,CommonCodeConstant.COMMON_MSG_999982);
		}
		//（2）转账人信息校验
		Map<String, Object> rollUserMap = userInfoMapper.getUserInfoByUserAccount(map.get("in_user_account").toString());
		if(rollUserMap==null || map.get("sys_user_id").toString().equals(rollUserMap.get("id").toString())) {
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999781,UserInfoCodeConstant.USER_INFO_MSG_999781);
		}
		if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(rollUserMap, "status"))) {
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999780,UserInfoCodeConstant.USER_INFO_MSG_999780);
		}
		//（3）支付密码校验
		R checkSmsPassResult = verifyUserService.checUserPayPass(map);
		if(!Boolean.valueOf(checkSmsPassResult.get(R.SUCCESS_TAG).toString())) {
			return checkSmsPassResult;
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, rollUserMap);
	}

	
	/**
	 * 查询用户转账记录列表
	 */
	@Override
	public R getUserRollLogList(Map<String, Object> map) {
		try {
			//返回对象信息
			Map<String, Object> respondMap=new HashMap<>();
			List<Map<String, Object>> userRollLogList = new ArrayList<>();
			if(TypeStatusConstant.user_roll_log_select_roll_type_01.equals(StringUtil.getMapValue(map, "select_roll_type"))) {
				//转出
				userRollLogList = userRollMapper.getUserRollOutList(map);
			}else if(TypeStatusConstant.user_roll_log_select_roll_type_02.equals(StringUtil.getMapValue(map, "select_roll_type"))) {
				//转入
				userRollLogList = userRollMapper.getUserRollInList(map);
			}else {
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			respondMap.put("userRollLogList", userRollLogList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

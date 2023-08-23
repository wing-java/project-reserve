package com.example.longecological.service.wallet.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.constant.OperParamConstant;
import com.example.longecological.constant.SysFunctionLockParamConstants;
import com.example.longecological.constant.SysParamCodeConstants;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserAccountMapper;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.mapper.wallet.UserCashMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.common.SysFunctionLockParamService;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.wallet.WalletCashService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.ParamValidUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户账户相关
 * @author Administrator
 *
 */
@Service
public class WalletCashServiceImpl implements WalletCashService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WalletCashServiceImpl.class);
	
	@Autowired
	VerifyUserService verifyUserService;
	@Autowired
	SysParamService sysParamService;
	@Autowired
	SysFunctionLockParamService sysFunctionLockParamService;
	
	@Autowired
	UserAccountMapper userAccountMapper;
	@Autowired
	UserWalletMapper userWalletMapper;
	@Autowired
	UserCashMapper userCashMapper;
	@Autowired
	UserInfoMapper userInfoMapper;
	
	
	/**
	 * 用户提现页面信息加载
	 */
	@Override
	public R getCashPageInfo(Map<String, Object> map) {
		try {
			//（1）查询用户信息
			Map<String, Object> userInfo = userWalletMapper.getUserWalletInfo(map.get("sys_user_id").toString());
			//（2）//查询提现参数信息
			String userBalanceMinCashMoney = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_userBalanceMinCashMoney);//用户最小提现金额
			String userBalanceCashMinCahrgeNum = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_userBalanceCashMinCahrgeNum);//用户提现最低手续费
			String userBalanceCashSingleCahrgeRate = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_userBalanceCashSingleCahrgeRate);//用户提现手续费率
			Map<String, Object> userCashInfo = new HashMap<>();
			userCashInfo.put("minCashMoney", userBalanceMinCashMoney);
			userCashInfo.put("cashMinCahrgeNum", userBalanceCashMinCahrgeNum);
			userCashInfo.put("cashSingleCahrgeRate", userBalanceCashSingleCahrgeRate);
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userInfo", userInfo);
			respondMap.put("userCashInfo", userCashInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCashServiceImpl -- getCashPageInfo方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	/**
	 * 用户申请提现
	 */
	@Override
	@Transactional
	public R userApplyCash(Map<String, Object> map) {
		try {
			//（1）用户申请提现功能是否开放
			String userApplyCashLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userApplyCashLock);
			if(!TypeStatusConstant.sys_function_lock_param_lock_1.equals(userApplyCashLock)) {
				return R.error(CommonCodeConstant.COMMON_CODE_999981, CommonCodeConstant.COMMON_MSG_999981);
			}
			//（2）校验交易密码
			R checkPaypassword = verifyUserService.checUserPayPass(map);
			if(!Boolean.valueOf(checkPaypassword.get(R.SUCCESS_TAG).toString())) {
				return checkPaypassword;
			}
			//校验是否已提现
			int count = userCashMapper.getUserCashByDate(map.get("sys_user_id").toString(), TimeUtil.getDayString());
			if(count > 0) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999731, UserInfoCodeConstant.USER_INFO_MSG_999731);
			}
			//校验时间范围
			String cashDateRange = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_cashDateRange);
			LocalDateTime now = LocalDateTime.now();
			int hour = now.getHour();
			String[] cashDateRanges = cashDateRange.split("-");
			if(hour<Integer.parseInt(cashDateRanges[0]) || hour>=Integer.parseInt(cashDateRanges[1])) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999730, "当前时间提现暂未开放，提现时间"+cashDateRanges[0]+"点到"+cashDateRanges[1]+"点");
			}
			//校验实名
			Map<String, Object> userMap = userInfoMapper.getUserInfoById(map.get("sys_user_id").toString());
			if(!"09".equals(StringUtil.getMapValue(userMap, "auth_status"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999980,CommonCodeConstant.COMMON_MSG_999980);
			}
			if(new BigDecimal(StringUtil.getMapValue(userMap, "person_performance")).compareTo(BigDecimal.ZERO)<=0) {
				return R.error(CommonCodeConstant.COMMON_CODE_999954,CommonCodeConstant.COMMON_MSG_999954);
			}
			Map<String, Object> dataMap = new HashMap<>();
			
			//去掉市级代理银行卡提现限制
			Map<String, Object> accountMap = userAccountMapper.getUserAccountById(map);
			if(accountMap==null) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999754, UserInfoCodeConstant.USER_INFO_MSG_999754);
			}
			dataMap.put("cash_type", accountMap.get("type"));//提现类型
			dataMap.put("account", accountMap.get("account"));//卡号
			dataMap.put("account_name", accountMap.get("account_name"));//账户名
			dataMap.put("bank_name", accountMap.get("bank_name"));//开户行名称
//			dataMap.put("bank_branch_name", accountMap.get("bank_branch_name"));//支行名称
//			dataMap.put("account_img", accountMap.get("account_img"));//账户收款码
//			dataMap.put("legal_id_card", accountMap.get("legal_id_card"));//身份证号码
//			dataMap.put("legal_crad_photo", accountMap.get("legal_crad_photo"));//证件照
			
			//（4）前端必须把账户类型（余额）参数传过来
			R checkAccountTypeResult = ParamValidUtil.checkSpecifyParam(map, "account_type", OperParamConstant.USER_CASH_ACCOUNT_TYPE);
			if(!Boolean.valueOf(checkAccountTypeResult.get(R.SUCCESS_TAG).toString())) {
				return checkAccountTypeResult;
			}
			//（5）获取提现参数设置信息，并校验参数信息、计算相关金额
//			if(!RegexUtil.isValidInteger(StringUtil.getMapValue(map, "cash_money"))){
//				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999755, UserInfoCodeConstant.USER_INFO_MSG_999755);
//			}
			BigDecimal userMinCashMoney = BigDecimal.ZERO;
			BigDecimal userCashMinCahrgeNum = BigDecimal.ZERO;
			BigDecimal userCashSingleCahrgeRate = BigDecimal.ZERO;
			if(TypeStatusConstant.user_cash_record_account_type_01.equals(map.get("account_type").toString())) {
				userMinCashMoney = new BigDecimal(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_userBalanceMinCashMoney));//最小提现金额
				userCashMinCahrgeNum = new BigDecimal(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_userBalanceCashMinCahrgeNum));//用户提现最低手续费
				userCashSingleCahrgeRate = new BigDecimal(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_userBalanceCashSingleCahrgeRate));//用户提现手续费率
			}
			BigDecimal cash_money = new BigDecimal(map.get("cash_money").toString());//提现总金额
			if(cash_money.compareTo(userMinCashMoney) < 0){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999753, UserInfoCodeConstant.USER_INFO_MSG_999753);
			}
			BigDecimal single_rate_charge = cash_money.multiply(userCashSingleCahrgeRate);//单笔比例手续费
			BigDecimal single_min_charge = userCashMinCahrgeNum;//单笔固定手续费
			BigDecimal feet_money = (single_rate_charge.compareTo(single_min_charge)>0) ? single_rate_charge : single_min_charge;//USDT转账手续费
			BigDecimal arrival_money = cash_money.subtract(feet_money).setScale(2, BigDecimal.ROUND_DOWN);//到账数量
			//（7）更新用户提现账户
			int num=0;
			dataMap.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
			dataMap.put("balance_num", "-"+cash_money);//提现数量
			dataMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_07);//申请提现
			dataMap.put("op_order_no", StringUtil.getDateTimeAndRandomForID());
			dataMap.put("up_date", TimeUtil.getDayString());
			dataMap.put("up_time", TimeUtil.getTimeString());
			num = userWalletMapper.updateUserWalletNum(dataMap);
			if(num != 1){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999761, UserInfoCodeConstant.USER_INFO_MSG_999761);
			}
			//（8）记录订单记录
			dataMap.put("account_type", map.get("account_type"));//账户类型
			dataMap.put("cash_money", cash_money);//提现总额
			dataMap.put("feet_money", feet_money);//手续费金额
			dataMap.put("arrival_money", arrival_money);//到账金额
			dataMap.put("charge_rate", single_rate_charge);//转账手续费比例
			dataMap.put("charge_min", single_min_charge);//转账最低手续费
			dataMap.put("cash_status", TypeStatusConstant.user_cash_record_cash_status_00);//提现状态：待处理
			dataMap.put("cash_id", null);//提现主键
			num = userCashMapper.addUserCash(dataMap);
			if(num != 1){//进行回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999752, UserInfoCodeConstant.USER_INFO_MSG_999752);
			}
			//（9）记录提现记录详情
			dataMap.put("cash_detail_status", TypeStatusConstant.user_cash_record_cash_status_00);//提现状态：待处理
			dataMap.put("note", "申请提现");
			num = userCashMapper.addUserCashDetail(dataMap);
			if(num != 1){//进行回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999751, UserInfoCodeConstant.USER_INFO_MSG_999751);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserCashServiceImpl -- userApplyCash方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	
	/**
	 * 查询用户提现记录列表
	 */
	@Override
	public R getUserCashList(Map<String, Object> map) {
		try {
			List<Map<String, Object>> cashRecordList = userCashMapper.getUserCashList(map);
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("cashRecordList", cashRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCashServiceImpl -- getUserCashRecordList方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	/**
	 * 查询用户提现记录详情
	 */
	@Override
	public R getUserCashDetail(Map<String, Object> map) {
		try {
			Map<String, Object> cashRecordDetail = userCashMapper.getUserCashDetail(map);
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("cashRecordDetail", cashRecordDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserCashServiceImpl -- getUserCashDetail方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

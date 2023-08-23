package com.example.longecological.service.user.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.properties.QiniuProperties;
import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.constant.OperParamConstant;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysFunctionLockParamConstants;
import com.example.longecological.constant.SysParamCodeConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.VerifyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.async.AsyncUserInfoService;
import com.example.longecological.service.common.SmsCodeService;
import com.example.longecological.service.common.SysFunctionLockParamService;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.service.common.VerifyRecordService;
import com.example.longecological.service.common.VerifyTokenService;
import com.example.longecological.service.system.SysInfoCacheService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.service.user.UserLoginService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.ip.IpUtils;
import com.example.longecological.utils.string.ParamValidUtil;
import com.example.longecological.utils.string.RegexUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户登录注册相关
 * @author Administrator
 *
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginServiceImpl.class);
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	SysParamService sysParamService;
	@Autowired
	SysFunctionLockParamService sysFunctionLockParamService;
	@Autowired
	VerifyTokenService verifyTokenService;
	@Autowired
	VerifyRecordService verifyRecordService;
	@Autowired
	UserInfoCacheService userInfoCacheService;
	@Autowired
	SysInfoCacheService sysInfoCacheService;
	@Autowired
	SmsCodeService smsCodeService;
	@Autowired
	AsyncUserInfoService asyncUserLoginLogService;
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	QiniuProperties qiniuProperties;
	@Autowired
	UserWalletMapper userWalletMapper;

	/**
	 * 用户登录（APP端）
	 */
	@Override
	public R userLogin(Map<String, Object> dataMap, HttpServletRequest request) {
		try {
			//（2）校验登录类型
			R checkParamResult = ParamValidUtil.checkSpecifyParam(dataMap, "login_type", OperParamConstant.USER_LOGIN_TYPE);
			if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
				return checkParamResult;
			}
			//（3）登录操作
			//用户信息对象
			Map<String, Object> userMap = new HashMap<>();
			//账号登录
			if(OperParamConstant.USER_LOGIN_TYPE_ACCOUNT.equals(dataMap.get("login_type").toString())) {
				//（1）账号信息校验
				userMap = userInfoMapper.getUserInfoByUserAccount(StringUtil.getMapValue(dataMap, "sys_user_account"));
				if(userMap==null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999798,"您的账号不存在");
				}
				//（2）判断密码信息
				String login_password = new Md5Hash(StringUtil.getMapValue(userMap, "cre_date")+StringUtil.getMapValue(userMap, "cre_time"), StringUtil.getMapValue(dataMap, "login_password").toUpperCase(), SysParamConstant.passNum).toString();
				if(!login_password.equals(StringUtil.getMapValue(userMap, "login_password"))) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999798,UserInfoCodeConstant.USER_INFO_MSG_999798);
				}
				//(3)账号是否冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))) {
					return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
				}
				//（4）生成新的token
				userMap.put("token", verifyTokenService.setToken(userMap.get("id").toString()));
			}else if(OperParamConstant.USER_LOGIN_TYPE_TOKEN.equals(dataMap.get("login_type").toString())) {
				//token登录
				//（1）校验token
				R checkTokenResult = verifyTokenService.isToken(dataMap.get("token").toString());
				if(!Boolean.valueOf(checkTokenResult.get(R.SUCCESS_TAG).toString())) {
					return checkTokenResult;
				}
				//（2）校验账户信息（ID是否存在，账户是否冻结）
				userMap = userInfoMapper.getUserInfoById(dataMap.get("token").toString().split("\\|")[0]);
				if (userMap==null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999798,UserInfoCodeConstant.USER_INFO_MSG_999798);
				}
				//(3)账号是否冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))) {
					return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
				}
				userMap.put("token", dataMap.get("token").toString());
			}else {
				//短信验证码登录
				//（1）账号信息校验
				userMap = userInfoMapper.getUserInfoByUserAccount(StringUtil.getMapValue(dataMap, "sys_user_account"));
				if(userMap==null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999799,UserInfoCodeConstant.USER_INFO_MSG_999799);
				}
				//（2）账号是否冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))) {
					return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
				}
				//（3）校验短信验证码
				R checkSmsResult = verifyRecordService.compare(userMap.get("id").toString(),userMap.get("sys_user_account").toString(), VerifyConstant.BusType_FrontUserLogin, userMap.get("register_type").toString(), userMap.get("user_tel").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
				if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
					return checkSmsResult;
				}
				//（4）生成新的token
				userMap.put("token", verifyTokenService.setToken(userMap.get("id").toString()));
			}
			//（5）校验设备号
			dataMap.put("user_id", userMap.get("id"));
			//（4）异步记录登录日志
	        dataMap.put("ipaddr", IpUtils.getIpAddr(request));
			asyncUserLoginLogService.addUserLoginLog(dataMap, request);
	        
	        //查询参数信息
	        String webRegisterLink = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_webRegisterLink);
			//（5）返回数据
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("sys_time", new Date().getTime());//系统时间戳
			respondMap.put("qiniu_domain",qiniuProperties.getQiniu_domain());//七牛域名
			respondMap.put("qr_code_url", webRegisterLink+"?invite_code="+userMap.get("uid2"));//WEB端注册链接
			respondMap.put("token",StringUtil.getMapValue(userMap, "token"));//token
			respondMap.put("user_id",StringUtil.getMapValue(userMap, "id"));//用户编号
			respondMap.put("user_uid",StringUtil.getMapValue(userMap, "uid"));
			respondMap.put("sys_user_account",StringUtil.getMapValue(userMap, "sys_user_account"));//用户账号
			respondMap.put("head_photo",StringUtil.getMapValue(userMap, "head_photo"));//用户头像
			respondMap.put("nick_name",StringUtil.getMapValue(userMap, "nick_name"));//昵称
			respondMap.put("uid",StringUtil.getMapValue(userMap, "uid"));//邀请码uid
			respondMap.put("pay_password", StringUtils.isEmpty(StringUtil.getMapValue(userMap, "pay_password"))?"":"99999999999999");//支付密码数据返回处理，不返回正确密码
			respondMap.put("user_tel",StringUtil.getMapValue(userMap, "user_tel"));//手机号
			respondMap.put("head_photo",StringUtil.getMapValue(userMap, "head_photo"));//头像
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, respondMap);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- userLoginOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	
	/**
	 * 用户注册
	 */
	@Override
	@Transactional
	public R userRegister(Map<String, Object> dataMap, HttpServletRequest request) {
		//（1）注册信息校验
		R checkRegisterResult = this.checkUserRegister(dataMap, request);
		if(!Boolean.valueOf(checkRegisterResult.get(R.SUCCESS_TAG).toString())) {
			return checkRegisterResult;
		}
		//（2）注册操作
		return this.userRegisterOper(dataMap);
	}
	/**
	 * 校验用户注册信息
	 * @param dataMap
	 * @return
	 */
	private R checkUserRegister(Map<String, Object> dataMap, HttpServletRequest request) {
		try {
			//（1）注册账号类型校验
			if(RegexUtil.isValidInteger(StringUtil.getMapValue(dataMap, "user_account"))) {
				//手机号校验
				if(!RegexUtil.isValidTelFirst(StringUtil.getMapValue(dataMap, "user_account"))) {
					return R.error(CommonCodeConstant.COMMON_CODE_999987, "请输入正确的手机号");
				}
				//系统参数：一个手机号注册人数
			    Integer telRegisterNum = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_telRegisterNum));
				//查询该手机号注册人数
			    Integer telHaveRegisterNum = userInfoMapper.getRegisterNumByUserTel(dataMap.get("user_account").toString());
				if(telHaveRegisterNum+1 > telRegisterNum) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999779,UserInfoCodeConstant.USER_INFO_MSG_999779);
				}
				dataMap.put("register_type", TypeStatusConstant.user_info_register_type_1);//注册类型：手机号
				dataMap.put("user_tel", dataMap.get("user_account"));//手机号
				dataMap.put("user_email", null);//邮箱
			}else if(StringUtil.containString(StringUtil.getMapValue(dataMap, "user_account"), "@")) {
				//系统参数：一个邮箱注册人数
			    Integer emailRegisterNum = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_emailRegisterNum));
				//查询该手机号注册人数
			    Integer emailHaveRegisterNum = userInfoMapper.getRegisterNumByUserEmail(dataMap.get("user_account").toString());
				if(emailHaveRegisterNum+1 > emailRegisterNum) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999778,UserInfoCodeConstant.USER_INFO_MSG_999778);
				}
				dataMap.put("register_type", TypeStatusConstant.user_info_register_type_2);//注册类型：邮箱
				dataMap.put("user_tel", null);//手机号
				dataMap.put("user_email", dataMap.get("user_account"));//邮箱
			}else {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999779,UserInfoCodeConstant.USER_INFO_MSG_999779);
			}
			//校验用户昵称
			/*
			if(StringUtil.isEmpty(StringUtil.getMapValue(dataMap, "nick_name"))) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999748,UserInfoCodeConstant.USER_INFO_MSG_999748);
			}
			if(StringUtil.getMapValue(dataMap, "nick_name").length()>11) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999774,UserInfoCodeConstant.USER_INFO_MSG_999774);
			}*/
			//==================校验短信验证码===============
			/*
			R checkSmsResult = verifyRecordService.compare(null, null, VerifyConstant.BusType_FrontRegister, dataMap.get("register_type").toString(), dataMap.get("user_account").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			*/
			if(StringUtils.isNotEmpty(StringUtil.getMapValue(dataMap, "invite_code"))){
				//（5）邀请人信息是否存在
				Map<String, Object> referUserInfo = userInfoMapper.getUserInfoByUid(dataMap.get("invite_code").toString());
				if (referUserInfo == null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999790,UserInfoCodeConstant.USER_INFO_MSG_999790);
				} 
				//(6)邀请人是否被冻结
				if(!TypeStatusConstant.user_info_status_0.equals(referUserInfo.get("status").toString())) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999789,UserInfoCodeConstant.USER_INFO_MSG_999789);
				}
				//记录自己的代数和父级链
				dataMap.put("referer_id", referUserInfo.get("id").toString());//存的是前台用户的ID
				dataMap.put("algebra", Integer.parseInt(referUserInfo.get("algebra").toString())+1);//代数
				if(!StringUtil.isEmpty(StringUtil.getMapValue(referUserInfo, "parent_chain"))) {
					dataMap.put("parent_chain", referUserInfo.get("parent_chain").toString()+","+referUserInfo.get("id").toString());//父级链
				}else {
					dataMap.put("parent_chain", referUserInfo.get("id").toString());//父级链
				}
			}else {
				dataMap.put("referer_id", null);//存的是前台用户的ID
			}
			String cre_date = TimeUtil.getDayString();
			String cre_time = TimeUtil.getTimeString();
			dataMap.put("cre_date", cre_date);//注册日期
			dataMap.put("cre_time", cre_time);//注册时间
			dataMap.put("login_password", StringUtils.isNoneEmpty(StringUtil.getMapValue(dataMap, "login_password")) ? new Md5Hash(cre_date+cre_time, dataMap.get("login_password").toString().toUpperCase(), SysParamConstant.passNum).toString() : null);//登录密码
			dataMap.put("pay_password", StringUtils.isNoneEmpty(StringUtil.getMapValue(dataMap, "pay_password")) ? new Md5Hash(cre_date+cre_time, dataMap.get("pay_password").toString().toUpperCase(), SysParamConstant.passNum).toString() : null);//支付密码
			dataMap.put("sys_user_account", dataMap.get("user_account").toString());//账号（单账号才用）
			dataMap.put("nick_name", "用户"+dataMap.get("user_account").toString().substring(7));
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- checkUserRegister方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999788,UserInfoCodeConstant.USER_INFO_MSG_999788);
		}
	}
	/**
	 * 用户注册操作
	 * @param dataMap
	 * @return
	 */
	private R userRegisterOper(Map<String, Object> dataMap) {
		int num=0;
		try {
			if(StringUtils.isNotEmpty(StringUtil.getMapValue(dataMap, "referer_id"))) {
				//（1）更新所有父级的伞下人数
				num = userInfoMapper.updateParentUnderNum(dataMap);
				if(num < 1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999787,UserInfoCodeConstant.USER_INFO_MSG_999787);
				}
				//（3）更新父级直推人数和等级信息
				num = userInfoMapper.updateUserReferNum(dataMap);
				if(num != 1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999786,UserInfoCodeConstant.USER_INFO_MSG_999786);
				}
			}
			//（4）保存用户信息
			dataMap.put("id",null);//可用来记录用户返回主键位置
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- userRegisterOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
		
		//第一次注册失败
	    R addUserResult_1 = this.addUserInfo(dataMap);
	    if(!Boolean.valueOf(addUserResult_1.get(R.SUCCESS_TAG).toString())) {
	    	//第二次注册失败
	    	R addUserResult_2 = this.addUserInfo(dataMap);
	    	 if(!Boolean.valueOf(addUserResult_2.get(R.SUCCESS_TAG).toString())) {
	 	    	//第三次注册
	 	    	R addUserResult_3 = this.addUserInfo(dataMap);
	 	    	if(!Boolean.valueOf(addUserResult_3.get(R.SUCCESS_TAG).toString())) {
	 	    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		 	    	return addUserResult_3;
		 		}
	 		}
		}
	    
	    try {
	    	
	    	//添加钱包
		    userInfoMapper.addUserWallet(dataMap.get("id").toString());
		    //添加总流水
		    userInfoMapper.addSummaryUserBenefit(dataMap.get("id").toString());
		    //赠送现金
		    String userRegisterGiveMoneyLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_userRegisterGiveMoneyLock);
		    if("1".equals(userRegisterGiveMoneyLock)) {
		    	String userRegisterGiveMoney = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_userRegisterGiveMoney);
		    	if(new BigDecimal(userRegisterGiveMoney).compareTo(BigDecimal.ZERO)>0) {
		    		Map<String, Object> edit_user = new HashMap<>();
					edit_user.put("user_id", dataMap.get("id").toString());//用户编号
					edit_user.put("op_order_no", StringUtil.getDateTimeAndRandomForID());//订单号
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_18);//操作类型：购买产品
					edit_user.put("balance_num", userRegisterGiveMoney);//支付余额账户
					edit_user.put("up_date", dataMap.get("cre_date"));//更新日期
					edit_user.put("up_time", dataMap.get("cre_time"));//更新时间
					int i = userWalletMapper.updateUserWalletNum(edit_user);
					if(i != 1){
						throw new Exception("用户余额更新异常");
					}
		    	}
		    }
		    
			//用户信息返回
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("token", verifyTokenService.setToken(dataMap.get("id").toString()));//原生返回信息
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("UserInfoServiceImpl -- userRegisterOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	/**
	 * 生成用户账号并且保存用户信息
	 * @param dataMap
	 * @return
	 */
	private R addUserInfo(Map<String, Object> dataMap) {
		try {
			dataMap.put("uid", StringUtil.getIntegerRandom(6));
			dataMap.put("uid2", StringUtil.getCode(8));
			int i = userInfoMapper.saveUserInfo(dataMap);
			if(i != 1){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999785,UserInfoCodeConstant.USER_INFO_MSG_999785);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		} 
	}


	/**
	 * 用户退出登录
	 */
	@Override
	public R userLogOut(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "token"))) {
				return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
			}
			R checkTokenResult = verifyTokenService.isToken(map.get("token").toString());
			if(Boolean.valueOf(checkTokenResult.get(R.SUCCESS_TAG).toString())) {
				verifyTokenService.deleteToken(map.get("token").toString());
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- userLogOutOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 用户忘记密码
	 */
	@Override
	@Transactional
	public R userForgetPass(Map<String, Object> dataMap) {
		try {
			/*//（1）MD5签名验证
			R signCheckResult = SignUtil.checkSign(dataMap, SysSecurityKeyConstant.md5Key_app_inter);
			if(!Boolean.valueOf(signCheckResult.get(R.SUCCESS_TAG).toString())) {
				return signCheckResult;
			}*/
			//（2）账号类型校验
			if(RegexUtil.isValidTelFirst(StringUtil.getMapValue(dataMap, "user_account"))) {
				dataMap.put("register_type", TypeStatusConstant.user_info_register_type_1);//注册类型：手机号
			}else if(StringUtil.containString(StringUtil.getMapValue(dataMap, "user_account"), "@")) {
				dataMap.put("register_type", TypeStatusConstant.user_info_register_type_2);//注册类型：邮箱
			}else {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999792,UserInfoCodeConstant.USER_INFO_MSG_999792);
			}
			dataMap.put("bus_type", VerifyConstant.BusType_FrontForgetPass);
			Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccountTel(dataMap);
			if(userMap==null) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999799,UserInfoCodeConstant.USER_INFO_MSG_999799);
			}
			//如果验证类型不为空，则说明不是初次绑定，只能发送短信到绑定账户
			if(StringUtils.isNotEmpty(StringUtil.getMapValue(userMap, "register_type"))) {
				//验证类型为：手机验证
				if(TypeStatusConstant.user_info_register_type_1.equals(userMap.get("register_type").toString())) {
					if(!dataMap.get("user_account").toString().equals(userMap.get("user_tel").toString())) {
						return R.error(UserInfoCodeConstant.USER_INFO_CODE_999799,UserInfoCodeConstant.USER_INFO_MSG_999799);
					}
				}else if(TypeStatusConstant.user_info_register_type_2.equals(userMap.get("register_type").toString())) {
					//验证类型：邮箱
					if(!dataMap.get("user_account").toString().equals(userMap.get("user_email").toString())) {
						return R.error(UserInfoCodeConstant.USER_INFO_CODE_999799,UserInfoCodeConstant.USER_INFO_MSG_999799);
					}
				}else {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999799,UserInfoCodeConstant.USER_INFO_MSG_999799);
				}
			}
			//（3）校验短信验证码
			R checkSmsResult = verifyRecordService.compare(userMap.get("id").toString(), userMap.get("sys_user_account").toString(), VerifyConstant.BusType_FrontForgetPass, dataMap.get("register_type").toString(), dataMap.get("user_account").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			//（4）重置登录密码并锁定绑定注册类型
			dataMap.put("sys_user_id", userMap.get("id"));
			dataMap.put("login_password", new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("login_password").toString().toUpperCase(), SysParamConstant.passNum).toString());//密码
			//如果register_type为空则是迁移过来的数据，是初次进行忘记密码操作，绑定验证类型
			if(StringUtils.isEmpty(StringUtil.getMapValue(userMap, "register_type"))) {
				int i = userInfoMapper.updateUserLoginPassRegisterType(dataMap);
				if(i != 1) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999784,UserInfoCodeConstant.USER_INFO_MSG_999784);
				}
			}else {
				//否则不是第一次操作，不能更改验证类型，只能修改登录密码
				int i = userInfoMapper.updateUserLoginPass(dataMap);
				if(i != 1) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999784,UserInfoCodeConstant.USER_INFO_MSG_999784);
				}
			}
			
			//（5）删除缓存
			redisUtils.remove(RedisNameConstants.user_info_id+dataMap.get("sys_user_id").toString());
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}



	/**
	 * 用户注册第一步
	 */
	@Override
	public R userRegisterFirst(Map<String, Object> dataMap, HttpServletRequest request) {
		//（1）注册信息校验
		R checkRegisterResult = this.checkUserRegister(dataMap, request);
		if(!Boolean.valueOf(checkRegisterResult.get(R.SUCCESS_TAG).toString())) {
			return checkRegisterResult;
		}
		try {
			//（2）返回校验通过的唯一标识并将注册信息存入redis
			String valid_flag = StringUtil.getDateTimeAndRandomForID();//随机数ID（字母数字混合）
			//String redis_valid_flag_key = RedisNameConstants.user_register_first_valid_flag+dataMap.get("sys_user_account");//多账号
			String redis_valid_flag_key = RedisNameConstants.user_register_first_valid_flag+dataMap.get("user_account");//单账号
			Map<String, Object> redisObject = new HashMap<>();
			redisObject.put("valid_flag", valid_flag);
			redisObject.put("user_data", dataMap);
			redisUtils.set(redis_valid_flag_key, redisObject, SysParamConstant.verification_code_seconds);//存入redis
			//（3）返回标识信息
			Map<String, Object> responMap = new HashMap<>();
			responMap.put("valid_flag", valid_flag);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999,responMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 用户注册第二步
	 */
	@Override
	@Transactional
	public R userRegisterSecond(Map<String, Object> dataMap) {
		Map<String, Object> user_data;
		try {
			//（1）MD5签名验证
			/*R signCheckResult = SignUtil.checkSign(dataMap, SysSecurityKeyConstant.md5Key_app_inter);
			if(!Boolean.valueOf(signCheckResult.get(R.SUCCESS_TAG).toString())) {
				return signCheckResult;
			}*/
			//（2）返回的标识信息校验
			//String redis_valid_flag_key = RedisNameConstants.user_register_first_valid_flag+dataMap.get("sys_user_account").toString();//多账号
			String redis_valid_flag_key = RedisNameConstants.user_register_first_valid_flag+dataMap.get("user_account");//单账号
			Map<String, Object> redisObject = (Map<String, Object>) redisUtils.get(redis_valid_flag_key);
			if(redisObject == null){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999756,UserInfoCodeConstant.USER_INFO_MSG_999756);
			}
			String valid_flag = redisObject.get("valid_flag").toString();
			user_data = (Map<String, Object>) redisObject.get("user_data");
			if(!valid_flag.toString().toUpperCase().equals(StringUtil.getMapValue(dataMap, "valid_flag").toUpperCase())){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999775,UserInfoCodeConstant.USER_INFO_MSG_999775);
			}
			//（3）用户信息
			String cre_date = user_data.get("cre_date").toString();
			String cre_time = user_data.get("cre_time").toString();
			user_data.put("login_password", StringUtils.isNoneEmpty(StringUtil.getMapValue(dataMap, "login_password")) ? new Md5Hash(cre_date+cre_time, dataMap.get("login_password").toString(), SysParamConstant.passNum).toString() : null);//登录密码
			user_data.put("pay_password", StringUtils.isNoneEmpty(StringUtil.getMapValue(dataMap, "pay_password")) ? new Md5Hash(cre_date+cre_time, dataMap.get("pay_password").toString(), SysParamConstant.passNum).toString() : null);//支付密码
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
		//注册操作
		return this.userRegisterOper(user_data);
	}

}

package com.example.longecological.service.common.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.MsgTemplateConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.VerifyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.ImgCodeService;
import com.example.longecological.service.common.SmsCodeService;
import com.example.longecological.service.common.VerifyRecordService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.string.RegexUtil;
import com.example.longecological.utils.string.StringUtil;


/**
 * 发送和比较短信验证码
 * @author Administrator
 *
 */
@Service
public class SmsCodeServiceImpl implements SmsCodeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsCodeServiceImpl.class);
	
	@Autowired
	ImgCodeService imgCodeService;
	@Autowired
	VerifyRecordService verifyRecordService;
	
	@Autowired
	UserInfoCacheService userInfoCacheService;
	@Autowired
	UserInfoMapper userInfoMapper;
	
	
	@Autowired
	RedisUtils redisUtils;
	

	/**
	 * 短信验证码发送=====》整体仅仅rsa加密（适用于注册和忘记密码）
	 */
	@Override
	public R sendSmsCodeOnly(Map<String, Object> dataMap) {
		//基本参数信息校验
		R checkParamResult = this.checkSendSmsCodeOnlyParam(dataMap);
		if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
			return checkParamResult;
		}
		//发送短信验证码
		if(StringUtils.isEmpty(StringUtil.getMapValue(dataMap, "sys_user_id"))) {
			return verifyRecordService.transmit(null,null,dataMap.get("bus_type").toString(), 
					dataMap.get("register_type").toString(), dataMap.get("user_account").toString(), VerifyConstant.SystemFront,MsgTemplateConstants.COMMON_TIP);
		}else {
			return verifyRecordService.transmit(dataMap.get("sys_user_id").toString(),dataMap.get("sys_user_account").toString(),dataMap.get("bus_type").toString(), 
					dataMap.get("register_type").toString(), dataMap.get("user_account").toString(), VerifyConstant.SystemFront,MsgTemplateConstants.COMMON_TIP);
		}
	}


	/**
	 * 校验短信验证码发送的参数====》适用于注册和忘记密码
	 * @param map
	 * @return
	 */
	private R checkSendSmsCodeOnlyParam(Map<String, Object> dataMap) {
		try {
			//设备类型验证
			if(RegexUtil.isValidTelFirst(StringUtil.getMapValue(dataMap, "user_account"))) {
				dataMap.put("register_type", VerifyConstant.MobileAccType);//设备类型：手机号
			}else if(StringUtil.containString(StringUtil.getMapValue(dataMap, "user_account"), "@")) {
				dataMap.put("register_type", VerifyConstant.EmailAccType);//设备类型：邮箱
			}else {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999877,MsgImgCodeConstant.MESSAGE_MSG_999877);
			}
			if(VerifyConstant.BusType_FrontRegister.equals(dataMap.get("bus_type").toString())) {
				//校验手机号信息（注册类型：账号不能存在）
				Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("user_account").toString());
				//注册类型：账号已存在
				if(userMap!=null) {
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999876,MsgImgCodeConstant.MESSAGE_MSG_999876);
				}
			}else if(VerifyConstant.BusType_FrontForgetPass.equals(dataMap.get("bus_type").toString())){
				//校验手机号信息
				dataMap.put("sys_user_account", dataMap.get("user_account").toString());
				Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccountTel(dataMap);
				//忘记密码类型：账号必须存在
				if(userMap==null) {
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999875,MsgImgCodeConstant.MESSAGE_MSG_999875);
				}
				//如果验证类型不为空，则说明不是初次绑定，只能发送短信到绑定账户
				if(StringUtils.isNotEmpty(StringUtil.getMapValue(userMap, "register_type"))) {
					//验证类型为：手机验证
					if(TypeStatusConstant.user_info_register_type_1.equals(userMap.get("register_type").toString())) {
						if(!dataMap.get("user_account").toString().equals(userMap.get("user_tel").toString())) {
							return R.error(MsgImgCodeConstant.MESSAGE_CODE_999877,MsgImgCodeConstant.MESSAGE_MSG_999877);
						}
					}else if(TypeStatusConstant.user_info_register_type_2.equals(userMap.get("register_type").toString())) {
						//验证类型：邮箱
						if(!dataMap.get("user_account").toString().equals(userMap.get("user_email").toString())) {
							return R.error(MsgImgCodeConstant.MESSAGE_CODE_999877,MsgImgCodeConstant.MESSAGE_MSG_999877);
						}
					}else {
						return R.error(MsgImgCodeConstant.MESSAGE_CODE_999877,MsgImgCodeConstant.MESSAGE_MSG_999877);
					}
				}
				//账号存在，但已被冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))){
					return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
				}
				dataMap.put("sys_user_id", userMap.get("id"));
				dataMap.put("sys_user_account", userMap.get("sys_user_account"));
			}else if(VerifyConstant.BusType_FrontUserLogin.equals(dataMap.get("bus_type").toString())){
				//校验手机号信息（注册类型：账号必须存在）
				Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("user_account").toString());
				//注册类型：账号不存在
				if(userMap==null) {
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999875,MsgImgCodeConstant.MESSAGE_MSG_999875);
				}
				//账号存在，但已被冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))){
					return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
				}
				dataMap.put("sys_user_id", userMap.get("id"));
				dataMap.put("sys_user_account", userMap.get("sys_user_account"));
			}else {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999872,MsgImgCodeConstant.MESSAGE_MSG_999872);
			}
			//校验图形验证码
			R checkResult = imgCodeService.checkImgCode(dataMap);
			if(!Boolean.valueOf(checkResult.get(R.SUCCESS_TAG).toString())) {
				return checkResult;
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 短信验证码发送=====》整体rsa加密+token验证（适用于修改登录密码和交易密码）
	 */
	@Override
	public R sendSmsCodeToken(Map<String, Object> dataMap) {
		//基本参数信息校验
		R checkParamResult = this.checkSendSmsCodeTokenParam(dataMap);
		if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
			return checkParamResult;
		}
		return verifyRecordService.transmit(dataMap.get("sys_user_id").toString(),dataMap.get("sys_user_account").toString(),dataMap.get("bus_type").toString(), 
				dataMap.get("register_type").toString(), dataMap.get("user_account").toString(), VerifyConstant.SystemFront,MsgTemplateConstants.COMMON_TIP);
	}


	/**
	 * 短信验证码发送基本参数校验
	 * @param dataMap
	 * @return
	 */
	private R checkSendSmsCodeTokenParam(Map<String, Object> dataMap) {
		System.out.println("参数信息====="+dataMap);
		try {
			//（1）业务类型校验
			if(StringUtils.isEmpty(StringUtil.getMapValue(dataMap, "bus_type"))) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999874,MsgImgCodeConstant.MESSAGE_MSG_999874);
			}
			//（2）校验图形验证码
			R checkResult = imgCodeService.checkImgCode(dataMap);
			if(!Boolean.valueOf(checkResult.get(R.SUCCESS_TAG).toString())) {
				return checkResult;
			}
			//（3）根据用户id查询详情
			Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
			if(userMap==null) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
			}
			//账号存在，但已被冻结
			if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))){
				return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
			}
			dataMap.put("register_type", userMap.get("register_type").toString());
			dataMap.put("sys_user_account", userMap.get("sys_user_account").toString());
			//手机号注册
			if(VerifyConstant.MobileAccType.equals(dataMap.get("register_type").toString())) {
				dataMap.put("user_account", userMap.get("user_tel").toString());
			}else {
				//邮箱注册
				dataMap.put("user_account", userMap.get("user_email").toString());
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	
	/**
	 * 短信验证码发送=====》整体rsa加密+token验证=====》只能发送到传入手机号
	 */
	@Override
	public R sendSmsCodeAccept(Map<String, Object> dataMap) {
		//基本参数信息校验
		R checkParamResult = this.checkSendSmsCodeAcceptParam(dataMap);
		if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
			return checkParamResult;
		}
		return verifyRecordService.transmit(dataMap.get("sys_user_id").toString(),dataMap.get("sys_user_account").toString(),dataMap.get("bus_type").toString(), 
				dataMap.get("register_type").toString(), dataMap.get("user_account").toString(), VerifyConstant.SystemFront,MsgTemplateConstants.COMMON_TIP);
	}

	
	/**
	 * 校验短信验证码发送的参数====》适用于注册和忘记密码
	 * @param map
	 * @return
	 */
	private R checkSendSmsCodeAcceptParam(Map<String, Object> dataMap) {
		//（1）设备类型验证
		if(RegexUtil.isValidTelFirst(StringUtil.getMapValue(dataMap, "user_account"))) {
			dataMap.put("register_type", VerifyConstant.MobileAccType);//设备类型：手机号
		}else if(StringUtil.containString(StringUtil.getMapValue(dataMap, "user_account"), "@")) {
			dataMap.put("register_type", VerifyConstant.EmailAccType);//设备类型：邮箱
		}else {
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999877,MsgImgCodeConstant.MESSAGE_MSG_999877);
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(dataMap, "bus_type"))) {
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999874,MsgImgCodeConstant.MESSAGE_MSG_999874);
		}
		//（2）根据用户id查询详情
		Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
		if(userMap==null) {
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
		}
		//（3）账号存在，但已被冻结
		if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))){
			return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
		}
		dataMap.put("sys_user_account", userMap.get("sys_user_account").toString());
		//校验图形验证码
		R checkResult = imgCodeService.checkImgCode(dataMap);
		if(!Boolean.valueOf(checkResult.get(R.SUCCESS_TAG).toString())) {
			return checkResult;
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999999);
	}

	
	/**
	 * 校验短信验证码（验证码接收的必须是当前登录绑定账号）
	 */
	@Override
	public R checkSmsCode(Map<String, Object> dataMap) {
		//（1）查询用户信息
		Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
		if(userMap==null) {
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
		}
		dataMap.put("cre_date", userMap.get("cre_date"));
		dataMap.put("cre_time", userMap.get("cre_time"));
		//（2）校验短信验证码
		//手机号注册
		if(VerifyConstant.MobileAccType.equals(userMap.get("register_type").toString())) {
			return verifyRecordService.compare(userMap.get("id").toString(),userMap.get("sys_user_account").toString(), dataMap.get("bus_type").toString(), userMap.get("register_type").toString(), userMap.get("user_tel").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
		}else {
			//邮箱注册
			return verifyRecordService.compare(userMap.get("id").toString(),userMap.get("sys_user_account").toString(), dataMap.get("bus_type").toString(), userMap.get("register_type").toString(), userMap.get("user_email").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
		}
	}


	/**
	 * 校验短信验证码和支付密码
	 */
	@Override
	public R checkSmsCodePayPass(Map<String, Object> dataMap) {
		//（1）查询用户信息
		Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
		if(userMap==null) {
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
		}
		//（2）校验用户交易密码
		if(StringUtils.isEmpty(StringUtil.getMapValue(userMap, "pay_password"))) {
			return R.error(CommonCodeConstant.COMMON_CODE_999986,CommonCodeConstant.COMMON_MSG_999986);
		}
		String pay_password = new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("pay_password").toString(), SysParamConstant.passNum).toString();
		if(!pay_password.equals(userMap.get("pay_password").toString())) {
			return R.error(CommonCodeConstant.COMMON_CODE_999985,CommonCodeConstant.COMMON_MSG_999985);
		}
		//（3）校验短信验证码
		//手机号注册
		if(VerifyConstant.MobileAccType.equals(userMap.get("register_type").toString())) {
			return verifyRecordService.compare(userMap.get("id").toString(), userMap.get("sys_user_account").toString(), dataMap.get("bus_type").toString(), userMap.get("register_type").toString(), userMap.get("user_tel").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
		}else {
			//邮箱注册
			return verifyRecordService.compare(userMap.get("id").toString(), userMap.get("sys_user_account").toString(), dataMap.get("bus_type").toString(), userMap.get("register_type").toString(), userMap.get("user_email").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
		}
	}
	
	
}

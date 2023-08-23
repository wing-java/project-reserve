package com.example.longecological.service.common.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.StringUtil;

@Service
public class VerifyUserServiceImpl implements VerifyUserService {
	
	private static final  Logger LOGGER=LoggerFactory.getLogger(VerifyUserServiceImpl.class);
	
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	UserInfoCacheService userInfoCacheService;
	
	
	/**
	 * 校验用户支付密码
	 */
	@Override
	public R checUserPayPass(Map<String, Object> dataMap) {
		try {
			Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
			if(userMap==null) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(userMap, "pay_password"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999986,CommonCodeConstant.COMMON_MSG_999986);
			}
			String pay_password = new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("pay_password").toString().toUpperCase(), SysParamConstant.passNum).toString();//密码
			if(!pay_password.equals(userMap.get("pay_password").toString())) {
				return R.error(CommonCodeConstant.COMMON_CODE_999985,CommonCodeConstant.COMMON_MSG_999985);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			LOGGER.error("VerifyUserServiceImpl -- checUserPayPass方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999984,CommonCodeConstant.COMMON_MSG_999984);
		}
	}


	@Override
	public R checUserAuthStatus(Map<String, Object> dataMap) {
		try {
			Map<String, Object> userMap = userInfoMapper.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
			if(userMap==null) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
			}
			if(!"09".equals(StringUtil.getMapValue(userMap, "auth_status"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999980,CommonCodeConstant.COMMON_MSG_999980);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			LOGGER.error("VerifyUserServiceImpl -- checUserAuthStatus方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999984,CommonCodeConstant.COMMON_MSG_999984);
		}
	}

}

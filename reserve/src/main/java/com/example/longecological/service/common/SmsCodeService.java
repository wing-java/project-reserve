package com.example.longecological.service.common;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 发送和比较短信验证码
 * @author Administrator
 *
 */
public interface SmsCodeService {

	
	/**
	 * 短信验证码发送=====》整体仅仅rsa加密（适用于注册和忘记密码）
	 * @param map
	 * @return
	 */
	R sendSmsCodeOnly(Map<String, Object> map);


	/**
	 * 短信验证码发送=====》整体rsa加密+token验证（适用于修改登录密码和交易密码）
	 * @param map
	 * @return
	 */
	R sendSmsCodeToken(Map<String, Object> map);
	
	
	/**
	 * 短信验证码发送=====》整体rsa加密+token验证=====》只能发送到传入手机号
	 * @param map
	 * @return
	 */
	R sendSmsCodeAccept(Map<String, Object> map);
	
	
	/**
	 * 校验短信验证码
	 * @param map
	 * @return
	 */
	R checkSmsCode(Map<String, Object> map);


	/**
	 * 校验用户短信验证码和支付密码
	 * @param dataMap
	 * @return
	 */
	R checkSmsCodePayPass(Map<String, Object> dataMap);
	
}

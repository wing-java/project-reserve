package com.ruoyi.project.develop.common.service;

import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;


/**
 * 发送和比较短信验证码
 * @author Administrator
 *
 */
public interface SendSmsCodeService {

	/**
	 * 发送短信验证码 =======>后台shiro处理图形验证码手段
	 * @param map
	 * @return
	 */
	R sendSmsCode(Map<String, Object> map);
	
	
}

package com.example.longecological.service.common;

import java.util.Map;

import com.example.longecological.entity.R;

/**
 * 校验用户信息service层
 * @author Administrator
 *
 */
public interface VerifyUserService {

	/**
	 * 校验用户支付密码
	 * @param map
	 * @return
	 */
	R checUserPayPass(Map<String, Object> map);
	
	/**
	 * 校验用户实名认证
	 * @param map
	 * @return
	 */
	R checUserAuthStatus(Map<String, Object> map);
}

package com.example.longecological.service.common;

import com.example.longecological.entity.R;

public interface VerifyTokenService {

	/**
	 * 校验Token
	 * @param token
	 * @return
	 */
	R isToken(String token);
	
	
	/**
	 * 生成Token
	 * @param userId
	 * @return
	 */
	String setToken(String userId);
	
	
	/**
	 * 删除Token
	 * @param token
	 */
	void deleteToken(String token);
}

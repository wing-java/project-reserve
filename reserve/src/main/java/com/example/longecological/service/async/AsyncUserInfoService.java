package com.example.longecological.service.async;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AsyncUserInfoService {

	
	/**
	 * 记录用户登录日志
	 * @param dataMap
	 * @param request 
	 */
	void addUserLoginLog(Map<String, Object> dataMap, HttpServletRequest request);

	
	/**
	 * 记录用户异常操作日志
	 * @param dataMap
	 * @param request
	 */
	void addUserErrorOperLog(Map<String, Object> dataMap, HttpServletRequest request);

	
}

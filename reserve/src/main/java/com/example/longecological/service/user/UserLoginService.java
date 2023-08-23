package com.example.longecological.service.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.longecological.entity.R;


/**
 * 用户登录注册相关
 * @author Administrator
 *
 */
public interface UserLoginService {

	
	/**
	 * 用户登录
	 * @param map
	 * @return
	 */
	R userLogin(Map<String, Object> map, HttpServletRequest request);
	/**
	 * 用户退出登录
	 * @param map
	 * @return
	 */
	R userLogOut(Map<String, Object> map);
	/**
	 * 用户注册
	 * @param map
	 * @param request 
	 * @return
	 */
	R userRegister(Map<String, Object> map, HttpServletRequest request);
	/**
	 * 用户忘记密码
	 * @param map
	 * @return
	 */
	R userForgetPass(Map<String, Object> map);


	/**
	 * 用户注册第一步
	 * @param map
	 * @return
	 */
	R userRegisterFirst(Map<String, Object> map, HttpServletRequest request);
	/**
	 * 用户注册第二步
	 * @param map
	 * @return
	 */
	R userRegisterSecond(Map<String, Object> map);
	
}

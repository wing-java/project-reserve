package com.example.longecological.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserLoginService;


@Controller
@RequestMapping("/api/user/login")
public class UserLoginController {

	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private  UserLoginService userLoginService;
	
	
	/**
	 * 用户登录：map实例加密（APP端）
	 * @param map
	 * @return
	 */
	@RequestMapping("/userLogin")
	@ResponseBody
	public R userLogin(@RequestBody Map<String, Object> map){
		return userLoginService.userLogin(map, request);
	}
	/**
	 * 用户退出登录（APP端）
	 * @param sessionStatus
	 * @return
	 */
	@RequestMapping(value = "/userLogOut")
	@ResponseBody
	public R userLogOut(@RequestBody Map<String, Object> map) {
		return userLoginService.userLogOut(map);
	}
	
	
	/**
	 * 用户注册======》整体仅仅rsa加密
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userRegister")
	public R userRegister(@RequestBody Map<String, Object> map) {
		return userLoginService.userRegister(map, request);
	}
	/**
	 * 找回登录密码（忘记密码）======》整体仅仅rsa加密
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userForgetPass")
	public R userForgetPass(@RequestBody Map<String, Object> map) {
		return userLoginService.userForgetPass(map);
	}
	/**
	 * 用户注册第一步
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userRegisterFirst")
	public R userRegisterFirst(@RequestBody Map<String, Object> map) {
		return userLoginService.userRegisterFirst(map, request);
	}
	/**
	 * 用户注册第二步
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userRegisterSecond")
	public R userRegisterSecond(@RequestBody Map<String, Object> map) {
		return userLoginService.userRegisterSecond(map);
	}
	
}

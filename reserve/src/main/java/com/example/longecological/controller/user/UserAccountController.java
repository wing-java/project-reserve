package com.example.longecological.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserAccountService;


@Controller
@RequestMapping("/api/user/account")
public class UserAccountController {

	@Autowired
	private  UserAccountService userAccountService;
	
	
	//===================================================银行卡设置多个（列表分页加载）================================
	/**
	 * 查询用户账户信息列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserAccountList")
	public R getUserAccountList(@RequestBody Map<String, Object> map) {
		return userAccountService.getUserAccountList(map);
	}
	/**
	 * 根据id查询用户账户信息详情
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserAccountById")
	public R getUserAccountById(@RequestBody Map<String, Object> map) {
		return userAccountService.getUserAccountById(map);
	}
	
	
	//===================================================支付宝、微信、银行卡一样一个================================
	/**
	 * 查询用户账户信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserAccountInfo")
	public R getUserAccountInfo(@RequestBody Map<String, Object> map) {
		return userAccountService.getUserAccountInfo(map);
	}
	/**
	 * 查询用户该类型的账户信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserAccountByType")
	public R getUserAccountByType(@RequestBody Map<String, Object> map) {
		return userAccountService.getUserAccountByType(map);
	}
	/**
	 * 修改用户账户信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateUserAccount")
	public R updateUserAccount(@RequestBody Map<String, Object> map) {
		return userAccountService.updateUserAccount(map);
	}
	
}

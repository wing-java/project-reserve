package com.example.longecological.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserInfoService;


@Controller
@RequestMapping("/api/user/info")
public class UserInfoController {

	@Autowired
	private  UserInfoService userInfoService;
	
	
	/**
	 * 根据编号查询用户缓存信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserInfoCacheById")
	public R getUserInfoCacheById(@RequestBody Map<String, Object> map) {
		return userInfoService.getUserInfoCacheById(map);
	}
	/**
	 * 实时查询用户信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRealUserInfo")
	public R getRealUserInfo(@RequestBody Map<String, Object> map) {
		return userInfoService.getRealUserInfo(map);
	}
	/**
	 * 实时查询钱包信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserWalletInfo")
	public R getUserWalletInfo(@RequestBody Map<String, Object> map) {
		return userInfoService.getUserWalletInfo(map);
	}
	/**
	 * 查询用户团队信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserTeamInfo")
	public R getUserTeamInfo(@RequestBody Map<String, Object> map) {
		return userInfoService.getUserTeamInfo(map);
	}
	/**
	 * 查询用户团队列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserTeamList")
	public R getUserTeamList(@RequestBody Map<String, Object> map) {
		return userInfoService.getUserTeamList(map);
	}
	/**
	 * 校验用户信息是否存在
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUserExist")
	public R checkUserExist(@RequestBody Map<String, Object> map) {
		return userInfoService.checkUserExist(map);
	}
	/**
	 * 根据手机号查询用户信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserByTel")
	public R getUserByTel(@RequestBody Map<String, Object> map) {
		return userInfoService.getUserByTel(map);
	}
	/**
	 * 查询团队统计金额
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserByTeamBenefit")
	public R getUserByTeamBenefit(@RequestBody Map<String, Object> map) {
		return userInfoService.getUserByTeamBenefit(map);
	}
}

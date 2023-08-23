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
@RequestMapping("/api/user/setting")
public class UserSettingController {
	
	@Autowired
	private  UserInfoService userInfoService;
	
	
	/**
	 * 修改登录密码======》整体rsa加密+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyLoginPass")
	public R modifyLoginPass(@RequestBody Map<String, Object> map) {
		return userInfoService.modifyLoginPass(map);
	}
	/**
	 * 修改交易密码======》整体rsa加密+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyPayPass")
	public R modifyPayPass(@RequestBody Map<String, Object> map) {
		return userInfoService.modifyPayPass(map);
	}
	/**
	 * 修改手机号第一步
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyTelFirst")
	public R modifyTelFirst(@RequestBody Map<String, Object> map) {
		return userInfoService.modifyTelFirst(map);
	}
	/**
	 * 修改手机号第二步
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyTelSecond")
	public R modifyTelSecond(@RequestBody Map<String, Object> map) {
		return userInfoService.modifyTelSecond(map);
	}
	/**
	 * 修改用户资料（昵称和头像）
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyUserInfo")
	public R modifyUserInfo(@RequestBody Map<String, Object> map) {
		return userInfoService.modifyUserInfo(map);
	}
	
	/**
	 * 实名认证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/submitUserReal")
	public R submitUserReal(@RequestBody Map<String, Object> map) {
		return userInfoService.submitUserReal(map);
	}
	
	/**
	 * 查询实名
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserReal")
	public R getUserReal(@RequestBody Map<String, Object> map) {
		return userInfoService.getUserReal(map);
	}
}

package com.example.longecological.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserSignService;

/**
 * 用户签到
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/user/sign")
public class UserSignController {

	@Autowired
	private UserSignService userSignService;
	

	/**
	 * 查询用户签到
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserSignList")
	public R getUserSignList(@RequestBody Map<String, Object> map) {
		return userSignService.getUserSignList(map);
	}
	
	/**
	 * 签到
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userSign")
	public R userSign(@RequestBody Map<String, Object> map) {
		return userSignService.userSign(map);
	}
}

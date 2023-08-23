package com.example.longecological.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserRechargeService;
import com.example.longecological.utils.ip.IpUtils;


/**
 * 用户充值
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/user/recharge")
public class UserRechargeController {

	@Autowired
	private  UserRechargeService userRechargeService;
	@Autowired
	private  HttpServletRequest request;
	

	/**
	 * 查询用户充值记录列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserRechargeList")
	public R getUserRechargeList(@RequestBody Map<String, Object> map) {
		return userRechargeService.getUserRechargeList(map);
	}
	/**
	 * 查询充值记录详情
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserRechargeDetail")
	public R getUserRechargeDetail(@RequestBody Map<String, Object> map) {
		return userRechargeService.getUserRechargeDetail(map);
	}
	/**
	 * 用户线下充值
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userRechargeOffLine")
	public R userRechargeOffLine(@RequestBody Map<String, Object> map) {
		return userRechargeService.userRechargeOffLine(map);
	}
	/**
	 * 用户线上充值（APP端）
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userRechargeOnLineApp")
	public R userRechargeOnLineApp(@RequestBody Map<String, Object> map) {
		String user_ip = IpUtils.getIpAddr(request);
		map.put("user_ip", user_ip.split(",")[0]);
		return userRechargeService.userRechargeOnLineApp(map);
	}
	
}

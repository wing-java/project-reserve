package com.ruoyi.project.develop.chart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.develop.chart.service.ChartUserInfoService;
import com.ruoyi.project.develop.user.service.UserInfoService;

/**
 * 定存钱包流水操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/chartUser")
public class ChartUserInfoController extends BaseController
{
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	ChartUserInfoService chartUserInfoService;
	
	
	/**
	 * 用户每日注册量统计
	 * @return
	 */
	@ResponseBody
	@PostMapping("/userRegister")
	public List<Map<String, Object>> userRegister() {
		return userInfoService.getUserRegisterList();
	}
	
	
	/**
	 * 平台钱包统计
	 * @return
	 */
	@ResponseBody
	@PostMapping("/platformPurse")
	public Map<String, Object> platformPurse() {
		return userInfoService.getPlatformPurseInfo();
	}
	
	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/summaryUserInfo")
	public Map<String, Object> summaryUserInfo(@RequestParam Map<String, Object> params) {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return chartUserInfoService.summaryUserInfo(params);
	}
	
	/**
	 * 平台钱包统计
	 * @return
	 */
	@ResponseBody
	@PostMapping("/summaryTotalUser")
	public Map<String, Object> summaryTotalUser(@RequestParam Map<String, Object> params) {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return chartUserInfoService.summaryTotalUser(params);
	}
	
	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/summaryRecharge")
	public Map<String, Object> summaryRecharge(@RequestParam Map<String, Object> params) {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return chartUserInfoService.summaryRecharge(params);
	}
	
	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/summaryCash")
	public Map<String, Object> summaryCash(@RequestParam Map<String, Object> params) {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return chartUserInfoService.summaryCash(params);
	}
	
	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/summaryTotalRecharge")
	public Map<String, Object> summaryTotalRecharge(@RequestParam Map<String, Object> params) {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return chartUserInfoService.summaryTotalRecharge(params);
	}
	
	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/summaryTotalCash")
	public Map<String, Object> summaryTotalCash(@RequestParam Map<String, Object> params) {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return chartUserInfoService.summaryTotalCash(params);
	}
}

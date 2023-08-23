package com.ruoyi.project.develop.chart.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.SysParamCodeConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.project.develop.chart.mapper.ChartUserInfoMapper;
import com.ruoyi.project.develop.chart.service.ChartUserInfoService;
import com.ruoyi.project.develop.param.service.SysParamService;

@Service
public class ChartUserInfoServiceImpl implements ChartUserInfoService {

	@Autowired
	ChartUserInfoMapper chartUserInfoMapper;
	@Autowired
	SysParamService sysParamService;

	@Override
	public Map<String, Object> summaryUserInfo(Map<String, Object> params) {
		String summaryExcludeUser = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_summaryExcludeUser);
		params.put("summaryExcludeUser", summaryExcludeUser);
		//获取首充人数 num1
		int num1 = chartUserInfoMapper.getSummaryNum1(params);
		//获取签到人数 num2
		int num2 = chartUserInfoMapper.getSummaryNum2(params);
		//获取登录人数  num3
		int num3 = chartUserInfoMapper.getSummaryNum3(params);
		//获取新增用户 num4
		int num4 = chartUserInfoMapper.getSummaryNum4(params);
		//获取认购总额 num5
		String num5 = chartUserInfoMapper.getSummaryNum5(params);
		//获取拨款 num12
		String num12 = chartUserInfoMapper.getSummaryNum12(params);
		//获取扣款 num13
		String num13 = chartUserInfoMapper.getSummaryNum13(params);
		Map<String, Object> respondMap = new HashMap<String, Object>();
		respondMap.put("num1", num1);
		respondMap.put("num2", num2);
		respondMap.put("num3", num3);
		respondMap.put("num4", num4);
		respondMap.put("num5", num5);
		respondMap.put("num12", num12);
		respondMap.put("num13", num13);
		return respondMap;
	}

	@Override
	public Map<String, Object> summaryTotalUser(Map<String, Object> params) {
		String summaryExcludeUser = sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_summaryExcludeUser);
		params.put("summaryExcludeUser", summaryExcludeUser);
		//总用户 num20 
		int num20 = chartUserInfoMapper.getSummaryNum20(params);
		//总余额 num21
		String num21 = chartUserInfoMapper.getSummaryNum21(params);
		//总拨款 num22
		String num22 = chartUserInfoMapper.getSummaryNum22(params);
		//总扣款 num23
		String num23 = chartUserInfoMapper.getSummaryNum23(params);
		Map<String, Object> respondMap = new HashMap<String, Object>();
		respondMap.put("num20", num20);
		respondMap.put("num21", num21);
		respondMap.put("num22", num22);
		respondMap.put("num23", num23);
		return respondMap;
	}

	@Override
	public Map<String, Object> summaryRecharge(Map<String, Object> params) {
		Map<String, Object> summary = chartUserInfoMapper.getSummaryNum_6_7_8(params);
		Map<String, Object> respondMap = new HashMap<String, Object>();
		if(summary == null) {
			respondMap.put("num6", 0);
			respondMap.put("num7", 0);
			respondMap.put("num8", 0.00);
		}else {
			respondMap.put("num6", summary.get("num6"));
			respondMap.put("num7", summary.get("num7"));
			respondMap.put("num8", summary.get("num8"));
		}
		return respondMap;
	}

	@Override
	public Map<String, Object> summaryCash(Map<String, Object> params) {
		Map<String, Object> summary = chartUserInfoMapper.getSummaryNum_9_10_11(params);
		Map<String, Object> respondMap = new HashMap<String, Object>();
		if(summary == null) {
			respondMap.put("num9", 0);
			respondMap.put("num10", 0);
			respondMap.put("num11", 0.00);
		}else {
			respondMap.put("num9", summary.get("num9"));
			respondMap.put("num10", summary.get("num10"));
			respondMap.put("num11", summary.get("num11"));
		}
		return respondMap;
	}

	@Override
	public Map<String, Object> summaryTotalRecharge(Map<String, Object> params) {
		Map<String, Object> summary = chartUserInfoMapper.getSummaryNum_14_15_16(params);
		Map<String, Object> respondMap = new HashMap<String, Object>();
		if(summary == null) {
			respondMap.put("num14", 0.00);
			respondMap.put("num15", 0);
			respondMap.put("num16", 0);
		}else {
			respondMap.put("num14", summary.get("num14"));
			respondMap.put("num15", summary.get("num15"));
			respondMap.put("num16", summary.get("num16"));
		}
		return respondMap;
	}
	
	@Override
	public Map<String, Object> summaryTotalCash(Map<String, Object> params) {
		Map<String, Object> summary = chartUserInfoMapper.getSummaryNum_17_18_19(params);
		Map<String, Object> respondMap = new HashMap<String, Object>();
		if(summary == null) {
			respondMap.put("num17", 0.00);
			respondMap.put("num18", 0);
			respondMap.put("num19", 0);
		}else {
			respondMap.put("num17", summary.get("num17"));
			respondMap.put("num18", summary.get("num18"));
			respondMap.put("num19", summary.get("num19"));
		}
		return respondMap;
	}
	
}

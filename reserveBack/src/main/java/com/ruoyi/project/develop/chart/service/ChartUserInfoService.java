package com.ruoyi.project.develop.chart.service;

import java.util.Map;

public interface ChartUserInfoService {

	Map<String, Object> summaryUserInfo(Map<String, Object> params);
	
	Map<String, Object> summaryTotalUser(Map<String, Object> params);
	
	Map<String, Object> summaryRecharge(Map<String, Object> params);
	
	Map<String, Object> summaryCash(Map<String, Object> params);
	
	Map<String, Object> summaryTotalRecharge(Map<String, Object> params);
	
	Map<String, Object> summaryTotalCash(Map<String, Object> params);
}

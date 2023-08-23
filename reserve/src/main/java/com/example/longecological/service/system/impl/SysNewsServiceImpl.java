package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysInfoCacheService;
import com.example.longecological.service.system.SysNewsService;
import com.example.longecological.utils.string.StringUtil;


/**
 * 系统新闻相关
 * @author Administrator
 *
 */
@Service
public class SysNewsServiceImpl implements SysNewsService {
	
	@Autowired
	SysInfoCacheService sysInfoCacheService;
	

	/**
	 * 查询系统最新新闻
	 */
	@Override
	public R getNewNews(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> newsInfoMap = sysInfoCacheService.getNewNews(map);
			respondMap.put("newsInfoMap", newsInfoMap);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	
	/**
	 * 查询系统新闻列表
	 */
	@Override
	public R getSysNewsList(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			map.put("lastIdKey", "".equals(StringUtil.getMapValue(map, "last_id")) ? "0" : StringUtil.getMapValue(map, "last_id"));
			List<Map<String, Object>> sysNewsList = sysInfoCacheService.getSysNewsList(map);
			respondMap.put("sysNewsList", sysNewsList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}



	/**
	 * 查询系统新闻详情
	 */
	@Override
	public R getSysNewsDetail(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> sysNewsDetail = sysInfoCacheService.getSysNewsDetail(map);
			respondMap.put("sysNewsDetail", sysNewsDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}



}

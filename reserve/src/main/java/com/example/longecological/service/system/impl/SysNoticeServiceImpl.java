package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysInfoCacheService;
import com.example.longecological.service.system.SysNoticeService;
import com.example.longecological.utils.string.StringUtil;


/**
 * 系统公告相关
 * @author Administrator
 *
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
	
	@Autowired
	SysInfoCacheService sysInfoCacheService;
	

	/**
	 * 查询系统最新公告
	 */
	@Override
	public R getNewNotice(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> noticeInfoMap = sysInfoCacheService.getNewNotice(map);
			respondMap.put("noticeInfo", noticeInfoMap);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	
	/**
	 * 查询系统公告列表
	 */
	@Override
	public R getSysNoticeList(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			map.put("lastIdKey", "".equals(StringUtil.getMapValue(map, "last_id")) ? "0" : StringUtil.getMapValue(map, "last_id"));
			List<Map<String, Object>> sysNoticeList = sysInfoCacheService.getSysNoticeList(map);
			respondMap.put("sysNoticeList", sysNoticeList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}



	/**
	 * 查询系统公告详情
	 */
	@Override
	public R getSysNoticeDetail(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> sysNoticeDetail = sysInfoCacheService.getSysNoticeDetail(map);
			respondMap.put("sysNoticeDetail", sysNoticeDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}



}

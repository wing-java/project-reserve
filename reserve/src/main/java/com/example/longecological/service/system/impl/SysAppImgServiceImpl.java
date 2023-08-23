package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysAppImgService;
import com.example.longecological.service.system.SysInfoCacheService;
import com.example.longecological.utils.string.StringUtil;


/**
 * app图片相关
 * @author Administrator
 *
 */
@Service
public class SysAppImgServiceImpl implements SysAppImgService {
	
	@Autowired
	SysInfoCacheService sysInfoCacheService;
	

	
	/**
	 * 查询app图片列表
	 */
	@Override
	public R getAppImgList(Map<String, Object> map) {
		try {
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "img_type"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> appImgListMap = sysInfoCacheService.getAppImgList(map);
			respondMap.put("appImgList", appImgListMap);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

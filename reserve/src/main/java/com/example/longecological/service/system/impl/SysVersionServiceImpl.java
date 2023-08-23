package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.OperParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysInfoCacheService;
import com.example.longecological.service.system.SysVersionService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.ParamValidUtil;


/**
 * 系统版本相关
 * @author Administrator
 *
 */
@Service
public class SysVersionServiceImpl implements SysVersionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysVersionServiceImpl.class);
	
	@Autowired
	SysInfoCacheService sysInfoCacheService;

	
	/**
	 * 查询系统最新版本
	 */
	@Override
	public R getNewVersion(Map<String, Object> map) {
		try {
			//校验参数信息
			R checkParamResult = ParamValidUtil.checkSpecifyParam(map, "device_type", OperParamConstant.USER_DEVICE_TYPE);
			if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
				return checkParamResult;
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> versionInfoMap = sysInfoCacheService.getNewVersion(map);
			respondMap.put("versionInfo", versionInfoMap);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysVersionServiceImpl -- getNewVersion方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


}

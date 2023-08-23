package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysContractService;
import com.example.longecological.service.system.SysInfoCacheService;
import com.example.longecological.utils.string.StringUtil;


/**
 * 系统协议合同相关
 * @author Administrator
 *
 */
@Service
public class SysContractServiceImpl implements SysContractService {
	
	@Autowired
	SysInfoCacheService sysInfoCacheService;
	

	
	/**
	 * 根据类型查询系统协议合同信息
	 */
	@Override
	public R getSysContractByType(Map<String, Object> map) {
		try {
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "contract_type"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> sysContract = sysInfoCacheService.getSysContractByType(map);
			respondMap.put("sysContract", sysContract);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

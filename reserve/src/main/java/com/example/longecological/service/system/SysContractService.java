package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 系统协议合同相关
 * @author Administrator
 *
 */
public interface SysContractService {

	/**
	 * 根据类型查询系统协议合同信息
	 * @param map
	 * @return
	 */
	R getSysContractByType(Map<String, Object> map);

}

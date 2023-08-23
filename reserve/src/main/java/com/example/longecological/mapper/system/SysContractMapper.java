package com.example.longecological.mapper.system;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 系统协议合同相关
 * @author Administrator
 *
 */
public interface SysContractMapper {

	
	/**
	 * 根据类型查询系统协议合同信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysContractByType(@Param("map") Map<String, Object> map);

	
}

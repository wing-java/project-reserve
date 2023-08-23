package com.example.longecological.mapper.system;

import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 系统地区
 * @author Administrator
 *
 */
public interface SysCompanyMapper {
	
	/**
	 * 查询公司信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysCompanyInfo(@Param("map") Map<String, Object> map);
	
}

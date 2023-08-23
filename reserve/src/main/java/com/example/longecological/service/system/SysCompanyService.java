package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;

/**
 * 系统地区相关
 * @author Administrator
 *
 */
public interface SysCompanyService {

	/**
	 * 查询公司信息
	 * @param map
	 * @return
	 */
	R getSysCompanyInfo(Map<String, Object> map);


}

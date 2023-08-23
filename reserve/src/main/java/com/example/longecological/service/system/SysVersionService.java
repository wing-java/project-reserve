package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 系统版本相关
 * @author Administrator
 *
 */
public interface SysVersionService {

	/**
	 * 查询系统最新版本
	 * @param map
	 * @return
	 */
	R getNewVersion(Map<String, Object> map);

}

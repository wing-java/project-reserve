package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * app图片相关
 * @author Administrator
 *
 */
public interface SysAppImgService {

	/**
	 * 查询app图片列表
	 * @param map
	 * @return
	 */
	R getAppImgList(Map<String, Object> map);

}

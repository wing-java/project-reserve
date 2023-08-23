package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * APP图片相关
 * @author Administrator
 *
 */
public interface SysAppImgMapper {

	
	/**
	 * 查询APP图片列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getAppImgList(@Param("map") Map<String, Object> map);

	
}

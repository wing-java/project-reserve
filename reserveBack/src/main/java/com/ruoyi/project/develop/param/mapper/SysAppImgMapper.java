package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysAppImg;

/**
 * APP图片 数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface SysAppImgMapper 
{

	/**
	 * 查询APP图片列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysAppImgList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出APP图片列表
	 * @param params
	 * @return
	 */
	List<SysAppImg> selectSysAppImgList(@Param("map") Map<String, Object> params);


	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysAppImgById(@Param("appImg_id") String appImg_id);


	/**
	 * 更新APP图片
	 * @param params
	 * @return
	 */
	int updateSysAppImg(@Param("map") Map<String, Object> params);


	/**
	 * 新增APP图片
	 * @param params
	 * @return
	 */
	int addSysAppImg(@Param("map") Map<String, Object> params);


	/**
	 * 根据APP图片id删除
	 * @param exchangeUrlId
	 * @return
	 */
	int deleteSysAppImg(@Param("appImg_id") String appImg_id);
}
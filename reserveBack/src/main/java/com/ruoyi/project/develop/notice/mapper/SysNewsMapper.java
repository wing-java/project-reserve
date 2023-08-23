package com.ruoyi.project.develop.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 新闻资讯 数据层
 * 
 * @author ruoyi
 */
public interface SysNewsMapper
{

	/**
	 * 查询新闻资讯列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSysNewsList(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询新闻资讯详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> getNewsById(@Param("news_id") String id);

	
	/**
	 * 新增新闻资讯
	 * @param params
	 * @return
	 */
	public int addSysNews(@Param("map") Map<String, Object> params);

	
	/**
	 * 更新新闻资讯
	 * @param params
	 * @return
	 */
	public int updateSysNews(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除新闻资讯
	 * @param notice_id
	 * @return
	 */
	public int deleteSysNews(@Param("news_id") String notice_id);

}
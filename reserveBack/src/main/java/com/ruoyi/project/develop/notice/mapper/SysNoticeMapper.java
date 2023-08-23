package com.ruoyi.project.develop.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 公告 数据层
 * 
 * @author ruoyi
 */
public interface SysNoticeMapper
{

	/**
	 * 查询公告列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSysNoticeList(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询公告详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> getNoticeById(@Param("notice_id") String id);

	
	/**
	 * 新增公告
	 * @param params
	 * @return
	 */
	public int addSysNotice(@Param("map") Map<String, Object> params);

	
	/**
	 * 更新公告
	 * @param params
	 * @return
	 */
	public int updateSysNotice(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除公告
	 * @param notice_id
	 * @return
	 */
	public int deleteSysNotice(@Param("notice_id") String notice_id);


	/**
	 * 获取队列长度
	 * @return
	 */
	public int getSysNoticeSize();
}
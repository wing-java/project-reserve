package com.ruoyi.project.develop.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysBonusMapper {

	/**
	 * 查询列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSysBonusList(@Param("map") Map<String, Object> params);

	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> getBonusById(@Param("bonus_id") String id);

	
	/**
	 * 新增
	 * @param params
	 * @return
	 */
	public int addSysBonus(@Param("map") Map<String, Object> params);

	
	/**
	 * 更新
	 * @param params
	 * @return
	 */
	public int updateSysBonus(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除
	 * @param bonus_id
	 * @return
	 */
	public int deleteSysBonus(@Param("bonus_id") String bonus_id);

}

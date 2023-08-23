package com.ruoyi.project.develop.task.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserBenefitMapper {

	/**
	 * 查询待汇总流水
	 * @return
	 */
	List<Map<String, Object>> getBenefitRecordAll();
	
	/**
	 * 更新总汇总流水
	 * @param map
	 * @return
	 */
	int updateUserBenefitAll(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询每日汇总
	 * @param user_id
	 * @param date
	 * @return
	 */
	int getUserBenefitEveryday(@Param("user_id") String user_id, @Param("date") String date);
	
	/**
	 * 新增每日汇总
	 * @param map
	 * @return
	 */
	int addUserBenefitEveryday(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新每日汇总
	 * @param map
	 * @return
	 */
	int updateUserBenefitEveryday(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除待汇总流水
	 * @param id
	 * @return
	 */
	int deleteUserBenefit(@Param("id") String id);
}

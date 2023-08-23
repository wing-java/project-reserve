package com.ruoyi.project.develop.reward.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.reward.domain.UserRewardToYear;

public interface UserRewardToYearMapper {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRewardToYearList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserRewardToYear> selectUserRewardToYearList(@Param("map") Map<String, Object> params);
}

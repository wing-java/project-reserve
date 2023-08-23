package com.ruoyi.project.develop.reward.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.reward.domain.UserRewardToDay;

public interface UserRewardToDayMapper {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRewardToDayList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserRewardToDay> selectUserRewardToDayList(@Param("map") Map<String, Object> params);
}

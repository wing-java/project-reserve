package com.ruoyi.project.develop.reward.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.reward.domain.UserRewardToRelease;

public interface UserRewardToReleaseMapper {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRewardToReleaseList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserRewardToRelease> selectUserRewardToReleaseList(@Param("map") Map<String, Object> params);
}

package com.ruoyi.project.develop.reward.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.reward.domain.UserRewardToRelease;

public interface UserRewardToReleaseService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRewardToReleaseList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserRewardToRelease> selectUserRewardToReleaseList(Map<String, Object> params);
}

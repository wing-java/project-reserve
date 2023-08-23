package com.ruoyi.project.develop.reward.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.reward.domain.UserRewardToYear;

public interface UserRewardToYearService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRewardToYearList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserRewardToYear> selectUserRewardToYearList(Map<String, Object> params);
}

package com.ruoyi.project.develop.reward.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.reward.domain.UserRewardToDay;

public interface UserRewardToDayService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRewardToDayList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserRewardToDay> selectUserRewardToDayList(Map<String, Object> params);
}

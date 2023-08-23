package com.ruoyi.project.develop.reward.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.reward.domain.UserRewardToAlgebra;

public interface UserRewardToAlgebraService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRewardToAlgebraList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserRewardToAlgebra> selectUserRewardToAlgebraList(Map<String, Object> params);
}

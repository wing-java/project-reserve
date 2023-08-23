package com.ruoyi.project.develop.sysoper.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.sysoper.domain.UserSysReward;

/**
 * 公司拨款管理
 * @author Administrator
 *
 */
public interface UserSysRewardService {

	
	/**
	 * 查询公司拨款列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserSysRewardList(Map<String, Object> params);
	
	
	/**
	 * 汇总数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserSysRewardList(Map<String, Object> params);

	
	/**
	 * 导出公司拨款列表
	 * @param params
	 * @return
	 */
	List<UserSysReward> selectUserSysRewardList(Map<String, Object> params);


	/**
	 * 新增公司拨款
	 * @param params
	 * @return
	 */
	R batchAddUserSysReward(Map<String, Object> params);
	
}
	
	

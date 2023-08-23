package com.ruoyi.project.develop.sysoper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.sysoper.domain.UserSysReward;


/**
 * 公司拨款信息管理
 * @author Administrator
 *
 */
public interface UserSysRewardMapper {


	/**
	 * 查询公司拨款列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserSysRewardList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 汇总数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserSysRewardList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出公司拨款列表
	 * @param params
	 * @return
	 */
	List<UserSysReward> selectUserSysRewardList(@Param("map") Map<String, Object> params);


	/**
	 * 记录拨款订单
	 * @param map
	 * @return
	 */
	int addUserSysReward(@Param("map") Map<String, Object> map);
	
}

package com.ruoyi.project.develop.sysoper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.sysoper.domain.UserSysRecycle;


/**
 * 公司扣款信息管理
 * @author Administrator
 *
 */
public interface UserSysRecycleMapper {


	/**
	 * 查询公司扣款列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserSysRecycleList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 汇总数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserSysRecycleList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出公司扣款列表
	 * @param params
	 * @return
	 */
	List<UserSysRecycle> selectUserSysRecycleList(@Param("map") Map<String, Object> params);


	/**
	 * 记录扣款订单
	 * @param map
	 * @return
	 */
	int addUserSysRecycle(@Param("map") Map<String, Object> map);
	
}

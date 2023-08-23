package com.ruoyi.project.develop.trade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.trade.domain.UserRoll;


/**
 * 用户转账日志信息管理
 * @author Administrator
 *
 */
public interface UserRollMapper {


	/**
	 * 查询用户转账日志列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRollList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 汇总数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserRollList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户转账日志列表
	 * @param params
	 * @return
	 */
	List<UserRoll> selectUserRollList(@Param("map") Map<String, Object> params);


	/**
	 * 根据转账编号查询详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserRollById(@Param("roll_id") String roll_id);

	/**
	 * 更新转账订单状态
	 * @param rollMap
	 * @return
	 */
	int updateUserRollStatus(@Param("map") Map<String, Object> rollMap);
}

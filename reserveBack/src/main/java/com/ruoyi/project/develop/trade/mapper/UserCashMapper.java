package com.ruoyi.project.develop.trade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.trade.domain.UserCash;



/**
 * 代理======用户取现记录管理
 * @author Administrator
 *
 */
public interface UserCashMapper {


	/**
	 * 查询用户取现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserCashList(@Param("map") Map<String, Object> params);
	/**
	 * 汇总取现记录数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserCashList(@Param("map") Map<String, Object> params);
	/**
	 * 导出用户取现记录列表
	 * @param params
	 * @return
	 */
	List<UserCash> selectUserCashList(@Param("map") Map<String, Object> params);
	/**
	 * 查询取现记录详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserCashDetailList(@Param("map") Map<String, Object> params);
	/**
	 * 查询用户待处理提现记录列表
	 * @param params
	 * @return
	 */
	List<UserCash> selectWaitUserCashList(@Param("map") Map<String, Object> params);


	/**
	 * 更新提现状态
	 * @param cashDealMap
	 * @return
	 */
	int updateUserCashStatus(@Param("map") Map<String, Object> cashDealMap);
	/**
	 * 记录提现记录详情
	 * @param cashDealMap
	 * @return
	 */
	int addUserCashDetail(@Param("map") Map<String, Object> cashDealMap);
	/**
	 * 根据提现id查询提现详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserCashById(@Param("cash_id") String cash_id);
	/**
	 * 用户余额提现汇总统计
	 */
	List<Map<String, Object>> chartUserCash();
	
	/**
	 * 
	 * @param dataMap
	 * @return
	 */
	int updateUserCashTaskStatus(@Param("map") Map<String, Object> dataMap);
	
}

package com.ruoyi.project.develop.pay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.pay.domain.UserTradeWechatpay;


/**
 * 线上微信充值信息管理
 * @author Administrator
 *
 */
public interface UserTradeWechatpayMapper {


	/**
	 * 查询线上微信充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserTradeWechatpayList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出线上微信充值列表
	 * @param params
	 * @return
	 */
	List<UserTradeWechatpay> selectUserTradeWechatpayList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 根据订单编号查询订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserTradeWechatpayById(@Param("id") String id);

}

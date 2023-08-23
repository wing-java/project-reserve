package com.ruoyi.project.develop.trade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.pay.domain.UserTradeAlipay;
import com.ruoyi.project.develop.pay.domain.UserTradeWechatpay;
import com.ruoyi.project.develop.trade.domain.UserRechargeOnline;



/**
 * 用户线上充值信息管理
 * @author Administrator
 *
 */
public interface UserRechargeOnlineMapper {


	/**
	 * 查询用户线上充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRechargeOnlineList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户线上充值列表
	 * @param params
	 * @return
	 */
	List<UserRechargeOnline> selectUserRechargeOnlineList(@Param("map") Map<String, Object> params);
	/**
	 * 统计充值信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserRechargeOnlineList(@Param("map") Map<String, Object> params);


	/**
	 * 根据编号查询线上充值详情
	 * @param id
	 * @return
	 */
	UserRechargeOnline getUserRechargeOnlineDetailById(@Param("recharge_id") String recharge_id);
	/**
	 * 根据商户订单号查询线上充值的支付宝支付详情
	 * @param out_trade_no
	 * @return
	 */
	UserTradeAlipay getUserRechargeOnlineAlipayByOutTradeNo(@Param("out_trade_no") String out_trade_no);
	/**
	 * 根据商户订单号查询线上充值的支付宝支付详情
	 * @param out_trade_no
	 * @return
	 */
	UserTradeWechatpay getUserRechargeOnlineWechatpayByOutTradeNo(@Param("out_trade_no") String out_trade_no);

}

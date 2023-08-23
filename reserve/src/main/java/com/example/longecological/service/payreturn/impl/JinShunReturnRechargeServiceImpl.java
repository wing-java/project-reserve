package com.example.longecological.service.payreturn.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.properties.JinShunProperties;
import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.mapper.common.JinShunMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.payreturn.JinShunReturnRechargeService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.string.RequestMap;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class JinShunReturnRechargeServiceImpl implements JinShunReturnRechargeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JinShunReturnRechargeServiceImpl.class);
	@Autowired
	JinShunProperties jinShunProperties;
	@Autowired
	JinShunMapper jinShunMapper;
	@Autowired
	UserWalletMapper userWalletMapper;

	@Override
	@Transactional
	public void jinshunAsynchronousResultRecharge(HttpServletRequest request) {
		Map<String,String> params = new HashMap<>();
		int i = 0;
		try {
			params = RequestMap.getParameterMap(request);
			LOGGER.info("金顺返回参数信息" + params.toString());
			if("1".equals(params.get("orderStatus"))) {
				//验签
				Map<String, Object> signMap = new HashMap<>();
				signMap.put("mchId", params.get("mchId"));
				signMap.put("platOrderId", params.get("platOrderId"));
				signMap.put("mchOrderId", params.get("mchOrderId"));
				signMap.put("notifyStatus", params.get("notifyStatus"));
				signMap.put("money", params.get("money"));
				signMap.put("actualMoney", params.get("actualMoney"));
				signMap.put("createTime", params.get("createTime"));
				signMap.put("payTime", params.get("payTime"));
				signMap.put("orderStatus", params.get("orderStatus"));
//				signMap.put("params", params.get("params"));
				String sign = SignUtil.getSign(signMap, false, false, jinShunProperties.getMd5key());
				if(sign.equals(params.get("sign"))) {
					//查询订单
					Map<String, Object> order = jinShunMapper.getJinShunTradeByOrderNo(params.get("mchOrderId"));
					if(order == null) throw new Exception("为查询到订单信息");
					//更新用户余额
					String date = TimeUtil.getDayString();
					String time = TimeUtil.getTimeString();
					Map<String, Object> edit_user = new HashMap<>();
					edit_user.put("user_id", StringUtil.getMapValue(order, "user_id"));//用户编号
					edit_user.put("op_order_no", StringUtil.getMapValue(order, "sys_order_no"));//订单号
					edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_01);//操作类型：购买产品
					edit_user.put("balance_num", params.get("money"));//支付余额账户
					edit_user.put("up_date", date);//更新日期
					edit_user.put("up_time", time);//更新时间
					i = userWalletMapper.updateUserWalletNum(edit_user);
					if(i != 1){
						throw new Exception("用户余额更新异常");
					}
					//更新订单状态
					Map<String, Object> edit_order = new HashMap<>();
					edit_order.put("id", StringUtil.getMapValue(order, "id"));
					edit_order.put("status", "09");
					edit_order.put("plat_order_id", params.get("platOrderId"));
					edit_order.put("pay_datetime", params.get("payTime"));
					edit_order.put("up_date", date);//更新日期
					edit_order.put("up_time", time);//更新时间
					i = jinShunMapper.updateOrderStatus(edit_order);
					if(i != 1){
						throw new Exception("订单状态更新异常");
					}
					//更新充值订单状态
					Map<String, Object> edit_recharge = new HashMap<>();
					edit_recharge.put("order_no", StringUtil.getMapValue(order, "sys_order_no"));
					edit_recharge.put("new_status", "09");
					edit_recharge.put("old_status", "00");
					edit_recharge.put("up_date", date);//更新日期
					edit_recharge.put("up_time", time);//更新时间
					i = jinShunMapper.updateRechargeOrderStatus(edit_recharge);
					if(i != 1){
						throw new Exception("订单状态更新异常");
					}
				}else {
					throw new Exception("验签失败");
				}
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LOGGER.error("ChuanJunReturnRechargeServiceImpl -- jinshunAsynchronousResultRecharge方法执行异常:"+ExceptionUtil.getExceptionAllinformation(e));
		}
	}

}

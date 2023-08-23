package com.example.longecological.service.common.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.config.properties.CikeProperties;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.OrderInfoConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.common.CikeMapper;
import com.example.longecological.service.common.CikeService;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.http.HttpClient;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class CikeServiceImpl implements CikeService {

	@Autowired
	CikeProperties cikeProperties;
	
	@Autowired
	CikeMapper cikeMapper;
	
	@Override
	public R dealCikeTrade(String user_id, String money, String callbackurl, String oper_type, String sys_order_no, String date,
			String time, String product, String user_ip) {
		//（2）封装统一支付接口并调用
		Map<String, Object> data = new HashMap<>();
		data.put("version", cikeProperties.getVersion());
		data.put("merId", cikeProperties.getMerId());
		data.put("merOrderId", StringUtil.getDateTimeAndRandomForID());
		data.put("tranAmt", money);
		data.put("submitTime", TimeUtil.getDate());
		data.put("signType", cikeProperties.getSignType());
		String sign = SignUtil.getSignToCiKePay(data, cikeProperties.getMd5key());
		data.put("signValue", sign);
		data.put("notifyUrl", cikeProperties.getNotifyUrl());
		data.put("frontUrl", callbackurl);
		data.put("merUserIp", user_ip);
		data.put("goodsName", "充值");
		data.put("payType", product);
		String result = HttpClient.sendHttpPost(cikeProperties.getUrl(), data);
		JSONObject ret_json = JSONObject.parseObject(result);
		if(!"200".equals(ret_json.getString("code"))) {
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999285, ret_json.getString("msg"));
		}
		//保存金顺记录
		JSONObject ret_data_json = ret_json.getJSONObject("data");
		data.put("user_id", user_id);
		data.put("recharge_url", ret_data_json.getString("url"));
		data.put("oper_type", oper_type);
		data.put("sys_order_no", sys_order_no);
		data.put("cre_date", date);
		data.put("cre_time", time);
		int num = cikeMapper.insertCikeTrade(data);
		if(num != 1) {
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999287, OrderInfoConstant.ORDER_INFO_MSG_999287);
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,ret_data_json.getString("url"));
	}

}

package com.example.longecological.service.common.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.config.properties.JinShunProperties;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.OrderInfoConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.common.JinShunMapper;
import com.example.longecological.service.common.JinShunService;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.http.HttpClient;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class JinShunServiceImpl implements JinShunService {

	@Autowired
	JinShunProperties jinShunProperties;
	
	@Autowired
	JinShunMapper jinShunMapper;
	
	@Override
	public R dealJinShunTrade(String user_id, String money, String oper_type, String sys_order_no,
			String date, String time, String product) {
		//（2）封装统一支付接口并调用
		Map<String, Object> data = new HashMap<>();
		data.put("mID", jinShunProperties.getMid());
		data.put("product", product);
		data.put("mOrderId", StringUtil.getDateTimeAndRandomForID());
		data.put("money", money);
		data.put("notifyUrl", jinShunProperties.getNotifyUrl());
		data.put("params", StringUtil.getMapValue(data, "mOrderId"));
		data.put("timeStamp", new Date().getTime()/1000);
		String sign = SignUtil.getSign(data, false, false, jinShunProperties.getMd5key());
		data.put("sign", sign);
		String result = HttpClient.sendHttpPost(jinShunProperties.getUrl(), data);
		JSONObject ret_json = JSONObject.parseObject(result);
		if(!"200".equals(ret_json.getString("status"))) {
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999285, ret_json.getString("message"));
		}
		//保存金顺记录
		JSONObject ret_data_json = ret_json.getJSONObject("data");
		data.put("user_id", user_id);
		data.put("recharge_url", ret_data_json.getString("payUrl"));
		data.put("oper_type", oper_type);
		data.put("sys_order_no", sys_order_no);
		data.put("cre_date", date);
		data.put("cre_time", time);
		int num = jinShunMapper.insertJinShunTrade(data);
		if(num != 1) {
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999287, OrderInfoConstant.ORDER_INFO_MSG_999287);
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,ret_data_json.getString("payUrl"));
	}

}

package com.example.longecological.service.common.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.config.properties.ChuanJunProperties;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.OrderInfoConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.common.ChuanJunMapper;
import com.example.longecological.service.common.ChuanJunService;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.http.HttpClient;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class ChuanJunServiceImpl implements ChuanJunService {

	@Autowired
	ChuanJunProperties chuanJunProperties;
	
	@Autowired
	ChuanJunMapper chuanJunMapper;
	
	@Override
	public R dealChuanJunTrade(String user_id, String money, String callbackurl, String oper_type, String sys_order_no, String date, String time, String bankcode) {
		//（2）封装统一支付接口并调用
		Map<String, Object> data = new HashMap<>();
		data.put("pay_memberid", chuanJunProperties.getMemberid());
		data.put("pay_orderid", StringUtil.getDateTimeAndRandomForID(3));
		data.put("pay_applydate", TimeUtil.get_format5(new Date()));
		data.put("pay_bankcode", StringUtil.isEmpty(bankcode) ? chuanJunProperties.getBankcode() : bankcode);
		data.put("pay_notifyurl", chuanJunProperties.getNotifyurl());
		data.put("pay_callbackurl", callbackurl);
		data.put("pay_amount", money);
		String sign = SignUtil.getSign(data, false, false, chuanJunProperties.getMd5key());
		data.put("pay_md5sign", sign);
		data.put("pay_productname", "充值");
		String result = HttpClient.sendHttpPost(chuanJunProperties.getUrl(), data);
		JSONObject ret_json = JSONObject.parseObject(result);
		if(!"200".equals(ret_json.getString("code"))) {
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999285, ret_json.getString("msg"));
		}
		//保存川军记录
		data.put("user_id", user_id);
		data.put("recharge_url", ret_json.getString("data"));
		data.put("oper_type", oper_type);
		data.put("sys_order_no", sys_order_no);
		data.put("cre_date", date);
		data.put("cre_time", time);
		int num = chuanJunMapper.insertChuanJunTrade(data);
		if(num != 1) {
			return R.error(OrderInfoConstant.ORDER_INFO_CODE_999287, OrderInfoConstant.ORDER_INFO_MSG_999287);
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,ret_json.getString("data"));
	}

}

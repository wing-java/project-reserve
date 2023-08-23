package com.example.longecological.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.http.HttpClient;
import com.example.longecological.utils.string.StringUtil;

public class Test {

	public static void main(String[] args) {
//		/*
		String url = "http://52.221.67.182:5521/api/pay/order";
		Map<String, Object> param = new HashMap<>();
		param.put("mID", "V181");
		param.put("product", "zfb_ptu");
		param.put("mOrderId", StringUtil.getDateTimeAndRandomForID());
		param.put("money", "800");
		param.put("notifyUrl", "127.0.0.1");
		param.put("params", StringUtil.getMapValue(param, "mOrderId"));
		param.put("timeStamp", new Date().getTime()/1000);
		String sign = SignUtil.getSign(param, false, false, "2fdfff1c415e483598b0da75829ee92b");
		param.put("sign", sign);
		String result = HttpClient.sendHttpPost(url, param);
		System.out.println(result);
//		*/
		
		/*
		String url = "https://pay.cikepay.com/pay";
		Map<String, Object> param = new HashMap<>();
		param.put("version", "1.0");
		param.put("merId", "11000006211");
		param.put("merOrderId", StringUtil.getDateTimeAndRandomForID());
		param.put("tranAmt", "1");
		param.put("submitTime", TimeUtil.getDate());
		param.put("signType", "MD5");
		String sign = SignUtil.getSignToCiKePay(param, "o26ie0h97kaylqyy7kum4naf8ip4kvyc");
		param.put("signValue", sign);
		param.put("notifyUrl", "127.0.0.1");
		param.put("frontUrl", "127.0.0.1");
		param.put("merUserIp", "127.0.0.1");
		param.put("goodsName", "充值");
		param.put("payType", "alipayH5");
		String result = HttpClient.sendHttpPost(url, param);
		*/
	}
	
	
}

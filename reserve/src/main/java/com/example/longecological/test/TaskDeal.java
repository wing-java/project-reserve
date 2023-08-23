package com.example.longecological.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.http.HttpRequest;
import com.example.longecological.utils.string.StringUtil;



/**
 * @Scheduled定时任务测试
 * @author Administrator
 *
 */
@Component
public class TaskDeal {
	
	 @Autowired
	 private RedisUtils redisUtils;
	
	
	//  每分钟启动 （测试）
	//@Scheduled(cron = "0 0/1 * * * ?")
	public void timerToNow(){
		redisUtils.set("user_account_16616053.122@qq.com", "1661605307@qq.com");
		System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println(redisUtils.get("user_account_16616053.122@qq.com"));
	}
	
	public static void main(String[] args) {
//		System.out.println("13100000000".substring(7));
		
//		商户号：230693251
//		网关地址：http://tue.chuanjun100.top/Pay_Index.html
//		APIKEY: grwhc3mxokzlpykmln2hr36n2ji1w8d7
		/*Map<String, Object> data = new HashMap<>();
		data.put("pay_memberid", "230693251");
		data.put("pay_orderid", StringUtil.getDateTimeAndRandomForID(3));
		data.put("pay_applydate", TimeUtil.get_format5(new Date()));
		data.put("pay_bankcode", "8802");
		data.put("pay_notifyurl", "127.0.0.1");
		data.put("pay_callbackurl", "127.0.0.1");
		data.put("pay_amount", 10.00);
		String key = "grwhc3mxokzlpykmln2hr36n2ji1w8d7";
		String sign = SignUtil.getSign(data, false, false, key);
		data.put("pay_md5sign", sign);
		data.put("pay_productname", "购物消费");*/
//		String result = HttpClient.sendHttpPost("http://tue.chuanjun100.top/Pay_Index.html", data);
//		System.out.println(result);
		
		//代付
//		appid:2ddc834a47733dbd35755278
//		secret:330475fb27eAC4b0c71fa6cDcA2565eF11f5b5C1
		/*
		Map<String, Object> data2 = new HashMap<>();
		data2.put("app_id", "2ddc834a47733dbd35755278");
		data2.put("product_id", 1);
		data2.put("out_trade_no", StringUtil.getDateTimeAndRandomForID());
		data2.put("amount", 100);
		data2.put("time", new Date().getTime()/1000);
		String sign2 = SignUtil.getSign(data2, false, false, "330475fb27eAC4b0c71fa6cDcA2565eF11f5b5C1").toLowerCase();
		data2.put("sign", sign2);
		JSONObject ext = new JSONObject();
		ext.put("accountName", "马燕");
		ext.put("accountNumber", "6230580000359755654");
		ext.put("bankName", "中国平安银行");
		data2.put("ext", ext);
		String result2 = HttpRequest.sendPost("http://tue-api.alinpayment.top/api/payment", JSON.toJSONString(data2));
//				sendHttpPost("http://tue-api.alinpayment.top/api/payment", data2);
		System.out.println(result2);
		*/
		//20230711181012020680786
		Map<String, Object> data3 = new HashMap<>();
		data3.put("app_id", "2ddc834a47733dbd35755278");
		data3.put("out_trade_no", "20230711181012020680786");
		data3.put("time", new Date().getTime()/1000);
		String sign3 = SignUtil.getSign(data3, false, false, "330475fb27eAC4b0c71fa6cDcA2565eF11f5b5C1").toLowerCase();
		data3.put("sign", sign3);
		String result3 = HttpRequest.sendPost("http://tue-api.alinpayment.top/api/payment/status", JSON.toJSONString(data3));
		System.out.println(result3);
	}

}

package com.example.longecological.utils.message;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;

import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class BanyanPalmDemoUtil {
	
	public static final String url = "http://api.1cloudsp.com/api/v2/single_send";
	public static final String accesskey = "Lc55xXcv2MEOuAth";
    public static final String accessSecret = "KhPKnKmg8fl7ibG1TZcI20YLjUI2F7rh";
    public static final String sign = "291701";
    public static final String templateId = "258123";

	public static R sendMesg(String account, String code) {
		try {
			HttpClient httpClient = new HttpClient();
	        PostMethod postMethod = new PostMethod(url);
	        postMethod.getParams().setContentCharset("UTF-8");
	        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());


	        NameValuePair[] data = {
	                new NameValuePair("accesskey", accesskey),
	                new NameValuePair("secret", accessSecret),
	                new NameValuePair("sign", sign), //签名ID
	                new NameValuePair("templateId", templateId), //模版ID
	                new NameValuePair("mobile", account), //手机号
	                new NameValuePair("content", URLEncoder.encode(code, "utf-8"))//（验证码）
	        };
	        postMethod.setRequestBody(data);
	        postMethod.setRequestHeader("Connection", "close");

	        int statusCode = httpClient.executeMethod(postMethod);
	        System.out.println("statusCode: " + statusCode + ", body: "
	                    + postMethod.getResponseBodyAsString());
	        if(statusCode != 200) return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999899, MsgImgCodeConstant.MESSAGE_MSG_999899);
	        JSONObject body = JSONObject.parseObject(postMethod.getResponseBodyAsString());
	        if("0".equals(body.getString("code"))) {
	        	return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999897, MsgImgCodeConstant.MESSAGE_MSG_999897);
	        }else {
	        	return R.error(MsgImgCodeConstant.MESSAGE_CODE_999899, body.getString("msg"));
	        }
		}catch (Exception e) {
			e.printStackTrace();
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999898, MsgImgCodeConstant.MESSAGE_MSG_999898);
		}
	 }
	
	public static void main(String[] args) {
		BanyanPalmDemoUtil.sendMesg("198********", "123456");
	}
}

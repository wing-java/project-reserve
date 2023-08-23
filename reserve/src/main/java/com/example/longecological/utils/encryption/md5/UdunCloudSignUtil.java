package com.example.longecological.utils.encryption.md5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * U盾钱包签名
 * @author Administrator
 *
 */
public class UdunCloudSignUtil {
	
	
	public static String sign(String key,String timestamp,String nonce,String type,String body) throws Exception {
        return DigestUtils.md5Hex(body + key + nonce + timestamp + type).toLowerCase();
    }

    public static String sign(String key,String timestamp,String nonce,String body) throws Exception {
        String raw = body + key + nonce + timestamp;
        String sign = DigestUtils.md5Hex(raw).toLowerCase();
        return sign;
    }

    public static String sign(String key,String timestamp,String nonce) throws Exception {
        return DigestUtils.md5Hex(key + nonce + timestamp ).toLowerCase();
    }
    
    
    /**
     * 请求参数对象
     * @param key
     * @param body
     * @return
     * @throws Exception
     */
    public static Map<String ,Object> wrapperParams(String key,String body) throws Exception {
        String timestamp = System.currentTimeMillis()+"" ;//时间戳
        String nonce = String.valueOf(getNonceString(8));//随机字符串
        String sign = sign(key,timestamp,nonce,body);//签名
        //请求map对象
        Map<String ,Object> map = new HashMap<>();
        map.put("body",body);//消息内容，json字符串
        map.put("sign",sign);//签名
        map.put("timestamp",timestamp);//时间戳
        map.put("nonce",nonce);//随机数
        return map;
    }
	
    
    /**
     * 获取随机字符串
     * @param len
     * @return
     */
	public static String getNonceString(int len){
        String seed = "1234567890";
        StringBuffer tmp = new StringBuffer();
        for (int i = 0; i < len; i++) {
            tmp.append(seed.charAt(getRandomNumber(0,9)));
        }
        return tmp.toString();
    }
	
	
	/**
	 * 获取随机数
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getRandomNumber(int from, int to) {
        float a = from + (to - from) * (new Random().nextFloat());
        int b = (int) a;
        return ((a - b) > 0.5 ? 1 : 0) + b;
    }

}

package com.example.longecological.utils.encryption.rsa;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.utils.encryption.des.AESUtil;
import com.example.longecological.utils.encryption.md5.MD5Utils;
import com.example.longecological.utils.json.NetJsonUtils;

public class RSAUtilApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtilApp.class);
	public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    //加密最大长度（1024位密钥）
    private static final int maxEncryptLength = 117;

    //解密最大长度（1024位密钥）
    private static final int maxDecryptLength = 128;
    

    //解密base64
    public static byte[] decryptBASE64(String key) {
        return Base64.decode(key);
    }

    //加密base64
    public static String encryptBASE64(byte[] bytes) {
        return Base64.encode(bytes);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        LOGGER.info("解密transformation："+keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance(/*keyFactory.getAlgorithm()*/"RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return shardingProcess(cipher,maxDecryptLength,data);
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key)
            throws Exception {
        return decryptByPrivateKey(decryptBASE64(data),key);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        LOGGER.info("解密transformation："+keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm()/*"RSA/ECB/PKCS1Padding"*/);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return shardingProcess(cipher,maxDecryptLength,data);
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte data[], String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密

        Cipher cipher = Cipher.getInstance(/*keyFactory.getAlgorithm()*/"RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return shardingProcess(cipher,maxEncryptLength,data);
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        LOGGER.info("加密transformation："+keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance(/*keyFactory.getAlgorithm()*//*"RSA/ECB/PKCS1Padding"*/"RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return shardingProcess(cipher,maxEncryptLength,data);
    }



    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
        return keyMap;
    }

    private static byte[] shardingProcess(Cipher cipher, int MaxLength, byte[] data) throws Exception{
        //分段加密
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length = data.length;
        int offset = 0;
        int i = 0;
        byte[] cache;
        while(length - offset > 0){
            if(length - offset > MaxLength){  //加密数据长度大于最大加密长度
                cache = cipher.doFinal(data,offset,MaxLength);
            }else{
                cache = cipher.doFinal(data,offset,length-offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MaxLength;
        }
        cache = out.toByteArray();
        out.close();
        return cache;
    }

    public static void main(String[] args) throws Exception {
    	System.out.println("=======================开始加密啦=======================");
    	//header的key值
//    	/String en_key = Base64.encode(encryptByPublicKey(AESUtil.KEY.getBytes(),SysSecurityKeyConstant.publicKey_app)); 
    	String en_key = "FzRZlr0pehk93Eo0qj/q5hpFJvQBUxEPNkxCNuuKPe7ULWCsyQOmTBJhPwzQ BkDujApSONbRTq33JUW54nqidpmlMIE5ecgs3+8puw1X/d8f/+Di0aEgemT/ yXuBqscGkjN61CM1aOY1gZ53X02vmCj2WglvaSXhBEDIKUVo0hk=";
    	System.out.println("header的头部key加密结果："+en_key);
    	//数据对象
    	Map<String, Object> dataMap = new HashMap<>();
    	/*dataMap.put("login_type", "account");
    	dataMap.put("sys_user_account", "18888888888");
    	dataMap.put("login_password", MD5Utils.MD5Encode("cs123456", "UTF-8"));*/
    	
    	/*dataMap.put("user_account", "18772101525");
    	dataMap.put("nick_name", "沧海");
    	dataMap.put("invite_code", "38491245");
    	dataMap.put("login_password", MD5Utils.MD5Encode("hr2013125118", "UTF-8").toUpperCase());
    	dataMap.put("pay_password", MD5Utils.MD5Encode("123456", "UTF-8").toUpperCase());
    	dataMap.put("sms_code", "848322");
    	dataMap.put("device_type", "iOS");
    	dataMap.put("device_no", "HUAWEI");
    	dataMap.put("version_no", "1.1.0");
    	dataMap.put("app_version", "haha");*/
    	
    	//{"token":"9191|U2L29VB15985PTG3N4CS2OIBFKBTBS6B","order_id":"20210716202745402110935,20210716202745417799166","user_coupons_id":" , ","notes_str":" | ","address_id":"341"}
    	
    	
    	dataMap.put("token", "1|U542SH9WT0SPRK14W3ZJ46EM4HSTVIFJ");
    	dataMap.put("recharge_type", "02");
    	dataMap.put("account_type", "01");
    	dataMap.put("recharge_num", "0.01");
    	
    	/*dataMap.put("name", "测试");
    	dataMap.put("legal_id_card", "123456789012345678");
    	dataMap.put("legal_crad_photo", "defaultHeadPhoto.png,defaultHeadPhoto.png");
    	dataMap.put("bus_license", "defaultHeadPhoto.png");
    	dataMap.put("bank_card", "123456789012345678");
    	dataMap.put("bank_name", "中国农业银行");
    	dataMap.put("bank_branch_name", "中国农业银行支行");
    	dataMap.put("bank_crad_photo", "defaultHeadPhoto.png");
    	dataMap.put("reserve_tel", "18772101525");
    	dataMap.put("sms_code", "999999");*/
    	
    	
    	
    	/*dataMap.put("token", "1|095YNBI6QTI2ZR59F1T3F6F27U5SBZ6C");
    	dataMap.put("name", "测试");
    	dataMap.put("legal_id_card", "123456789012345678");
    	dataMap.put("legal_crad_photo", "defaultHeadPhoto.png,defaultHeadPhoto.png");
    	dataMap.put("bus_license", "defaultHeadPhoto.png");
    	dataMap.put("bank_card", "123456789012345678");
    	dataMap.put("bank_name", "中国农业银行");
    	dataMap.put("bank_branch_name", "中国农业银行支行");
    	dataMap.put("bank_crad_photo", "defaultHeadPhoto.png");
    	dataMap.put("reserve_tel", "18772101525");
    	dataMap.put("sms_code", "18772101525");*/
    	/*dataMap.put("recharge_voucher", "3c59a19e-83af-411c-8d8b-0c2ebec9ad3f.jpg");
    	dataMap.put("pay_password", MD5Utils.MD5Encode("123456", "UTF-8").toUpperCase());*/
    	
    	
    	/*dataMap.put("address_id", "340");*/
    	/**/
    	/*dataMap.put("goods_id", "77");
    	dataMap.put("user_type", "01");*/
    	/*
    	dataMap.put("user_coupons_id", " , ");
    	dataMap.put("notes_str", " | ");
    	dataMap.put("address_id", "341");*/
    	/*dataMap.put("shipper_code", "ZTO");
    	dataMap.put("logistic_code", "1234567890");*/
    	
    	
    	System.out.println("测试| ".split("\\|")[0]);
    	
    	
    	/*dataMap.put("order_id", "20210706170130713129512");
    	dataMap.put("pay_password", MD5Utils.MD5Encode("123456", "UTF-8"));*/
    	
    	/*dataMap.put("order_id", "20210706170130713129512");
    	dataMap.put("evaluate_goods_list", "[{\"goods_id\": \"14\",\"goods_name\": \"束一美 SOUEEUM富勒烯赋活修护面膜\",\"goods_num\": \"2\",\"goods_detail_show\": \"24e01aaa-48d1-489f-8e14-6432b9bbb58a.jpg\",\"character_value\": \"颜色:粉色,码数:35\",\"underline_cash\": \"108\",\"cash\": \"100.00\",\"evaluate_content\": \"超级喜欢，质量非常好\",\"evaluate_imgs\": \"24e01aaa-48d1-489f-8e14-6432b9bbb58a.jpg\",\"evaluate_score\": \"5\",\"is_anonymity\": \"0\"}]");*/
    	/*dataMap.put("address_id", "340");
    	dataMap.put("user_coupons_id", "");
    	dataMap.put("note", "早点发货");*/
    	
    	/*dataMap.put("goods_name", "束一美 SOUEEUM富勒烯赋活修护面膜");
    	dataMap.put("goods_show", "5f94caa1-0efb-4ed0-8427-093d7f9779f4.jpg");
    	dataMap.put("goods_navigation", "c75873b4-b3bc-4f9e-9af0-c96d0e8eefbb.jpg,008e221a-386f-4186-ad9d-ba55747a8c9e.jpg,268861d6-0310-45fc-8c2f-289cdbed2b1e.jpg");
    	dataMap.put("goods_describe", "57271abd-21f6-4df9-b25a-15e3d7909d3c.jpg,f647242b-879c-4012-a770-a0a3e50f2578.jpg,63a509ad-0f98-494f-9843-82a12786c1fd.jpg,fd99018d-a917-451c-915b-e7d9e2f43fd6.jpg,2f35237f-5b49-471f-983a-000651a2fd4c.jpg,81843f42-a8c9-4162-8fbf-cf92520dc939.jpg");
    	
    	dataMap.put("goods_detail_info_list", "[{\"underline_cash\": \"108\",\"cash\": \"100\",\"goods_detail_stock_num\": \"100\",\"goods_detail_show\": \"16961bde-1db4-4bcb-8632-863c475dff7c.jpg\",\"goods_character_value_id\": \"34,60\"},{\"underline_cash\": \"108\",\"cash\": \"100\",\"goods_detail_stock_num\": \"100\",\"goods_detail_show\": \"8cf0e005-f7bf-4f43-8f63-3a240bfa77da.jpg\",\"goods_character_value_id\": \"60,78\"},{\"underline_cash\": \"108\",\"cash\": \"100\",\"goods_detail_stock_num\": \"100\",\"goods_detail_show\": \"43e3b929-97e5-4b43-a7b7-ff83c30770fc.jpg\",\"goods_character_value_id\": \"34,62\"}{\"underline_cash\": \"108\",\"cash\": \"100\",\"goods_detail_stock_num\": \"100\",\"goods_detail_show\": \"24e01aaa-48d1-489f-8e14-6432b9bbb58a.jpg\",\"goods_character_value_id\": \"62,78\"}]");*/
    	
    	String dataString  = NetJsonUtils.mapToJson1(dataMap);
    	System.out.println("请求数据json对象："+dataString);
    	String encrypt = AESUtil.aesEncrypt(dataString, AESUtil.KEY);  
    	System.out.println("请求数据加密结果："+encrypt);
    	
    	System.out.println("=======================开始解密啦=======================");
    	String de_key = new String(RSAUtilApp.decryptByPrivateKey(Base64.decode(en_key),SysSecurityKeyConstant.privateKey_app), "utf-8");
    	System.out.println("header的头部key解密结果："+de_key);
    	String context = AESUtil.aesDecrypt(encrypt, de_key);
    	System.out.println("请求数据解密结果："+context);
    }
    
    
    
    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7'
            , '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    public static String bytesToHexString(byte[] data) {
        String string = "";
        try {
            char[] chars = new char[data.length << 1];
            //十六进制数一个四位，byte一个八位
            //data存在负数 java内部采用补码形式 无符号右移会出问题
            for (int i = 0, j = 0; i < data.length; i++) {
                chars[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
                chars[j++] = DIGITS[data[i] & 0x0F];
            }
            string = new String(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static byte[] hexStringToBytes(String data) {
        //两个四位十六进制字符合成一个八位byte
        byte[] bytes = new byte[data.length() / 2];
        char[] chars = data.toCharArray();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((hexCharToByte(chars[i * 2]) << 4) | hexCharToByte(chars[i * 2 + 1]));
        }
        return bytes;
    }
    
    private static byte hexCharToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}

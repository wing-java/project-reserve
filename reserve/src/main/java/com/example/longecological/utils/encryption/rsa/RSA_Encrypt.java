package com.example.longecological.utils.encryption.rsa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.Cipher;

import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.entity.ObjectClass;
import com.example.longecological.utils.json.AliJsonUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
 
public class RSA_Encrypt {
	
	/** 指定加密算法为RSA */
	private static String ALGORITHM = "RSA";
	
	/** 指定key的大小 */
	private static int KEYSIZE = 1024;
	
	/** 指定公钥存放文件 */
	private static String PUBLIC_KEY_FILE = "PublicKey";
	
	/** 指定私钥存放文件 */
	private static String PRIVATE_KEY_FILE = "PrivateKey";
 
	public static final String KEY_ALGORITHM = "RSA";   
	
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA"; 
	
	
	/**
	 * 生成密钥对
	 * @throws Exception
	 */
	public static void generateKeyPair() throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		/** 用对象流将生成的密钥写入文件 */
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
		oos1.writeObject(publicKey);
		oos2.writeObject(privateKey);
		/** 清空缓存，关闭文件输出流 */
		oos1.close();
		oos2.close();
	}
	 
	 
	/**
	 * 产生签名
	 * @param data
	 * @param privateKey
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
 
       // 取私钥对象   
       PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);   
 
       // 用私钥对信息生成数字签名   
       Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);   
       signature.initSign(priKey);   
       signature.update(data);   
 
       return encryptBASE64(signature.sign());   
   } 
	 
	 
   /**
    * 验证签名
    * @param data
    * @param publicKey
    * @param sign
    * @return
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
	   
	   
   /**
    * BASE64解密
    * @param key
    * @return
    * @throws Exception
    */
   public static byte[] decryptBASE64(String key) throws Exception{
       return (new BASE64Decoder()).decodeBuffer(key);
   }
	   
   
   /**
    * BASE64加密
    * @param key
    * @return
    * @throws Exception
    */
   public static String encryptBASE64(byte[] key)throws Exception{
       return (new BASE64Encoder()).encodeBuffer(key);
   }
	   
   
	/**
	* 加密方法 source： 源数据
	* 秘钥采取从文件读取的方法
	*/
	public static String encrypt(String source) throws Exception {
		//generateKeyPair();
		/** 将文件中的公钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		//字符串分段成数组
		int MaxBlockSize = KEYSIZE / 8 ;    
		String[] datas = splitString(source, MaxBlockSize - 11);
		
		//针对数组每一段进行加密，并拼接编码之后的加密结果
		String mi = "";
		for (String s : datas) {  
			mi += bcd2Str(cipher.doFinal(s.getBytes()));
			//mi += cipher.doFinal(s.getBytes()); 
		}
		return mi;
	}
	
	
	/**
	* 加密方法 source： 源数据
	* 秘钥采取读取参数的形式
	*/
	public static String encryptByParam(String source) throws Exception {
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, loadPublicKey());
		
		//字符串分段成数组
		int MaxBlockSize = KEYSIZE / 8 ;    
		String[] datas = splitString(source, MaxBlockSize - 11);
		
		
		//针对数组每一段进行加密，并拼接编码之后的加密结果
		String mi = "";
		for (String s : datas) {  
			byte[] output = cipher.doFinal(s.getBytes());
			mi += bcd2Str(cipher.doFinal(s.getBytes()));
			//mi += cipher.doFinal(s.getBytes()); 
		}
		return mi;
	}
	
	
	/**
	 * 字符串分段成数组
	 * @param string
	 * @param len
	 * @return
	 */
	public static String[] splitString(String string, int len) {  
		int x = string.length() / len;  
		int y = string.length() % len;  
		int z = 0;  
		if (y != 0) {  
			z = 1;  
		}  
		String[] strings = new String[x + z];  
		String str = "";  
		for (int i=0; i<x+z; i++) {  
			if (i==x+z-1 && y!=0) {  
				str = string.substring(i*len, i*len+y);  
			}else{  
				str = string.substring(i*len, i*len+len);  
			}  
			strings[i] = str;  
		}  
		return strings;  
	} 
	 
	
	/** 
	 * 字符转码
	*/  
	public static String bcd2Str(byte[] bytes) {  
		char temp[] = new char[bytes.length * 2], val;  
	 
		for (int i = 0; i < bytes.length; i++) {  
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);  
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
			val = (char) (bytes[i] & 0x0f);  
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0'); 
		}  
		return new String(temp);  
	}  
	 
	
	/**
	 * 解密算法 cryptograph:密文
	 * 秘钥采取从文件读取的方法
	 * @param cryptograph：源数据
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String cryptograph) throws Exception {
		/** 将文件中的私钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
		Key key = (Key) ois.readObject();
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);

		int key_len = KEYSIZE / 8 ; 
		byte[] bytes = cryptograph.getBytes();  
		byte[] bcd = ASCII_To_BCD(bytes, bytes.length); 
		System.err.println(bcd.length); 
		
		String ming = "";  
		byte[][] arrays = splitArray(bcd, key_len);  
		for(byte[] arr : arrays){  
			ming += new String(cipher.doFinal(arr));  
		}  
		return ming;  
	}
	
	
	
	/**
	 * 解密算法 cryptograph:密文
	 * 秘钥采取从文件读取的方法
	 * @param cryptograph：源数据
	 * @return
	 * @throws Exception
	 */
	public static String decryptByParam(String cryptograph) throws Exception {
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, loadPrivateKey());

		int key_len = KEYSIZE / 8 ; 
		byte[] bytes = cryptograph.getBytes();  
		byte[] bcd = ASCII_To_BCD(bytes, bytes.length); 
		System.err.println(bcd.length); 
		
		String ming = "";  
		byte[][] arrays = splitArray(bcd, key_len);  
		for(byte[] arr : arrays){  
			ming += new String(cipher.doFinal(arr));  
		}  
		return ming;  
	}
	 
	
	
	/** 
	*  
	*/  
	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {  
		byte[] bcd = new byte[asc_len / 2];  
		int j = 0;  
		for (int i = 0; i < (asc_len + 1) / 2; i++) {  
		bcd[i] = asc_to_bcd(ascii[j++]);  
		bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));  
		}  
		return bcd;  
	} 
	 
	
	public static byte asc_to_bcd(byte asc) {  
		byte bcd;  
		if ((asc >= '0') && (asc <= '9'))  
		bcd = (byte) (asc - '0');  
		else if ((asc >= 'A') && (asc <= 'F'))  
		bcd = (byte) (asc - 'A' + 10);  
		else if ((asc >= 'a') && (asc <= 'f'))  
		bcd = (byte) (asc - 'a' + 10);  
		else  
		bcd = (byte) (asc - 48);  
		return bcd;  
	}  
	 
	
	/** 
	*/  
	public static byte[][] splitArray(byte[] data,int len){  
		int x = data.length / len;  
		int y = data.length % len;  
		int z = 0;  
		if(y!=0){  
		z = 1;  
		}  
		byte[][] arrays = new byte[x+z][];  
		byte[] arr;  
		for(int i=0; i<x+z; i++){  
		arr = new byte[len];  
		if(i==x+z-1 && y!=0){  
		System.arraycopy(data, i*len, arr, 0, y);  
		}else{  
		System.arraycopy(data, i*len, arr, 0, len);  
		}  
		arrays[i] = arr;  
		}  
		return arrays;  
	} 
	 
	
	/**
	 * 从文件中读取公钥信息
	 * @return
	 * @throws Exception
	 */
	public static String getpublickey() throws Exception{
		/** 将文件中的公钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
		Key key = (Key) ois.readObject();
		String publickey=encryptBASE64(key.getEncoded());
		return publickey;
	}
	 
	
	/**
	 * 从文件中读取私钥信息
	 * @return
	 * @throws Exception
	 */
	public static String getprivatekey() throws Exception{
		/** 将文件中的私钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
		Key key = (Key) ois.readObject();
		String privatekey=encryptBASE64(key.getEncoded());
		return privatekey;
	}
	
	
	
	/**
	 * 从系统参数中读取公钥信息
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey loadPublicKey()throws Exception{
		try{
			byte[] buffer = javax.xml.bind.DatatypeConverter.parseBase64Binary(SysSecurityKeyConstant.publicKey_app);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		}catch(NoSuchAlgorithmException e){
			throw new Exception("无此算法", e);
		}catch(InvalidKeySpecException e){
			throw new Exception("公钥非法", e);
		}catch(NullPointerException e){
			throw new Exception("公钥数据为空", e);
		}
	}
	
	
	/**
	 * 从系统参数中读取私钥信息
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey loadPrivateKey()throws Exception{
		try{
			byte[] buffer = javax.xml.bind.DatatypeConverter.parseBase64Binary(SysSecurityKeyConstant.privateKey_app);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		}catch(NoSuchAlgorithmException e){
			throw new Exception("无此算法", e);
		}catch(InvalidKeySpecException e){
			throw new Exception("私钥非法", e);
		}catch(NullPointerException e){
			throw new Exception("私钥数据为空", e);
		}
	}
	
	
	
	/**
	 * map对象数据解密
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public static Map<String, Object> mapDataEecryption(Map<String, Object> map) throws Exception {
		//数据解密
		String resultString = decryptByParam(map.get("data").toString());
		return AliJsonUtils.jsonStringToMap1(resultString);
	}
	
	
	
	/**
	 * 实体类对象数据解密
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public static<T> Object domainDataEecryption(ObjectClass objectClass, Class<T> obj) throws Exception {
		//数据解密
		String resultString = decryptByParam(objectClass.getData());
		return AliJsonUtils.jsonToBean1(resultString,obj);
	}
	
	
	/**
	 * 字符串处理 字节 数组转 String
	 * @param b
	 * @return
	 */
	public static String base64ToStr(byte[] b){
		return javax.xml.bind.DatatypeConverter.printBase64Binary(b);
	}
	
	
	
	/**
	 * 字符串处理 字符串 转字节数组
	 * @param str
	 * @return
	 */
	public static byte[] strToBase64(String str){
		return javax.xml.bind.DatatypeConverter.parseBase64Binary(str);
	}
	
	
	
	
	public static void main(String[] args)throws Exception {
		//RSA_Encrypt.generateKeyPair();
		String source ="{\"user_tel\":\"18303769975\",\"login_password\":\"1111111111111\"}";// 要加密的字符串
		String cryptograph = RSA_Encrypt.encryptByParam(source);// 生成的密文
		System.out.println("加密之后的密文是："+cryptograph);
		String target = RSA_Encrypt.decryptByParam(cryptograph);// 解密密文
		System.out.println("解密之后的明文是："+target);
		
		
		byte[]bytes =source.getBytes();  
		// 产生签名   
		String sign = RSA_Encrypt.sign(bytes, SysSecurityKeyConstant.privateKey_app);   
		System.err.println("签名:" +sign);   
		  
		// 验证签名   
		boolean status = RSA_Encrypt.verify(bytes, SysSecurityKeyConstant.publicKey_app,sign);   
		System.err.println("状态:" +status); 
		
	}
		
}

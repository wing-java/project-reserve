package com.example.longecological.utils.encryption.rsa;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.entity.ObjectClass;
import com.example.longecological.utils.json.AliJsonUtils;



public class RSACrypt {
	
	/**
	 * 生成公密钥
	 */
	public static void getKeys(){
		KeyPairGenerator keyPairGen = null;
		try{
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		}catch(Exception e){
			e.printStackTrace();
		}
		//初始化秘钥对生成器，秘钥大小为96-1024位
		keyPairGen.initialize(1024, new SecureRandom());
		//生成一个秘钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		
		//得到公钥字符串
		String publicKey = base64ToStr(keyPair.getPublic().getEncoded());
		//得到私钥字符串
		String privateKey = base64ToStr(keyPair.getPrivate().getEncoded());
		
		System.out.println("publicKey="+publicKey);
		
		System.out.println("privateKey="+privateKey);
	}
	
	
	/**
	 * 获取公钥对象
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
	 * 获取密钥对象
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
	 * 加密
	 * @param plainTextData
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(byte[] plainTextData)throws Exception{
		if(loadPublicKey() == null){
			throw new Exception("加密公钥为空，请设置");
		}
		Cipher cipher = null;
		try{
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, loadPublicKey());
			byte[] output = cipher.doFinal(plainTextData);
			return base64ToStr(output);
		}catch(NoSuchAlgorithmException e){
			throw new Exception("无法加密算法", e);
		}catch(NoSuchPaddingException e){
			e.printStackTrace();
			return null;
		}catch(InvalidKeyException e){
			throw new Exception("公钥非法", e);
		}catch(IllegalBlockSizeException e){
			throw new Exception("明文长度非法", e);
		}catch(BadPaddingException e){
			throw new Exception("明文数据已损坏", e);
		}
		
	}
	
	/**
	 * 解密
	 * @param cipherData
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(byte[] cipherData)throws Exception{
		if(loadPrivateKey() == null){
			throw new Exception("加密私钥为空，请设置");
		}
		Cipher cipher = null;
		try{
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, loadPrivateKey());
			byte[] output = cipher.doFinal(cipherData);
			return new String(output);
		}catch(NoSuchAlgorithmException e){
			throw new Exception("无法解密算法", e);
		}catch(NoSuchPaddingException e){
			e.printStackTrace();
			throw new Exception("无法解密算法", e);
		}catch(InvalidKeyException e){
			throw new Exception("私钥非法", e);
		}catch(IllegalBlockSizeException e){
			throw new Exception("密文长度非法", e);
		}catch(BadPaddingException e){
			throw new Exception("密文数据已损坏", e);
		}
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
	
	
	
	/**
	 * map对象数据解密
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public static Map<String, Object> mapDataEecryption(Map<String, Object> map) throws Exception {
		//数据解密
		String resultString = decrypt(strToBase64(map.get("data").toString()));
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
		String resultString = decrypt(strToBase64(objectClass.getData()));
		return AliJsonUtils.jsonToBean1(resultString,obj);
	}
	
	
	
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		getKeys();
		
		/*try {
			//String extension = "{\'customercardid\':\'"+dto.getId()+"',\'customid\':\'"+dto.getCustomid()+"'}";
			//String str = "{\'tel\':\'18802671616',\'password\':\'1111111111'}";
			String str = "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"";
			String str ="{\"user_tel\":\"183037691111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111975\",\"login_password\":\"1111111111111111111111111111111111111111111\"}";// 要加密的字符串
			str = encrypt(str.getBytes());
			System.out.println("加密文："+str);
			//SnCKRmFyoQYz1YSaJFSyGIvuLhk9+S5QQh6dsW5lAZ/GQN2g5NQCyPjXntWxden344AXd+qa2nWerHtGCf7eV+f2roOic4SnZfL4yi+zr9WJyM2WwAiss5gu/bnyhSRGzEMDRmtgBIuANryrFVz6paXkdDBxG9DR7jRkFtYxE3w=
			//SnCKRmFyoQYz1YSaJFSyGIvuLhk9%2BS5QQh6dsW5lAZ%2FGQN2g5NQCyPjXntWxden344AXd%2Bqa2nWerHtGCf7eV%2Bf2roOic4SnZfL4yi%2Bzr9WJyM2WwAiss5gu%2FbnyhSRGzEMDRmtgBIuANryrFVz6paXkdDBxG9DR7jRkFtYxE3w%3D
			//SnCKRmFyoQYz1YSaJFSyGIvuLhk9 S5QQh6dsW5lAZ/GQN2g5NQCyPjXntWxden344AXd qa2nWerHtGCf7eV f2roOic4SnZfL4yi zr9WJyM2WwAiss5gu/bnyhSRGzEMDRmtgBIuANryrFVz6paXkdDBxG9DR7jRkFtYxE3w==
			System.out.println("解密后："+decrypt(strToBase64("MPGU4AgT2m+Y6BF5DudqVbkctZIkPP1iO0H1rO2/nj+wXO7KYVB0BxhtZ80cBzh0cSE3FnvL7fomLkUJS5255uEUtDV3p3dgNR0bMfb4SUbuLb5VAcRDpa/jOqPzUgQ0j4L4gquGJ7pRauWYJYW7EAgtgqfTyzwwx83ePrCNelU=")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	
	
	
}
		
	
	
	
	
	
	


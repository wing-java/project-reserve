package com.ruoyi.common.utils.string;

import java.util.regex.Pattern;


/**
 * 正则表达式相关类
 * @author Administrator
 *
 */
public class RegexUtil {
	
	/**
	 * 正则表达式，交易金额校验（包含0）
	 * @param str
	 * @return
	 */
	public static boolean isVaildTradeNum(String tardeNum) {  
		if("".equals(tardeNum)||null==tardeNum) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(([0-9][0-9]*)|(([0]\\.\\d{1,2}|[1-9][0-9]*\\.\\d{1,2})))$");  
			return pattern.matcher(tardeNum).matches();  
		}
	}
	
	
	/**
	 * 正则表达式，交易金额校验（不包含0）
	 * @param str
	 * @return
	 */
	public static boolean isVaildTradeNumNoZero(String tardeNum) {  
		if("".equals(tardeNum)||null==tardeNum) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(([1-9][0-9]*)|(([0]\\.\\d{1,2}|[1-9][0-9]*\\.\\d{1,2})))$");  
			return pattern.matcher(tardeNum).matches();  
		}
	}
	
	
	
	/**
	 * 邮箱校验
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email){
		if("".equals(email)||null==email) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^\\w+@[a-zA-Z0-9]{2,10}(?:\\.[a-z]{2,4}){1,3}$");  
			return pattern.matcher(email).matches();  
		}
	}
	
	
	/**
	 * 手机号码校验
	 * @param tel
	 * @return
	 */
	public static boolean isValidTel(String tel){
		if("".equals(tel)||null==tel) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^1(3|4|5|6|7|8|9)\\d{9}");  
			return pattern.matcher(tel).matches();  
		}
	}
	
	
	/**
	 * 6位整数校验
	 * @param num
	 * @return
	 */
	public static boolean isValidSixNum(String num){
		if("".equals(num)||null==num) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^\\d{6}$");  
			return pattern.matcher(num).matches();  
		}
	}
	
	/**
	 * 6位整数校验
	 * @param num
	 * @return
	 */
	public static boolean isValidEightNum(String num){
		if("".equals(num)||null==num) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^\\d{8}$");  
			return pattern.matcher(num).matches();  
		}
	}
	
	
	/**
	 * 交易所UUID校验
	 * @param str
	 * @return
	 */
	public static boolean isExchangeUUID(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(\\d{8}|\\d{9})$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	public static boolean isNotNumEmail(String num){
		if("".equals(num)||null==num) {
			return false;
		}else {
			Pattern pattern1 = Pattern.compile("^[0-9]*$");  
			Pattern pattern2 = Pattern.compile("^\\w+@[a-zA-Z0-9]{2,10}(?:\\.[a-z]{2,4}){1,3}$");  
			return (!pattern1.matcher(num).matches())&&(!pattern2.matcher(num).matches());  
		}
	}
	
	
	/**
	 * 要求：可以包含数字、字母、下划线，并且要同时含有数字和字母，且长度要在4-8位之间
	 * @param str
	 * @return
	 */
	public static boolean isValidName(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{4,8}$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	/**
	 * 要求：可以包含数字、字母、下划线，并且要同时含有数字和字母，且长度要在6-16位之间
	 * @param str
	 * @return
	 */
	public static boolean isValidPass(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,30}$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	/**
	 * 要求：可以包含数字、字母、下划线，并且要同时含有数字和字母，且长度要在6-20位之间
	 * @param str
	 * @return
	 */
	public static boolean isValidQQ(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,20}$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	/**
	 * 姓名校验：要求（两到四位中文）
	 * @param str
	 * @return
	 */
	public static boolean isValidTrueName(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]{2,4}$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	/**
	 * 身份证号校验
	 * @param str
	 * @return
	 */
	public static boolean isValidIdCard(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(\\d{15}|\\d{18}|\\d{17}[x|X])$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	
	
	/**
	 * 银行账号校验
	 * @param str
	 * @return
	 */
	public static boolean isValidBankCard(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(\\d{16}|\\d{19}|\\d{18})$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	/**
	 * 正整数校验
	 * @param str
	 * @return
	 */
	public static boolean isValidNum(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^[1-9]\\d*$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	/**
	  * 判断ETH地址是否规范
	  * @param address
	  * @return
	  */
	 public static boolean isValidETHAddress(String address){
		 try{
			 if("0x".equals(address.substring(0,2))&&address.length()==42){
				 return true;
			 }
			 return false;
		 }catch(Exception e){
			 e.printStackTrace();
			 return false;
		 }
	 }
	
	 
	 /**
	  * 校验是否是0~1的数字（四位包含0和1）
	  * @param str
	  * @return
	  */
	 public static boolean isValidTradeRate(String str){
		 if("".equals(str)||null==str) {
			 return false;
		 }else {
			 Pattern pattern = Pattern.compile("^(0(\\.\\d{1,4})?|1(\\.0{1,4})?)$");  
			 return pattern.matcher(str).matches();
		 }
	 }
	
	
	public static void main(String[] args) {
		System.out.println(isValidTradeRate("1.2"));
	}

}

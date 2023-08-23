package com.example.longecological.utils.string;

import java.util.regex.Pattern;


/**
 * 正则表达式相关类
 * @author Administrator
 *
 */
public class RegexUtil {
	
	
	//=============================================整数正则表达式==================================
	/**
	 * 要求：2到11位（可以包含数字、字母、下划线、中文）
	 * @param str
	 * @return
	 */
	public static boolean isValidAccountFirst(String str){
		if("".equals(str)||null==str || str.length()<2 || str.length()>11) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$");  
			return pattern.matcher(str).matches();
		}
	}
	/**
	 * 要求：4到16位（可以包含字母，数字，下划线，减号）
	 * @param str
	 * @return
	 */
	public static boolean isValidAccountSecond(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]{4,16}$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	
	//=============================================整数正则表达式==================================
	/**
	 * 正整数正则（包含0）
	 * @param num
	 * @return
	 */
	public static boolean isValidPositiveInteger(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^\\d+$");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 正整数正则（不包含0）
	 * @param num
	 * @return
	 */
	public static boolean isValidPositiveIntegerNotZero(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^[1-9]\\d*$");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 负整数正则（不包含0）
	 * @param num
	 * @return
	 */
	public static boolean isValidNegativeInteger(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^-\\d+$");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 整数正则
	 * @param num
	 * @return
	 */
	public static boolean isValidInteger(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^-?\\d+$");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 6位整数校验
	 * @param num
	 * @return
	 */
	public static boolean isValidSixInteger(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^\\d{6}$");  
			return pattern.matcher(str).matches();  
		}
	}
	
	//=============================================数字（可以使正数也可以是浮点数）正则表达式==================================
	/**
	 * 正数校验
	 * @param num
	 * @return
	 */
	public static boolean isValidPositiveNumber(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^\\d*\\.?\\d+$");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 负数正则
	 * @param num
	 * @return
	 */
	public static boolean isValidNegativeNumber(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^-\\d*\\.?\\d+$");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 数字正则
	 * @param num
	 * @return
	 */
	public static boolean isValidNumber(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^-?\\d*\\.?\\d+$");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 交易金额校验（最多两位小数的正数，包含0）
	 * @param str
	 * @return
	 */
	public static boolean isVaildTradeNum(String str) {  
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(([0-9][0-9]*)|(([0]\\.\\d{1,2}|[1-9][0-9]*\\.\\d{1,4})))$");  
			return pattern.matcher(str).matches();  
		}
	}
	
	
	//=============================================邮箱正则表达式==================================
	/**
	 * 邮箱校验
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailFirst(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^\\w+@[a-zA-Z0-9]{2,10}(?:\\.[a-z]{2,4}){1,3}$");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 邮箱校验
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailSecond(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$");  
			return pattern.matcher(str).matches();  
		}
	}
	
	
	//=============================================手机号码正则表达式==================================
	/**
	 * 手机号码校验（只判断前两位固定，后面有九位）
	 * @param tel
	 * @return
	 */
	public static boolean isValidTelFirst(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^1(3|4|5|6|7|8|9)\\d{9}");  
			return pattern.matcher(str).matches();  
		}
	}
	/**
	 * 手机号码校验（只判断前三位固定，后面有八位）
	 * @param tel
	 * @return
	 */
	public static boolean isValidTelSecond(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");  
			return pattern.matcher(str).matches();  
		}
	}
	
	
	
	//=============================================身份证号正则表达式==================================
	/**
	 * 身份证号校验（15、18或者17+x/X）
	 * @param str
	 * @return
	 */
	public static boolean isValidIdCardFirst(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(\\d{15}|\\d{18}|\\d{17}[x|X])$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	
	//=============================================姓名正则表达式==================================
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
	
	
	//=============================================银行账号正则表达式==================================
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
	
	
	
	//=============================================交易所UID和ETH地址正则表达式==================================
	/**
	 * 交易所UUID校验
	 * @param str
	 * @return
	 */
	public static boolean isValidExchangeUUID(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(\\d{8}|\\d{9})$");  
			return pattern.matcher(str).matches();
		}
	}
	/**
	  * 判断ETH地址是否规范
	  * @param address
	  * @return
	  */
	 public static boolean isValidETHAddress(String str){
		 try{
			 if("0x".equals(str.substring(0,2))&&str.length()==42){
				 return true;
			 }
			 return false;
		 }catch(Exception e){
			 e.printStackTrace();
			 return false;
		 }
	 }
	
	
	 
	//=============================================车牌号正则表达式==================================
	/**
	 * 车牌号校验
	 * @param str
	 * @return
	 */
	public static boolean isValidLicensePlateNumber(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	
	//=============================================QQ号码正则表达式==================================
	/**
	 * QQ号校验
	 * @param str
	 * @return
	 */
	public static boolean isValidQQNumber(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^[1-9][0-9]{4,10}$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	
	//==============================================微信号正则表达式==================================
	/**
	 * 微信号校验
	 * @param str
	 * @return
	 */
	public static boolean isValidWechatNumber(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^[a-zA-Z]([-_a-zA-Z0-9]{5,19})+$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	
	//==============================================十六进制颜色正则表达式==================================
	/**
	 * 十六进制颜色校验
	 * @param str
	 * @return
	 */
	public static boolean isValidHexadecimalColor(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");  
			return pattern.matcher(str).matches();
		}
	}
	
	
	//==============================================IPv4地址正则表达式==================================
	/**
	 * IPv4地址校验
	 * @param str
	 * @return
	 */
	public static boolean isValidIPv4(String str){
		if("".equals(str)||null==str) {
			return false;
		}else {
			Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");  
			return pattern.matcher(str).matches();
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
	
	
	
	public static void main(String[] args) {
		System.out.println(isVaildTradeNum("10"));
	}

}

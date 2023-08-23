package com.example.longecological.constant;

public class OperParamConstant {

	//用户登录类型类型（选取====》账号登录、token登录）
	public static final String[] USER_LOGIN_TYPE = {"account","token","code"};
	/**
	 * 用户登录类型：账号登录
	 */
	public static final String USER_LOGIN_TYPE_ACCOUNT = "account";
	/**
	 * 用户登录类型：token登录
	 */
	public static final String USER_LOGIN_TYPE_TOKEN = "token";
	/**
	 * 用户登录类型：短信验证码登录
	 */
	public static final String USER_LOGIN_TYPE_CODE = "code";
	
	
	//用户设备类型（选取====》android、iOS）
	public static final String[] USER_DEVICE_TYPE = {"android","iOS"};
	
	
	//用户收货地址操作类型（选取====》新增、编辑、删除、设置默认）
	public static final String[] USER_ADDRESS_OPER_UPDATE_TYPE = {"user_address_add","user_address_edit","user_address_del","user_address_set"};
	//收货地址必备参数（收货人、电话、地址）
	public static final String[] USER_ADDRESS_MUST_PARAM = {"name","tel","address"};
	/**
	 * 用户收货地址操作类型：新增
	 */
	public static final String USER_ADDRESS_OPER_UPDATE_TYPE_ADD = "user_address_add";
	/**
	 * 用户收货地址操作类型：编辑
	 */
	public static final String USER_ADDRESS_OPER_UPDATE_TYPE_EDIT = "user_address_edit";
	/**
	 * 用户收货地址操作类型：删除
	 */
	public static final String USER_ADDRESS_OPER_UPDATE_TYPE_DEL = "user_address_del";
	/**
	 * 用户收货地址操作类型：设置默认
	 */
	public static final String USER_ADDRESS_OPER_UPDATE_TYPE_SET = "user_address_set";
	
	
	//用户账户操作类型（选取====》新增、编辑、删除、设置默认）
	public static final String[] USER_ACCOUNT_OPER_UPDATE_TYPE = {"user_account_add","user_account_edit","user_account_del","user_account_set"};
	/**
	 * 用户账户操作类型：新增
	 */
	public static final String USER_ACCOUNT_OPER_UPDATE_TYPE_ADD = "user_account_add";
	/**
	 * 用户账户操作类型：编辑
	 */
	public static final String USER_ACCOUNT_OPER_UPDATE_TYPE_EDIT = "user_account_edit";
	/**
	 * 用户账户操作类型：删除
	 */
	public static final String USER_ACCOUNT_OPER_UPDATE_TYPE_DEL = "user_account_del";
	/**
	 * 用户账户操作类型：设置默认
	 */
	public static final String USER_ACCOUNT_OPER_UPDATE_TYPE_SET = "user_account_set";
	
	
	//用户账户类型（选取====》支付宝、微信、银行卡）
	public static final String[] USER_ACCOUNT_TYPE = {"01","02","03"};
	//账户类型1必备参数（账号、账户名、开户行、开户行支行）
	public static final String[] USER_ACOOUNT_TYPE_03_MUST_PARAM = {"account","account_name","bank_name"};
	//账户类型非1必备参数（账号）
	public static final String[] USER_ACOOUNT_TYPE_NO_03_MUST_PARAM = {"account","account_name","account_img"};
	/**
	 * 用户账户类型：支付宝
	 */
	public static final String USER_ACCOUNT_TYPE_01 = "01";
	/**
	 * 用户账户类型：微信
	 */
	public static final String USER_ACCOUNT_TYPE_02 = "02";
	/**
	 * 用户账户类型：银行卡
	 */
	public static final String USER_ACCOUNT_TYPE_03 = "03";
	
	
	//用户提现账户类型（选取====》可提现账户）
	public static final String[] USER_CASH_ACCOUNT_TYPE = {"01"};
	
	
	//允许上传的文件类型
	public static final String[] UPLOAD_IMG_TYPE = {".jpg",".png"};
	
	
	//用户线下充值类型（选取====》支付宝、微信、银行卡）
	public static final String[] USER_RECHARGE_OFFLINE_RECHARGE_TYPE = {"01","02","03"};
	//用户线下充值账户类型（选取====》余额）
	public static final String[] USER_RECHARGE_OFFLINE_ACCOUNT_TYPE = {"01"};
	
	//用户线上充值类型（选取====》川军
	public static final String[] USER_RECHARGE_ONLINE_RECHARGE_TYPE = {"01"};
	//用户线上充值账户类型（选取====》余额）
	public static final String[] USER_RECHARGE_ONLINE_ACCOUNT_TYPE = {"01"};
	
	
	//用户线下捐赠类型（选取====》支付宝、微信、银行卡）
	public static final String[] USER_DONATE_OFFLINE_DONATE_TYPE = {"01","02","03"};
	
	
	//代理申请必传参数：姓名、身份证号、身份证件照、营业执照、银行卡号、开户行、开户支行、银行卡照片、预留手机号
	public static final String[] APPLY_USER_AGENT_MUST_PARAM = {"name","legal_id_card","legal_crad_photo","bus_license","bank_name","bank_branch_name","bank_card","bank_crad_photo","reserve_tel"};
	
}
package com.example.longecological.constant;

public class RedisNameConstants {
	
	/**
	 * redis前缀名称
	 */
	public static final String redis_name_key_prefix = "reserve_";

	/**
	 * 参数表key值前缀
	 */
	public static final String sys_param = redis_name_key_prefix + "sys_param_";
	
	/**
	 * 系统功能模块参数表key值前缀
	 */
	public static final String sys_function_lock_param = redis_name_key_prefix + "sys_function_lock_param_";
	
	/**
	 * 系统功能模块参数表列表key值前缀
	 */
	public static final String sys_function_lock_param_list = redis_name_key_prefix + "sys_function_lock_param_list";
	
	
	/**
	 * 七牛Token key值前缀
	 */
	public static final String qiniu_token = redis_name_key_prefix + "qiniu_token";
	
	
	/**
	 * 用户TOKEN前缀
	 */
	public static final String user_token = redis_name_key_prefix + "user_token_";
	
	
	/**
	 * 图形验证码前缀
	 */
	public static final String img_code = redis_name_key_prefix + "img_code_";
	
	/**
	 * 用户注册第一步通过标识
	 */
	public static final String user_register_first_valid_flag = redis_name_key_prefix + "user_register_first_valid_flag_";
	
	
	/**
	 * 修改手机号校验通过的标识前缀
	 */
	public static final String modify_tel_valid_flag = redis_name_key_prefix + "modify_tel_valid_flag_";
	

	/**
	 * 系统版本信息
	 */
	public static final String sys_version_info_new = redis_name_key_prefix + "sys_version_info_new_";
	
	
	/**
	 * 系统最新公告通知
	 */
	public static final String sys_notice_info_new = redis_name_key_prefix + "sys_notice_info_new";
	/**
	 * 系统公告通知列表
	 */
	public static final String sys_notice_info_list = redis_name_key_prefix + "sys_notice_info_list_";
	/**
	 * 系统公告通知列表
	 */
	public static final String sys_notice_info_detail = redis_name_key_prefix + "sys_notice_info_detail_";
	
	
	/**
	 * 系统最新新闻通知
	 */
	public static final String sys_news_info_new = redis_name_key_prefix + "sys_news_info_new_";
	/**
	 * 系统新闻资讯列表
	 */
	public static final String sys_news_info_list = redis_name_key_prefix + "sys_news_info_list_";
	/**
	 * 系统新闻资讯详情
	 */
	public static final String sys_news_info_detail = redis_name_key_prefix + "sys_news_info_detail_";
	
	
	/**
	 * 用户基础信息前缀===>id
	 */
	public static final String user_info_id = redis_name_key_prefix + "user_info_id_";
	/**
	 * 用户冻结信息前缀===>id
	 */
	public static final String user_freeze = redis_name_key_prefix + "user_freeze_";
	
	
	/**
	 * APP图片
	 */
	public static final String sys_app_img = redis_name_key_prefix + "sys_app_img_";
	
	
	/**
	 * 短信验证码校验成功的唯一标识
	 */
	public static final String send_code = redis_name_key_prefix + "send_code_";
	
	
	/**
	 * 商品分类集合
	 */
	public static final String goods_category_list= redis_name_key_prefix + "goods_category_list_";
	
	
	/**
	 * 用户支付订单校验标识
	 */
	public static final String user_pay_order_valid_flag = redis_name_key_prefix + "user_pay_order_valid_flag_";
	
	
	/**
	 * 用户地址列表
	 */
	public static final String user_address_list = redis_name_key_prefix + "user_address_list_";
	/**
	 * 查询地址详情
	 */
	public static final String user_address_id = redis_name_key_prefix + "user_address_id_";
	/**
	 * 查询常用地址
	 */
	public static final String common_user_address_user_id = redis_name_key_prefix + "common_user_address_user_id";
	
	
	/**
	 * 用户意见反馈列表
	 */
	public static final String user_feedback_list = redis_name_key_prefix + "user_feedback_list_";
	/**
	 * 查询意见反馈详情
	 */
	public static final String user_feedback_id = redis_name_key_prefix + "user_feedback_id_";
	

	/**
	 * 系统账户列表
	 */
	public static final String sys_account_list = redis_name_key_prefix + "sys_account_list_";
	
	
	
	/**
	 * 广告封面列表
	 */
	public static final String goods_advertise_cover_list = redis_name_key_prefix + "goods_advertise_cover_list_";
	/**
	 * 广告封面
	 */
	public static final String goods_advertise_cover_info = redis_name_key_prefix + "goods_advertise_cover_info_";

	
	/**
	 * 用户收款账户列表
	 */
	public static final String user_account_list = redis_name_key_prefix + "user_account_list_";
	/**
	 * 用户收款账户信息
	 */
	public static final String user_account_id = redis_name_key_prefix + "user_account_id_";
	/**
	 * 用户收款账户信息
	 */
	public static final String user_account_info = redis_name_key_prefix + "user_account_info_";
	
	
	/**
	 * 用户提现记录列表
	 */
	public static final String user_cash_record_list = redis_name_key_prefix + "user_cash_record_list_";
	
	/**
	 * 用户线下充值记录列表
	 */
	public static final String user_recharge_offline_list = redis_name_key_prefix + "user_recharge_offline_list_";
	/**
	 * 用户线上充值记录列表
	 */
	public static final String user_recharge_online_list = redis_name_key_prefix + "user_recharge_online_list_";
	
	
	/**
	 * 用户线下捐赠记录列表
	 */
	public static final String user_donate_offline_list = redis_name_key_prefix + "user_donate_offline_list_";
	/**
	 * 用户线上捐赠记录列表
	 */
	public static final String user_donate_online_list = redis_name_key_prefix + "user_donate_online_list_";
	
	
	/**
	 * 平台产品列表
	 */
	public static final String sys_product_list = redis_name_key_prefix + "sys_product_list_";
	/**
	 * 平台产品详情
	 */
	public static final String sys_product_detail = redis_name_key_prefix + "sys_product_detail_";
	/**
	 * 用户产品订单详情
	 */
	public static final String user_product_order_detail = redis_name_key_prefix + "user_product_order_detail_";
	/**
	 * 用户产品订单列表
	 */
	public static final String user_product_order_list = redis_name_key_prefix + "user_product_order_list_";
	/**
	 * 系统协议合同
	 */
	public static final String sys_contract_info_type = redis_name_key_prefix + "sys_contract_info_type_";
	
	/**
	 * 白名单
	 */
	public static final String ip_md5 = redis_name_key_prefix + "ip_md5_";
}

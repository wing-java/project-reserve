package com.ruoyi.common.constant;

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
	 * 系统协议合同
	 */
	public static final String sys_contract_info_type = redis_name_key_prefix + "sys_contract_info_type_";

	
	/**
	 * 用户基础信息前缀===>id
	 */
	public static final String user_info_id = redis_name_key_prefix + "user_info_id_";
	/**
	 * 用户实名认证信息前缀===>id
	 */
	public static final String user_info_auth_status_id = redis_name_key_prefix + "user_info_auth_status_id_";
	/**
	 * 用户冻结信息前缀===>id
	 */
	public static final String user_freeze = redis_name_key_prefix + "user_freeze_";
	
	
	/**
	 * APP图片
	 */
	public static final String sys_app_img = redis_name_key_prefix + "sys_app_img_";
	
	/**
	 * 发送邮箱账号前缀
	 */
	public static final String sys_send_email_account = redis_name_key_prefix + "sys_send_email_account_";
	/**
	 * 邮箱账号数量
	 */
	public static final String sys_email_number = redis_name_key_prefix + "sys_email_number_";

	/**
	 * 交易所链接URL
	 */
	public static final String sys_exchange_url= redis_name_key_prefix + "sys_exchange_url";
	
	
	/**
	 * 短信验证码校验成功的唯一标识
	 */
	public static final String send_code = redis_name_key_prefix + "send_code_";
	
	
	/**
	 * 商品列表
	 */
	public static final String goods_list = redis_name_key_prefix + "goods_list_";
	/**
	 * 热销商品列表（推荐商品）
	 */
	public static final String goods_index_hot_list = redis_name_key_prefix + "goods_index_hot_list_";
	/**
	 * 用户推荐商品列表
	 */
	public static final String goods_user_hot_list = redis_name_key_prefix + "goods_user_hot_list_";
	/**
	 * 供应商详情
	 */
	public static final String supplier_info_id = redis_name_key_prefix + "supplier_info_id_";
	/**
	 * 商品信息
	 */
	public static final String goods_info = redis_name_key_prefix + "goods_info_";
	/**
	 * 商品详情列表
	 */
	public static final String goods_detail_list = redis_name_key_prefix + "goods_detail_list_";
	/**
	 * 商品属性列表
	 */
	public static final String goods_character_list = redis_name_key_prefix + "goods_character_list_";
	/**
	 * 商品最新评价
	 */
	public static final String new_goods_evaluate = redis_name_key_prefix + "new_goods_evaluate_";
	/**
	 * 商品评价列表
	 */
	public static final String goods_evaluate_list = redis_name_key_prefix + "goods_evaluate_list_";
	/**
	 * 商品分类集合
	 */
	public static final String goods_category_list= redis_name_key_prefix + "goods_category_list_";
	
	
	/**
	 * 订单列表
	 */
	public static final String order_list = redis_name_key_prefix + "order_list_";
	/**
	 * 订单详情
	 */
	public static final String order_info = redis_name_key_prefix + "order_info_";
	/**
	 * 订单物流详情
	 */
	public static final String order_info_shipper = redis_name_key_prefix + "order_info_shipper_";
	/**
	 * 订单商品列表
	 */
	public static final String order_goods_list = redis_name_key_prefix + "order_goods_list_";
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
	 * 用户线下充值记录列表
	 */
	public static final String user_recharge_offline_list = redis_name_key_prefix + "user_recharge_offline_list_";
	/**
	 * 用户线上充值记录列表
	 */
	public static final String user_recharge_online_list = redis_name_key_prefix + "user_recharge_online_list_";
	
	
	/**
	 * 用户收藏夹列表
	 */
	public static final String user_goods_collect_info_list = redis_name_key_prefix + "user_goods_collect_info_list_";
	/**
	 * 用户收藏夹数量
	 */
	public static final String user_goods_collect_info_num = redis_name_key_prefix + "user_goods_collect_info_num_";
	/**
	 * 用户足迹列表
	 */
	public static final String user_goods_track_info_list = redis_name_key_prefix + "user_goods_track_info_list_";
	/**
	 * 用户足迹数量
	 */
	public static final String user_goods_track_info_num = redis_name_key_prefix + "user_goods_track_info_num_";
	/**
	 * 用户购物车列表
	 */
	public static final String user_goods_cart_info_list = redis_name_key_prefix + "user_goods_cart_info_list_";
	/**
	 * 用户购物车商品数量
	 */
	public static final String user_goods_cart_info_num = redis_name_key_prefix + "user_goods_cart_info_num_";
	
	
	/**
	 * 广告封面列表
	 */
	public static final String goods_advertise_cover_list = redis_name_key_prefix + "goods_advertise_cover_list";
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
	 * 孝道基金提现记录列表
	 */
	public static final String user_piety_cash_record_list = redis_name_key_prefix + "user_piety_cash_record_list_";
	/**
	 * 专项提现账户提现记录列表
	 */
	public static final String user_special_cash_record_list = redis_name_key_prefix + "user_special_cash_record_list_";
	
	
	/**
	 * 系统充值银行卡列表
	 */
	public static final String sys_recharge_bankcard_list = redis_name_key_prefix + "sys_recharge_bankcard_list";
	/**
	 * 用户充值记录列表
	 */
	public static final String user_recharge_record_list = redis_name_key_prefix + "user_recharge_record_list_";
	
	
	/**
	 * 用户转出日志列表
	 */
	public static final String user_roll_out_log_list = redis_name_key_prefix + "user_roll_out_log_list_";
	/**
	 * 用户转入日志列表
	 */
	public static final String user_roll_in_log_list = redis_name_key_prefix + "user_roll_in_log_list_";
	
	
	/**
	 * 系统用户等级列表
	 */
	public static final String sys_user_grade_list = redis_name_key_prefix + "sys_user_grade_list";
	/**
	 * 系统用户等级对象
	 */
	public static final String sys_user_grade_info = redis_name_key_prefix + "sys_user_grade_info_";
	
	
	
	/**
	 * 系统等级套餐列表
	 */
	public static final String sys_deposit_info_list = redis_name_key_prefix + "sys_deposit_info_list";
	/**
	 * 系统会员等级对象
	 */
	public static final String sys_member_grade_info = redis_name_key_prefix + "sys_member_grade_info_";
	
	
	/**
	 * 系统免费专区列表
	 */
	public static final String sys_free_zone_list = redis_name_key_prefix + "sys_free_zone_list";
	
	
	/**
	 * 系统体验信息列表
	 */
	public static final String sys_experience_list = redis_name_key_prefix + "sys_experience_list";
	
	
	/**
	 * 系统代数招商收益参数列表
	 */
	public static final String sys_algebra_reward_invest_incom_list = redis_name_key_prefix + "sys_algebra_reward_invest_incom_list";
	/**
	 * 系统代数管理收益参数列表
	 */
	public static final String sys_algebra_reward_manage_sales_incom_list_type = redis_name_key_prefix + "sys_algebra_reward_manage_sales_incom_list_type_";
	
	
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
	
	
}





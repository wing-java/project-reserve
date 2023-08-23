package com.ruoyi.common.constant;


/**
 * 接口URL地址
 * @author Administrator
 *
 */
public class InterfaceUrlsConstant {
	
	/**
	 * php域名
	 */
	public static final String php_realm_name = "https://www.trttoken.vip";//线上
	//public static final String realm_name = "http://trt01.wgzvip.com";//测试
	
	
	/**
	 * 获取php用户信息
	 */
	public static final String get_user_info_url = php_realm_name + "/api/GetUserInfo";
	
	/**
	 * 查询价格信息
	 */
	public static final String get_price_url = php_realm_name + "/api/GetPrice";
	
	/**
	 * 查询价格信息
	 */
	public static final String set_coin_url = php_realm_name + "/api/Set_Coin";
	
	
	/**
	 * app域名  
	 */
	public static final String app_realm_name = "https://www.1caratzb.com/";//线上
	//public static final String app_realm_name = "http://trt.wgzvip.com";//测试
	
	/**
	 * 跳转app商品详情页面
	 */
	public static final String app_toGoodsDetail = app_realm_name + "/web/shop/goods/toGoodsDetail";
	
	/**
	 * 跳转app商品详情预览页面
	 */
	public static final String app_toPreviewGoodsDetail = app_realm_name + "/api/back/preview/goods/toGoodsDetail";
	
	/**
	 * 跳转app订单详情预览页面
	 */
	public static final String app_toPreviewOrderDetail = app_realm_name + "/api/back/preview/order/toOrderDetail";
}

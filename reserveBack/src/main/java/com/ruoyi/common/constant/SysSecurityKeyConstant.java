package com.ruoyi.common.constant;


/**
 * 系统安全秘钥常亮
 * @author Administrator
 *
 */
public class SysSecurityKeyConstant {
	
	/**
	 * 前后台约定md5加签秘钥=====>APP端
	 */
	public static final String md5Key_app="SPNbRiokxGNpAe0ysIEr732QdzcsofDe7O6HNkqd42IXPTJWy2ruCA4jNuwi9yZVryJP1Yrp";
	
	/**
	 * 前后台约定md5加签秘钥=====>PHP的TRT请求奕克拉商城项目
	 */
	public static final String md5Key_php_request="8tZTvyHef9GXh88QA40RjJ9YnW0g5Dxk4FwmVxtdvYqJ5wpiM7zf9kta2xZxeB7jK27FxF36";
	
	/**
	 * 前后台约定md5加签秘钥=====>奕克拉商城项目请求PHP的TRT
	 */
	public static final String md5Key_request_php="523a49ca308822b6bba04e42e63abb1a";
	
	
	/**
	 * 前后台约定md5加签秘钥=====>php端
	 */
	public static final String md5Key_php="523a49ca308822b6bba04e42e63abb1a";
	
	
	/**
	 * RSA加密公钥=====>app端
	 */
	public static final String publicKey_app = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLRBwD8Lpk70Ys9pjcnOx53A6Fd/p0Lq644pfvIzUx3RucOztVrK1QBj707ARipC5cZ2TtyNQMK/Eo2REatG9RZCULu4kNT7AXDS1vWP9X9K1kFBoCpEvLVAXjmxEKmksdc1xdOVeTMgGu0GGtNDK4FVYjvfWTNi81N/F8XDIZ+QIDAQAB";
	/**
	 * RSA加密私钥=====>app端
	 */
	public static final String privateKey_app = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAItEHAPwumTvRiz2mNyc7HncDoV3+nQurrjil+8jNTHdG5w7O1WsrVAGPvTsBGKkLlxnZO3I1Awr8SjZERq0b1FkJQu7iQ1PsBcNLW9Y/1f0rWQUGgKkS8tUBeObEQqaSx1zXF05V5MyAa7QYa00MrgVViO99ZM2LzU38XxcMhn5AgMBAAECgYBnSRsx/O5wf1jfbgBO1f84HXGToTjZw+mHa15dWBJSqfaZNwC5eLiM+iTlBsn10mgj1fbvt5s0b76KmfpqgNlxPHFwim15mffePKyegRyRFCeQm6p0R2JnI0YvINgc2FyTz7kT+ZovQ+vB54VJbB7iPdPbx+eJJdYNyreE/BpUpQJBANY/LTtiHUljHMebCF7VO/EQT1OxwUnT7jfcgiqqbk3itJiERqarXeBpgUmWI8UGTK1lIqRzAeN61+UU0oEGPB8CQQCmaCBJFK/98zpN8Rs2rROL3GLA/dY+3v+SxuGdiDMSqKeoB3dJTEmNxOqM8iN8Lmc9y3arceh1HiMpd6Z4subnAkBE9H51X2W3qfuoTsCJ7S1mr+4oLMzeGyTPu9v1KXdo/+9KK0CmAf+r66kd6wHGNvnU0PkuXomcEnyMEYCS4FPXAkBcNf8OACS1+H4qipyl46qdKfafMwnxtOiykPqcrMiAYmzlM53qRBfOM3w1tkfrnFshZwnPX0ONArJBXEgnQhupAkBT0OVoJxPc6PxUS0qSLbdT5mHvgnlcfO1IqQWylGc95kzXVn1zU/37wJ7eH8X//tBKWxo3rSE5vGvOoFW0tCeQ";
	
	
	/**
	 * RSA加密公钥=====>交易所
	 */
	public static final String publicKey_exchange = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTyAuI7JyA89vJFcOTQPwh9ppcaeEse8DxZxiCaiEUqqZ8JplzaJecroNLrOUBndDAoihy5o+uso/o/xiqDaRwSyQ/+TMYLjzXmddMk09djrPlYcnkp3TVeL4+YdnDvBS5Jk4Yny0qTkKMYOgBzNqYvaQ/rEhCV8poVhDNVdX+wQIDAQAB";
	/**
	 * RSA加密私钥=====>交易所
	 */
	public static final String privateKey_exchange = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJPIC4jsnIDz28kVw5NA/CH2mlxp4Sx7wPFnGIJqIRSqpnwmmXNol5yug0us5QGd0MCiKHLmj66yj+j/GKoNpHBLJD/5MxguPNeZ10yTT12Os+VhyeSndNV4vj5h2cO8FLkmThifLSpOQoxg6AHM2pi9pD+sSEJXymhWEM1V1f7BAgMBAAECgYB/+MuEVuFwCbxZ9a7W5X9xKcuiG04S51e9tDlVNfJPozdoa0SW3AvucnDgJ2hobfFH3ySH24CxF4/nhIAnQokSH083m7UPkmTix7M8fj5hW/fAlc62xO/u1yL1YulPNNUytU0yakcjTXAiGakV8b6gyXS+GkoUdRPpnTaFP+IuwQJBAOZPxcK/T3TM5vhUDtJE0KpR0Tpo+g+vqChlmO1CE2O3jfyQwgUbuDvhHHjtzaiOrSAv6k5FBxs2JmlX32g8aK8CQQCkQ7zsj7yQyIu/XYwzPjp+yy3b0N+Ip9uCWsFJui2lpF64SHRNvMv6w6rmaUfeVvOXAYsRnbBOXkcLQFDr6AuPAkEAg0t80P3hHRPmpDC/Vk2Wq1IDNgo8bA7WjVkF6lPd2937Wr1nbiQUTkaRxbrDoFDeD1hheYdOesvO8AUBPloZzwJAco1Gh9YpEvK3+gj7x5SC6H86iMudx0f/3WT63DZ4tMXuxCbK7JdXzJV9bddHpjcPAmSyHePQ2qYGd0Mv64PT4wJBAMcdO1kuKlwuUSpiflIFs+NJ2e49xAQJAERV2bm20FtJacLjweYhg1IQbq5p4GhbA6ZFbmKHuBNa6fvq/3xoOzY=";
	
	
}


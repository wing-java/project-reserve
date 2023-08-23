package com.ruoyi.framework.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/alin.properties")
public class AlinProperties {

	/**
	 * 
	 */
	@Value("${alin.appid}")
	private String appid;
	
	/**
	 * 
	 */
	@Value("${alin.url}")
	private String url;
	
	/**
	 * 
	 */
	@Value("${alin.md5key}")
	private String md5key;
	
	/**
	 * 
	 */
	@Value("${alin.productid}")
	private String productid;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5key() {
		return md5key;
	}

	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}
	
}

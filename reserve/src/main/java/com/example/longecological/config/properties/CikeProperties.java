package com.example.longecological.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/cike.properties")
public class CikeProperties {

	/**
	 * 
	 */
	@Value("${cike.merId}")
	private String merId;
	
	/**
	 * 
	 */
	@Value("${cike.md5key}")
	private String md5key;
	
	/**
	 * 
	 */
	@Value("${cike.notifyUrl}")
	private String notifyUrl;
	
	/**
	 * 
	 */
	@Value("${cike.url}")
	private String url;
	
	/**
	 * 
	 */
	@Value("${cike.signType}")
	private String signType;
	
	/**
	 * 
	 */
	@Value("${cike.version}")
	private String version;

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMd5key() {
		return md5key;
	}

	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}

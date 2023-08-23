package com.example.longecological.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/jinshun.properties")
public class JinShunProperties {

	/**
	 * 
	 */
	@Value("${jinshun.mid}")
	private String mid;
	
	/**
	 * 
	 */
	@Value("${jinshun.md5key}")
	private String md5key;
	
	/**
	 * 
	 */
	@Value("${jinshun.notifyUrl}")
	private String notifyUrl;
	
	/**
	 * 
	 */
	@Value("${jinshun.url}")
	private String url;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
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
	
}

package com.example.longecological.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/chuanjun.properties")
public class ChuanJunProperties {

	/**
	 * 
	 */
	@Value("${chuanjun.memberid}")
	private String memberid;
	
	/**
	 * 
	 */
	@Value("${chuanjun.bankcode}")
	private String bankcode;
	
	/**
	 * 
	 */
	@Value("${chuanjun.notifyurl}")
	private String notifyurl;
	
	/**
	 * 
	 */
	@Value("${chuanjun.md5key}")
	private String md5key;
	
	/**
	 * 
	 */
	@Value("${chuanjun.url}")
	private String url;

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getNotifyurl() {
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}

	public String getMd5key() {
		return md5key;
	}

	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}

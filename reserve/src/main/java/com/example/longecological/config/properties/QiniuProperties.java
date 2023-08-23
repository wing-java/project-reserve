package com.example.longecological.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/qiniu.properties")
public class QiniuProperties {

	/**
	 * 七牛剪裁大小
	 */
	@Value("${img_qiniu_deal}")
	private String img_qiniu_deal;
	
	/**
	 * 七牛剪裁大小
	 */
	@Value("${img_qiniu_deal2}")
	private String img_qiniu_deal2;
	
	/**
	 * 七牛剪裁大小
	 */
	@Value("${img_qiniu_deal3}")
	private String img_qiniu_deal3;
	
	/**
	 * 七牛访问域名
	 */
	@Value("${qiniu_domain}")
	private String qiniu_domain;
	
	/**
	 * 七牛AK
	 */
	@Value("${qiniu_accessKey}")
	private String qiniu_accessKey;
	
	/**
	 * 七牛SK
	 */
	@Value("${qiniu_secretKey}")
	private String qiniu_secretKey;
	
	/**
	 * 七牛目录
	 */
	@Value("${qiniu_bucket}")
	private String qiniu_bucket;

	public String getImg_qiniu_deal() {
		return img_qiniu_deal;
	}

	public void setImg_qiniu_deal(String img_qiniu_deal) {
		this.img_qiniu_deal = img_qiniu_deal;
	}

	public String getQiniu_domain() {
		return qiniu_domain;
	}

	public void setQiniu_domain(String qiniu_domain) {
		this.qiniu_domain = qiniu_domain;
	}

	public String getImg_qiniu_deal2() {
		return img_qiniu_deal2;
	}

	public void setImg_qiniu_deal2(String img_qiniu_deal2) {
		this.img_qiniu_deal2 = img_qiniu_deal2;
	}

	public String getImg_qiniu_deal3() {
		return img_qiniu_deal3;
	}

	public void setImg_qiniu_deal3(String img_qiniu_deal3) {
		this.img_qiniu_deal3 = img_qiniu_deal3;
	}

	public String getQiniu_accessKey() {
		return qiniu_accessKey;
	}

	public void setQiniu_accessKey(String qiniu_accessKey) {
		this.qiniu_accessKey = qiniu_accessKey;
	}

	public String getQiniu_secretKey() {
		return qiniu_secretKey;
	}

	public void setQiniu_secretKey(String qiniu_secretKey) {
		this.qiniu_secretKey = qiniu_secretKey;
	}

	public String getQiniu_bucket() {
		return qiniu_bucket;
	}

	public void setQiniu_bucket(String qiniu_bucket) {
		this.qiniu_bucket = qiniu_bucket;
	}
	
}

package com.example.longecological.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/dementedGod.properties")
public class DementedGodProperties {
    /**
     *加密key
     */
    @Value("${dementedGod.md5key}")
    private String md5key;
    /**
     *商户id
     */
    @Value("${dementedGod.mchId}")
    private String mchId;
    /**
     *充值地址
     */
    @Value("${dementedGod.url}")
    private String url;
    /**
     *同步通知地址
     */
    @Value("${dementedGod.returnUrl}")
    private String returnUrl;
    /**
     *回调地址
     */
    @Value("${dementedGod.notifyUrl}")
    private String notifyUrl;


    public String getMd5key() {
        return md5key;
    }

    public void setMd5key(String md5key) {
        this.md5key = md5key;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}

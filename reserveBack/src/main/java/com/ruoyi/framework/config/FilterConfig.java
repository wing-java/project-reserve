package com.ruoyi.framework.config;

import java.util.Map;
import javax.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.xss.XssFilter;

/**
 * Filter过滤器配置
 *
 * @author ruoyi
 */
@Configuration
public class FilterConfig
{
	//过滤开关：开启
    @Value("${xss.enabled}")
    private String enabled;

    //排除链接（多个用逗号分隔）:/system/notice/*
    @Value("${xss.excludes}")
    private String excludes;

    //匹配链接：/system/*,/monitor/*,/tool/*
    @Value("${xss.urlPatterns}")
    private String urlPatterns;

    
    /**
     * 配置过滤器
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean xssFilterRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        //xss过滤
        registration.setFilter(new XssFilter());//过滤器
        registration.addUrlPatterns(StringUtil.split(urlPatterns, ","));//匹配链接：/system/*,/monitor/*,/tool/*
        registration.setName("xssFilter");//过滤器名称
        registration.setOrder(Integer.MAX_VALUE);//过滤器顺序，也可通过@Order注解配置
        
        //初始化参数
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("excludes", excludes);//排除链接（多个用逗号分隔）:/system/notice/*
        initParameters.put("enabled", enabled);//过滤开关：开启
        registration.setInitParameters(initParameters);
        return registration;
    }
}

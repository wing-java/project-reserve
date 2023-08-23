package com.ruoyi.framework.config;

import java.util.Locale;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 资源文件配置加载
 * 配置类用于mvc拦截区域语言类型设置默认语言类型
 * @author ruoyi
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class I18nConfig implements WebMvcConfigurer
{
	
	/**
	 * 默认解析器：LocaleResolver 用于设置当前会话的默认的国际化语言。
	 * 其中locale表示默认语言
	 * @return
	 */
    @Bean
    public LocaleResolver localeResolver()
    {
    	// 检测解析区域
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 设置默认语言：中文简体
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return slr;
    }

    
    /**
     * 默认拦截器：LocaleChangeInterceptor 指定切换国际化语言的参数名。例如?lang=zh_CN 表示读取国际化文件messages_zh_CN.properties。
     * 配置拦截器获取URL中的key=“lang” （?lang=zh_CN）
     * 给每一个请求加一个中间件，用来检查lang参数，并根据该参数的值来设置Locale
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor()
    {
    	System.out.println("中英文拦截器");
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }

    
    /**
     * 注册拦截器到容器中
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
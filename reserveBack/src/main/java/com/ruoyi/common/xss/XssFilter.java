package com.ruoyi.common.xss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.string.StringUtil;

/**
 * 防止XSS攻击的过滤器
 * 拦截防止xss注入，通过Jsoup过滤请求参数内的特定字符
 * @author ruoyi
 */
public class XssFilter implements Filter
{
    /**
     * 排除链接
     */
    public List<String> excludes = new ArrayList<>();

    /**
     * xss过滤开关
     */
    public boolean enabled = false;
    

    /**
     * xss过滤器初始化方法
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    	//初始化参数：排除链接（多个用逗号分隔）:/system/notice/*
        String tempExcludes = filterConfig.getInitParameter("excludes");
        //初始化参数：过滤开关，开启
        String tempEnabled = filterConfig.getInitParameter("enabled");
        
        //排除链接不为空
        if (StringUtil.isNotEmpty(tempExcludes))
        {
        	//切割得到每一个链接
            String[] url = tempExcludes.split(",");
            for (int i = 0; url != null && i < url.length; i++)
            {
            	//依次处理加入排除链接
                excludes.add(url[i]);
            }
        }
        
        //过滤开关不为空
        if (StringUtil.isNotEmpty(tempEnabled))
        {
        	//过滤开关设置
            enabled = Boolean.valueOf(tempEnabled);
        }
    }

    
    /**
     * 过滤方法
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
    	//request请求
        HttpServletRequest req = (HttpServletRequest) request;
        //respond响应
        HttpServletResponse resp = (HttpServletResponse) response;
       
        //判断是否需要过滤：不需要，直接下一步
        if (handleExcludeURL(req, resp))
        {
            chain.doFilter(request, response);
            return;
        }
        
        
        //否则handleExcludeURL返回了false，需要进行xss过滤了
        /**
         * XssFilter是过滤XSS请求的入口，在这里通过XssHttpServletRequestWrapper将HttpServletRequest进行了封装，
         * chain.doFilter(xssRequest, response);保证了后续代码执行request.getParameter，request.
         * getParameterValues，request.getHeader时调用的都是XssHttpServletRequestWrapper内重写的方法，获取到的参数是已经进行过标签过滤的内容，从而消除了敏感信息。
         */
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
       
        chain.doFilter(xssRequest, response);
    }

    
    /**
     * 处理排除的URL链接：返回是否是不用过滤
     * @param request
     * @param response
     * @return
     */
    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response)
    {
    	//如果过滤开关未开启，不需要xss过滤
        if (!enabled)
        {
            return true;
        }
        //开关开启了，而且没有排除的链接（所有请求都需要过滤），所以需要xss过滤
        if (excludes == null || excludes.isEmpty())
        {
            return false;
        }
        
        //处理判断当前请求是否需要过滤
        String url = request.getServletPath();//得到请求URL
        //循环遍历排除链接的URL，看当前URL请求是否匹配排除链接
        for (String pattern : excludes)
        {
            Pattern p = Pattern.compile("^" + pattern);//正则URL
            Matcher m = p.matcher(url);//匹配
            //匹配到，说明满足排除链接，可以不用xss过滤
            if (m.find())
            {
                return true;
            }
        }
        
        //否则都需要进行xss过滤
        return false;
    }

    
    /**
     * 销毁方法
     */
    @Override
    public void destroy()
    {

    }
}
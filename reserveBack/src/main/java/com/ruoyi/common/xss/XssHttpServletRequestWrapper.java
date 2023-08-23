package com.ruoyi.common.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * XSS过滤处理
 * 这是实现XSS过滤的关键，在其内重写了getParameter，getParameterValues，getHeader等方法，对http请求内的参数进行了过滤。
 * @author ruoyi
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    /**
     * 构造函数
     * @param request
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }
    
    
    /**
     * 覆盖getParameter方法
     */
    @Override
    public String getParameter(String name) {
    	if(super.getParameter(name)!=null) {
    		return Jsoup.clean(super.getParameter(name), Whitelist.relaxed()).trim();
    	}else {
    		return super.getParameter(name);
    	}
    }

    
    /**
     * 覆盖getParameterValues方法
     */
    @Override
    public String[] getParameterValues(String name)
    {
    	//获得请求参数数组
        String[] values = super.getParameterValues(name);
        
        //参数不为空
        if (values != null)
        {
        	//参数个数
            int length = values.length;
            //定义一个对应长度的数组用来存放过滤后的参数
            String[] escapseValues = new String[length];
            //依次处理每一个请求参数
            for (int i = 0; i < length; i++)
            {
                // 防xss攻击和过滤前后空格
                escapseValues[i] = Jsoup.clean(values[i], Whitelist.relaxed()).trim();
            }
            //返回处理后的参数
            return escapseValues;
        }
        
        //否则参数为空，直接返回父方法
        return super.getParameterValues(name);
    }
}
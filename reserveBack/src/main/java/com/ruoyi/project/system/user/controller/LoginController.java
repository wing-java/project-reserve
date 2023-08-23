package com.ruoyi.project.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class LoginController extends BaseController
{
	
	/**
	 * 登录的get方法
	 * @param request
	 * @param response
	 * @return
	 */
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,String lang)
    {
    	// 如果是Ajax请求，返回Json字符串并且渲染到客户端。
        if (ServletUtils.isAjaxRequest(request))
        {
        	//否则，直接返回到登录页面
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        return "login";
    }

    
    /**
     * 登录的POS方法
     * 调用Subject.login后会触发UserRealm的doGetAuthenticationInfo方法，进行具体的登录验证处理。
     * 
     * @param username：用户名
     * @param password：密码
     * @param rememberMe：记住密码
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        //添加用户认证消息
        Subject subject = SecurityUtils.getSubject();
        try
        {
        	//会触发doGetAuthenticationInfo方法，进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(token);
            return success();
        }catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtil.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    
    /**
     * 没有权限跳转页面
     * @return
     */
    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
    
}

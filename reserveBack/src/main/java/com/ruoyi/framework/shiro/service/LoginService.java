package com.ruoyi.framework.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.UserBlockedException;
import com.ruoyi.common.exception.user.UserDeleteException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.time.DateUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserStatus;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class LoginService
{
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password)
    {
        // （1）图形验证码校验（获取request请求，拿到captcha图形验证码参数）
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
        	//异步任务记录登录信息：登录失败（图形验证码错误）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            //抛出图形验证码错误异常
            throw new CaptchaException();
        }
        
        // （2）用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
        	//异步任务记录登录信息：登录失败（用户名或密码为空 错误）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            //抛出用户不存在异常
            throw new UserNotExistsException();
        }
        
        // （3）密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
        	//异步任务记录登录信息：登录失败（用户密码不正确或不符合规范异常类）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            //抛出用户密码不正确或不符合规范异常
            throw new UserPasswordNotMatchException();
        }

        // （4）用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
        	//异步任务记录登录信息：登录失败（用户密码不正确或不符合规范异常类）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            //抛出用户密码不正确或不符合规范异常
            throw new UserPasswordNotMatchException();
        }

        // （5）通过用户名查询用户信息
        User user = userService.selectUserByLoginName(username);
        //如果根据用户名查询为空，并且用户名是手机号格式，则根据手机号查询用户信息
        if (user == null && maybeMobilePhoneNumber(username))
        {
            user = userService.selectUserByPhoneNumber(username);
        }
        //如果用户对象仍然为空，并且用户名是邮箱格式，则根据邮箱查询用户信息
        if (user == null && maybeEmail(username))
        {
            user = userService.selectUserByEmail(username);
        }
        //如果仍然为空，说明用户信息不存在
        if (user == null)
        {
        	//异步任务记录登录信息：登录失败（用户不存在异常）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            //抛出用户不存在异常
            throw new UserNotExistsException();
        }
        
        //（6）用户状态：已删除
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
        	//异步任务记录登录信息：登录失败（用户账号已被删除）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            //抛出用户账号已被删除异常
            throw new UserDeleteException();
        }
        
        //（7）用户状态：已停用
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
        	//异步任务记录登录信息：登录失败（用户账号已被停用）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            //抛出用户锁定异常类
            throw new UserBlockedException();
        }

        //校验用户登录密码：登录失败次数限制
        passwordService.validate(user, password);

        //异步任务记录登录信息：登录成功
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        //记录登录信息
        recordLoginInfo(user);
        return user;
    }

    
    /**
     * 判断是否是邮箱格式
     * @param username
     * @return
     */
    private boolean maybeEmail(String username)
    {
        if (!username.matches(UserConstants.EMAIL_PATTERN))
        {
            return false;
        }
        return true;
    }

    
    /**
     * 判断是否是手机格式
     * @param username
     * @return
     */
    private boolean maybeMobilePhoneNumber(String username)
    {
    	//用户名不是手机号
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        //用户名是手机号
        return true;
    }

    
    /**
     * 记录登录信息
     * @param user
     */
    public void recordLoginInfo(User user)
    {
    	//登录ip
        user.setLoginIp(ShiroUtils.getIp());
        //登录时间
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}

package com.ruoyi.framework.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.exception.user.UserPasswordRetryLimitExceedException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.project.system.user.domain.User;

/**
 * 登录密码方法
 * 
 * @author ruoyi
 */
@Component
public class PasswordService
{
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    
    /**
     * 初始化方法
     */
    @PostConstruct
    public void init()
    {
    	loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    
    /**
     * 校验用户登录密码：登录失败次数限制
     * @param user
     * @param password
     */
    public void validate(User user, String password)
    {
    	//获取登录用户名
        String loginName = user.getLoginName();
        //获取用户登录次数
        AtomicInteger retryCount = loginRecordCache.get(loginName);
        
        //如果用户没有登录过，登录次数加1，并放入缓存
        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        
        //如果用户登录失败次数大于设置最大次数，抛出用户错误最大次数异常
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
        	//异步任务记录登录信息：登录失败（用户错误最大次数异常）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            //抛出用户错误最大次数异常
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        //判断用户账号和密码是否正确
        if (!matches(user, password))
        {
        	//异步任务记录登录信息：登录失败（用户密码不正确或不符合规范异常类）
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            //抛出用户密码不正确或不符合规范异常
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
        	//如果正确，从缓存中将用户登录计数清除
            clearLoginRecordCache(loginName);
        }
    }

    
    /**
     * 用户密码匹配校验
     * @param user：shiro用户对象
     * @param newPassword：输入的密码
     * @return
     */
    public boolean matches(User user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    
    /**
     * 从缓存中将用户登录计数清楚
     * @param username
     */
    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    
    /**
     * 密码用户名加盐加密
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex().toString();
    }

    public static void main(String[] args)
    {
        System.out.println(new PasswordService().encryptPassword("admin", "admincbjh110.", "fe2e99"));
        System.out.println(new PasswordService().encryptPassword("ry", "admin123", "222222"));
    }
}

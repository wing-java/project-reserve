package com.ruoyi.framework.web.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.DateUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.AjaxResult.Type;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.system.user.domain.User;

/**
 * web层通用数据处理
 * 
 * @author ruoyi
 */
public class BaseController
{
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    
    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
    	//获得封装分页对象
        PageDomain pageDomain = TableSupport.buildPageRequest();
        //当前记录起始索引
        Integer pageNum = pageDomain.getPageNum();
        //每页显示记录数
        Integer pageSize = pageDomain.getPageSize();
        //当前记录起始索引和记录条数均非空的情况下
        if (StringUtil.isNotNull(pageNum) && StringUtil.isNotNull(pageSize))
        {
        	//得到排序对象，注意防止sql注入
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            //分页并排序
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    
    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
    	//表格分页数据对象
        TableDataInfo rspData = new TableDataInfo();
        //消息状态码
        rspData.setCode(0);
        //列表数据
        rspData.setRows(list);
        //总记录数
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    
    
    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    
    /**
     * 响应返回结果
     * 
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    
    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    
    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    
    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    
    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    
    /**
     * 返回错误码消息
     */
    public AjaxResult error(Type type, String message)
    {
        return new AjaxResult(type, message);
    }

    
    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtil.format("redirect:{}", url);
    }

    
    /**
     * 获取shiro身份对象
     * @return
     */
    public User getSysUser()
    {
        return ShiroUtils.getSysUser();
    }

    
    /**
     * 设置shiro身份对象
     * @param user
     */
    public void setSysUser(User user)
    {
        ShiroUtils.setSysUser(user);
    }

    
    /**
     * 获取shiro身份用户id
     * @return
     */
    public Long getUserId()
    {
        return getSysUser().getUserId();
    }

    
    /**
     * 获取shiro身份用户名
     * @return
     */
    public String getLoginName()
    {
        return getSysUser().getLoginName();
    }
}

package com.ruoyi.project.develop.user.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.user.domain.UserLoginLog;
import com.ruoyi.project.develop.user.service.UserLoginLogService;

/**
 * 用户登录日志操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userLoginLog")
public class UserLoginLogController extends BaseController
{
    private String prefix = "develop/userLoginLog";
	
	@Autowired
	private UserLoginLogService userLoginLogService;
	
	
	/**
	 * 跳转用户登录日志列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userLoginLog:view")
	@GetMapping()
	public String userLoginLog()
	{
	    return prefix + "/userLoginLog";
	}
	
	
	/**
	 * 查询用户登录日志列表
	 */
	@RequiresPermissions("develop:userLoginLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userLoginLogService.getUserLoginLogList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 用户登录日志列表
	 */
	@Log(title = "用户登录日志管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userLoginLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<UserLoginLog> list = userLoginLogService.selectUserLoginLogList(params);
        ExcelUtil<UserLoginLog> util = new ExcelUtil<UserLoginLog>(UserLoginLog.class);
        return util.exportExcel(list, "用户登录日志数据");
    }
	
}

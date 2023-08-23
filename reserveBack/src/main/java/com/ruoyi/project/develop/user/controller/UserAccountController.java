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
import com.ruoyi.project.develop.user.domain.UserAccount;
import com.ruoyi.project.develop.user.service.UserAccountService;

/**
 * 用户收款账户操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userAccount")
public class UserAccountController extends BaseController
{
    private String prefix = "develop/userAccount";
	
	@Autowired
	private UserAccountService userAccountService;
	
	
	/**
	 * 跳转用户收款账户列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userAccount:view")
	@GetMapping()
	public String userAccount()
	{
	    return prefix + "/userAccount";
	}
	
	
	/**
	 * 查询用户收款账户列表
	 */
	@RequiresPermissions("develop:userAccount:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userAccountService.getUserAccountList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 用户收款账户列表
	 */
	@Log(title = "用户收款账户管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userAccount:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<UserAccount> list = userAccountService.selectUserAccountList(params);
        ExcelUtil<UserAccount> util = new ExcelUtil<UserAccount>(UserAccount.class);
        return util.exportExcel(list, "用户收款账户数据");
    }
	
}

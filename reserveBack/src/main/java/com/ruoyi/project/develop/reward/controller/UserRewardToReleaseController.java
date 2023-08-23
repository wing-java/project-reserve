package com.ruoyi.project.develop.reward.controller;

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
import com.ruoyi.project.develop.reward.domain.UserRewardToRelease;
import com.ruoyi.project.develop.reward.service.UserRewardToReleaseService;

/**
 * 
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userRewardToRelease")
public class UserRewardToReleaseController extends BaseController{

private String prefix = "develop/userRewardToRelease";
	
	@Autowired
	private UserRewardToReleaseService userRewardToReleaseService;
	
	
	/**
	 * 跳转
	 * @return
	 */
	@RequiresPermissions("develop:userRewardToRelease:view")
	@GetMapping()
	public String userRewardToRelease()
	{
	    return prefix + "/userRewardToRelease";
	}
	
	
	/**
	 * 查询
	 */
	@RequiresPermissions("develop:userRewardToRelease:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userRewardToReleaseService.getUserRewardToReleaseList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 直推奖励列表
	 */
	@Log(title = "释放管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userRewardToRelease:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<UserRewardToRelease> list = userRewardToReleaseService.selectUserRewardToReleaseList(params);
        ExcelUtil<UserRewardToRelease> util = new ExcelUtil<UserRewardToRelease>(UserRewardToRelease.class);
        return util.exportExcel(list, "释放数据");
    }
}

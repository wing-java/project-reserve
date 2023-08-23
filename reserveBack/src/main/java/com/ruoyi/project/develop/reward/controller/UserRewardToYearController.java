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
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.reward.domain.UserRewardToYear;
import com.ruoyi.project.develop.reward.service.UserRewardToYearService;

/**
 * 
 * 
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userRewardToYear")
public class UserRewardToYearController extends BaseController{

	private String prefix = "develop/userRewardToYear";
	
	@Autowired
	private UserRewardToYearService userRewardToYearService;
	
	
	/**
	 * 跳转
	 * @return
	 */
	@RequiresPermissions("develop:userRewardToYear:view")
	@GetMapping()
	public String userRewardToYear()
	{
	    return prefix + "/userRewardToYear";
	}
	
	
	/**
	 * 查询
	 */
	@RequiresPermissions("develop:userRewardToYear:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userRewardToYearService.getUserRewardToYearList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出
	 */
	@Log(title = "年度收益", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userRewardToYear:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
    	List<UserRewardToYear> list = userRewardToYearService.selectUserRewardToYearList(params);
        ExcelUtil<UserRewardToYear> util = new ExcelUtil<UserRewardToYear>(UserRewardToYear.class);
        return util.exportExcel(list, "年度收益");
    }
}

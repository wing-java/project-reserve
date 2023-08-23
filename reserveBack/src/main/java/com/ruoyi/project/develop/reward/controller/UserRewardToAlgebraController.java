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
import com.ruoyi.project.develop.reward.domain.UserRewardToAlgebra;
import com.ruoyi.project.develop.reward.service.UserRewardToAlgebraService;

/**
 * 
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userRewardToAlgebra")
public class UserRewardToAlgebraController extends BaseController{

private String prefix = "develop/userRewardToAlgebra";
	
	@Autowired
	private UserRewardToAlgebraService userRewardToAlgebraService;
	
	
	/**
	 * 跳转
	 * @return
	 */
	@RequiresPermissions("develop:userRewardToAlgebra:view")
	@GetMapping()
	public String userRewardToAlgebra()
	{
	    return prefix + "/userRewardToAlgebra";
	}
	
	
	/**
	 * 查询
	 */
	@RequiresPermissions("develop:userRewardToAlgebra:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userRewardToAlgebraService.getUserRewardToAlgebraList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出
	 */
	@Log(title = "分享收益", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userRewardToAlgebra:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
    	List<UserRewardToAlgebra> list = userRewardToAlgebraService.selectUserRewardToAlgebraList(params);
        ExcelUtil<UserRewardToAlgebra> util = new ExcelUtil<UserRewardToAlgebra>(UserRewardToAlgebra.class);
        return util.exportExcel(list, "分享收益");
    }
}

package com.ruoyi.project.develop.trade.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.ruoyi.project.develop.trade.domain.UserRechargeOnline;
import com.ruoyi.project.develop.trade.domain.UserRechargeRecordDetail;
import com.ruoyi.project.develop.trade.service.UserRechargeOnlineService;

/**
 * 用户线上充值操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userRechargeOnline")
public class UserRechargeOnlineController extends BaseController
{
    private String prefix = "develop/userRechargeOnline";
	
	@Autowired
	private UserRechargeOnlineService userRechargeOnlineService;
	
	
	/**
	 * 跳转用户线上充值列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userRechargeOnline:view")
	@GetMapping()
	public String userRechargeOnline()
	{
	    return prefix + "/userRechargeOnline";
	}
	
	
	/**
	 * 查询用户线上充值列表
	 */
	@RequiresPermissions("develop:userRechargeOnline:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userRechargeOnlineService.getUserRechargeOnlineList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	/**
	 * 用户线上充值列表
	 */
	@Log(title = "用户线上充值管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userRechargeOnline:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
    	List<UserRechargeOnline> list = userRechargeOnlineService.selectUserRechargeOnlineList(params);
        ExcelUtil<UserRechargeOnline> util = new ExcelUtil<UserRechargeOnline>(UserRechargeOnline.class);
        return util.exportExcel(list, "用户线上充值数据");
    }
	/**
	 * 统计充值信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("develop:userRechargeOnline:list")
	@PostMapping("/summaryUserRechargeOnlineList")
	@ResponseBody
	public Map<String, Object> summaryUserRechargeOnlineList(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return userRechargeOnlineService.summaryUserRechargeOnlineList(params);
	}
	
}

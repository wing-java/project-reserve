package com.ruoyi.project.develop.sysoper.controller;

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

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.sysoper.domain.UserSysReward;
import com.ruoyi.project.develop.sysoper.service.UserSysRewardService;

/**
 * 公司拨款操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userSysReward")
public class UserSysRewardController extends BaseController
{
    private String prefix = "develop/userSysReward";
	
	@Autowired
	private UserSysRewardService userSysRewardService;
	
	
	/**
	 * 跳转公司拨款列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userSysReward:view")
	@GetMapping()
	public String userSysReward()
	{
	    return prefix + "/userSysReward";
	}
	
	
	/**
	 * 查询公司拨款列表
	 */
	@RequiresPermissions("develop:userSysReward:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userSysRewardService.getUserSysRewardList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 汇总数据
	 */
	@RequiresPermissions("develop:userSysReward:list")
	@PostMapping("/summaryUserSysRewardList")
	@ResponseBody
	public Map<String, Object> summaryUserSysRewardList(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		return userSysRewardService.summaryUserSysRewardList(params);
	}
	
	
	/**
	 * 导出公司拨款列表
	 */
	@Log(title = "公司拨款管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userSysReward:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
    	List<UserSysReward> list = userSysRewardService.selectUserSysRewardList(params);
        ExcelUtil<UserSysReward> util = new ExcelUtil<UserSysReward>(UserSysReward.class);
        return util.exportExcel(list, "公司拨款数据");
    }
	
	
	 /**
	  * 跳转公司拨款页面
	  * @param id
	  * @param operParam
	  * @param mmap
	  * @return
	  */
    @GetMapping("/add/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_AddUserSysReward;
    	}else {
    		mmap.put("id", id);
            return prefix + "/add";
    	}
    }
    
    
    /**
     * 新增公司拨款
     * @param params
     * @return
     */
    @RequiresPermissions("develop:userSysReward:add")
    @Log(title = "公司拨款管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
        return userSysRewardService.batchAddUserSysReward(params);
    }

}

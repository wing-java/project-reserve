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

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.trade.domain.UserRechargeOffline;
import com.ruoyi.project.develop.trade.service.UserRechargeOfflineService;

/**
 * 用户线下充值操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userRechargeOffline")
public class UserRechargeOfflineController extends BaseController
{
    private String prefix = "develop/userRechargeOffline";
	
	@Autowired
	private UserRechargeOfflineService userRechargeOfflineService;
	
	
	/**
	 * 跳转用户线下充值列表页面
	 * @return
	 */
	@RequiresPermissions("develop:userRechargeOffline:view")
	@GetMapping()
	public String userRechargeOffline()
	{
	    return prefix + "/userRechargeOffline";
	}
	
	
	/**
	 * 查询用户线下充值列表
	 */
	@RequiresPermissions("develop:userRechargeOffline:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userRechargeOfflineService.getUserRechargeOfflineList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	/**
	 * 用户线下充值列表
	 */
	@Log(title = "用户线下充值管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userRechargeOffline:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<UserRechargeOffline> list = userRechargeOfflineService.selectUserRechargeOfflineList(params);
        ExcelUtil<UserRechargeOffline> util = new ExcelUtil<UserRechargeOffline>(UserRechargeOffline.class);
        return util.exportExcel(list, "用户线下充值数据");
    }
	/**
	 * 统计充值信息
	 * @param params
	 * @return
	 */
	@RequiresPermissions("develop:userRechargeOffline:list")
	@PostMapping("/summaryUserRechargeOfflineList")
	@ResponseBody
	public Map<String, Object> summaryUserRechargeOfflineList(@RequestParam Map<String, Object> params)
	{
		return userRechargeOfflineService.summaryUserRechargeOfflineList(params);
	}
	
	
	/**
	 * 批量审核线下充值记录
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysAudit/{id}")
	@RequiresPermissions("develop:userRechargeOffline:sysAudit")
    public String sysAudit(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_SysAuditUserRechargeOffline;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysAudit";
    	}
    }
	/**
	 * 批量审核用户线下充值记录
	 * @param params
	 * @return
	 */
	@RequiresPermissions("develop:userRechargeOffline:sysAudit")
    @Log(title = "用户线下充值管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysAudit")
    @ResponseBody
    public R sysAudit(@RequestParam Map<String, Object> params)
    {
        return userRechargeOfflineService.batchSysAuditUserRechargeOffline(params);
    }

	
}

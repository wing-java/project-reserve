package com.ruoyi.project.develop.user.controller;

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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.user.domain.UserReal;
import com.ruoyi.project.develop.user.service.UserRealService;

/**
 * 用户实名
 * 
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/userReal")
public class UserRealController extends BaseController{

	private String prefix = "develop/userReal";
	
	@Autowired
	private UserRealService userRealService;
	
	
	/**
	 * 跳转
	 * @return
	 */
	@RequiresPermissions("develop:userReal:view")
	@GetMapping()
	public String userAccount()
	{
	    return prefix + "/userReal";
	}
	
	
	/**
	 * 查询
	 */
	@RequiresPermissions("develop:userReal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = userRealService.getUserRealList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 列表
	 */
	@Log(title = "用户实名管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:userReal:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<UserReal> list = userRealService.selectUserRealList(params);
        ExcelUtil<UserReal> util = new ExcelUtil<UserReal>(UserReal.class);
        return util.exportExcel(list, "用户实名数据");
    }
	
	 /**
     * 跳转审核详情
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("develop:userReal:check")
    @GetMapping("/check/{id}")
    public String toCheck(@PathVariable("id") String id, ModelMap mmap)
    {
    	Map<String, Object> userReal = userRealService.getUserRealById(id);
        mmap.put("userReal", userReal);
        return prefix + "/check";
    }
    
    /**
     * 审核
     * @param params
     * @return
     */
    @RequiresPermissions("develop:userReal:check")
    @Log(title = "实名审核", businessType = BusinessType.OTHER)
    @PostMapping("/check")
    @ResponseBody
    public R check(@RequestParam Map<String, Object> params) {
    	return userRealService.check(params);
    }
    
    /**
     * 跳转详情
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("develop:userReal:detail")
    @GetMapping("/detail/{id}")
    public String toDetail(@PathVariable("id") String id, ModelMap mmap)
    {
    	Map<String, Object> userReal = userRealService.getUserRealById(id);
        mmap.put("userReal", userReal);
        return prefix + "/detail";
    }
}

package com.ruoyi.project.develop.param.controller;

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
import com.ruoyi.project.develop.param.domain.SysCompany;
import com.ruoyi.project.develop.param.service.SysCompanyService;

/**
 * 公司简介操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysCompany")
public class SysCompanyController extends BaseController
{
    private String prefix = "develop/sysCompany";
	
	@Autowired
	private SysCompanyService sysCompanyService;
	
	
	/**
	 * 跳转公司简介列表页面
	 * @return
	 */
	@RequiresPermissions("develop:sysCompany:view")
	@GetMapping()
	public String sysCompany()
	{
	    return prefix + "/sysCompany";
	}
	
	
	/**
	 * 查询公司简介列表
	 */
	@RequiresPermissions("develop:sysCompany:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysCompanyService.getSysCompanyList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	/**
	 * 导出公司简介列表
	 */
	@Log(title = "公司简介管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysCompany:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysCompany> list = sysCompanyService.selectSysCompanyList(params);
        ExcelUtil<SysCompany> util = new ExcelUtil<SysCompany>(SysCompany.class);
        return util.exportExcel(list, "系统套餐信息数据");
    }
	
    
    /**
     * 跳转修改公司简介页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
		mmap.put("id", id);
        return prefix + "/edit";
    }
    /**
     * 根据参数id查询详情
     * @param id
     * @return
     */
    @RequiresPermissions("develop:sysCompany:list")
    @PostMapping("/getSysCompanyById")
    @ResponseBody
    public Map<String, Object> getSysCompanyById(String id)
    {
        return sysCompanyService.getSysCompanyById(id);
    }
    /**
     * 修改保存公司简介
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysCompany:edit")
    @Log(title = "公司简介管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysCompanyService.editSysCompany(params);
    }
    
}

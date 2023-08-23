package com.ruoyi.project.develop.product.controller;

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
import com.ruoyi.project.develop.product.domain.SysCategory;
import com.ruoyi.project.develop.product.service.SysCategoryService;

/**
 * 
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysCategory")
public class SysCategoryController extends BaseController{

	private String prefix = "develop/sysCategory";
	
	@Autowired
	private SysCategoryService sysCategoryService;
	
	
	/**
	 * 跳转商品列表页面
	 * @return
	 */
	@RequiresPermissions("develop:sysCategory:view")
	@GetMapping()
	public String sysCategory()
	{
	    return prefix + "/sysCategory";
	}
	/**
	 * 查询商品列表
	 */
	@RequiresPermissions("develop:sysCategory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysCategoryService.getSysCategoryList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	/**
	 * 导出商品列表
	 */
	@Log(title = "产品包管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysCategory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysCategory> list = sysCategoryService.selectSysCategoryList(params);
        ExcelUtil<SysCategory> util = new ExcelUtil<SysCategory>(SysCategory.class);
        return util.exportExcel(list, "系统产品包数据");
    }
	
	
	/**
     * 跳转到新增页面
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }
    /**
     * 新增保存商品
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysCategory:add")
    @Log(title = "产品包管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return sysCategoryService.addSysCategory(params);
    }
    /**
     * 跳转修改商品页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	mmap.put("id", id);
    	return prefix + "/edit";
    }
    /**
     * 根据参数id查询绑定详情
     * @param id
     * @return
     */
    @RequiresPermissions("develop:sysCategory:detail")
    @PostMapping("/getsysCategoryById")
    @ResponseBody
    public Map<String, Object> getsysCategoryById(String id)
    {
        return sysCategoryService.getSysCategoryById(id);
    }
    /**
     * 修改保存产品包
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysCategory:edit")
    @Log(title = "产品包管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysCategoryService.editSysCategory(params);
    }
    /**
     * 排序
     * @param params
     * @return
     */
    @RequiresPermissions("develop:sysCategory:edit")
    @Log(title = "产品包管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sort")
    @ResponseBody
    public R sortSave(@RequestParam Map<String, Object> params)
    {
        return sysCategoryService.sortSysCategory(params);
    }
    
    /**
	 * 查询类型列表
	 * @param params
	 * @return
	 */
	@PostMapping("/getSysCategoryList")
	@ResponseBody
	public List<Map<String, Object>> getSysCategoryList(@RequestParam Map<String, Object> params)
	{
		return sysCategoryService.getSysCategoryList(params);
	}
	
	/**
     * 删除
     * @param ids
     * @return
     */
    @RequiresPermissions("develop:sysCategory:remove")
    @Log(title = "商品分类管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return sysCategoryService.batchRemoveSysCategory(ids);
    }
}

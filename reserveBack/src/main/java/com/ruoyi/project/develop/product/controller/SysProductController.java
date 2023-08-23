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

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.product.domain.SysProduct;
import com.ruoyi.project.develop.product.service.SysProductService;

/**
 * 商品操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysProduct")
public class SysProductController extends BaseController
{
    private String prefix = "develop/sysProduct";
	
	@Autowired
	private SysProductService sysProductService;
	
	
	/**
	 * 跳转商品列表页面
	 * @return
	 */
	@RequiresPermissions("develop:sysProduct:view")
	@GetMapping()
	public String sysProduct()
	{
	    return prefix + "/sysProduct";
	}
	/**
	 * 查询商品列表
	 */
	@RequiresPermissions("develop:sysProduct:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysProductService.getSysProductList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	/**
	 * 导出商品列表
	 */
	@Log(title = "商品管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysProduct:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysProduct> list = sysProductService.selectSysProductList(params);
        ExcelUtil<SysProduct> util = new ExcelUtil<SysProduct>(SysProduct.class);
        return util.exportExcel(list, "系统商品数据");
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
    @RequiresPermissions("develop:sysProduct:add")
    @Log(title = "商品管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return sysProductService.addSysProduct(params);
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
    @RequiresPermissions("develop:sysProduct:detail")
    @PostMapping("/getsysProductById")
    @ResponseBody
    public Map<String, Object> getsysProductById(String id)
    {
        return sysProductService.getSysProductById(id);
    }
    /**
     * 修改保存商品
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysProduct:edit")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysProductService.editSysProduct(params);
    }

    
    /**
	 * 系统批量删除恢复商品
	 * @param id
	 * @param operParam
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysDel/{id}")
	@RequiresPermissions("develop:sysProduct:sysDel")
    public String sysDel(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_SysDelSysProduct;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysDel";
    	}
    }
    /**
     * 批量删除恢复商品
     * @param ids
     * @return
     */
    @RequiresPermissions("develop:sysProduct:sysDel")
    @Log(title = "商品管理", businessType = BusinessType.DELETE)
    @PostMapping("/sysDel")
    @ResponseBody
    public R sysDel(@RequestParam Map<String, Object> params)
    {
        return sysProductService.batchSysDelSysProduct(params);
    }
	/**
	 * 系统批量上下架商品
	 * @param id
	 * @param operParam
	 * @param mmap
	 * @return
	 */
	@GetMapping("/sysRelease/{id}")
	@RequiresPermissions("develop:sysProduct:sysRelease")
    public String sysRelease(@PathVariable("id") String id,String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_SysReleaseSysProduct;
    	}else {
    		mmap.put("id", id);
    		mmap.put("operParam", operParam);
            return prefix + "/sysRelease";
    	}
    }
	/**
	 * 系统批量上下架商品
	 * @param params
	 * @return
	 */
	@RequiresPermissions("develop:sysProduct:sysRelease")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sysRelease")
    @ResponseBody
    public R sysRelease(@RequestParam Map<String, Object> params)
    {
        return sysProductService.batchSysReleaseSysProduct(params);
    }
	
	
    /**
	 * 跳转商品回收站列表页面
	 * @return
	 */
	@RequiresPermissions("develop:sysProduct:viewDel")
	@GetMapping("/sysProductDel")
	public String sysProductDel()
	{
	    return prefix + "/sysProductDel";
	}
    /**
     * 跳转到详情页面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
    	Map<String, Object> sysProduct = sysProductService.getSysProductById(id);
		mmap.put("sysProduct", sysProduct);
		return prefix + "/detail";
    }
    
    /**
     * 排序
     * @param params
     * @return
     */
    @RequiresPermissions("develop:sysProduct:edit")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/sort")
    @ResponseBody
    public R sortSave(@RequestParam Map<String, Object> params)
    {
        return sysProductService.sortSysProduct(params);
    }
	
}

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
import com.ruoyi.project.develop.param.domain.SysAppImg;
import com.ruoyi.project.develop.param.service.SysAppImgService;

/**
 * APP图片操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysAppImg")
public class SysAppImgController extends BaseController
{
    private String prefix = "develop/sysAppImg";
	
	@Autowired
	private SysAppImgService sysAppImgService;
	
	
	/**
	 * 跳转国际交易所列表页面
	 * @return
	 */
	@RequiresPermissions("develop:sysAppImg:view")
	@GetMapping()
	public String sysAppImg()
	{
	    return prefix + "/sysAppImg";
	}
	
	
	/**
	 * 查询国际交易所列表
	 */
	@RequiresPermissions("develop:sysAppImg:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysAppImgService.getSysAppImgList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出国际交易所列表
	 */
	@Log(title = "APP图片管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysAppImg:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysAppImg> list = sysAppImgService.selectSysAppImgList(params);
        ExcelUtil<SysAppImg> util = new ExcelUtil<SysAppImg>(SysAppImg.class);
        return util.exportExcel(list, "APP图片数据");
    }
	
    
    /**
     * 跳转修改APP图片页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	mmap.put("appImg", sysAppImgService.getSysAppImgById(id));
    	return prefix + "/edit";
    }
    
   
    /**
     * 修改APP图片
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysAppImg:edit")
    @Log(title = "APP图片管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysAppImgService.editSysAppImg(params);
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
     * 新增保存APP图片
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysAppImg:add")
    @Log(title = "APP图片管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return sysAppImgService.addSysAppImg(params);
    }
    
    
    /**
     * 批量删除APP图片
     * @param ids
     * @return
     */
    @RequiresPermissions("develop:sysAppImg:remove")
    @Log(title = "APP图片管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return sysAppImgService.batchRemoveSysAppImg(ids);
    }
	
}

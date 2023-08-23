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
import com.ruoyi.project.develop.param.domain.SysChannel;
import com.ruoyi.project.develop.param.service.SysChannelService;

/**
 * APP图片操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysChannel")
public class SysChannelController extends BaseController{

	private String prefix = "develop/sysChannel";
	
	@Autowired
	private SysChannelService sysChannelService;
	
	
	/**
	 * 跳转
	 * @return
	 */
	@RequiresPermissions("develop:sysChannel:view")
	@GetMapping()
	public String sysChannel()
	{
	    return prefix + "/sysChannel";
	}
	
	
	/**
	 * 查询
	 */
	@RequiresPermissions("develop:sysChannel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysChannelService.getSysChannelList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出
	 */
	@Log(title = "川军渠道", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysChannel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysChannel> list = sysChannelService.selectSysChannelList(params);
        ExcelUtil<SysChannel> util = new ExcelUtil<SysChannel>(SysChannel.class);
        return util.exportExcel(list, "APP图片数据");
    }
	
    
    /**
     * 跳转
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	mmap.put("channel", sysChannelService.getSysChannelById(id));
    	return prefix + "/edit";
    }
    
   
    /**
     * 修改
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysChannel:edit")
    @Log(title = "川军渠道", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysChannelService.editSysChannel(params);
    }

    
    /**
     * 跳转
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    
    /**
     * 新增
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysChannel:add")
    @Log(title = "川军渠道", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return sysChannelService.addSysChannel(params);
    }
    
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequiresPermissions("develop:sysChannel:remove")
    @Log(title = "川军渠道", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return sysChannelService.batchRemoveSysChannel(ids);
    }
}

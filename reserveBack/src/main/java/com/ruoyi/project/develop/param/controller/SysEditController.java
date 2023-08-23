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
import com.ruoyi.project.develop.param.domain.SysEdit;
import com.ruoyi.project.develop.param.service.SysEditService;

/**
 * 系统参数修改记录操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysEdit")
public class SysEditController extends BaseController
{
    private String prefix = "develop/sysEdit";
	
	@Autowired
	private SysEditService sysEditService;
	
	
	/**
	 * 跳转参数修改记录列表页面
	 * @return
	 */
	@RequiresPermissions("develop:sysEdit:view")
	@GetMapping()
	public String sysParam()
	{
	    return prefix + "/sysEdit";
	}
	
	
	/**
	 * 查询参数修改记录列表
	 */
	@RequiresPermissions("develop:sysEdit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysEditService.getSysEditList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出参数修改记录列表
	 */
	@Log(title = "系统参数列表管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysEdit:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysEdit> list = sysEditService.selectSysEditList(params);
        ExcelUtil<SysEdit> util = new ExcelUtil<SysEdit>(SysEdit.class);
        return util.exportExcel(list, "系统参数修改记录数据");
    }
	
	
	/**
	 * 根据id查询修改记录详情
	 * @param id
	 * @param mmap
	 * @return
	 */
    @RequiresPermissions("develop:sysEdit:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        //查询调度任务详情
        mmap.put("sysEdit", sysEditService.getSysEditById(id));
        return prefix + "/detail";
    }
	
}

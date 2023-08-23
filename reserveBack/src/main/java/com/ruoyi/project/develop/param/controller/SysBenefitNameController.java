package com.ruoyi.project.develop.param.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.ruoyi.project.develop.param.domain.SysBenefitName;
import com.ruoyi.project.develop.param.service.SysBenefitNameService;

/**
 * 流水类型操作处理
 * 
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysBenefitName")
public class SysBenefitNameController extends BaseController
{
    private String prefix = "develop/sysBenefitName";
	
	@Autowired
	private SysBenefitNameService sysBenefitNameService;
	
	
	/**
	 * 跳转流水类型列表页面
	 * @return
	 */
	@RequiresPermissions("develop:sysBenefitName:view")
	@GetMapping()
	public String sysBenefitName()
	{
	    return prefix + "/sysBenefitName";
	}
	
	
	/**
	 * 查询流水类型列表
	 */
	@RequiresPermissions("develop:sysBenefitName:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysBenefitNameService.getSysBenefitNameList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出参数列表
	 */
	@Log(title = "流水类型列表管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysBenefitName:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysBenefitName> list = sysBenefitNameService.selectSysBenefitNameList(params);
        ExcelUtil<SysBenefitName> util = new ExcelUtil<SysBenefitName>(SysBenefitName.class);
        return util.exportExcel(list, "流水类型数据");
    }
	

	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	@PostMapping("/getBenefitTypeList")
	@ResponseBody
	public List<Map<String, Object>> getBenefitTypeList(@RequestParam Map<String, Object> params)
	{
		return sysBenefitNameService.getBenefitTypeList(params);
	}
}

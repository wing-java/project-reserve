package com.ruoyi.project.develop.statistics.controller;

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
import com.ruoyi.project.develop.statistics.domain.SummaryPlatformBenefitEvery;
import com.ruoyi.project.develop.statistics.service.SummaryPlatformBenefitService;

/**
 * 平台流水总汇总操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/summaryPlatformBenefit")
public class SummaryPlatformBenefitController extends BaseController
{
    private String prefix = "develop/summaryPlatformBenefit";
	
	@Autowired
	private SummaryPlatformBenefitService summaryPlatformBenefitService;
	
	
	/**
	 * 跳转用户流水类性汇总列表页面
	 * @return
	 */
	@RequiresPermissions("develop:summaryPlatformBenefit:view")
	@GetMapping()
	public String summaryPlatformBenefit()
	{
	    return prefix + "/summaryPlatformBenefit";
	}
	
	
	/**
	 * 查询平台流水类性汇总列表
	 */
	@RequiresPermissions("develop:summaryPlatformBenefit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = summaryPlatformBenefitService.getSummaryPlatformBenefitList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户流水类性汇总列表
	 */
	@Log(title = "平台流水类型总汇总", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:summaryPlatformBenefit:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SummaryPlatformBenefitEvery> list = summaryPlatformBenefitService.selectSummaryPlatformBenefitList(params);
        ExcelUtil<SummaryPlatformBenefitEvery> util = new ExcelUtil<SummaryPlatformBenefitEvery>(SummaryPlatformBenefitEvery.class);
        return util.exportExcel(list, "平台流水类型总汇总数据");
    }

}

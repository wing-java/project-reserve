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
import com.ruoyi.project.develop.statistics.domain.SummaryUserBenefitEvery;
import com.ruoyi.project.develop.statistics.service.SummaryUserBenefitEveryService;

/**
 * 用户流水每日汇总操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/summaryUserBenefitEvery")
public class SummaryUserBenefitEveryController extends BaseController
{
    private String prefix = "develop/summaryUserBenefitEvery";
	
	@Autowired
	private SummaryUserBenefitEveryService summaryUserBenefitEveryService;
	
	
	/**
	 * 跳转用户流水类型每日汇总列表页面
	 * @return
	 */
	@RequiresPermissions("develop:summaryUserBenefitEvery:view")
	@GetMapping()
	public String summaryUserBenefitEvery()
	{
	    return prefix + "/summaryUserBenefitEvery";
	}
	
	
	/**
	 * 查询用户流水类性汇总列表
	 */
	@RequiresPermissions("develop:summaryUserBenefitEvery:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = summaryUserBenefitEveryService.getSummaryUserBenefitEveryList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户流水类每日汇总列表
	 */
	@Log(title = "用户流水类型每日汇总", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:summaryUserBenefitEvery:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SummaryUserBenefitEvery> list = summaryUserBenefitEveryService.selectSummaryUserBenefitEveryList(params);
        ExcelUtil<SummaryUserBenefitEvery> util = new ExcelUtil<SummaryUserBenefitEvery>(SummaryUserBenefitEvery.class);
        return util.exportExcel(list, "用户流水类型每日汇总数据");
    }

}

package com.ruoyi.project.develop.benefit.controller;

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
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.benefit.domain.Benefit;
import com.ruoyi.project.develop.benefit.service.BenefitBalanceService;

/**
 * 余额流水操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/benefitBalance")
public class BenefitBalanceController extends BaseController
{
    private String prefix = "develop/benefitBalance";
	
	@Autowired
	private BenefitBalanceService benefitBalanceService;
	
	
	/**
	 * 跳转用户余额流水列表页面
	 * @return
	 */
	@RequiresPermissions("develop:benefitBalance:view")
	@GetMapping()
	public String benefitBalance()
	{
	    return prefix + "/benefitBalance";
	}
	
	
	/**
	 * 查询用户余额流水列表
	 */
	@RequiresPermissions("develop:benefitBalance:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = benefitBalanceService.getBenefitRecordBalanceList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出余额流水列表
	 */
	@Log(title = "余额流水管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:benefitBalance:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
		params.put("memberId", ShiroUtils.getSysUser().getMemberId());
    	List<Benefit> list = benefitBalanceService.selectBenefitRecordBalanceList(params);
        ExcelUtil<Benefit> util = new ExcelUtil<Benefit>(Benefit.class);
        return util.exportExcel(list, "余额流水数据");
    }

}

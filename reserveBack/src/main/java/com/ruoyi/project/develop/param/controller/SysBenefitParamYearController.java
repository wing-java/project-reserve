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

import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysBenefitParamYear;
import com.ruoyi.project.develop.param.service.SysBenefitParamYearService;

/**
 * 流水类型操作处理
 * 
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysBenefitParamYear")
public class SysBenefitParamYearController extends BaseController{

	private String prefix = "develop/sysBenefitParamYear";
	
	@Autowired
	private SysBenefitParamYearService sysBenefitParamYearService;
	
	
	/**
	 * 跳转
	 * @return
	 */
	@RequiresPermissions("develop:sysBenefitParamYear:view")
	@GetMapping()
	public String sysParam()
	{
	    return prefix + "/sysBenefitParamYear";
	}
	
	
	/**
	 * 查询
	 */
	@RequiresPermissions("develop:sysBenefitParamYear:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysBenefitParamYearService.getSysBenefitParamYearList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出
	 */
	@Log(title = "年度收益参数管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysBenefitParamYear:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysBenefitParamYear> list = sysBenefitParamYearService.selectSysBenefitParamYearList(params);
        ExcelUtil<SysBenefitParamYear> util = new ExcelUtil<SysBenefitParamYear>(SysBenefitParamYear.class);
        return util.exportExcel(list, "年度收益参数数据");
    }
	
    
    /**
     * 跳转修改参数页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String operParam, ModelMap mmap)
    {
    	if(!ShiroUtils.getSysUser().isAuth()) {
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_EditSysBenefitParamYear;
    	}else {
    		mmap.put("id", id);
            return prefix + "/edit";
    	}
    }
    
    
    /**
     * 详情
     * @param id
     * @return
     */
    @RequiresPermissions("develop:sysBenefitParamYear:list")
    @PostMapping("/getSysBenefitParamYearById")
    @ResponseBody
    public Map<String, Object> getSysBenefitParamYearById(String id)
    {
        return sysBenefitParamYearService.getSysBenefitParamYearById(id);
    }

   
    /**
     * 修改保存参数
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysBenefitParamYear:edit")
    @Log(title = "年度收益参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysBenefitParamYearService.editSave(params);
    }
}

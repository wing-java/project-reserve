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
import com.ruoyi.project.develop.param.domain.SysBenefitParamAlgebra;
import com.ruoyi.project.develop.param.service.SysBenefitParamAlgebraService;

/**
 * 流水类型操作处理
 * 
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/sysBenefitParamAlgebra")
public class SysBenefitParamAlgebraController extends BaseController{

	private String prefix = "develop/sysBenefitParamAlgebra";
	
	@Autowired
	private SysBenefitParamAlgebraService sysBenefitParamAlgebraService;
	
	
	/**
	 * 跳转
	 * @return
	 */
	@RequiresPermissions("develop:sysBenefitParamAlgebra:view")
	@GetMapping()
	public String sysParam()
	{
	    return prefix + "/sysBenefitParamAlgebra";
	}
	
	
	/**
	 * 查询
	 */
	@RequiresPermissions("develop:sysBenefitParamAlgebra:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam Map<String, Object> params)
	{
		//此方法配合前端完成自动分页
		startPage();
		//根据条件分页查询用户列表
        List<Map<String, Object>> list = sysBenefitParamAlgebraService.getSysBenefitParamAlgebraList(params);
        //处理响应请求分页数据
		return getDataTable(list);
	}
	
	
	/**
	 * 导出
	 */
	@Log(title = "分享收益参数管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("develop:sysBenefitParamAlgebra:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam Map<String, Object> params)
    {
    	List<SysBenefitParamAlgebra> list = sysBenefitParamAlgebraService.selectSysBenefitParamAlgebraList(params);
        ExcelUtil<SysBenefitParamAlgebra> util = new ExcelUtil<SysBenefitParamAlgebra>(SysBenefitParamAlgebra.class);
        return util.exportExcel(list, "分享收益参数数据");
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
    		return "redirect:/system/user/profile/toIsAuth?id="+id+"&operParam="+operParam+"&bus_type="+VerifyConstant.AuthVerfiy_EditSysBenefitParamAlgebra;
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
    @RequiresPermissions("develop:sysBenefitParamAlgebra:list")
    @PostMapping("/getSysBenefitParamAlgebraById")
    @ResponseBody
    public Map<String, Object> getSysBenefitParamAlgebraById(String id)
    {
        return sysBenefitParamAlgebraService.getSysBenefitParamAlgebraById(id);
    }

   
    /**
     * 修改保存参数
     * @param user
     * @return
     */
    @RequiresPermissions("develop:sysBenefitParamAlgebra:edit")
    @Log(title = "分享收益参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysBenefitParamAlgebraService.editSave(params);
    }
}

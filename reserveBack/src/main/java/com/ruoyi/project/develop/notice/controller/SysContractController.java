package com.ruoyi.project.develop.notice.controller;

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

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.notice.service.SysContractService;

/**
 * 合同资讯 信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/develop/sysContract")
public class SysContractController extends BaseController
{
    private String prefix = "develop/sysContract";

    @Autowired
    private SysContractService sysContractService;

    
    /**
     * 跳转合同资讯列表页面
     * @return
     */
    @RequiresPermissions("develop:sysContract:view")
    @GetMapping()
    public String sysContract()
    {
        return prefix + "/sysContract";
    }

   
    /**
     * 查询合同资讯列表
     * @param notice
     * @return
     */
    @RequiresPermissions("develop:sysContract:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> params)
    {
    	//此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询公告列表
        List<Map<String, Object>> list = sysContractService.getSysContractList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    
    /**
     * 跳转新增合同资讯页面
     * @return
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

   
    /**
     * 新增保存合同资讯
     * @param params
     * @return
     */
    @RequiresPermissions("develop:sysContract:add")
    @Log(title = "新增合同", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return sysContractService.addSysContract(params);
    }

    
    /**
     * 跳转修改合同资讯页面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	//根据公告id查询合同资讯详情
        mmap.put("sysContract", sysContractService.getContractById(id));
        return prefix + "/edit";
    }

   
    /**
     * 修改保存合同资讯
     * @param notice
     * @return
     */
    @RequiresPermissions("develop:sysContract:edit")
    @Log(title = "修改合同内容", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysContractService.editSysContract(params);
    }
    
    
    /**
     * 批量删除系统合同信息
     * @param ids
     * @return
     */
    @RequiresPermissions("develop:sysContract:remove")
    @Log(title = "删除合同管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return sysContractService.batchRemoveSysContract(ids);
    }
   
}

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
import com.ruoyi.project.develop.notice.service.SysBonusService;

/**
 * 奖励制度
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/develop/sysBonus")
public class SysBonusController extends BaseController
{
    private String prefix = "develop/sysBonus";

    @Autowired
    private SysBonusService sysBonusService;

    
    /**
     * 跳转
     * @return
     */
    @RequiresPermissions("develop:sysBonus:view")
    @GetMapping()
    public String bonus()
    {
        return prefix + "/sysBonus";
    }

   
    /**
     * 查询
     * @param notice
     * @return
     */
    @RequiresPermissions("develop:sysBonus:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> params)
    {
    	//此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询公告列表
        List<Map<String, Object>> list = sysBonusService.getSysBonusList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    
    /**
     * 跳转新增
     * @return
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

   
    /**
     * 新增保存
     * @param params
     * @return
     */
    @RequiresPermissions("develop:sysBonus:add")
    @Log(title = "分红奖励", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return sysBonusService.addSysBonus(params);
    }

    
    /**
     * 跳转修改
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	//根据公告id查询公告详情
        mmap.put("sysBonus", sysBonusService.getBonusById(id));
        return prefix + "/edit";
    }

   
    /**
     * 修改保存
     * @param notice
     * @return
     */
    @RequiresPermissions("develop:sysBonus:edit")
    @Log(title = "分红奖励", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysBonusService.editSysBonus(params);
    }
    
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequiresPermissions("develop:sysBonus:remove")
    @Log(title = "分红奖励", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return sysBonusService.batchRemoveSysBonus(ids);
    }
   
}

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
import com.ruoyi.project.develop.notice.service.SysNoticeService;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/develop/sysNotice")
public class SysNoticeController extends BaseController
{
    private String prefix = "develop/sysNotice";

    @Autowired
    private SysNoticeService sysNoticeService;

    
    /**
     * 跳转公告列表页面
     * @return
     */
    @RequiresPermissions("develop:sysNotice:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/sysNotice";
    }

   
    /**
     * 查询公告列表
     * @param notice
     * @return
     */
    @RequiresPermissions("develop:sysNotice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> params)
    {
    	//此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询公告列表
        List<Map<String, Object>> list = sysNoticeService.getSysNoticeList(params);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    
    /**
     * 跳转新增公告页面
     * @return
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

   
    /**
     * 新增保存公告
     * @param params
     * @return
     */
    @RequiresPermissions("develop:sysNotice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@RequestParam Map<String, Object> params)
    {
    	return sysNoticeService.addSysNotice(params);
    }

    
    /**
     * 跳转修改公告页面
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
    	//根据公告id查询公告详情
        mmap.put("sysNotice", sysNoticeService.getNoticeById(id));
        return prefix + "/edit";
    }

   
    /**
     * 修改保存公告
     * @param notice
     * @return
     */
    @RequiresPermissions("develop:sysNotice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@RequestParam Map<String, Object> params)
    {
        return sysNoticeService.editSysNotice(params);
    }
    
    
    /**
     * 批量删除系统公告
     * @param ids
     * @return
     */
    @RequiresPermissions("develop:sysNotice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
       return sysNoticeService.batchRemoveSysNotice(ids);
    }
   
}

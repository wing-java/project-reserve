package com.ruoyi.project.monitor.job.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.monitor.job.domain.JobLog;
import com.ruoyi.project.monitor.job.service.IJobLogService;

/**
 * 调度日志操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class JobLogController extends BaseController
{
    private String prefix = "monitor/job";

    @Autowired
    private IJobLogService jobLogService;

    
    /**
     * 跳转查看调读日志列表页面
     * @return
     */
    @RequiresPermissions("monitor:job:view")
    @GetMapping()
    public String jobLog()
    {
        return prefix + "/jobLog";
    }

    
    /**
     * 查询调度任务日志列表
     * @param jobLog
     * @return
     */
    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JobLog jobLog)
    {
    	//此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询调度任务日志列表
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    
    /**
     * 导出调度任务日志报表
     * @param jobLog
     * @return
     */
    @Log(title = "调度日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JobLog jobLog)
    {
    	//不分页查询所有调度任务
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
        //Excel相关处理对象
        ExcelUtil<JobLog> util = new ExcelUtil<JobLog>(JobLog.class);
        //对list数据源将其里面的数据导入到excel表单
        return util.exportExcel(list, "调度日志");
    }

    
    /**
     * 批量删除调度任务日志
     * @param ids
     * @return
     */
    @Log(title = "调度日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jobLogService.deleteJobLogByIds(ids));
    }
    
    
    /**
     * 查看调读任务日志详情
     * @param jobLogId
     * @param mmap
     * @return
     */
    @RequiresPermissions("monitor:job:detail")
    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap)
    {
        mmap.put("name", "jobLog");
        mmap.put("jobLog", jobLogService.selectJobLogById(jobLogId));
        return prefix + "/detail";
    }
    
    
    /**
     * 清空调读任务日志
     * @return
     */
    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}

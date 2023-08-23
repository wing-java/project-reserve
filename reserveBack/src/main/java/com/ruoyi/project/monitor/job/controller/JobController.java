package com.ruoyi.project.monitor.job.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.monitor.job.domain.Job;
import com.ruoyi.project.monitor.job.service.IJobService;

/**
 * 调度任务信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/job")
public class JobController extends BaseController
{
    private String prefix = "monitor/job";

    @Autowired
    private IJobService jobService;

    
    /**
     * 跳转调度任务列表页面
     * @return
     */
    @RequiresPermissions("monitor:job:view")
    @GetMapping()
    public String job()
    {
        return prefix + "/job";
    }

    
    /**
     * 查询调度任务列表
     * @param job
     * @return
     */
    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Job job)
    {
    	//此方法配合前端完成自动分页
        startPage();
        //根据条件分页查询操作日志列表
        List<Job> list = jobService.selectJobList(job);
        //处理响应请求分页数据
        return getDataTable(list);
    }

    
    /**
     * 导出调度任务报表
     * @param job
     * @return
     */
    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Job job)
    {
    	//不分页查询所有调度任务
        List<Job> list = jobService.selectJobList(job);
        //Excel相关处理对象
        ExcelUtil<Job> util = new ExcelUtil<Job>(Job.class);
        //对list数据源将其里面的数据导入到excel表单
        return util.exportExcel(list, "定时任务");
    }

    
    /**
     * 批量删除调度任务
     * @param ids
     * @return
     * @throws SchedulerException
     */
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String ids)
    {
        return jobService.batchRemoveJob(ids);
    }

    
    /**
     * 根据调度任务id查询详情
     * @param jobId
     * @param mmap
     * @return
     */
    @RequiresPermissions("monitor:job:detail")
    @GetMapping("/detail/{jobId}")
    public String detail(@PathVariable("jobId") Long jobId, ModelMap mmap)
    {
        mmap.put("name", "job");
        //查询调度任务详情
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/detail";
    }

   
    /**
     * 调度任务状态修改
     * @param job
     * @return
     * @throws SchedulerException
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/changeStatus")
    @ResponseBody
    public R changeStatus(@RequestParam Map<String, Object> map)
    {
    	return jobService.changeJobStatus(map);
    }

    
    /**
     * 调度任务立即执行一次
     * @param job
     * @return
     * @throws SchedulerException
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/run")
    @ResponseBody
    public R run(@RequestParam Map<String, Object> map)
    {
        return jobService.run(map);
    }

   
    /**
     * 跳转新增调度任务页面
     * @return
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

   
    /**
     * 新增保存调度任务
     * @param job
     * @return
     */
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @RequiresPermissions("monitor:job:add")
    @PostMapping("/add")
    @ResponseBody
    public R addSave(@Validated Job job)
    {
    	return jobService.addJob(job);
    }

    
    /**
     * 跳转修改调度任务页面
     * @param jobId
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap)
    {
    	//查询调度任务详情
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/edit";
    }

    
    /**
     * 编辑保存调度任务
     * @param job
     * @return
     * @throws SchedulerException
     * @throws TaskException
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:edit")
    @PostMapping("/edit")
    @ResponseBody
    public R editSave(@Validated Job job)
    {
    	return jobService.updateJob(job);
    }

    
    /**
     * 校验corn表达式是否有效
     * @param job
     * @return
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(Job job)
    {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }
}

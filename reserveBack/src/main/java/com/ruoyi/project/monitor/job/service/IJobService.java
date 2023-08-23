package com.ruoyi.project.monitor.job.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.monitor.job.domain.Job;

/**
 * 定时任务调度信息信息 服务层
 * 
 * @author ruoyi
 */
public interface IJobService
{
	
    /**
     * 获取quartz调度器的计划任务列表
     * 
     * @param job 调度信息
     * @return 调度任务集合
     */
    public List<Job> selectJobList(Job job);

    
    /**
     * 通过调度任务ID查询调度信息详情
     * 
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    public Job selectJobById(Long jobId);

    
    /**
     * 立即运行任务
     * 
     * @param map 调度信息
     * @return 结果
     */
    public R run(Map<String, Object> map);


    /**
     * 校验cron表达式是否有效
     * 
     * @param cronExpression 表达式
     * @return 结果
     */
    public boolean checkCronExpressionIsValid(String cronExpression);


    /**
     * 批量删除调度任务
     * @param ids
     * @return
     */
	public R batchRemoveJob(String ids);


	/**
	 * 调度任务状态修改
	 * @param map 
	 * @return
	 */
	public R changeJobStatus(Map<String, Object> map);


	/**
	 * 新增保存调度任务====》默认不开启
	 * @param job
	 * @return
	 */
	public R addJob(Job job);


	/**
	 * 编辑保存调度任务
	 * @param job
	 * @return
	 */
	public R updateJob(Job job);


	/**
	 * 新增保存调度任务====》默认开启
	 * @param job
	 * @return
	 */
	public R addJobOpen(Job job);
}

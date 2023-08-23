package com.ruoyi.project.monitor.job.util;

import org.quartz.JobExecutionContext;
import com.ruoyi.project.monitor.job.domain.Job;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
	
	/**
	 * 执行任务
	 */
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception
    {
        JobInvokeUtil.invokeMethod(job);
    }
}

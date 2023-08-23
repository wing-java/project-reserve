package com.ruoyi.project.monitor.job.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.CornUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.monitor.job.domain.Job;
import com.ruoyi.project.monitor.job.mapper.JobMapper;
import com.ruoyi.project.monitor.job.util.CronUtils;
import com.ruoyi.project.monitor.job.util.ScheduleUtils;

/**
 * 定时任务调度信息 服务层
 * 
 * @author ruoyi
 */
@Service
public class JobServiceImpl implements IJobService
{
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobMapper jobMapper;

    
    /**
     * 项目启动时，初始化定时器
     * 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     * @throws SchedulerException
     * @throws TaskException
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException
    {
    	//查询所有调度任务列表
        List<Job> jobList = jobMapper.selectJobAll();
        //依次循环处理每一个调度任务
        for (Job job : jobList)
        {
        	//只执行一次的任务
        	if(TypeStatusConstant.sys_job_job_type_02.equals(job.getJobType())){
        		//先判断比较任务执行的时间：任务可以正常执行（没有超过执行时间）
        		if(TimeUtil.get_format4(CornUtil.getCronToDate(job.getCronExpression())).compareTo(TimeUtil.get_format4(new Date()))>0) {
        			System.out.println("活动任务没有超过执行时间");
        			updateSchedulerJob(job, job.getJobGroup());
        		}else {
        			//否则任务超过了执行时间，删除掉
        			this.removeJob(job.getJobId());
        		}
        	}else {
        		System.out.println("普通任务");
        		updateSchedulerJob(job, job.getJobGroup());
        	}
        }
    }
    
    
    /**
     * 更新任务
     * @param job 调度信息
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(Job job, String jobGroup) throws SchedulerException, TaskException
    {
        Long jobId = job.getJobId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey))
        {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        //创建定时任务
        ScheduleUtils.createScheduleJob(scheduler, job);
    }
    
    

    /**
     * 获取quartz调度器的计划任务列表
     * @param job 调度信息
     * @return
     */
    @Override
    public List<Job> selectJobList(Job job)
    {
        return jobMapper.selectJobList(job);
    }
    
    

    /**
     * 通过调度任务ID查询调度信息
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public Job selectJobById(Long jobId)
    {
        return jobMapper.selectJobById(jobId);
    }



    /**
     * 立即运行任务
     * 
     * @param job 调度信息
     */
    @Override
    @Transactional
    public R run(Map<String, Object> map)
    {
        try {
        	//任务id
        	Long jobId = Long.parseLong(map.get("job_id").toString());
        	//任务组
            String jobGroup = map.get("job_group").toString();
            //根据任务id查询详情
            Job properties = selectJobById(jobId);
            
            //参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
            scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		} 
    }
    
    

    /**
     * 校验cron表达式是否有效
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression)
    {
        return CronUtils.isValid(cronExpression);
    }
    

    /**
     * 批量删除调度任务
     */
	@Override
	public R batchRemoveJob(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        Long[] jobIds = Convert.toLongArray(ids);
        //依次循环遍历删除
        for (Long jobId : jobIds)
        {
            //依次删除每个任务
        	R result = SpringUtils.getAopProxy(this).removeJob(jobId);
        	//R result = ((JobServiceImpl) AopContext.currentProxy()).removeJob(jobId); 
            if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        
        if(failure_num>0) {
        	failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 删除单个调度任务
	 * @param jobId
	 * @return
	 */
	@Transactional
	public R removeJob(Long jobId) {
		try {
			int i=0;
			//根据任务id查询任务详情
			Job job = jobMapper.selectJobById(jobId);
			//根据任务id删除任务
			i = jobMapper.deleteJobById(job.getJobId());
			if(i != 1) {
				return R.error(Type.WARN, "调度任务编号"+jobId+"：信息删除失败");
			}
			scheduler.deleteJob(ScheduleUtils.getJobKey(job.getJobId(), job.getJobGroup()));
			return R.ok("调度任务删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "调度任务编号"+jobId+"：删除异常");
		} 
	}
	
	
	
	/**
	 * 调度任务状态修改
	 */
	@Override
	@Transactional
	public R changeJobStatus(Map<String, Object> map) {
		//1、恢复调度任务
		if(ScheduleConstants.Status.NORMAL.getValue().equals(map.get("status").toString())) {
			return this.resumeJob(map);
		}else if(ScheduleConstants.Status.PAUSE.getValue().equals(map.get("status").toString())) {
			//2、暂停调度任务
			return this.pauseJob(map);
		}else {
			return R.error(Type.WARN, "参数指令异常");
		}
	}

	
	/**
	 * 暂停任务
	 * @param map
	 * @return
	 */
	private R pauseJob(Map<String, Object> map) {
		try {
			int i=0;
			//（1）更新状态
			map.put("old_status", ScheduleConstants.Status.NORMAL.getValue());//旧状态：开启
			map.put("new_status", ScheduleConstants.Status.PAUSE.getValue());//新状态：暂停
			map.put("update_by", ShiroUtils.getLoginName());//更新人
			System.out.println(map.toString());
			i = jobMapper.updateJobStatus(map);
			if(i != 1) {
				return R.error(Type.WARN, "调度任务状态更新失败，请勿重复操作");
			}
			//（2）暂停任务
			Long jobId = Long.parseLong(map.get("job_id").toString());//任务id
			String jobGroup = map.get("job_group").toString();//任务组
			scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
			
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "操作异常");
		}
	}


	/**
	 * 恢复任务
	 * @param map
	 * @return
	 */
	private R resumeJob(Map<String, Object> map) {
		try {
			int i=0;
			//（1）更新状态
			map.put("old_status", ScheduleConstants.Status.PAUSE.getValue());//旧状态：暂停
			map.put("new_status", ScheduleConstants.Status.NORMAL.getValue());//新状态：开启
			map.put("update_by", ShiroUtils.getLoginName());//更新人
			i = jobMapper.updateJobStatus(map);
			if(i != 1) {
				return R.error(Type.WARN, "调度任务状态更新失败，请勿重复操作");
			}
			//（2）开启任务
			Long jobId = Long.parseLong(map.get("job_id").toString());//任务id
			String jobGroup = map.get("job_group").toString();//任务组
			scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
			
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "操作异常");
		}
	}


	/**
	 * 新增保存调度任务：默认不启动
	 */
	@Override
	@Transactional
	public R addJob(Job job) {
		try {
			int i=0;
			//（1）新增调度任务
			job.setCreateBy(ShiroUtils.getLoginName());//创建人
			job.setStatus(ScheduleConstants.Status.PAUSE.getValue());//任务状态
			i = jobMapper.insertJob(job);
			if(i != 1) {
				return R.error(Type.WARN, "调度任务保存失败");
			}
			ScheduleUtils.createScheduleJob(scheduler, job);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "操作异常");
		}
	}
	
	
	/**
	 * 新增保存调度任务，默认开启
	 */
	@Override
	@Transactional
	public R addJobOpen(Job job) {
		try {
			int i=0;
			//（1）新增调度任务
			job.setCreateBy(ShiroUtils.getLoginName());//创建人
			i = jobMapper.insertJob(job);
			if(i != 1) {
				return R.error(Type.WARN, "调度任务保存失败");
			}
			ScheduleUtils.createScheduleJob(scheduler, job);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "操作异常");
		}
	}




	/**
	 * 编辑保存调度任务
	 */
	@Override
	public R updateJob(Job job) {
		try {
			//查询任务详情
			Job properties = selectJobById(job.getJobId());
			
			int i=0;
			job.setUpdateBy(ShiroUtils.getLoginName());//更新人
			i = jobMapper.updateJob(job);
			if(i != 1) {
				return R.error(Type.WARN, "调度任务更新失败");
			}
			//更新任务
			updateSchedulerJob(job, properties.getJobGroup());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "操作异常");
		} 
	}
	
	
}
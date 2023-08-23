package com.ruoyi.project.monitor.job.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.monitor.job.domain.Job;


/**
 * 调度任务信息 数据层
 * 
 * @author ruoyi
 */
public interface JobMapper
{
	
    /**
     * 查询调度任务日志集合
     * 
     * @param job 调度信息
     * @return 操作日志集合
     */
    public List<Job> selectJobList(Job job);
    

    /**
     * 查询所有调度任务
     * 
     * @return 调度任务列表
     */
    public List<Job> selectJobAll();
    
    

    /**
     * 通过调度ID查询调度任务信息
     * 
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    public Job selectJobById(Long jobId);
    
    

    /**
     * 通过调度ID删除调度任务信息
     * 
     * @param jobId 调度ID
     * @return 结果
     */
    public int deleteJobById(Long jobId);
    
    

    /**
     * 批量删除调度任务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobByIds(Long[] ids);
    
    

    /**
     * 修改调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int updateJob(Job job);
    
    

    /**
     * 新增调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int insertJob(Job job);


    /**
     * 更新调度任务状态
     * @param map
     * @return
     */
	public int updateJobStatus(@Param("map") Map<String, Object> map);
}

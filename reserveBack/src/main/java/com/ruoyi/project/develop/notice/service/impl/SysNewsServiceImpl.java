package com.ruoyi.project.develop.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.RedisNameVersionConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.notice.mapper.SysNewsMapper;
import com.ruoyi.project.develop.notice.service.SysNewsService;


/**
 * 新闻资讯 服务层实现
 * @author Administrator
 *
 */
@Service
public class SysNewsServiceImpl implements SysNewsService
{
	
	@Autowired
    private RedisUtils redisUtils;
	
    @Autowired
    private SysNewsMapper sysNewsMapper;

    
    /**
     * 查询新闻资讯列表
     */
	@Override
	public List<Map<String, Object>> getSysNewsList(Map<String, Object> params) {
		return sysNewsMapper.getSysNewsList(params);
	}

	
	/**
	 * 查询新闻资讯详情
	 */
	@Override
	public Map<String, Object> getNewsById(String id) {
		return sysNewsMapper.getNewsById(id);
	}

	
	/**
	 * 新增新闻资讯
	 */
	@Override
	public R addSysNews(Map<String, Object> params) {
		try {
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			int i = sysNewsMapper.addSysNews(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			redisUtils.remove(RedisNameConstants.sys_news_info_new);
			redisUtils.updateVersion(RedisNameVersionConstants.sys_news_info_list_version);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}

	
	/**
	 * 更新系统新闻资讯
	 */
	@Override
	public R editSysNews(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = sysNewsMapper.updateSysNews(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			//删除最新缓存
			redisUtils.remove(RedisNameConstants.sys_news_info_new,
					RedisNameConstants.sys_news_info_detail+params.get("news_id").toString());
			redisUtils.updateVersion(RedisNameVersionConstants.sys_news_info_list_version);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}


	/**
	 * 批量删除系统新闻资讯
	 */
	@Override
	public R batchRemoveSysNews(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] news_ids = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String news_id : news_ids)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeSysNews(news_id);
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
        redisUtils.remove(RedisNameConstants.sys_news_info_new);
		redisUtils.updateVersion(RedisNameVersionConstants.sys_news_info_list_version);
        return R.ok(failure_msg);
	}


	/**
	 * 删除单个数据
	 * @param jobId
	 * @return
	 */
	@Transactional
	public R removeSysNews(String news_id) {
		try {
			int i = sysNewsMapper.deleteSysNews(news_id);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "新闻编号"+news_id+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "新闻编号"+news_id+"：删除异常");
		} 
	}
	
    
}

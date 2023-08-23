package com.ruoyi.project.develop.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.param.domain.SysChannel;
import com.ruoyi.project.develop.param.mapper.SysChannelMapper;
import com.ruoyi.project.develop.param.service.SysChannelService;

@Service
public class SysChannelServiceImpl implements SysChannelService {

	@Autowired
	private SysChannelMapper sysChannelMapper;

	
	/**
	 * 查询
	 */
	@Override
	public List<Map<String, Object>> getSysChannelList(Map<String, Object> params) {
		return sysChannelMapper.getSysChannelList(params);
	}

	
	/**
	 * 导出
	 */
	@Override
	public List<SysChannel> selectSysChannelList(Map<String, Object> params) {
		return sysChannelMapper.selectSysChannelList(params);
	}


	/**
	 * 详情
	 */
	@Override
	public Map<String, Object> getSysChannelById(String id) {
		return sysChannelMapper.getSysChannelById(id);
	}


	/**
	 * 编辑
	 */
	@Override
	public R editSysChannel(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = sysChannelMapper.updateSysChannel(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}


	/**
	 * 新增
	 */
	@Override
	public R addSysChannel(Map<String, Object> params) {
		try {
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			
			int i = sysChannelMapper.addSysChannel(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}


	/**
	 * 批量删除
	 */
	@Override
	public R batchRemoveSysChannel(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] ChannelIds = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String ChannelId : ChannelIds)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeSysChannel(ChannelId);
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
	 * 删除单个数据
	 * @param jobId
	 * @return
	 */
	@Transactional
	public R removeSysChannel(String ChannelId) {
		try {
			//（1）根据编号查询图片详情
			Map<String, Object> sysChannelMap = sysChannelMapper.getSysChannelById(ChannelId);
			//（2）删除图片
			int i = sysChannelMapper.deleteSysChannel(ChannelId);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "渠道编号"+ChannelId+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "渠道编号"+ChannelId+"：删除异常");
		} 
	}
}

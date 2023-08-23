package com.ruoyi.project.develop.notice.service.impl;

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
import com.ruoyi.project.develop.notice.mapper.SysBonusMapper;
import com.ruoyi.project.develop.notice.service.SysBonusService;

@Service
public class SysBonusServiceImpl implements SysBonusService {
	
    @Autowired
    private SysBonusMapper sysBonusMapper;

    
    /**
     * 查询
     */
	@Override
	public List<Map<String, Object>> getSysBonusList(Map<String, Object> params) {
		return sysBonusMapper.getSysBonusList(params);
	}

	
	/**
	 * 查询
	 */
	@Override
	public Map<String, Object> getBonusById(String id) {
		return sysBonusMapper.getBonusById(id);
	}

	
	/**
	 * 新增公告
	 */
	@Override
	public R addSysBonus(Map<String, Object> params) {
		try {
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			int i = sysBonusMapper.addSysBonus(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增分红奖励异常");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}
	
	
	/**
	 * 更新
	 */
	@Override
	public R editSysBonus(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = sysBonusMapper.updateSysBonus(params);
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
	 * 批量删除
	 */
	@Override
	public R batchRemoveSysBonus(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] bonus_ids = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String bonus_id : bonus_ids)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeSysBonus(bonus_id);
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
	 * 删除单个
	 * @param jobId
	 * @return
	 */
	@Transactional
	public R removeSysBonus(String bonus_id) {
		try {
			int i = sysBonusMapper.deleteSysBonus(bonus_id);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "分红奖励编号"+bonus_id+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "分红奖励编号"+bonus_id+"：删除异常");
		} 
	}
}

package com.ruoyi.project.develop.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.notice.mapper.SysContractMapper;
import com.ruoyi.project.develop.notice.service.SysContractService;


/**
 * 合同 服务层实现
 * @author Administrator
 *
 */
@Service
public class SysContractServiceImpl implements SysContractService
{
	
	@Autowired
    private RedisUtils redisUtils;
	
    @Autowired
    private SysContractMapper sysContractMapper;

    
    /**
     * 查询合同列表
     */
	@Override
	public List<Map<String, Object>> getSysContractList(Map<String, Object> params) {
		return sysContractMapper.getSysContractList(params);
	}

	
	/**
	 * 查询合同详情
	 */
	@Override
	public Map<String, Object> getContractById(String id) {
		return sysContractMapper.getContractById(id);
	}

	
	/**
	 * 新增合同
	 */
	@Override
	public R addSysContract(Map<String, Object> params) {
		try {
			//（1）校验是否存在该类型的注册协议
			Map<String, Object> sysContractMap = sysContractMapper.getContractByType(params.get("contract_type").toString());
			if(sysContractMap!=null) {
				return R.error(Type.WARN, "已经存在该类型的合同");
			}
			//（2）新增保存协议合同
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			int i = sysContractMapper.addSysContract(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			//（3）清除缓存
			redisUtils.remove(RedisNameConstants.sys_contract_info_type+params.get("contract_type").toString());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}
	

	/**
	 * 更新系统合同
	 */
	@Override
	public R editSysContract(Map<String, Object> params) {
		try {
			//（1）更新合同内容
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = sysContractMapper.updateSysContract(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			//（2）根据编号查询详情
			Map<String, Object> sysContractMap = sysContractMapper.getContractById(params.get("contract_id").toString());
			//（3）清除缓存
			redisUtils.remove(RedisNameConstants.sys_contract_info_type+sysContractMap.get("contract_type").toString());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}


	/**
	 * 批量删除系统合同
	 */
	@Override
	public R batchRemoveSysContract(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] contract_ids = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String contract_id : contract_ids)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeSysContract(contract_id);
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
	public R removeSysContract(String contract_id) {
		try {
			//（1）删除协议
			int i = sysContractMapper.deleteSysContract(contract_id);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+contract_id+"：删除失败");
			}
			//（2）根据编号查询详情
			Map<String, Object> sysContractMap = sysContractMapper.getContractById(contract_id);
			//（3）清除缓存
			redisUtils.remove(RedisNameConstants.sys_contract_info_type+sysContractMap.get("contract_type").toString());
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "编号"+contract_id+"：删除异常");
		} 
	}
	
    
}

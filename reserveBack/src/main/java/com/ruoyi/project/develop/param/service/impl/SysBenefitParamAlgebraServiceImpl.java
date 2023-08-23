package com.ruoyi.project.develop.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.param.domain.SysBenefitParamAlgebra;
import com.ruoyi.project.develop.param.mapper.SysBenefitParamAlgebraMapper;
import com.ruoyi.project.develop.param.service.SysBenefitParamAlgebraService;

@Service
public class SysBenefitParamAlgebraServiceImpl implements SysBenefitParamAlgebraService {
	
	@Autowired
	SysBenefitParamAlgebraMapper sysBenefitParamAlgebraMapper;

	/**
	 * 查询系统参数列表
	 */
	@Override
	public List<Map<String, Object>> getSysBenefitParamAlgebraList(Map<String, Object> params) {
		return sysBenefitParamAlgebraMapper.getSysBenefitParamAlgebraList(params);
	}


	/**
	 * 导出系统参数
	 */
	@Override
	public List<SysBenefitParamAlgebra> selectSysBenefitParamAlgebraList(Map<String, Object> params) {
		return sysBenefitParamAlgebraMapper.selectSysBenefitParamAlgebraList(params);
	}


	/**
	 * 根据参数id查询参数详情
	 */
	@Override
	public Map<String, Object> getSysBenefitParamAlgebraById(String id) {
		return sysBenefitParamAlgebraMapper.getSysBenefitParamAlgebraById(id);
	}


	/**
	 * 更新参数
	 */
	@Override
	@Transactional
	public R editSave(Map<String, Object> map) {
		try {
			int i=0;
			//（2）更新参数信息
			map.put("update_by", ShiroUtils.getSysUser().getLoginName());
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			i = sysBenefitParamAlgebraMapper.updateSysBenefitParamAlgebra(map);
			if(i != 1) {
				return R.error(Type.WARN, "分享收益参数更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "系统参数更新异常");
		}
	}

}

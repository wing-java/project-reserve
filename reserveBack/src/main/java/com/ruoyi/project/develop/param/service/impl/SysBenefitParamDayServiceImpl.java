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
import com.ruoyi.project.develop.param.domain.SysBenefitParamDay;
import com.ruoyi.project.develop.param.mapper.SysBenefitParamDayMapper;
import com.ruoyi.project.develop.param.service.SysBenefitParamDayService;

@Service
public class SysBenefitParamDayServiceImpl implements SysBenefitParamDayService {
	
	@Autowired
	SysBenefitParamDayMapper sysBenefitParamDayMapper;

	/**
	 * 查询
	 */
	@Override
	public List<Map<String, Object>> getSysBenefitParamDayList(Map<String, Object> params) {
		return sysBenefitParamDayMapper.getSysBenefitParamDayList(params);
	}


	/**
	 * 导出
	 */
	@Override
	public List<SysBenefitParamDay> selectSysBenefitParamDayList(Map<String, Object> params) {
		return sysBenefitParamDayMapper.selectSysBenefitParamDayList(params);
	}


	/**
	 * 详情
	 */
	@Override
	public Map<String, Object> getSysBenefitParamDayById(String id) {
		return sysBenefitParamDayMapper.getSysBenefitParamDayById(id);
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
			i = sysBenefitParamDayMapper.updateSysBenefitParamDay(map);
			if(i != 1) {
				return R.error(Type.WARN, "阶段收益参数更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "阶段收益更新异常");
		}
	}

}

package com.ruoyi.project.develop.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.param.domain.SysEdit;
import com.ruoyi.project.develop.param.mapper.SysEditMapper;
import com.ruoyi.project.develop.param.service.SysEditService;


/**
 * 参数修改记录管理
 * @author Administrator
 *
 */
@Service
public class SysEditServiceImpl implements SysEditService {
	
	@Autowired
	private SysEditMapper sysEditMapper;

	
	/**
	 * 查询系统参数列表
	 */
	@Override
	public List<Map<String, Object>> getSysEditList(Map<String, Object> params) {
		return sysEditMapper.getSysEditList(params);
	}


	/**
	 * 导出系统参数
	 */
	@Override
	public List<SysEdit> selectSysEditList(Map<String, Object> params) {
		return sysEditMapper.selectSysEditList(params);
	}


	/**
	 * 根据id查询修改参数详情
	 */
	@Override
	public Map<String, Object> getSysEditById(String id) {
		return sysEditMapper.getSysEditById(id);
	}
	
}

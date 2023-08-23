package com.ruoyi.project.develop.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.param.domain.SysCompany;
import com.ruoyi.project.develop.param.mapper.SysCompanyMapper;
import com.ruoyi.project.develop.param.service.SysCompanyService;


/**
 * 公司简介管理
 * @author Administrator
 *
 */
@Service
public class SysCompanyServiceImpl implements SysCompanyService {
	
	@Autowired
	private SysCompanyMapper sysCompanyMapper;


	/**
	 * 查询公司简介列表
	 */
	@Override
	public List<Map<String, Object>> getSysCompanyList(Map<String, Object> params) {
		return sysCompanyMapper.getSysCompanyList(params);
	}
	/**
	 * 导出公司简介
	 */
	@Override
	public List<SysCompany> selectSysCompanyList(Map<String, Object> params) {
		return sysCompanyMapper.selectSysCompanyList(params);
	}
	/**
	 * 根据参数id查询版本详情
	 */
	@Override
	public Map<String, Object> getSysCompanyById(String id) {
		return sysCompanyMapper.getSysCompanyById(id);
	}
	
	
	/**
	 * 更新版本
	 */
	@Override
	@Transactional
	public R editSysCompany(Map<String, Object> map) {
		try {
			int i=0;
			map.put("update_by", ShiroUtils.getSysUser().getLoginName());
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			i = sysCompanyMapper.updateSysCompany(map);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}
}
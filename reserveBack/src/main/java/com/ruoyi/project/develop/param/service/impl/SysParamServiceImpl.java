package com.ruoyi.project.develop.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Redis;
import com.ruoyi.framework.aspectj.lang.annotation.Redis.CacheOperation;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.param.domain.SysParam;
import com.ruoyi.project.develop.param.mapper.SysEditMapper;
import com.ruoyi.project.develop.param.mapper.SysParamMapper;
import com.ruoyi.project.develop.param.service.SysParamService;
import com.ruoyi.project.develop.user.mapper.UserInfoMapper;


/**
 * 参数管理
 * @author Administrator
 *
 */
@Service
public class SysParamServiceImpl implements SysParamService {
	

	@Autowired
	private RedisUtils redisUtils;
	
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private SysParamMapper sysParamMapper;
	@Autowired
	private SysEditMapper sysEditMapper;
	

	
	/**
	 * 根据参数代码查询参数值
	 */
	@Redis(keyPrefix=RedisNameConstants.sys_param,
			fieldKey="#code", cacheOperation=CacheOperation.QUERY)
	@Override
	public String getParamByCode(String code) {
		return sysParamMapper.getParamByCode(code);
	}


	/**
	 * 查询系统参数列表
	 */
	@Override
	public List<Map<String, Object>> getSysParamList(Map<String, Object> params) {
		return sysParamMapper.getSysParamList(params);
	}


	/**
	 * 导出系统参数
	 */
	@Override
	public List<SysParam> selectSysParamList(Map<String, Object> params) {
		return sysParamMapper.selectSysParamList(params);
	}


	/**
	 * 根据参数id查询参数详情
	 */
	@Override
	public Map<String, Object> getSysParamById(String id) {
		return sysParamMapper.getSysParamById(id);
	}


	/**
	 * 更新参数
	 */
	@Redis(keyPrefix=RedisNameConstants.sys_param,
			fieldKey="#map['code']", cacheOperation=CacheOperation.UPDATE)
	@Override
	@Transactional
	public R editSysParam(Map<String, Object> map) {
		try {
			if(!TypeStatusConstant.sys_oper_flag_modify_email_account.equals(StringUtil.getMapValue(map, "sys_oper_flag"))) {
				if(!ShiroUtils.getSysUser().isAuth()) {
					return R.error(Type.WARN, "身份信息未认证，不能操作");
				}
			}
			/*if(StringUtils.isEmpty(StringUtil.replaceBlank(StringUtil.getMapValue(map, "remark")))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}*/
			int i=0;
			//（1）查询old参数信息
			Map<String, Object> oldValue = sysParamMapper.getParamMapByCode(map.get("code").toString());
			//（2）更新参数信息
			map.put("update_by", ShiroUtils.getSysUser().getLoginName());
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			i = sysParamMapper.updateSysParam(map);
			if(i != 1) {
				return R.error(Type.WARN, "系统参数更新失败");
			}
			//（3）查询new参数信息
			Map<String, Object> newValue = sysParamMapper.getParamMapByCode(map.get("code").toString());
			//（4）记录修改记录
			map.put("table_name", SysTableNameConstant.t_sys_param);
			map.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			map.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = sysEditMapper.addSysEdit(map);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "系统参数修改记录记录失败");
			}
			redisUtils.remove(RedisNameConstants.sys_param+map.get("code").toString());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "系统参数更新异常");
		}
	}
}

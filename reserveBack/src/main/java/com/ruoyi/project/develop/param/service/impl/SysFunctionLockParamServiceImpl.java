package com.ruoyi.project.develop.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Redis;
import com.ruoyi.framework.aspectj.lang.annotation.Redis.CacheOperation;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.param.domain.SysFunctionLockParam;
import com.ruoyi.project.develop.param.mapper.SysEditMapper;
import com.ruoyi.project.develop.param.mapper.SysFunctionLockParamMapper;
import com.ruoyi.project.develop.param.service.SysFunctionLockParamService;


/**
 * 系统开关参数管理
 * @author Administrator
 *
 */
@Service
public class SysFunctionLockParamServiceImpl implements SysFunctionLockParamService {
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private SysFunctionLockParamMapper sysFunctionLockParamMapper;
	@Autowired
	private SysEditMapper sysEditMapper;
	

	
	/**
	 * 根据参数代码查询系统开关参数值
	 */
	@Redis(keyPrefix=RedisNameConstants.sys_function_lock_param,
			fieldKey="#code", cacheOperation=CacheOperation.QUERY)
	@Override
	public String getFunctionLockParamByCode(String code) {
		return sysFunctionLockParamMapper.getFunctionLockParamByCode(code);
	}


	/**
	 * 查询系统开关参数列表
	 */
	@Override
	public List<Map<String, Object>> getSysFunctionLockParamList(Map<String, Object> params) {
		return sysFunctionLockParamMapper.getSysFunctionLockParamList(params);
	}


	/**
	 * 导出系统开关参数
	 */
	@Override
	public List<SysFunctionLockParam> selectSysFunctionLockParamList(Map<String, Object> params) {
		return sysFunctionLockParamMapper.selectSysFunctionLockParamList(params);
	}


	/**
	 * 根据参数id查询系统开关参数详情
	 */
	@Override
	public Map<String, Object> getSysFunctionLockParamById(String id) {
		return sysFunctionLockParamMapper.getSysFunctionLockParamById(id);
	}


	/**
	 * 更新系统开关参数
	 */
	@Override
	@Transactional
	public R editSysFunctionLockParam(Map<String, Object> map) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			/*if(StringUtils.isEmpty(StringUtil.replaceBlank(StringUtil.getMapValue(map, "remark")))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}*/
			int i=0;
			//（1）查询old参数信息
			Map<String, Object> oldValue = sysFunctionLockParamMapper.getFunctionLockParamMapByCode(map.get("code").toString());
			//（2）更新参数信息
			map.put("update_by", ShiroUtils.getSysUser().getLoginName());
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			i = sysFunctionLockParamMapper.updateSysFunctionLockParam(map);
			if(i != 1) {
				return R.error(Type.WARN, "系统参数更新失败");
			}
			//（3）查询new参数信息
			Map<String, Object> newValue = sysFunctionLockParamMapper.getFunctionLockParamMapByCode(map.get("code").toString());
			//（4）记录修改记录
			map.put("table_name", SysTableNameConstant.t_sys_function_lock_param);
			map.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			map.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = sysEditMapper.addSysEdit(map);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "系统参数修改记录记录失败");
			}
			//（5）删除缓存
			redisUtils.remove(RedisNameConstants.sys_function_lock_param+map.get("code").toString());
			redisUtils.remove(RedisNameConstants.sys_function_lock_param_list);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "系统参数更新异常");
		}
	}
}

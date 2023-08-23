package com.example.longecological.service.common.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.common.SysFunctionLockParamMapper;
import com.example.longecological.service.common.SysFunctionLockParamService;
import com.example.longecological.utils.ExceptionUtil;



/**
 * 系统开关参数管理
 * @author Administrator
 *
 */
@Service
public class SysFunctionLockParamServiceImpl implements SysFunctionLockParamService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysParamServiceImpl.class);
	
	@Autowired
	private SysFunctionLockParamMapper sysFunctionLockParamMapper;

	
	/**
	 * 根据参数代码查询参数开关值
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_function_lock_param,
			fieldKey="#code", cacheOperation=CacheOperation.QUERY)
	@Override
	public String getFunctionLockParamByCode(String code) {
		return sysFunctionLockParamMapper.getFunctionLockParamByCode(code);
	}


	/**
	 * 查询系统功能开关参数值列表
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_function_lock_param_list,
			fieldKey="", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getSysFuctionLockParamList(Map<String, Object> map) {
		return sysFunctionLockParamMapper.getSysFuctionLockParamList(map);
	}
	
	
	/**
	 * 根据参数代码查询参数值
	 */
	@Override
	public R getFunctionLockParamByCode(Map<String, Object> map) {
		try{
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, this.getFunctionLockParamByCode(map.get("code").toString()));
		 }catch(Exception e){
			 LOGGER.error("SysFunctionLockParamServiceImpl -- getFunctionLockParamByCode方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			 return R.error(CommonCodeConstant.COMMON_CODE_999991, CommonCodeConstant.COMMON_MSG_999991);
		 }
	}

}

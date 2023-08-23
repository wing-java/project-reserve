package com.example.longecological.service.common.impl;

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
import com.example.longecological.mapper.common.SysParamMapper;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.utils.ExceptionUtil;



/**
 * 参数管理
 * @author Administrator
 *
 */
@Service
public class SysParamServiceImpl implements SysParamService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysParamServiceImpl.class);
	
	@Autowired
	private SysParamMapper sysParamMapper;

	
	/**
	 * 根据参数代码查询参数值
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_param,
			fieldKey="#code", cacheOperation=CacheOperation.QUERY)
	@Override
	public String getParamByCode(String code) {
		return sysParamMapper.getParamByCode(code);
	}


	/**
	 * 根据参数代码查询参数值
	 */
	@Override
	public R getParamByCode(Map<String, Object> map) {
		try{
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, this.getParamByCode(map.get("code").toString()));
		 }catch(Exception e){
			 LOGGER.error("SysParamServiceImpl -- getParamByCode方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			 return R.error(CommonCodeConstant.COMMON_CODE_999991, CommonCodeConstant.COMMON_MSG_999991);
		 }
	}

}

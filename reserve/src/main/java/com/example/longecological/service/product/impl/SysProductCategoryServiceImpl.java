package com.example.longecological.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.SysParamCodeConstants;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.product.SysProductCategoryMapper;
import com.example.longecological.service.product.SysProductCategoryService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.StringUtil;

@Service
public class SysProductCategoryServiceImpl implements SysProductCategoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysProductCategoryServiceImpl.class);
	
	@Autowired
	SysProductCategoryMapper sysProductCategoryMapper;

	@Override
	public R getCategoryList(Map<String, Object> map) {
		try {
			//校验用户是否登录
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			List<Map<String, Object>> sysCategoryList = sysProductCategoryMapper.getCategoryList();
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("sysCategoryList", sysCategoryList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("SysProductCategoryServiceImpl -- getCategoryList方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

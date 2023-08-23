package com.example.longecological.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.product.SysProductMapper;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.product.SysProductCacheService;
import com.example.longecological.service.product.SysProductService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.StringUtil;

@Service
public class SysProductServiceImpl implements SysProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysProductServiceImpl.class);
	
	@Autowired
	SysProductMapper sysProductMapper;
	@Autowired
	SysProductCacheService sysProductCacheService;
	@Autowired
	UserInfoMapper userInfoMapper;

	/**
	 * 查询平台产品列表
	 */
	@Override
	public R getSysProductList(Map<String, Object> map) {
		try {
			map.put("lastIdKey", "".equals(StringUtil.getMapValue(map, "last_id")) ? "0" : StringUtil.getMapValue(map, "last_id"));
			List<Map<String, Object>> sysProductList = sysProductCacheService.getSysProductList(map);
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("sysProductList", sysProductList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("SysProductServiceImpl -- sysProductList方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询平台产品详情
	 */
	@Override
	public R getSysProductDetail(Map<String, Object> map) {
		try {
			//校验用户是否登录
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			Map<String, Object> sysProduct = sysProductCacheService.getSysProductById(map);
			//判断商品是否需要排名
			if("2".equals(StringUtil.getMapValue(sysProduct, "grade"))) {
				//查询用户信息
				Map<String, Object> userMap = userInfoMapper.getRealUserInfoById(StringUtil.getMapValue(map, "sys_user_id"));
				if(Integer.parseInt(StringUtil.getMapValue(userMap, "grade"))<Integer.parseInt(StringUtil.getMapValue(sysProduct, "grade"))) {
					sysProduct.put("need_rank", "1");
				}else {
					sysProduct.put("need_rank", "0");
				}
			}else {
				sysProduct.put("need_rank", "0");
			}
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("sysProduct", sysProduct);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("SysProductServiceImpl -- getSysProductDetail方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

package com.ruoyi.project.develop.common.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;
import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.utils.exception.ExceptionUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.common.service.QiNiuService;

@Service
public class QiNiuServiceImpl implements QiNiuService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QiNiuServiceImpl.class);
	
	@Autowired
	private RedisUtils redisUtils;

	
	/**
	 * 获取七牛云token值
	 */
	@Override
	public R getQiNiuToken(Map<String, Object> map) {
		 try{
			Map<String, Object> param = new HashMap<>();
			map.put("token", "token");
			param.put("qiniu_token", createToken(map));
			param.put("qiniu_domain", SysParamConstant.qiniu_domain);
			return R.ok("操作成功", param);
		 }catch(Exception e){
			 LOGGER.error("QiNiuServiceImpl -- getQiNiuToken方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			 return R.error(Type.WARN, "操作异常");
		 }
	}
	
	
	/**
	 * 创建七牛云token值
	 * @param map
	 * @return
	 */
	private String createToken(Map<String, Object> map){
		if(redisUtils.exists(RedisNameConstants.qiniu_token)){
			return redisUtils.get(RedisNameConstants.qiniu_token).toString();
		}
		//生成秘钥
		Auth auth = Auth.create(SysParamConstant.qiniu_accessKey, SysParamConstant.qiniu_secretKey);
		String token = auth.uploadToken(SysParamConstant.qiniu_bucket);
		redisUtils.set(RedisNameConstants.qiniu_token, token, SysParamConstant.verification_code_seconds);
		return token;
	}



	/**
	 * 生成七牛云Token、域名、文件路径，并且校验文件后缀名
	 */
	@Override
	public R getQiNiuTokenAndUrl(Map<String, Object> map) {
		 try{
			//（1）图片格式校验
			String suffix = StringUtil.getMapValue(map, "suffix");
			boolean flag = false;
            String[] imgTypes = new String[]{"jpg","jpeg","bmp","gif","png"};
            for(String fileSuffix : imgTypes) {
                if(suffix.substring(suffix.lastIndexOf(".") + 1).equalsIgnoreCase(fileSuffix)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
            	return R.error(Type.WARN, "文件类型异常");
            }
			//（2）获取七牛token
			map.put("token", "token");
			Map<String, Object> respondMap = new HashMap<>();
			//七牛token
			respondMap.put("qiniu_token", createToken(map));
			//存入外链默认域名，用于拼接完整的资源外链路径
			respondMap.put("qiniu_domain", SysParamConstant.qiniu_domain);
			//生成实际路径名
			String randomFileName = UUID.randomUUID().toString() + suffix;
			respondMap.put("qiniu_img_url", randomFileName);
			return R.ok("操作成功", respondMap);
		 }catch(Exception e){
			 LOGGER.error("QiNiuServiceImpl -- getQiNiuToken方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			 return R.error(Type.WARN, "操作异常");
		 }
	}

	
}

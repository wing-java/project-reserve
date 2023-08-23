package com.example.longecological.service.common.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.config.properties.QiniuProperties;
import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.QiNiuService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.string.StringUtil;
import com.qiniu.util.Auth;

@Service
public class QiNiuServiceImpl implements QiNiuService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QiNiuServiceImpl.class);
	
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	QiniuProperties qiniuProperties;

	
	/**
	 * 获取七牛云token值=====》供网页端（和APP端）本地使用（区别在于web路径加密）
	 */
	@Override
	public R getQiNiuToken(Map<String, Object> map) {
		 try{
			Map<String, Object> param = new HashMap<>();
			map.put("token", "token");
			param.put("qiniu_token", createToken(map));
			param.put("qiniu_domain", qiniuProperties.getQiniu_domain());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, param);
		 }catch(Exception e){
			 LOGGER.error("QiNiuServiceImpl -- getQiNiuToken方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			 return R.error(CommonCodeConstant.COMMON_CODE_999991, CommonCodeConstant.COMMON_MSG_999991);
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
		Auth auth = Auth.create(qiniuProperties.getQiniu_accessKey(),qiniuProperties.getQiniu_secretKey());
		String token = auth.uploadToken(qiniuProperties.getQiniu_bucket());
		redisUtils.set(RedisNameConstants.qiniu_token, token, SysParamConstant.verification_code_seconds);
		return token;
	}


	/**
	 * 后台获取app七牛token=======》供管理后台调用
	 */
	@Override
	public R getAppQiNiuToken(Map<String, Object> map) {
		try{
			//签名验证
			Map<String, Object> param = new HashMap<>();
			map.put("token", "token");
			param.put("qiniu_token", createToken(map));
			param.put("qiniu_domain", qiniuProperties.getQiniu_domain());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, param);
		 }catch(Exception e){
			 LOGGER.error("QiNiuServiceImpl -- getQiNiuToken方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			 return R.error(CommonCodeConstant.COMMON_CODE_999991, CommonCodeConstant.COMMON_MSG_999991);
		 }
	}


	/**
	 * 生成七牛云Token、域名、文件路径，并且校验文件后缀名=======》供网页端（和APP端）本地使用（区别在于web路径加密）
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
            	return R.error(CommonCodeConstant.COMMON_CODE_999969, CommonCodeConstant.COMMON_MSG_999969);
            }
			//（2）获取七牛token
			map.put("token", "token");
			Map<String, Object> respondMap = new HashMap<>();
			//七牛token
			respondMap.put("qiniu_token", createToken(map));
			//存入外链默认域名，用于拼接完整的资源外链路径
			respondMap.put("qiniu_domain",qiniuProperties.getQiniu_domain());
			//生成实际路径名
			String randomFileName = UUID.randomUUID().toString() + suffix;
			respondMap.put("qiniu_img_url", randomFileName);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, respondMap);
		 }catch(Exception e){
			 LOGGER.error("QiNiuServiceImpl -- getQiNiuToken方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			 return R.error(CommonCodeConstant.COMMON_CODE_999991, CommonCodeConstant.COMMON_MSG_999991);
		 }
	}

	
}

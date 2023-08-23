package com.ruoyi.project.develop.common.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.MsgTemplateConstants;
import com.ruoyi.common.constant.VerifyConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.common.service.ImgCodeService;
import com.ruoyi.project.develop.common.service.SendSmsCodeService;
import com.ruoyi.project.develop.common.service.VerifyRecordService;


/**
 * 发送短信验证码
 * @author Administrator
 *
 */
@Service
public class SendSmsCodeServiceImpl implements SendSmsCodeService {
	
	@Autowired
	ImgCodeService imgCodeService;
	@Autowired
	VerifyRecordService verifyRecordService;
	
	@Autowired
	RedisUtils redisUtils;
	

	/**
	 * 发送短信验证码=======>后台shiro处理图形验证码手段
	 */
	@Override
	public R sendSmsCode(Map<String, Object> map) {
		//校验图形验证码
		R checkResult = imgCodeService.checkImgCodeBack(map);
		if(!R.Type.SUCCESS.value.equals(checkResult.get("code").toString())) {
			return checkResult;
		}
		//业务类型
		if(StringUtils.isEmpty(StringUtil.getMapValue(map, "bus_type"))) {
			return R.error(Type.WARN, "业务类型不能为空");
		}
		//发送短信验证码
		return verifyRecordService.transmit(ShiroUtils.getSysUser().getUserId().toString(),map.get("bus_type").toString(), 
				VerifyConstant.MobileAccType, ShiroUtils.getSysUser().getPhonenumber(), VerifyConstant.SystemBack,MsgTemplateConstants.COMMON_TIP);
	}
}

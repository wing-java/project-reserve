package com.example.longecological.service.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.SendEmailService;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.utils.email2.SendEmailUtil2;

@Service
public class SendEmailServiceImpl implements SendEmailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailServiceImpl.class);
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private SysParamService sysParamService;

/*	@Override
	public R sendEmail(String account, String code) {
//		Map<String, Object> sendEmailAccount = null;
		boolean status = true;
		for(int i=0; i<3; i++){
			//获取计数器
//			long num = redisUtils.incr(RedisNameConstants.t_sys_email_number, 86400);
			//获取邮箱队列数
//			int size = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_sysEmailAccountNum));
			//获取当前账号编号
//			int order = (int)(num%size);
			//获取账号
//			sendEmailAccount = sysParamService.getSendEmailAccount(String.valueOf(order));
//			if(sendEmailAccount == null){
//				LOGGER.info("发送失败--邮箱账号为空");
//				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999896,MsgImgCodeConstant.MESSAGE_MSG_999896);
//			}
			//处理发送操作
//			LOGGER.info("发送账号：" + StringUtil.getMapValue(sendEmailAccount, "account") + " 发送密码：" + StringUtil.getMapValue(sendEmailAccount, "password") + " 接收账号：" + account + "  接收验证码:" +code );
//			status = SendEmailUtil.sendEmail(account, code, StringUtil.getMapValue(sendEmailAccount, "account"), StringUtil.getMapValue(sendEmailAccount, "password"));
			status = SendEmailUtil.sendEmail(account, code, "jojost@service.proecosystem.com", "proecosyA8");
			if(status){
				break;
			}
		}
		if(status){
			LOGGER.info("发送成功--接收账号：" + account + "  接收验证码:" +code );
//			return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999897,MsgImgCodeConstant.MESSAGE_CODE_999897, StringUtil.getMapValue(sendEmailAccount, "account"));
			return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999897,MsgImgCodeConstant.MESSAGE_CODE_999897, "jojost@service.proecosystem.com");
		}else{
			LOGGER.info("发送失败--接收账号：" + account + "  接收验证码:" +code );
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999896,MsgImgCodeConstant.MESSAGE_MSG_999896);
		}
	}*/
	
	@Override
	public R sendEmail(String account, String code){
		boolean status = SendEmailUtil2.send(account, code);
		if(status){
			LOGGER.info("发送成功--接收账号：" + account + "  接收验证码:" +code );
			return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999897,MsgImgCodeConstant.MESSAGE_CODE_999897, "email@yks358.com");
		}else{
			LOGGER.info("发送失败--接收账号：" + account + "  接收验证码:" +code );
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999896,MsgImgCodeConstant.MESSAGE_MSG_999896);
		}
	}
	
}

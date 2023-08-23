package com.ruoyi.project.develop.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.service.SendSmsCodeService;


/**
 * 短信验证码
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/develop/smsCode")
public class SmsCodeController extends BaseController
{
	
	
	@Autowired
	private SendSmsCodeService sendSmsCodeService;

	
	/**
	 * 发送短信验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendSmsCode")
	public R sendSmsCode(@RequestParam Map<String, Object> map){
		return sendSmsCodeService.sendSmsCode(map);
	}
    
}

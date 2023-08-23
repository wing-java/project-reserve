package com.example.longecological.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/qrcode")
public class QrcodeController {

	
	/**
	 * 跳转到注册界面
	 * @return
	 */
	@RequestMapping("/toRegister")
	public String toRegister(@RequestParam Map<String, Object> map, Model model) {
		model.addAttribute("map",map);
		return "user/login/register";
	}
	
	
	/**
	 * 跳转到注册界面
	 * @return
	 */
	@RequestMapping("/toRegisterFirst")
	public String toRegisterFirst(@RequestParam Map<String, Object> map, Model model) {
		model.addAttribute("map",map);
		return "user/login/register-first";
	}
	
}

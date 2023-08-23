package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysBonusService;

/**
 * 分红制度
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/system/bonus")
public class SysBonusController {
	
	@Autowired
	SysBonusService sysBonusService;

	/**
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysBonusList")
	public R getSysBonusList(@RequestBody Map<String, Object> map) {
		return sysBonusService.getSysBonusList(map);
	}
	/**
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysBonusDetail")
	public R getSysBonusDetail(@RequestBody Map<String, Object> map) {
		return sysBonusService.getSysBonusDetail(map);
	}
}

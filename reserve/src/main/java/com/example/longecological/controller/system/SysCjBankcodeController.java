package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysCjBankcodeService;

/**
 * 川军
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/cj/bankcode")
public class SysCjBankcodeController {
	
	@Autowired
	SysCjBankcodeService sysCjBankcodeService;

	/**
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCjBankcodeList")
	public R getCjBankcodeList(@RequestBody Map<String, Object> map) {
		return sysCjBankcodeService.getCjBankcodeList(map);
	}
}

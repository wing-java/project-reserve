package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysCompanyService;


/**
 * 系统账户相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/system/company")
public class SysCompanyController {
	
	@Autowired
	private SysCompanyService sysCompanyService;

	
	/**
	 * 查询公司信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysCompanyInfo")
	public R getSysCompanyInfo(@RequestBody Map<String, Object> map) {
		return sysCompanyService.getSysCompanyInfo(map);
	}
	
}

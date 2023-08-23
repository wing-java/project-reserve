package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysVersionService;


/**
 * 系统版本相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/version")
public class SysVersionController {
	
	@Autowired
	private SysVersionService sysVersionService;

	
	/**
	 * 查询系统最新版本
	 * @param map
	 * @return
	 */
	@RequestMapping("/getNewVersion")
	@ResponseBody
	public R getNewVersion(@RequestBody Map<String, Object> map){
		return sysVersionService.getNewVersion(map);
	}
	
	
}

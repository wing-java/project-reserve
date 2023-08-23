package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysAppImgService;


/**
 * app图片相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/system/appImg")
public class SysAppImgController {
	
	@Autowired
	private SysAppImgService sysAppImgService;

	
	/**
	 * 查询app图片列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAppImgList")
	public R getAppImgList(@RequestBody Map<String, Object> map) {
		return sysAppImgService.getAppImgList(map);
	}
	
}

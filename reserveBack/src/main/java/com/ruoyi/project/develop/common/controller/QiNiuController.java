package com.ruoyi.project.develop.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.service.QiNiuService;

/**
 * 七牛controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/develop/qiniu")
public class QiNiuController {
	
	@Autowired
	private QiNiuService qiNiuService;

	
	/**
	 * 生成七牛云Token
	 * @param map
	 * @return
	 */
	@RequestMapping("/getQiNiuToken")
	@ResponseBody
	public R getQiNiuToken(@RequestBody Map<String, Object> map){
		return qiNiuService.getQiNiuToken(map);
	}
	
	
	/**
	 * 生成七牛云Token、域名、文件路径，并且校验文件后缀名（主要用于前端自己上传处理文件）
	 * @param map
	 * @return
	 */
	@RequestMapping("/getQiNiuTokenAndUrl")
	@ResponseBody
	public R getQiNiuTokenAndUrl(@RequestBody Map<String, Object> map){
		return qiNiuService.getQiNiuTokenAndUrl(map);
	}
}

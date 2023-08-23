package com.example.longecological.controller.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.common.QiNiuService;

/**
 * 七牛controller
 * @author Administrator
 *
 */
@Controller
public class QiNiuController {
	
	@Autowired
	private QiNiuService qiNiuService;

	
	/**
	 * 生成七牛云Token=======>供网页端（和APP端）本地使用（区别在于web路径加密）
	 * @param map
	 * @return
	 */
	@RequestMapping("/api/common/qiniu/getQiNiuToken")
	@ResponseBody
	public R getQiNiuToken(@RequestBody Map<String, Object> map){
		return qiNiuService.getQiNiuToken(map);
	}
	
	
	/**
	 * 生成七牛云Token=======>供管理后台调用
	 * @param map
	 * @return
	 */
	@RequestMapping("/manage/common/qiniu/getAppQiNiuToken")
	@ResponseBody
	public R getAppQiNiuToken(@RequestBody Map<String, Object> map){
		return qiNiuService.getAppQiNiuToken(map);
	}
	
	
	
	/**
	 * 生成七牛云Token、域名、文件路径，并且校验文件后缀名=======》供网页端（和APP端）本地使用（区别在于web路径加密）
	 * @param map
	 * @return
	 */
	@RequestMapping("/api/common/qiniu/getQiNiuTokenAndUrl")
	@ResponseBody
	public R getQiNiuTokenAndUrl(@RequestBody Map<String, Object> map){
		return qiNiuService.getQiNiuTokenAndUrl(map);
	}
}

package com.example.longecological.controller.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.common.ImgCodeService;


/**
 * 图形验证码相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/common/imgCode")
public class ImgCodeController {
	
	@Autowired
	private ImgCodeService imgCodeService;

	
	/**
	 * 生成图像验证码
	 * @param map
	 * @return
	 */
	@ResponseBody
	@PostMapping("/createImgCode")
	public R createImgCode(@RequestBody Map<String, Object> map) {
		return imgCodeService.createImgCode(map);
	}
}

package com.example.longecological.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.longecological.entity.R;
import com.example.longecological.service.common.FileDealService;


/**
 * 文件处理相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/common/fileDeal")
public class FileDealController {
	
	@Autowired
	private FileDealService fileDealService;

	
	/**
	 * 本地单文件上传
	 * @param file
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/localPhotopUpload")
	public R uploadLocalFile(@RequestParam("file") MultipartFile file){
		return fileDealService.localPhotopUpload(file);
	}
	
	
	/**
	 * 本地多文件上传
	 * @param file
	 * @return
	 */
	@PostMapping("/localMulPhotopUpload")
    @ResponseBody
	public R localMulPhotopUpload(@RequestParam("files") MultipartFile[] files) {
		return fileDealService.localMulPhotopUpload(files);
	}
	
	
	/**
	 * 七牛云单文件上传
	 * @param file
	 * @return
	 */
	@PostMapping("/qiniuPhotopUpload")
    @ResponseBody
	public R qiniuPhotopUpload(@RequestParam("file") MultipartFile file) {
		return fileDealService.qiniuPhotopUpload(file);
	}
	
	
	/**
	 * 七牛云多文件上传
	 * @param file
	 * @return
	 */
	@PostMapping("/qiniuMulPhotopUpload")
    @ResponseBody
	public R qiniuMulPhotopUpload(@RequestParam("files") MultipartFile[] files) {
		return fileDealService.qiniuMulPhotopUpload(files);
	}
	
}

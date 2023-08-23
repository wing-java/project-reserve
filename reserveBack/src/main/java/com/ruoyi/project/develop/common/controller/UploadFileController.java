package com.ruoyi.project.develop.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.develop.common.service.UploadFileService;


/**
 * 文件上传
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/develop/upload")
public class UploadFileController extends BaseController
{
	
	
	@Autowired
	private UploadFileService uploadFileService;

	
	
	/**
	 * 七牛云summernote编辑器文件上传==========》借助获取了APP的token处理
	 * @param file
	 * @return
	 */
	@PostMapping("/qiniuSummernoteUploadApp")
    @ResponseBody
	public AjaxResult qiniuSummernoteUploadApp(@RequestParam("file") MultipartFile file) {
		return uploadFileService.qiniuSummernoteUploadApp(file);
	}
	/**
	 * 七牛云summernote编辑器文件上传==========》自己获取token
	 * @param file
	 * @return
	 */
	@PostMapping("/qiniuSummernoteUpload")
    @ResponseBody
	public AjaxResult qiniuSummernoteUpload(@RequestParam("file") MultipartFile file) {
		return uploadFileService.qiniuSummernoteUpload(file);
	}
	
	
	
	/**
	 * 七牛云文件上传==========》借助获取了APP的token处理
	 * @param file
	 * @return
	 */
	@PostMapping("/qiniuPhotopUploadApp")
    @ResponseBody
	public AjaxResult qiniuPhotopUploadApp(@RequestParam("file") MultipartFile file) {
		return uploadFileService.qiniuPhotopUploadApp(file);
	}
	/**
	 * 七牛云文件上传==========》自己获取token
	 * @param file
	 * @return
	 */
	@PostMapping("/qiniuPhotopUpload")
    @ResponseBody
	public AjaxResult qiniuPhotopUpload(@RequestParam("file") MultipartFile file) {
		return uploadFileService.qiniuPhotopUpload(file);
	}
	
	
    
	
	/**
	 * 本地文件上传
	 * @param file
	 * @return
	 */
	@PostMapping("/localPhotopUpload")
    @ResponseBody
	public AjaxResult localPhotopUpload(@RequestParam("file") MultipartFile file) {
		return uploadFileService.localPhotopUpload(file);
	}
	
	
	/**
	 * 删除文件：暂未做处理
	 * @return
	 */
	@PostMapping("/delUploadPic")
    @ResponseBody
	public AjaxResult delUploadPic() {
		return AjaxResult.success();
	}
    
}

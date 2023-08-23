package com.ruoyi.project.develop.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 文件上传
 * @author Administrator
 *
 */
public interface UploadFileService {


	/**
	 * summernote编辑器文件上传==========》借助获取了APP的token处理
	 * @param file
	 * @return
	 */
	AjaxResult qiniuSummernoteUploadApp(MultipartFile file);
	/**
	 * 七牛云summernote编辑器文件上传==========》自己获取token
	 * @param file
	 * @return
	 */
	AjaxResult qiniuSummernoteUpload(MultipartFile file);
	

	
	/**
	 * 七牛云文件上传==========》借助获取了APP的token处理
	 * @param file
	 * @return
	 */
	AjaxResult qiniuPhotopUploadApp(MultipartFile file);
	/**
	 * 七牛云文件上传==========》自己获取token
	 * @param file
	 * @return
	 */
	AjaxResult qiniuPhotopUpload(MultipartFile file);


	/**
	 * 本地文件传
	 * @param file
	 * @param directory 
	 * @return
	 */
	AjaxResult localPhotopUpload(MultipartFile file);
	
	
	

	

	
	
}

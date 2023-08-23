package com.example.longecological.service.common;

import org.springframework.web.multipart.MultipartFile;

import com.example.longecological.entity.R;


/**
 * 文件处理
 * @author Administrator
 *
 */
public interface FileDealService {

	
	/**
	 * 本地文件上传
	 * @param file
	 * @param directory 
	 * @param map
	 * @return
	 */
	R localPhotopUpload(MultipartFile file);
	
	
	/**
	 * 本地多文件上传
	 * @param files
	 * @param directory 
	 * @return
	 */
	R localMulPhotopUpload(MultipartFile[] files);

	
	/**
	 * 七牛云文件长传
	 * @param file
	 * @return
	 */
	R qiniuPhotopUpload(MultipartFile file);

	
	/**
	 * 七牛云多文件上传
	 * @param files
	 * @return
	 */
	R qiniuMulPhotopUpload(MultipartFile[] files);

	
}

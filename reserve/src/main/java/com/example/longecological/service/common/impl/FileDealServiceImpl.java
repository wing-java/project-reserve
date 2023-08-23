package com.example.longecological.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.longecological.config.RuoYiConfig;
import com.example.longecological.constant.OperParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.FileDealService;
import com.example.longecological.service.common.QiNiuService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.file.CheckFileUtil;
import com.example.longecological.utils.file.FileUploadUtils;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;


/**
 * 文件处理
 * @author Administrator
 *
 */
@Service
public class FileDealServiceImpl implements FileDealService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileDealServiceImpl.class);

	Configuration cfg = new Configuration(); // zong1() 代表华北地区
	UploadManager uploadManager = new UploadManager(cfg);
	
	@Autowired
	private QiNiuService qiNiuService;
	
	
	/**
	 * 本地单文件上传
	 */
	@Override
	public R localPhotopUpload(MultipartFile file) {
		try {
			//（1）文件类型校验
			R checkUploadResult = CheckFileUtil.checkFileIllegal(file, OperParamConstant.UPLOAD_IMG_TYPE);
			if(!Boolean.valueOf(checkUploadResult.get(R.SUCCESS_TAG).toString())) {
				return checkUploadResult;
			}
			//（2）上传文件并得到返回路径
			String result = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), "", file);
			//（3）返回数据
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("url", result);
			return R.ok(CommonCodeConstant.COMMON_CODE_999975,CommonCodeConstant.COMMON_MSG_999975,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("FileDealServiceImpl -- localPhotopUpload方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999976,CommonCodeConstant.COMMON_MSG_999976);
		}
	}
	
	
	/**
	 * 本地多文件上传
	 */
	@Override
	public R localMulPhotopUpload(MultipartFile[] files) {
		try {
			String mul_url ="";
			if(files != null && files.length > 0){
				//（1）循环获取file数组中得文件
				for(int i = 0;i < files.length;i++){
					MultipartFile file = files[i];
					//（1）文件类型校验
					R checkUploadResult = CheckFileUtil.checkFileIllegal(file, OperParamConstant.UPLOAD_IMG_TYPE);
					if(!Boolean.valueOf(checkUploadResult.get(R.SUCCESS_TAG).toString())) {
						return checkUploadResult;
					}
					//（2）上传文件并得到返回路径
					String url = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), "", file);
					mul_url = url+","+mul_url;
			    }
			}
			mul_url = mul_url.substring(0, mul_url.length() - 1);
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("url", mul_url);
			return R.ok(CommonCodeConstant.COMMON_CODE_999975,CommonCodeConstant.COMMON_MSG_999975,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("FileDealServiceImpl -- localMulPhotopUpload方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999976,CommonCodeConstant.COMMON_MSG_999976);
		}
	}
	

	/**
	 * 七牛云单文件上传
	 */
	@Override
	public R qiniuPhotopUpload(MultipartFile file) {
		try {
			//（1）文件类型校验
			R checkUploadResult = CheckFileUtil.checkFileIllegal(file, OperParamConstant.UPLOAD_IMG_TYPE);
			if(!Boolean.valueOf(checkUploadResult.get(R.SUCCESS_TAG).toString())) {
				return checkUploadResult;
			}
			//（2）获取七牛云token
			Map<String, Object> map = new HashMap<String, Object>();
			R qiniuTokenResult = qiNiuService.getQiNiuToken(map);
			if(!Boolean.valueOf(qiniuTokenResult.get(R.SUCCESS_TAG).toString())) {
				return qiniuTokenResult;
			}
			//（3）设置七牛云文件的key值
			String file_name = FileUploadUtils.extractFilename(file, FileUploadUtils.IMAGE_JPG_EXTENSION);//重命名文件名称
			String file_name_key = FileUploadUtils.renameToUUID(file_name);//存储在七牛云上面的图片的key值名称
			//（4）七牛云文件上传
			Response response = uploadManager.put(file.getBytes(), file_name_key,((Map<String, Object>)qiniuTokenResult.get("data")).get("qiniu_token").toString());
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			String url = putRet.key;
			//（5）返回数据
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("fileName", file_name);
			respondMap.put("url", url);
			return R.ok(CommonCodeConstant.COMMON_CODE_999975,CommonCodeConstant.COMMON_MSG_999975,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("FileDealServiceImpl -- qiniuPhotopUpload方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999976,CommonCodeConstant.COMMON_MSG_999976);
		}
	}
	
	
	/**
	 * 七牛云多文件上传
	 */
	@Override
	public R qiniuMulPhotopUpload(MultipartFile[] files) {
		try {
			String mul_url ="";
			if(files != null && files.length > 0){
				//（1）获取七牛云token
				Map<String, Object> map = new HashMap<String, Object>();
				R qiniuTokenResult = qiNiuService.getQiNiuToken(map);
				if(!Boolean.valueOf(qiniuTokenResult.get(R.SUCCESS_TAG).toString())) {
					return qiniuTokenResult;
				}
				//（2）循环获取file数组中得文件
				for(int i = 0;i < files.length;i++){
					MultipartFile file = files[i];
					//（1）文件类型校验
					R checkUploadResult = CheckFileUtil.checkFileIllegal(file, OperParamConstant.UPLOAD_IMG_TYPE);
					if(!Boolean.valueOf(checkUploadResult.get(R.SUCCESS_TAG).toString())) {
						return checkUploadResult;
					}
					//（2）设置七牛云文件的key值
					String file_name = FileUploadUtils.extractFilename(file, FileUploadUtils.IMAGE_JPG_EXTENSION);//重命名文件名称
					String file_name_key = FileUploadUtils.renameToUUID(file_name);//存储在七牛云上面的图片的key值名称
					//（3）七牛云文件上传
					Response response = uploadManager.put(file.getBytes(), file_name_key,((Map<String, Object>)qiniuTokenResult.get("data")).get("qiniu_token").toString());
					DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
					String url = putRet.key;
					mul_url = url+","+mul_url;
			    }
			}
			mul_url = mul_url.substring(0, mul_url.length() - 1);
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("url", mul_url);
			return R.ok(CommonCodeConstant.COMMON_CODE_999975,CommonCodeConstant.COMMON_MSG_999975,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("FileDealServiceImpl -- qiniuMulPhotopUpload方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999976,CommonCodeConstant.COMMON_MSG_999976);
		}
	}

}

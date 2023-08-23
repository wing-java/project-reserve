package com.ruoyi.project.develop.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.service.QiNiuService;
import com.ruoyi.project.develop.common.service.UploadFileService;


/**
 * 文件上传
 * @author Administrator
 *
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {
	
	Configuration cfg = new Configuration(); // zong1() 代表华北地区
	UploadManager uploadManager = new UploadManager(cfg);
	
	@Autowired
	private QiNiuService qiNiuService;
	
	@Autowired
	RedisUtils redisUtils;

	
	/**
	 * summernote编辑器文件上传（返回路径带域名）==========》借助获取了APP的token处理
	 */
	@Override
	public AjaxResult qiniuSummernoteUploadApp(MultipartFile file) {
		try {
			//（1）调取http请求获取七牛云rdeis的token值
			Map<String, Object> map = new HashMap<String, Object>();
			R qiNiuResult = qiNiuService.getQiNiuToken(map);
			if(!R.Type.SUCCESS.value.equals(qiNiuResult.get("code").toString())) {
				return AjaxResult.error(qiNiuResult.get("msg").toString());
            }
			Map<String, Object> qiniuTokenMap = (Map<String, Object>) qiNiuResult.get("data");
			//（2）设置七牛云文件的key值
			String file_name = FileUploadUtils.extractFilename(file);//重命名文件名称
			String file_name_key = FileUploadUtils.renameToUUID(file_name);//存储在七牛云上面的图片的key值名称
			//（3）七牛云文件上传
			Response response = uploadManager.put(file.getBytes(), file_name_key,qiniuTokenMap.get("qiniu_token").toString());
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			String url = SysParamConstant.qiniu_domain + "/" + putRet.key;
			//（4）返回对象处理
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", file_name);
			ajax.put("url", url);//带域名
			return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("文件上传异常");
		}
	}
	/**
	 * summernote编辑器文件上传（返回路径带域名）==========》自己获取token
	 */
	@Override
	public AjaxResult qiniuSummernoteUpload(MultipartFile file) {
		try {
			//（1）获取七牛token
			Map<String, Object> map = new HashMap<String, Object>();
			R qiNiuResult = qiNiuService.getQiNiuToken(map);
			if(!R.Type.SUCCESS.value.equals(qiNiuResult.get("code").toString())) {
				return AjaxResult.error(qiNiuResult.get("msg").toString());
            }
			Map<String, Object> qiniuTokenMap = (Map<String, Object>) qiNiuResult.get("data");
			//（2）设置七牛云文件的key值
			String file_name = FileUploadUtils.extractFilename(file);//重命名文件名称
			String file_name_key = FileUploadUtils.renameToUUID(file_name);//存储在七牛云上面的图片的key值名称
			//（3）七牛云文件上传
			Response response = uploadManager.put(file.getBytes(), file_name_key,qiniuTokenMap.get("qiniu_token").toString());
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			String url = SysParamConstant.qiniu_domain + "/" + putRet.key;
			//（4）返回对象处理
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", file_name);
			ajax.put("url", url);//带域名
			return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("文件上传异常");
		}
	}


	/**
	 * 七牛云文件上传（返回路径不带域名）==========》借助获取了APP的token处理
	 */
	@Override
	public AjaxResult qiniuPhotopUploadApp(MultipartFile file) {
		try {
			//（1）获取七牛token
			Map<String, Object> map = new HashMap<String, Object>();
			R qiNiuResult = qiNiuService.getQiNiuToken(map);
			if(!R.Type.SUCCESS.value.equals(qiNiuResult.get("code").toString())) {
				return AjaxResult.error(qiNiuResult.get("msg").toString());
            }
			Map<String, Object> qiniuTokenMap = (Map<String, Object>) qiNiuResult.get("data");
			//（2）设置七牛云文件的key值
			String file_name = FileUploadUtils.extractFilename(file);//重命名文件名称
			String file_name_key = FileUploadUtils.renameToUUID(file_name);//存储在七牛云上面的图片的key值名称
			//（3）七牛云文件上传
			Response response = uploadManager.put(file.getBytes(), file_name_key,qiniuTokenMap.get("qiniu_token").toString());
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			String url = putRet.key;
			//（4）返回对象处理
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", file_name);
			ajax.put("url", url);//不带域名
			return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("文件上传异常");
		}
	}
	/**
	 * 七牛云文件上传==========》自己获取token
	 */
	@Override
	public AjaxResult qiniuPhotopUpload(MultipartFile file) {
		try {
			//（1）获取七牛token
			Map<String, Object> map = new HashMap<String, Object>();
			R qiNiuResult = qiNiuService.getQiNiuToken(map);
			if(!R.Type.SUCCESS.value.equals(qiNiuResult.get("code").toString())) {
				return AjaxResult.error(qiNiuResult.get("msg").toString());
            }
			Map<String, Object> qiniuTokenMap = (Map<String, Object>) qiNiuResult.get("data");
			//（2）设置七牛云文件的key值
			String file_name = FileUploadUtils.extractFilename(file);//重命名文件名称
			String file_name_key = FileUploadUtils.renameToUUID(file_name);//存储在七牛云上面的图片的key值名称
			//（3）七牛云文件上传
			Response response = uploadManager.put(file.getBytes(), file_name_key,qiniuTokenMap.get("qiniu_token").toString());
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			String url = putRet.key;
			//（4）返回对象处理
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", file_name);
			ajax.put("url", url);//不带域名
			return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("文件上传异常");
		}
	}


	
	/**
	 * 本地文件上传
	 */
	@Override
	public AjaxResult localPhotopUpload(MultipartFile file) {
		try {
			if(file.isEmpty()) {
				return AjaxResult.error("文件对象不能为空");
			}
			//上传文件并得到返回路径
			String result = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file);
			AjaxResult ajax = AjaxResult.success();
			ajax.put("url", result);
			return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("文件上传异常");
		}
	}
	
	public static void main(String[] args) {
		String date = TimeUtil.getDayString();
		String directory = date.substring(0,4)+"/"+date.substring(4,6)+"/"+date.substring(6);
		System.out.println(directory);
	}
	
	
}

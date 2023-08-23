package com.example.longecological.service.async.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.async.AsyncUserInfoService;
import com.example.longecological.utils.ExceptionUtil;

@Service
@Async
public class AsyncUserInfoServiceImpl implements AsyncUserInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncUserInfoServiceImpl.class);
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	
	/**
	 * 记录登录日志
	 */
	@Override
	public void addUserLoginLog(Map<String, Object> map, HttpServletRequest request) {
		try{
			int num=0;
			//（1）保存用户设备信息
			num = userInfoMapper.updateUserDeviceInfo(map);
			//（2）保存用户版本系统信息
			num = userInfoMapper.updateUserVersionInfo(map);
			
			//代理信息
	        //final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			//map.put("login_location", IpAddressUtils.getAddressByIP(StringUtil.getMapValue(map, "ipaddr")));
			//map.put("browser", userAgent.getBrowser().getName());//获取客户端浏览器
			//map.put("os", userAgent.getOperatingSystem().getName());//获取客户端操作系统
			num = userInfoMapper.addUserLoginInfo(map);
			if(num != 1) {
				LOGGER.info("记录登录日志失败："+map.toString());
			}else {
				LOGGER.info("记录登录日志成功："+map.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("AsyncUserLoginLogServiceImpl -- addUserLoginLog方法处理异常：" + map.toString() + ExceptionUtil.getExceptionAllinformation(e));
		}
	}


	/**
	 * 记录用户异常操作日志
	 */
	@Override
	public void addUserErrorOperLog(Map<String, Object> map, HttpServletRequest request) {
		try{
			//代理信息
	        //final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			//map.put("login_location", IpAddressUtils.getAddressByIP(StringUtil.getMapValue(map, "ipaddr")));
			//map.put("browser", userAgent.getBrowser().getName());//获取客户端浏览器
			//map.put("os", userAgent.getOperatingSystem().getName());//获取客户端操作系统
			int num = userInfoMapper.addUserErrorOperLog(map);
			if(num != 1) {
				LOGGER.info("记录异常日志失败："+map.toString());
			}else {
				LOGGER.info("记录异常日志成功："+map.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("AsyncUserLoginLogServiceImpl -- addUserErrorOperLog方法处理异常：" + map.toString() + ExceptionUtil.getExceptionAllinformation(e));
		}
	}



}

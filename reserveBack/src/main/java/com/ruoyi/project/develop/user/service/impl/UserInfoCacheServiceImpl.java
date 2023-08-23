package com.ruoyi.project.develop.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.user.mapper.UserInfoMapper;
import com.ruoyi.project.develop.user.service.UserInfoCacheService;

@Service
public class UserInfoCacheServiceImpl implements UserInfoCacheService {

	@Autowired
	private UserInfoMapper userInfoMapper;

}

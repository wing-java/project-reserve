package com.ruoyi.project.develop.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.param.domain.SysAppImg;
import com.ruoyi.project.develop.param.mapper.SysAppImgMapper;
import com.ruoyi.project.develop.param.service.SysAppImgService;

/**
 * APP图片 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class SysAppImgServiceImpl implements SysAppImgService 
{
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private SysAppImgMapper sysAppImgMapper;

	
	/**
	 * 查询APP图片列表
	 */
	@Override
	public List<Map<String, Object>> getSysAppImgList(Map<String, Object> params) {
		return sysAppImgMapper.getSysAppImgList(params);
	}

	
	/**
	 * 导出APP图片列表
	 */
	@Override
	public List<SysAppImg> selectSysAppImgList(Map<String, Object> params) {
		return sysAppImgMapper.selectSysAppImgList(params);
	}


	/**
	 * 根据id查询详情
	 */
	@Override
	public Map<String, Object> getSysAppImgById(String id) {
		return sysAppImgMapper.getSysAppImgById(id);
	}


	/**
	 * 编辑APP图片
	 */
	@Override
	public R editSysAppImg(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = sysAppImgMapper.updateSysAppImg(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			redisUtils.remove(RedisNameConstants.sys_app_img+params.get("img_type"));
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}


	/**
	 * 新增APP图片
	 */
	@Override
	public R addSysAppImg(Map<String, Object> params) {
		try {
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			
			int i = sysAppImgMapper.addSysAppImg(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			redisUtils.remove(RedisNameConstants.sys_app_img+params.get("img_type"));
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}


	/**
	 * 批量删除APP图片
	 */
	@Override
	public R batchRemoveSysAppImg(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] appImgIds = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String appImgId : appImgIds)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeSysAppImg(appImgId);
            if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        
        if(failure_num>0) {
        	failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	
	
	/**
	 * 删除单个数据
	 * @param jobId
	 * @return
	 */
	@Transactional
	public R removeSysAppImg(String appImgId) {
		try {
			//（1）根据编号查询图片详情
			Map<String, Object> sysAppImgMap = sysAppImgMapper.getSysAppImgById(appImgId);
			//（2）删除图片
			int i = sysAppImgMapper.deleteSysAppImg(appImgId);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "图片编号"+appImgId+"：删除失败");
			}
			//（3）清除缓存
			redisUtils.remove(RedisNameConstants.sys_app_img+sysAppImgMap.get("img_type").toString());
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "图片编号"+appImgId+"：删除异常");
		} 
	}
	
}

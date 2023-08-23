package com.ruoyi.project.develop.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.RegexUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.param.service.SysParamService;
import com.ruoyi.project.develop.product.domain.SysCategory;
import com.ruoyi.project.develop.product.mapper.SysCategoryMapper;
import com.ruoyi.project.develop.product.service.SysCategoryService;

@Service
public class SysCategoryServiceImpl implements SysCategoryService {

	@Autowired
	private SysCategoryMapper sysCategoryMapper;
	
	@Autowired
	SysParamService sysParamService;
	

	/**
	 * 查询产品包列表
	 */
	@Override
	public List<Map<String, Object>> getSysCategoryList(Map<String, Object> params) {
		return sysCategoryMapper.getSysCategoryList(params);
	}
	/**
	 * 导出产品包
	 */
	@Override
	public List<SysCategory> selectSysCategoryList(Map<String, Object> params) {
		return sysCategoryMapper.selectSysCategoryList(params);
	}
	/**
	 * 根据编号查询产品包详情
	 */
	@Override
	public Map<String, Object> getSysCategoryById(String id) {
		return sysCategoryMapper.getSysCategoryById(id);
	}
	
	
	/**
	 * 新增产品包信息
	 */
	@Override
	public R addSysCategory(Map<String, Object> map) {
		try {
			int i=0,rank=0;
			/*查询最大排序名次*/
			String count = sysCategoryMapper.getMaxRankSysCategory();
			if(StringUtil.isEmpty(count)) {
				rank = 1;
			}else {
				rank = Integer.parseInt(count)+1;
			}
			//（2）新增产品包信息
			map.put("rank", rank);
			map.put("create_by", ShiroUtils.getSysUser().getLoginName());
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			i = sysCategoryMapper.addSysCategory(map);
			if(i != 1) {
				return R.error(Type.WARN, "商品分类新增失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "商品分类保存异常");
		}
	}
	/**
	 * 更新产品包信息
	 */
	@Override
	@Transactional
	public R editSysCategory(Map<String, Object> map) {
		try {
			int num=0;
			//（2）更新产品包信息
			map.put("update_by", ShiroUtils.getSysUser().getLoginName());
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			num = sysCategoryMapper.updateSysCategory(map);
			if(num != 1) {
				return R.error(Type.WARN, "商品分类更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "商品分类更新异常");
		} 
	}
	/**
	 * 排序
	 */
	@Override
	public R sortSysCategory(Map<String, Object> map) {
		try {
			int i=0;
			//（1）查询是否存在该顺序的商品信息（存在则交换关系）
			Map<String, Object> sysBrandMap = sysCategoryMapper.getCategoryInfoByRank(map);
			if(sysBrandMap!=null && !map.get("id").toString().equals(sysBrandMap.get("id").toString())) {
				Map<String, Object> sysBrandMap1 = sysCategoryMapper.getSysCategoryById(StringUtil.getMapValue(map, "id"));
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", StringUtil.getMapValue(sysBrandMap, "id"));
				param.put("rank", null);
				//存在当前排序，先将当前产品排序
				i = sysCategoryMapper.updateSysCategoryRank(param);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("排序更新失败");
				}
				
				param.put("id", StringUtil.getMapValue(map, "id"));
				param.put("rank", StringUtil.getMapValue(map, "rank"));
				//存在当前排序，先将当前产品排序
				i = sysCategoryMapper.updateSysCategoryRank(param);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("排序更新失败");
				}
				
				param.put("id", StringUtil.getMapValue(sysBrandMap, "id"));
				param.put("rank", StringUtil.getMapValue(sysBrandMap1, "rank"));
				//存在当前排序，先将当前产品排序
				i = sysCategoryMapper.updateSysCategoryRank(param);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("排序更新失败");
				}
			}else {
				//（2）不存在，直接更新商品信息
				i = sysCategoryMapper.updateSysCategoryRank(map);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("排序更新失败");
				}
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "排序更新异常");
		}
	}
	@Override
	public R batchRemoveSysCategory(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] appImgIds = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String id : appImgIds)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeSysCategory(id);
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
	public R removeSysCategory(String id) {
		try {
			//（2）删除
			int i = sysCategoryMapper.deleteSysCategory(id);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+id+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "编号"+id+"：删除异常");
		} 
	}
}

package com.ruoyi.project.develop.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.RedisNameVersionConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.RegexUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.product.domain.SysProduct;
import com.ruoyi.project.develop.product.mapper.SysProductMapper;
import com.ruoyi.project.develop.product.service.SysProductService;


/**
 * 商品信息管理
 * @author Administrator
 *
 */
@Service
public class SysProductServiceImpl implements SysProductService {
	
	@Autowired
	private SysProductMapper sysProductMapper;
	
	@Autowired
	RedisUtils redisUtils;
	

	/**
	 * 查询商品列表
	 */
	@Override
	public List<Map<String, Object>> getSysProductList(Map<String, Object> params) {
		return sysProductMapper.getSysProductList(params);
	}
	/**
	 * 导出商品
	 */
	@Override
	public List<SysProduct> selectSysProductList(Map<String, Object> params) {
		return sysProductMapper.selectSysProductList(params);
	}
	/**
	 * 根据编号查询商品详情
	 */
	@Override
	public Map<String, Object> getSysProductById(String id) {
		return sysProductMapper.getSysProductById(id);
	}
	
	
	/**
	 * 新增商品信息
	 */
	@Override
	public R addSysProduct(Map<String, Object> map) {
		try {
			int i=0, order_num;
			//（1）校验交易价、快递费、库存数量
			if(!RegexUtil.isVaildTradeNum(StringUtil.getMapValue(map, "goods_price"))
					||!RegexUtil.isVaildTradeNum(StringUtil.getMapValue(map, "sharestock_num"))
					||!RegexUtil.isVaildTradeNum(StringUtil.getMapValue(map, "goods_stock_num"))
					||!RegexUtil.isVaildTradeNum(StringUtil.getMapValue(map, "goods_sales_num"))) {
				return R.error(Type.WARN, "商品信息不完善");
			}
			/*查询最大排序名次*/
			String count = sysProductMapper.getMaxRankSysProduct();
			if(StringUtil.isEmpty(count)) {
				order_num = 1;
			}else {
				order_num = Integer.parseInt(count)+1;
			}
			//（2）新增商品信息
			map.put("order_num", order_num);
			map.put("create_by", ShiroUtils.getSysUser().getLoginName());
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			i = sysProductMapper.addSysProduct(map);
			if(i != 1) {
				return R.error(Type.WARN, "商品信息新增失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "商品信息保存异常");
		}finally {
			redisUtils.updateVersion(RedisNameVersionConstants.sys_product_list_version);
		}
	}
	/**
	 * 更新商品信息
	 */
	@Override
	@Transactional
	public R editSysProduct(Map<String, Object> map) {
		try {
			//（1）校验市场价、快递费、库存数量
			if(!RegexUtil.isVaildTradeNum(StringUtil.getMapValue(map, "goods_price"))
					||!RegexUtil.isVaildTradeNum(StringUtil.getMapValue(map, "sharestock_num"))
					||!RegexUtil.isVaildTradeNum(StringUtil.getMapValue(map, "goods_stock_num"))
					||!RegexUtil.isVaildTradeNum(StringUtil.getMapValue(map, "goods_sales_num"))) {
				return R.error(Type.WARN, "商品基本数量信息不完善");
			}
			int num=0;
			//（2）更新商品信息
			map.put("old_goods_status", "("+TypeStatusConstant.goods_info_goods_status_00+","+TypeStatusConstant.goods_info_goods_status_08+")");
			map.put("update_by", ShiroUtils.getSysUser().getLoginName());
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			num = sysProductMapper.updateSysProduct(map);
			if(num != 1) {
				return R.error(Type.WARN, "商品信息更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "商品信息更新异常");
		}finally {
			redisUtils.remove(RedisNameConstants.sys_product_detail+StringUtil.getMapValue(map, "goods_id"));
			redisUtils.updateVersion(RedisNameVersionConstants.sys_product_list_version);
		} 
	}

	
	/**
	 * 系统删除恢复商品信息
	 */
	@Override
	public R batchSysDelSysProduct(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		/*if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }*/
        if(TypeStatusConstant.goods_info_del_status_1.equals(StringUtil.getMapValue(params, "del_status"))) {
        	//批量删除商品
        	return this.batchDelSysProduct(params);
        }else if(TypeStatusConstant.goods_info_del_status_0.equals(StringUtil.getMapValue(params, "del_status"))){
        	//恢复商品
        	return this.batchNoDelSysProduct(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}
	/**
	 * 系统批量删除商品
	 * @param params
	 * @return
	 */
	private R batchDelSysProduct(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		//拼接id转换成long型数组
        String[] goods_ids = Convert.toStrArray(StringUtil.getMapValue(params, "goods_ids"));
        for(int i=0;i<goods_ids.length;i++) {
        	Map<String, Object> goodsMap = new HashMap<>();
        	goodsMap.put("remark", params.get("remark"));
        	goodsMap.put("goods_id", goods_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).delSysProduct(goodsMap);
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
	 * 系统删除单个商品
	 * @param goodsMap
	 * @return
	 */
	@Transactional
	public R delSysProduct(Map<String, Object> goodsMap) {
		try {
			int num=0;
			//（1）更新商品删除状态
			goodsMap.put("update_by", ShiroUtils.getSysUser().getLoginName());
			goodsMap.put("up_date", TimeUtil.getDayString());
			goodsMap.put("up_time", TimeUtil.getTimeString());
			goodsMap.put("old_goods_status", "("+TypeStatusConstant.goods_info_goods_status_00+","+TypeStatusConstant.goods_info_goods_status_08+")");//上架状态：待上架、已下架
			goodsMap.put("old_del_status", TypeStatusConstant.goods_info_del_status_0);//删除状态：未删除
			goodsMap.put("new_del_status", TypeStatusConstant.goods_info_del_status_1);//删除状态：已删除
			num = sysProductMapper.updateSysProductDelStatus(goodsMap);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "商品编号"+goodsMap.get("goods_id").toString()+"：删除失败：不能删除已上架的商品");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "商品编号"+goodsMap.get("goods_id").toString()+"：删除异常");
		}finally {
			redisUtils.updateVersion(RedisNameVersionConstants.sys_product_list_version);
		} 
	}
	/**
	 * 系统批量恢复商品
	 * @param params
	 * @return
	 */
	private R batchNoDelSysProduct(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] goods_ids = Convert.toStrArray(StringUtil.getMapValue(params, "goods_ids"));
        for(int i=0;i<goods_ids.length;i++) {
        	Map<String, Object> goodsMap = new HashMap<>();
        	goodsMap.put("remark", params.get("remark"));
        	goodsMap.put("goods_id", goods_ids[i]);
        	R result = SpringUtils.getAopProxy(this).noDelSysProduct(goodsMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "下架结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "下架结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 系统恢复单个商品
	 * @param goodsMap
	 * @return
	 */
	@Transactional
	public R noDelSysProduct(Map<String, Object> goodsMap) {
		try {
			//（1）恢复商品信息
			goodsMap.put("update_by", ShiroUtils.getSysUser().getLoginName());
			goodsMap.put("up_date", TimeUtil.getDayString());
			goodsMap.put("up_time", TimeUtil.getTimeString());
			goodsMap.put("old_goods_status", "("+TypeStatusConstant.goods_info_goods_status_09+","+TypeStatusConstant.goods_info_goods_status_08+")");//上架状态：待上架、已下架
			goodsMap.put("old_del_status", TypeStatusConstant.goods_info_del_status_1);//删除状态：已删除
			goodsMap.put("new_del_status", TypeStatusConstant.goods_info_del_status_0);//删除状态：未删除
			int i = sysProductMapper.updateSysProductDelStatus(goodsMap);
			if(i != 1) {
				return R.error(Type.WARN, "商品编号"+goodsMap.get("goods_id").toString()+"：删除失败：中能删除未上架的商品");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "商品编号"+goodsMap.get("goods_id").toString()+"：删除异常");
		}finally {
			redisUtils.updateVersion(RedisNameVersionConstants.sys_product_list_version);
		} 
	}
	

	
	/**
	 * 系统批量上下架商品
	 */
	@Override
	public R batchSysReleaseSysProduct(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
        if(TypeStatusConstant.goods_info_goods_status_09.equals(StringUtil.getMapValue(params, "goods_status"))) {
        	//上架商品
        	return this.batchReleaseSysProduct(params);
        }else if(TypeStatusConstant.goods_info_goods_status_08.equals(StringUtil.getMapValue(params, "goods_status"))){
        	//下架商品
        	return this.batchNoReleaseSysProduct(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}
	/**
	 * 系统批量上架商品
	 * @param params
	 * @return
	 */
	private R batchReleaseSysProduct(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] goods_ids = Convert.toStrArray(StringUtil.getMapValue(params, "goods_ids"));
        for(int i=0;i<goods_ids.length;i++) {
        	Map<String, Object> goodsMap = new HashMap<>();
        	goodsMap.put("remark", params.get("remark"));
        	goodsMap.put("goods_id", goods_ids[i]);
        	//依次上架每一个商品
        	R result = SpringUtils.getAopProxy(this).releaseSysProduct(goodsMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "上架结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "上架结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 系统上架单个商品
	 * @param goodsMap
	 * @return
	 */
	@Transactional
	public R releaseSysProduct(Map<String, Object> goodsMap) {
		try {
			//（1）更新商品状态
			int i=0;
			goodsMap.put("old_goods_status", "("+TypeStatusConstant.goods_info_goods_status_00+","+TypeStatusConstant.goods_info_goods_status_08+")");//旧状态：待上架、已下架
			goodsMap.put("new_goods_status", TypeStatusConstant.goods_info_goods_status_09);//新状态：已上架
			goodsMap.put("del_status", TypeStatusConstant.goods_info_del_status_0);//删除状态：未删除
			goodsMap.put("update_by", ShiroUtils.getSysUser().getLoginName());
			goodsMap.put("up_date", TimeUtil.getDayString());
			goodsMap.put("up_time", TimeUtil.getTimeString());
			i = sysProductMapper.updateSysProductStatus(goodsMap);
			if(i != 1) {
				return R.error(Type.WARN, "商品编号"+goodsMap.get("goods_id").toString()+"：状态更新失败");
			}
			return R.ok("上架成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "商品编号"+goodsMap.get("goods_id").toString()+"：上架异常");
		}finally {
			redisUtils.remove(RedisNameConstants.sys_product_detail+StringUtil.getMapValue(goodsMap, "goods_id"));
			redisUtils.updateVersion(RedisNameVersionConstants.sys_product_list_version);
		}
	}
	/**
	 * 系统批量下架商品
	 * @param params
	 * @return
	 */
	private R batchNoReleaseSysProduct(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] goods_ids = Convert.toStrArray(StringUtil.getMapValue(params, "goods_ids"));
        for(int i=0;i<goods_ids.length;i++) {
        	Map<String, Object> goodsMap = new HashMap<>();
        	goodsMap.put("remark", params.get("remark"));
        	goodsMap.put("goods_id", goods_ids[i]);
        	R result = SpringUtils.getAopProxy(this).noReleaseSysProduct(goodsMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "下架结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "下架结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 系统下架单个商品
	 * @param goodsMap
	 * @return
	 */
	@Transactional
	public R noReleaseSysProduct(Map<String, Object> goodsMap) {
		try {
			int i=0;
			//（1）更新商品状态
			goodsMap.put("old_goods_status", "("+TypeStatusConstant.goods_info_goods_status_09+")");//旧状态：已上架
			goodsMap.put("new_goods_status", TypeStatusConstant.goods_info_goods_status_08);//新状态：已下架
			goodsMap.put("del_status", TypeStatusConstant.goods_info_del_status_0);//删除状态：未删除
			goodsMap.put("update_by", ShiroUtils.getSysUser().getLoginName());
			goodsMap.put("up_date", TimeUtil.getDayString());
			goodsMap.put("up_time", TimeUtil.getTimeString());
			i = sysProductMapper.updateSysProductStatus(goodsMap);
			if(i != 1) {
				return R.error(Type.WARN, "商品编号"+goodsMap.get("goods_id").toString()+"：状态更新失败");
			}
			return R.ok("下架成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "商品编号"+goodsMap.get("goods_id").toString()+"：下架异常");
		}finally {
			redisUtils.remove(RedisNameConstants.sys_product_detail+StringUtil.getMapValue(goodsMap, "goods_id"));
			redisUtils.updateVersion(RedisNameVersionConstants.sys_product_list_version);
		}
	}
	
	/**
	 * 排序
	 */
	@Override
	public R sortSysProduct(Map<String, Object> map) {
		try {
			int i=0;
			//（1）查询是否存在该顺序的商品信息（存在则交换关系）
			Map<String, Object> sysBrandMap = sysProductMapper.getProductInfoByRank(map);
			if(sysBrandMap!=null && !map.get("id").toString().equals(sysBrandMap.get("id").toString())) {
				Map<String, Object> sysBrandMap1 = sysProductMapper.getSysProductById(StringUtil.getMapValue(map, "id"));
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", StringUtil.getMapValue(sysBrandMap, "id"));
				param.put("order_num", null);
				//存在当前排序，先将当前产品排序
				i = sysProductMapper.updateSysProductRank(param);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("排序更新失败");
				}
				
				param.put("id", StringUtil.getMapValue(map, "id"));
				param.put("order_num", StringUtil.getMapValue(map, "order_num"));
				//存在当前排序，先将当前产品排序
				i = sysProductMapper.updateSysProductRank(param);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("排序更新失败");
				}
				
				param.put("id", StringUtil.getMapValue(sysBrandMap, "id"));
				param.put("order_num", StringUtil.getMapValue(sysBrandMap1, "order_num"));
				//存在当前排序，先将当前产品排序
				i = sysProductMapper.updateSysProductRank(param);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("排序更新失败");
				}
			}else {
				//（2）不存在，直接更新商品信息
				i = sysProductMapper.updateSysProductRank(map);
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
}
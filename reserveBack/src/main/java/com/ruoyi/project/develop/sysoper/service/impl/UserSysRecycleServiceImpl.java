package com.ruoyi.project.develop.sysoper.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.BenefitRecordTypeContants;
import com.ruoyi.common.constant.OperParamConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.ParamValidUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.sysoper.domain.UserSysRecycle;
import com.ruoyi.project.develop.sysoper.mapper.UserSysRecycleMapper;
import com.ruoyi.project.develop.sysoper.service.UserSysRecycleService;
import com.ruoyi.project.develop.user.mapper.UserInfoMapper;
import com.ruoyi.project.develop.user.service.impl.UserInfoServiceImpl;


/**
 * 公司扣款管理
 * @author Administrator
 *
 */
@Service
public class UserSysRecycleServiceImpl implements UserSysRecycleService {
	
	@Autowired
	UserInfoServiceImpl userInfoServiceImpl;
	
	@Autowired
	private UserSysRecycleMapper userSysRecycleMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;

	
	/**
	 * 查询公司扣款列表
	 */
	@Override
	public List<Map<String, Object>> getUserSysRecycleList(Map<String, Object> params) {
		return userSysRecycleMapper.getUserSysRecycleList(params);
	}
	
	
	/**
	 * 汇总数据
	 */
	@Override
	public Map<String, Object> summaryUserSysRecycleList(Map<String, Object> params) {
		return userSysRecycleMapper.summaryUserSysRecycleList(params);
	}

	
	/**
	 * 导出公司扣款列表
	 */
	@Override
	public List<UserSysRecycle> selectUserSysRecycleList(Map<String, Object> params) {
		return userSysRecycleMapper.selectUserSysRecycleList(params);
	}


	/**
	 * 新增公司拨款
	 */
	@Override
	@Transactional
	public R batchAddUserSysRecycle(Map<String, Object> map) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		/*if(StringUtils.isEmpty(StringUtil.getMapValue(map, "remark"))) {
			return R.error(Type.WARN, "操作备注不能为空");
		}*/
		//校验拨款数量信息
		if(!StringUtil.isValidLargeBigDecimal0(StringUtil.getMapValue(map, "num"))){
			return R.error(Type.WARN, "交易数据不合法");
		}
		//校验拨款钱包信息
		R checkPurseResult = ParamValidUtil.checkSpecifyParam(map, "purse_type", OperParamConstant.USER_SYS_RECYCLE_PURSE_TYPE);
		if(!Type.SUCCESS.value.equals(checkPurseResult.get("code").toString())) {
			return checkPurseResult;
		}
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] user_ids = Convert.toStrArray(StringUtil.getMapValue(map, "user_ids"));
        for(int i=0;i<user_ids.length;i++) {
        	Map<String, Object> userMap = new HashMap<>();
        	userMap.put("user_id", user_ids[i]);
        	userMap.put("purse_type", map.get("purse_type").toString());
        	userMap.put("num", map.get("num").toString());
        	userMap.put("remark", map.get("remark"));
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).addUserSysRecycle(userMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "扣款结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "扣款结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 新增公司扣款
	 * @param userMap
	 * @return
	 */
	@Transactional
	public R addUserSysRecycle(Map<String, Object> userMap) {
		try {
			int i=0;
			//（1）记录拨款订单
			String order_id = StringUtil.getDateTimeAndRandomForID();
			userMap.put("op_order_no", order_id);//订单号
			userMap.put("create_by", ShiroUtils.getSysUser().getLoginName());//创建人
			userMap.put("cre_date", TimeUtil.getDayString());//创建日期
			userMap.put("cre_time", TimeUtil.getTimeString());//创建时间
			i = userSysRecycleMapper.addUserSysRecycle(userMap);
			if(i != 1) {
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：拨款记录保存失败");
			}
			//（2）更新用户钱包信息
			userMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_04);//操作类型：公司扣款
			userMap.put("balance_num", new BigDecimal(0));//提现账户
			if(OperParamConstant.USER_SYS_RECYCLE_PURSE_TYPE_01.equals(StringUtil.getMapValue(userMap, "purse_type"))) {
				//余额
				userMap.put("balance_num", new BigDecimal(userMap.get("num").toString()).negate());
			}
			i = userInfoMapper.updateUserWallet(userMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：账户更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：拨款异常");
		}
	}

}

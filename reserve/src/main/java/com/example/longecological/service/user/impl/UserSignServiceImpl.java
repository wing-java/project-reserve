package com.example.longecological.service.user.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.constant.RedisNameVersionConstants;
import com.example.longecological.constant.SysParamCodeConstants;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.GoodsInfoConstant;
import com.example.longecological.constant.msgcode.OrderInfoConstant;
import com.example.longecological.constant.msgcode.TradeInfoCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.mapper.user.UserSignMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.service.user.UserSignService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.AmountUtils;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class UserSignServiceImpl implements UserSignService {
	
	@Autowired
	UserSignMapper userSignMapper;
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	SysParamService sysParamService;
	@Autowired
	UserWalletMapper userWalletMapper;

	@Override
	public R getUserSignList(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			List<Map<String, Object>> userSignList = userSignMapper.getUserSignList(StringUtil.getMapValue(map, "sys_user_id"), StringUtil.getMapValue(map, "month"));
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userSignList", userSignList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	@Override
	@Transactional
	public R userSign(Map<String, Object> map) {
		int num = 0;
		try {
			//校验参数
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//查询是否签到
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			Map<String, Object> userSign = userSignMapper.getUserSignByDate(StringUtil.getMapValue(map, "sys_user_id"), date);
			if(userSign != null) return R.error(TradeInfoCodeConstant.TRADE_INFO_CODE_999592, TradeInfoCodeConstant.TRADE_INFO_MSG_999592);
			//查询奖励金额
			BigDecimal benefit = BigDecimal.ZERO;
			Map<String, Object> userInfo = userInfoMapper.getRealUserInfoById(StringUtil.getMapValue(map, "sys_user_id"));
			if("1".equals(StringUtil.getMapValue(userInfo, "is_valid"))) {
				benefit = new BigDecimal(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_activatedUserSigninNum));
			}else {
				benefit = new BigDecimal(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_unactivatedUserSigninNum));
			}
			//保存签到记录
			Map<String, Object> record = new HashMap<>();
			String order_no = StringUtil.getDateTimeAndRandomForID();
			record.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
			record.put("order_no", order_no);
			record.put("benefit", benefit);
			record.put("cre_date", date);
			record.put("cre_time", time);
			num = userSignMapper.addUserSign(record);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(TradeInfoCodeConstant.TRADE_INFO_CODE_999591, TradeInfoCodeConstant.TRADE_INFO_MSG_999591);
			}
			if(benefit.compareTo(BigDecimal.ZERO)>0) {
				Map<String, Object> edit_user = new HashMap<>();
				edit_user.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
				edit_user.put("balance_num", benefit);
				edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_08);
				edit_user.put("op_order_no", StringUtil.getDateTimeAndRandomForID());
				edit_user.put("up_date", TimeUtil.getDayString());
				edit_user.put("up_time", TimeUtil.getTimeString());
				num = userWalletMapper.updateUserWalletNum(edit_user);
				if(num != 1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(TradeInfoCodeConstant.TRADE_INFO_CODE_999591, TradeInfoCodeConstant.TRADE_INFO_MSG_999591);
				}
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}

}

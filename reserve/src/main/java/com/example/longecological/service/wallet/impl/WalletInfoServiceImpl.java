package com.example.longecological.service.wallet.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.wallet.WalletRecordMapper;
import com.example.longecological.service.wallet.WalletInfoService;
import com.example.longecological.utils.string.StringUtil;


/**
 * 钱包中心相关
 * @author Administrator
 *
 */
@Service
public class WalletInfoServiceImpl implements WalletInfoService {
	
	@Autowired
	WalletRecordMapper walletRecordMapper;
	

	/**
	 * 查询用户流水列表记录
	 */
	@Override
	public R getWalletRecordList(Map<String, Object> map) {
		try {
			//返回对象信息
			Map<String, Object> respondMap=new HashMap<>();
			if(!StringUtils.isEmpty(StringUtil.getMapValue(map, "op_type"))) {
				map.put("op_type", "("+map.get("op_type").toString()+")");
			}
			List<Map<String, Object>> walletRecordList = new ArrayList<>();
			//余额
			if(TypeStatusConstant.sys_purse_type_01.equals(StringUtil.getMapValue(map, "purse_type"))) {
				map.put("table", "t_benefit_record_balance");
				walletRecordList = walletRecordMapper.getWalletRecordList(map);
			}
			respondMap.put("walletRecordList", walletRecordList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

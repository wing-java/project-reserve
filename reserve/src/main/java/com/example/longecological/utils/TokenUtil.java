package com.example.longecological.utils;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.utils.string.StringUtil;

public class TokenUtil {
	
	/**
	 * token值校验
	 * @param map
	 * @param ajaxJson
	 */
	public static R checkTokenMap(Map<String, Object> map) {
		//token值校验
		String token = StringUtil.getMapValue(map, "token");
		if(StringUtils.isEmpty(token)) {
			return R.error(CommonCodeConstant.COMMON_CODE_999992,CommonCodeConstant.COMMON_MSG_999992);
		}
		//用户ID
		String id = map.get("token").toString().split("\\|")[0];
		map.put("sys_user_id", id);
		return R.ok(CommonCodeConstant.COMMON_CODE_999999);
	}
	
	
	/**
	 * 校验rsa的解密结果
	 * @param resultMap
	 * @return
	 */
	public static boolean checkRSAdecrypt(Map<String, Object> resultMap) {
		return Boolean.valueOf(resultMap.get(R.SUCCESS_TAG).toString());
	}

}

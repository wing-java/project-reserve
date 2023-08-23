package com.example.longecological.utils.string;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;


/**
 * @author Administrator
 *
 */
public class ParamValidUtil {
		
	/**
	 * 必备参数校验
	 * @param map：传入对象
	 * @param paramArra：参数区域
	 * @return
	 */
    public static R checkMustParam(Map<String, Object> map,String[] paramArra) {
    	for(int i=0;i<paramArra.length;i++) {
    		if(!map.containsKey(paramArra[i])||StringUtils.isEmpty(map.get(paramArra[i]).toString())) {
    			return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
    		}
    	}
    	return R.ok(CommonCodeConstant.COMMON_CODE_999999);
    }
    
    
   /**
    * 特殊参数校验
    * @param map
    * @param paraName
    * @param paramValueArra
    * @return
    */
    public static R checkSpecifyParam(Map<String, Object> map, String paraName,
			String[] paramValueArra) {
		boolean flag=false;
		for(int i=0;i<paramValueArra.length;i++) {
			if(paramValueArra[i].equals(StringUtil.getMapValue(map, paraName))) {
				flag=true;
				break;
			}
		}
		if(flag==false) {
			return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
		}else {
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		}
	}
    
    
    /**
     * 交易数据处理
     * @param map
     * @param string
     */
    public static void dealTransData(Map<String, Object> map, String objectStr) {
    	if(!map.containsKey(objectStr)) {
    		map.put(objectStr, "0");
    	}
	}
    
	
}

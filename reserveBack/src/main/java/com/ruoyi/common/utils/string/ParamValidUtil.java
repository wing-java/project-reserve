package com.ruoyi.common.utils.string;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruoyi.framework.aspectj.lang.annotation.CheckObj;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;

/**
 * 接口调取商的参数校验工具类
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
    			return R.error(Type.WARN, "参数有误");
    		}
    	}
    	return R.ok("参数校验通过");
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
			return R.error(Type.WARN, "参数"+paraName+"无效，请仔细查阅接口文档");
		}else {
			return R.ok("参数校验通过");
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
    
    
    /**
	 * 验证对象
	 * 
	 * @param o
	 * @throws AppException
	 * @throws Exception
	 */
	public static R checkReqObj(Object o) {
		if (o == null) {
			return R.error(Type.WARN, "输入参数对象为空");
		}
		try {
			//getDeclaredField是可以获取一个类的所有字段. 
			//getField只能获取类的public 字段. 
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field field : fields) {
				//返回该程序元素上存在指定CheckObj类型的所有注解实例对象,如果该类型注解不存在，则返回null。
				CheckObj checkObj = field.getAnnotation(CheckObj.class);
				if (checkObj != null) {
				    //对所有属性设置访问权限  当类中的成员变量为private时 必须设置此项
					field.setAccessible(true);
					//获取当前对象0中当前Field的value  
					Object param = field.get(o);
					field.setAccessible(false);
					boolean status = validRequired(checkObj, param);
					if(!status){
						return R.error(Type.WARN, "交易失败：" + checkObj.fieldName() + "不能为空");
					}
				}
			}
			
			return R.ok("验证成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "参数验证异常");
		}
	}
	

	/**
	 * 验证必输
	 * 
	 * @param checkObj
	 * @param param
	 * @throws AppException
	 */
	private static boolean validRequired(CheckObj checkObj, Object param) {
		if (checkObj.required() && null != param && StringUtils.isNotBlank(param.toString())) {
			return true;
		}
		return false;
	}
    

	
}

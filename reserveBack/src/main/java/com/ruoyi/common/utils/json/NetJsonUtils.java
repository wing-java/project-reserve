package com.ruoyi.common.utils.json;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * net包的json工具类
 * @author Administrator
 *
 */
public class NetJsonUtils {

    /**
     * json字符串取值
     * @param jsonString
     * @param object
     * @return
     */
    public static String jsonToString(String jsonString,String object) {
    	if(jsonString.contains(object)) {
    		//获得返回参数的jsonString
        	JSONObject jsonObject = JSONObject.fromObject(jsonString);
        	return jsonObject.getString(object);
    	}else {
    		return null;
    	}
    }
    
    
    public static String json(String jsonArray,String object) {
    	JSONObject jsonObject = JSONObject.fromObject(jsonArray);
        //取出json中的data数据
        JSONObject result = jsonObject.getJSONObject("result");
        JSONObject jsonresultObject = JSONObject.fromObject(result);
    	return jsonObject.getString(object);//x的值，y类似
    }
    
    
   //===================================实体类转json===========================
	
  	/**
  	 * 1、使用JSONObject
  	 * 注意：时间类型变为了：{"date":7,"day":2,"hours":19,"minutes":53,"month":4,"seconds":6,"time":1557229986876,"timezoneOffset":-480,"year":119}
  	 * @param obj
  	 * @return
  	 */
  	public static String beanToJson1(Object obj) {
  		return JSONObject.fromObject(obj).toString();
  	}
  	
  	
  	/**
  	 * 使用JSONArray
  	 * 注意：时间类型变为了：{"date":7,"day":2,"hours":19,"minutes":53,"month":4,"seconds":6,"time":1557229986876,"timezoneOffset":-480,"year":119}
  	 * @param obj
  	 * @return
  	 */
  	public static String beanToJson2(Object obj) {
  		return JSONArray.fromObject(obj).toString();
  	}
    
  	
  	
  	//===================================json转实体类===========================
  	/**
  	 * 使用JSONObject
  	 * 注意：json字符串必须形如：
  	 * String str = "{\'user_tel\':\'18802671616',\'login_password\':\'1111111111'}";
  	 * @param jsonString
  	 * @param obj
  	 * @return
  	 */
  	public static<T> Object jsonToBean1(String jsonString, Class<T> obj) {
  		JSONObject jsonObject=JSONObject.fromObject(jsonString);
  		return (T)JSONObject.toBean(jsonObject, obj);
  	}
  	
  	
  	/**
  	 * 使用JSONArray
  	 * 注意：json字符串必须形如：
  	 * String str = "[{\'user_tel\':\'18802671616',\'login_password\':\'1111111111'}]";
  	 * 时间格式转换后形如：login_time=Tue May 07 20:08:17 CST 2019
  	 * @param jsonString
  	 * @param obj
  	 * @return
  	 */
  	public static<T> Object jsonToBean2(String jsonString, Class<T> obj) {
  		JSONArray jsonArray=JSONArray.fromObject(jsonString);
        //获得jsonArray的第一个元素
        Object o=jsonArray.get(0);
        JSONObject jsonObject2=JSONObject.fromObject(o);
  		return (T)JSONObject.toBean(jsonObject2, obj);
  	}
  	
  	
  	
  	
  	//===================================list转json===========================
  	/**
  	 * 使用JSONArray
  	 * @param listObj
  	 * @return
  	 */
  	public static <T> String listToJson1(List<T> listObj) {
  		return JSONArray.fromObject(listObj).toString();
  	}
  	
  	
  	
  	//===================================json转list===========================
  	/**
  	 * 使用JSONArray.toList
  	 * @param jsonString
  	 * @param clazz
  	 * @return
  	 */
  	public static <T> List<T> jsonToList1(String jsonString, Class<T> clazz) {
        List<T> ts = (List<T>) JSONArray.toList(JSONArray.fromObject(jsonString), clazz);
        return ts;
    }
  	
  	
  	
  	//===================================map转json===========================
  	/**
  	 * 使用JSONObject
  	 * @param map
  	 * @return
  	 */
  	public static String mapToJson1(Map<String, Object> map) {
  		return JSONObject.fromObject(map).toString();
  	}
  	
  	
  	/**
  	 * 使用JSONArray
  	 * @param map
  	 * @return
  	 */
  	public static String mapToJson2(Map<String, Object> map) {
  		return JSONArray.fromObject(map).toString();
  	}
  	
  	
  	//===================================json转map===========================
  	public static Map<String, Object> jsonToMap1(String jsonString){
  		return (Map)JSONObject.fromObject(jsonString);
  	}
  	
  	
  	
  	
  	
  	public static void main(String[] args) {
  		String arrayStr="{\"user_tel\":\"111111\",\"login_password\":\"11111\"}";
        System.out.println(jsonToMap1(arrayStr));
	}
}

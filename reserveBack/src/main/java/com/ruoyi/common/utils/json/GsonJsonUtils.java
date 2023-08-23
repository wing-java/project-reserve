package com.ruoyi.common.utils.json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * 谷歌Gson的json解析工具类
 * @author Administrator
 *
 */
public class GsonJsonUtils {

    
    /**
     * json字符串转换成List类型，例如：List<Map<String, Object>>
     * @param jsonString
     * @return
     */
    public static List jsonToList(String jsonString) {
    	Gson gson = new Gson();
		return gson.fromJson(jsonString, new TypeToken<List>() {}.getType());
    }
    
}

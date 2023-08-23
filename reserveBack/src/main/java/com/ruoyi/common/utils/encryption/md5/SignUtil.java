package com.ruoyi.common.utils.encryption.md5;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruoyi.common.constant.SysSecurityKeyConstant;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;



/**
 * 此签名工具针对开联通请求接口的加签来，其特点是：sign不参与签名，但是key参与签名
 * 准确讲是将请求参数排序之后加上key再进行md5加密
 * 因此在formatUrlMap方法中，sign排除，getSign方法中，key加上去
 * @author Administrator
 *
 */
public class SignUtil{   
    
    /**
     * 
     * 方法用途: 对所有传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     * 
     * @param paraMap   要排序的Map对象
     * @param urlEncode   是否需要URLENCODE
     * @param keyToLower    是否需要将Key转换为全小写
     *            true:key转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, Object> paraMap, boolean urlEncode, boolean keyToLower)
    {
        String buff = "";
        Map<String, Object> tmpMap = paraMap;
        try
        {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），空值不参与
            Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>()
            {
 
                @Override
                public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2)
                {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, Object> item : infoIds)
            {
                if (StringUtils.isNotBlank(item.getKey()))
                {
                    String key = item.getKey();
                    if(item.getValue()!=null) {
                    	String val = item.getValue().toString().replaceAll(" ", "");
                    	if(!StringUtils.isEmpty(val)&&!"sign".equals(key)&&!"sys_user_id".equals(key)) {
                        	if (urlEncode)
                            {
                                val = URLEncoder.encode(val, "utf-8");
                            }
                            if (keyToLower)
                            {
                                buf.append(key.toLowerCase() + "=" + val);
                            } else
                            {
                                buf.append(key + "=" + val);
                            }
                            buf.append("&");
                        }
                    }
                }
 
            }
            buff = buf.toString();
            if (buff.isEmpty() == false)
            {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e)
        {
           return null;
        }
        return buff;
    }
    
    
    /**
     * 获取用户的签名
     * @param paraMap
     * @param urlEncode
     * @param keyToLower：双方约定的加密秘钥
     * @return
     */
    public static String getSign(Map<String, Object> paraMap, boolean urlEncode, boolean keyToLower,String md5Key) {
    	System.out.println(paraMap.toString());
    	/**
    	 * 将接口商传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串，且空值不参与
    	 */
    	String result = formatUrlMap(paraMap,false,false);
    	result += "&key=" + md5Key;
    	System.out.println("加签字符串====="+result);
    	//MD5编码加密
    	result= MD5Utils.MD5Encode(result).toUpperCase();
        return result;
    }
    
    
    /**
	 * 签名验证
	 * @param map
	 * @param ajaxJson
	 */
	public static R checkSign(Map<String, Object> map) {
		try {
			String sign=getSign(map, false, false, SysSecurityKeyConstant.md5Key_app);
			if(!sign.equals(map.get("sign").toString())) {
				return R.error(Type.WARN, "签名错误");
			}
			return R.ok("签名正确");
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok("签名验证异常");
		}
	}
	
	
	 /**
	 * 签名验证
	 * @param map
	 * @param ajaxJson
	 */
	public static R checkSign(Map<String, Object> map,String md5Key) {
		try {
			String sign=getSign(map, false, false, md5Key);
			if(!sign.equals(map.get("sign").toString())) {
				return R.error("签名错误");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("签名异常");
		}
	}
    
    
    
    public static void testJSONObject() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("d", 10);
		String y2 = map.get("s").toString();
		System.out.println(y2);
	}

    
    public static void main(String[] args) {
    	Map<String, Object> map=new HashMap<>();
    	map.put("system_type", "getStarInfo");
    	map.put("other_id", "214");
    	//map.put("pay_password", "dsfsdf4yt34tdfgvfg45y4");
    	//map.put("pageNum", "0");
    	map.put("token", "38|9UOL25ZZFDV2C60MLLSACJO4GHMGRL12");
    	System.out.println(getSign(map, false, false, SysSecurityKeyConstant.md5Key_app));
    	
	}
    

}

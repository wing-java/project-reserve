package com.example.longecological.utils.encryption.md5;

import java.net.URLEncoder;
import java.util.*;

import org.apache.commons.lang.StringUtils;

import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.utils.json.NetJsonUtils;
import com.example.longecological.utils.string.StringUtil;




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
//                    	String val = item.getValue().toString().replaceAll(" ", "");
                    	String val = item.getValue().toString();
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
    	result= MD5Utils.MD5Encode(result, "UTF-8").toUpperCase();
    	System.out.println("加签结果====="+result);
        return result;
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
				return R.error(CommonCodeConstant.COMMON_CODE_999994, CommonCodeConstant.COMMON_MSG_999994);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999993, CommonCodeConstant.COMMON_MSG_999993);
		}
	}
    
    
    
    public static void testJSONObject() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("d", 10);
		String y2 = map.get("s").toString();
		System.out.println(y2);
	}
    
    /**
     * 获取用户的签名-刺客支付
     * @param paraMap
     * @param keyToLower：双方约定的加密秘钥
     * @return
     */
    public static String getSignToCiKePay(Map<String, Object> paraMap, String md5Key) {
    	System.out.println(paraMap.toString());
    	/**
    	 * 将接口商传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串，且空值不参与
    	 */
    	String result = "version=["
    					+StringUtil.getMapValue(paraMap, "version")
    					+"]merId=["
    					+StringUtil.getMapValue(paraMap, "merId")
    					+"]merOrderId=["
    					+StringUtil.getMapValue(paraMap, "merOrderId")
    					+"]tranAmt=["
    					+StringUtil.getMapValue(paraMap, "tranAmt")
    					+"]submitTime=["
    					+StringUtil.getMapValue(paraMap, "submitTime")
    					+"]signType=["
    					+StringUtil.getMapValue(paraMap, "signType")
    					+"]key=["
    					+md5Key
    					+"]";
    	System.out.println("加签字符串====="+result);
    	//MD5编码加密
    	result= MD5Utils.MD5Encode(result, "UTF-8");
    	System.out.println("加签结果====="+result);
        return result;
    }
    
    /**
     * 获取用户的签名-刺客支付回调
     * @param paraMap
     * @param keyToLower：双方约定的加密秘钥
     * @return
     */
    public static String getSignToCiKePayReturn(Map<String, Object> paraMap, String md5Key) {
    	System.out.println(paraMap.toString());
    	/**
    	 * 将接口商传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串，且空值不参与
    	 */
    	String result = "merId=["
    					+StringUtil.getMapValue(paraMap, "merId")
    					+"]merOrderId=["
    					+StringUtil.getMapValue(paraMap, "merOrderId")
    					+"]tranAmt=["
    					+StringUtil.getMapValue(paraMap, "tranAmt")
    					+"]paySuccessTime=["
    					+StringUtil.getMapValue(paraMap, "paySuccessTime")
    					+"]signType=["
    					+StringUtil.getMapValue(paraMap, "signType")
    					+"]key=["
    					+md5Key
    					+"]";
    	System.out.println("加签字符串====="+result);
    	//MD5编码加密
    	result= MD5Utils.MD5Encode(result, "UTF-8");
    	System.out.println("加签结果====="+result);
        return result;
    }

	/**
	 * 获取狂神支付加密sign
	 * @param paramMap
	 * @param md5Key
	 * @return
	 */
	public static String getSignToDementedGod(Map<String, Object> paramMap, String md5Key){
		//return encryptSign(paraMap,md5Key);
		String sign = formatUrlMap(paramMap,false,false);
		StringBuilder signBuilder = new StringBuilder(sign).append("&key=").append(md5Key);
		return MD5Utils.MD5Encode(signBuilder.toString(),"UTF-8").toLowerCase();
	}

//	/**
//	 * 参数加密
//	 * @param paramMap
//	 * @throws Exception
//	 */
//	public static String encryptSign(Map<String,Object> paramMap,String md5Key){
//		// 生成sign
//		String sign = formatUrlMap(paramMap,false,false);
//		StringBuilder signBuilder = new StringBuilder(sign).append("&key=").append(md5Key);
//		return MD5Utils.MD5Encode(signBuilder.toString(),"UTF-8").toLowerCase();
//	}

//	/**
//	 * 生成sign
//	 * @param paramMap
//	 */
//	public static String generateSing(Map<String,Object> paramMap){
//		String[] sortedKeys = paramMap.keySet().toArray(new String[]{});
//		Arrays.sort(sortedKeys);
//		StringBuilder buf = new StringBuilder();
//		for (String key : sortedKeys) {
//			if(key.equals("sign")){
//				continue;
//			}
//			buf.append(key).append("=").append(paramMap.get(key)).append("&");
//		}
//		buf.deleteCharAt(buf.length() -1);
//		return buf.toString();
//	}

    
    public static void main(String[] args) {
    	/*Map<String, Object> map=new HashMap<>();
    	map.put("timestamp", new Date().getTime()/1000);
    	map.put("oper_order_id", "20210113144010-912905433-1");
    	System.out.println(getSign(map, false, false, SysSecurityKeyConstant.md5Key_php_request));
    	map.put("sign", getSign(map, false, false, SysSecurityKeyConstant.md5Key_php_request));
    	System.out.println(NetJsonUtils.mapToJson1(map));*/
    	
//    	String result = "app_version=1.2.8&device_no=2edb2079716b239aa8f2181f459b1a55c&device_type=HUAWEI&login_type=token&token=8194|DMH7OAFK2D78OUUCQ13Q4C4600476JTK&version_no=10&key=NHqKBZjgv6MB0C8dmOLecwEowJ4cLVfFb7m9ZA1EWzHtyzSGXTSXzJdY3NxQXPmv";
//    	String result1= MD5Utils.MD5Encode(result, "UTF-8").toUpperCase();
//    	System.out.println(result1);
    	//6724070B4B4B4915D1FC213D3EDE5B4A
    	//System.out.println(MD5Utils.MD5Encode("hr2013125118").toUpperCase());

		Map<String,Object> map = new HashMap<>();
		map.put("cey1","1");
		map.put("bey2","2");
		map.put("aey3","3");
		System.out.println(getSignToDementedGod(map,"123465"));
	}


}

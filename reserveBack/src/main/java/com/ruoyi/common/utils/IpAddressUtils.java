package com.ruoyi.common.utils;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.string.StringUtil;
 
/**
 * @author Administrator
 */
public class IpAddressUtils {
 
	private static AtomicLong atomicLong = new AtomicLong(0);
 
	/**
	 * 通过IPws查询
	 * @param ip
	 * @return
	 */
	private static String getIpWs126Net(String ip){
		try{
			String res = httpRequest(String.format("http://ip.ws.126.net/ipquery?ip=%s", ip),"GBK");
			if(StringUtil.isNotBlank(res)){
				res = res.substring(res.indexOf("localAddress") + 13, res.length());
				JSONObject jsonObject = JSONObject.parseObject(res);
				String city = jsonObject.getString("city");
				String province = jsonObject.getString("province");
				if(city.equals(province)){
					return city;
				}
				return province+city;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
 
	/**
	 * 通过IPAPI查询
	 * @param ip
	 * @return
	 */
	private static String getIpAPI(String ip){
		try{
			String res = httpRequest(String.format("http://ip-api.com/json/%s?lang=zh-CN", ip));
			if(!StringUtils.isEmpty(res)){
				JSONObject json = JSONObject.parseObject(res);
				String status = json.getString("status");
				if ("success".equals(status)) {
					String regionName = json.getString("regionName");
					String city = json.getString("city");
					if(regionName.equals(city)){
						return city;
					}
					return regionName + city;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
 
 
	/**
	 * whois.pconline.com.cn  太平洋IP为最优选择
	 * @param ip
	 * @return
	 */
	private static String getWhoisPconLineIp(String ip){
		try{
			String res = httpRequest(String.format("http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true", ip),"GBK");
			return res;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
 
 
	private static String getAddressByIp(String ip){
		String addr = getWhoisPconLineIp(ip);
		return addr;
	}
 
 
	public static boolean isInnerIP(String ipAddress){
		boolean isInnerIp = false;
		long ipNum = getIpNum(ipAddress);
		/**
		 私有IP：A类  10.0.0.0-10.255.255.255
		 B类  172.16.0.0-172.31.255.255
		 C类  192.168.0.0-192.168.255.255
		 当然，还有127这个网段是环回地址
		 **/
		long aBegin = getIpNum("10.0.0.0");
		long aEnd = getIpNum("10.255.255.255");
		long bBegin = getIpNum("172.16.0.0");
		long bEnd = getIpNum("172.31.255.255");
		long cBegin = getIpNum("192.168.0.0");
		long cEnd = getIpNum("192.168.255.255");
		isInnerIp = isInner(ipNum,aBegin,aEnd) || isInner(ipNum,bBegin,bEnd) || isInner(ipNum,cBegin,cEnd) || ipAddress.equals("127.0.0.1");
		return isInnerIp;
	}
 
	/**
	 * 判断IP是否为内网IP
	 * @param ipAddress
	 * @return
	 */
	private static long getIpNum(String ipAddress) {
		String [] ip = ipAddress.split("\\.");
		long a = Integer.parseInt(ip[0]);
		long b = Integer.parseInt(ip[1]);
		long c = Integer.parseInt(ip[2]);
		long d = Integer.parseInt(ip[3]);
 
		long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
		return ipNum;
	}
 
	private static boolean isInner(long userIp,long begin,long end){
		return (userIp>=begin) && (userIp<=end);
	}
 
 
	/**
	 * 外部调用
	 * @param ip
	 * @return
	 */
	public static String getAddressByIP(String ip){
		if(StringUtils.isEmpty(ip)) {
			return null;
		}else if(isInnerIP(ip)){
			return "内网IP";
		}else{
			return NetJsonUtils.jsonToString(getAddressByIp(ip),"addr");
		}
	}
	
	
	
	public static String httpRequest(String reqUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
 
			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
 
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
 
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return buffer.toString();
	}
 
 
	public static String httpRequest(String reqUrl,String encoding) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
 
			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return buffer.toString();
	}
	
	
 
	public static void main(String[] args) {
		String ip = "";
//		String res = getIpWs126Net("39.134.32.255");
//		String res = getIpAPI("39.134.32.255");
//		String res = getWhoisPconLineIp("47.92.102.22");
		String res = getAddressByIP(ip);
		System.out.println(res);
	}
 
}
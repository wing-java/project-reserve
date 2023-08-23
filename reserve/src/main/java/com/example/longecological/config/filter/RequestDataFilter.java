package com.example.longecological.config.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.config.WrapperedFormRequest;
import com.example.longecological.config.WrapperedRequest;
import com.example.longecological.config.WrapperedResponse;
import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.VerifyTokenService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.SpringContextUtils;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.encryption.des.AESUtil;
import com.example.longecological.utils.encryption.rsa.Base64;
import com.example.longecological.utils.encryption.rsa.RSAUtilApp;
import com.example.longecological.utils.ip.IpUtils;
import com.example.longecological.utils.string.StringUtil;


@WebFilter(urlPatterns = { "/api/*" }, filterName = "requestDataFilter")
public class RequestDataFilter implements Filter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestDataFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String ipmd5 = null;
		try{
			// 接收到请求，记录请求内容
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest req = attributes.getRequest();
			LOGGER.info("访问链接:"+req.getRequestURL().toString());
			String ipaddr = IpUtils.getIpAddr(req);
			LOGGER.info("访问IP:"+ipaddr);
			ipmd5 = new Md5Hash(ipaddr).toString().toUpperCase();
			if(SpringContextUtils.getBean("redisUtils", RedisUtils.class).exists(RedisNameConstants.ip_md5+ipmd5)) {
				writeResponse(response, JSONObject.toJSONString(R.error(CommonCodeConstant.COMMON_CODE_999961, CommonCodeConstant.COMMON_MSG_999961)));
			}else {
				this.requestDataDecrypt(request, response, chain);
			}
		}catch(Exception e){
			String error = ExceptionUtil.getExceptionAllinformation(e);
			LOGGER.info("解密异常:"+ExceptionUtil.getExceptionAllinformation(e));
			if(error.indexOf("Decryption error")>=0 || error.indexOf("String index out of range")>=0) {
				SpringContextUtils.getBean("redisUtils", RedisUtils.class).set(RedisNameConstants.ip_md5+ipmd5, new Date().getTime(), 60000l);
			}
			response.setContentType("text/html;charset=utf-8");
			WrapperedResponse wrapResponse = new WrapperedResponse((HttpServletResponse) response);
			writeResponse(response, JSONObject.toJSONString(R.error(CommonCodeConstant.COMMON_CODE_999979, CommonCodeConstant.COMMON_MSG_999979)));
		}
		
	}
	
	/**
	 * 数据解密
	 * @param request
	 * @param response
	 * @param chain
	 * @throws Exception
	 */
	private void requestDataDecrypt(ServletRequest request, ServletResponse response, FilterChain chain)
			throws Exception {
		JSONObject param = new JSONObject();
		WrapperedRequest wrapRequest = null;
		WrapperedFormRequest wrapFormRequest = null;
		String context = null;
		String contentType = ((HttpServletRequest) request).getContentType();
		String requestBody = getRequestBody((HttpServletRequest) request, contentType);
		LOGGER.info("加密密文：" + requestBody);
		String key = ((HttpServletRequest)request).getHeader("key");
		LOGGER.info("加密KEY值为："+key);
		if(requestBody == null || "".equals(requestBody)){
			writeResponse(response, JSONObject.toJSONString(R.error(CommonCodeConstant.COMMON_CODE_999977,CommonCodeConstant.COMMON_MSG_999977)));
			return;
		}else{
			if(key==null || "".equals(key)){
				//非法访问
				writeResponse(response, JSONObject.toJSONString(R.error(CommonCodeConstant.COMMON_CODE_999977,CommonCodeConstant.COMMON_MSG_999977)));
				return;
			}else{
				//解密数据
				byte[] hexStringToBytes = Base64.decode(key);
				byte[] decryptByPrivateKey = RSAUtilApp.decryptByPrivateKey(hexStringToBytes,SysSecurityKeyConstant.privateKey_app);
				LOGGER.info("解密KEY值为："+new String(decryptByPrivateKey, "utf-8"));
				context = AESUtil.aesDecrypt(requestBody, new String(decryptByPrivateKey, "utf-8"));
				LOGGER.info("解密请求参数："+ context);
			}
			//校验参数Token
			if(!StringUtil.isEmpty(context)) {
				param = JSONObject.parseObject(context);//将参数转化成JSON对象
				String token = param.getString("token");//权限token
				if(!StringUtil.isEmpty(token)) {
					//校验token是否有效
					R checkToken = SpringContextUtils.getBean("verifyTokenServiceImpl", VerifyTokenService.class).isToken(token);
					if(!TokenUtil.checkRSAdecrypt(checkToken)) {
						writeResponse(response, JSONObject.toJSONString(R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990)));
						return;
					}else {
						param.put("sys_user_id", checkToken.get("data"));
					}
				}else {
					param.put("sys_user_id", "");
				}
			}
			//校验账号冻结状态
			String sys_user_id = param.getString("sys_user_id");//用户ID
			if(!StringUtil.isEmpty(sys_user_id)) {
				Map<String, Object> userMap = SpringContextUtils.getBean("userInfoCacheServiceImpl", UserInfoCacheService.class).getUserInfoCacheById(sys_user_id);
				//账号是否冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))) {
					writeResponse(response, JSONObject.toJSONString(R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988)));
					return;
				}
			}
			//将校验后的参数写入Request
			wrapRequest = new WrapperedRequest( (HttpServletRequest) request, param.toJSONString());
			chain.doFilter(wrapRequest, response);
		}
	}
	
	private String getRequestBody(HttpServletRequest req, String contentType) {
		String json = "";
		try {
			LOGGER.info("接口请求类型:"+contentType);
			if(contentType.indexOf("application/json")>=0){
				BufferedReader reader = req.getReader();
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				json = sb.toString();
			}else if(contentType.indexOf("application/x-www-form-urlencoded")>=0){
				json = req.getParameter("data");
			}else{
				if(req.getQueryString() != null && !"".equals(req.getQueryString())) 
					json = req.getQueryString();
			}
			LOGGER.info("获取到的请求数据:"+json);
		} catch (IOException e) {
			LOGGER.info("请求体读取失败"+e.getMessage());
		}
		return json;
	}

	private void writeResponse(ServletResponse response, String responseString)
		throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(responseString);
		out.flush();
		out.close();
	}
	
	@Override
	public void init(FilterConfig filterConfig)throws ServletException{
		
	}
	
	@Override
	public void destroy() {
		
	}
}

package com.ruoyi.project.develop.common.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Constants;
import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.utils.ImgCodeUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.common.service.ImgCodeService;

import sun.misc.BASE64Encoder;


/**
 * 图形验证码
 * @author Administrator
 *
 */
@Service
public class ImgCodeServiceImpl implements ImgCodeService {
	
	
	@Autowired
	RedisUtils redisUtils;
	
	
	/**
	 * 生成图形验证码
	 */
	@Override
	public R createCode() {
		String png_base64 = null;
		ByteArrayOutputStream baos = null;
		try{
			String img_id = StringUtil.getDateTimeAndRandomForID();//图形验证码随机数ID（字母数字混合）
			ImgCodeUtil vCode = new ImgCodeUtil(110,40,4,0);//验证码对象
			String img_code = vCode.getCode();//图形验证码内容
			baos = new ByteArrayOutputStream();//io流
	        ImageIO.write(vCode.getBuffImg(), "png", baos);//写入流中
	        byte[] bytes = baos.toByteArray();//转换成字节
	        BASE64Encoder encoder = new BASE64Encoder();
	        png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
	        png_base64 = "data:image/png;base64,"+png_base64;
	        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
	        
	        String redis_img_code_key = RedisNameConstants.img_code+img_id;
	        //存入redis
	        redisUtils.set(redis_img_code_key, img_code, SysParamConstant.verification_code_seconds);
			
	        Map<String, Object> respondMap = new HashMap<>();
	        respondMap.put("img_id", img_id);
	        respondMap.put("img_io", png_base64);
	        
	        return R.ok("图形验证码生成", respondMap);
		}catch(Exception e){
			e.printStackTrace();
			return R.error(Type.ERROR, "图形验证码生成异常");
		}finally{
			if(baos!=null){
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
					return R.error(Type.ERROR, "图形验证码生成io流异常");
				}
			}
		}		
	}


	/**
	 * 校验图形验证码========>redis中处理的
	 */
	@Override
	public R checkImgCode(Map<String, Object> map) {
		try {
			//从redis中拿出图形验证码
			String redis_img_code_key = RedisNameConstants.img_code+map.get("img_id").toString();
			Object redis_img_code = redisUtils.get(redis_img_code_key);
			if(redis_img_code == null){
				return R.error(Type.WARN, "图形验证码失效，请刷新");
			}
			if(!redis_img_code.toString().toUpperCase().equals(StringUtil.getMapValue(map, "img_code").toUpperCase())){
				return R.error(Type.WARN, "图形验证错误");
			}
			redisUtils.remove(redis_img_code_key);//校验通过，删除
			return R.ok("图形验证码校验通过");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "图形验证码验证异常");
		}
	}

	
	/**
	 * 校验图像验证码=======>shiro的session处理的
	 */
	@Override
	public R checkImgCodeBack(Map<String, Object> map) {
		try {
			//校验图形验证码
			Object obj = ShiroUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);//从session获取正确的验证码
			String code = String.valueOf(obj != null ? obj : "");//处理验证码                             
			//若验证码为空或者匹配失败
			if (StringUtil.isEmpty(StringUtil.getMapValue(map, "img_code")) || !StringUtil.getMapValue(map, "img_code").equalsIgnoreCase(code)){
				return R.error(Type.WARN, "图形验证码错误");
			}
			return R.ok("图形验证码校验通过");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "图形验证码验证异常");
		}
	}
}

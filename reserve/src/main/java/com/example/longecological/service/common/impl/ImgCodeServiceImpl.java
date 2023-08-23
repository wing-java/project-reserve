package com.example.longecological.service.common.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.InterfaceTypeConstant;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.ImgCodeService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.ImgCodeUtil;
import com.example.longecological.utils.string.StringUtil;

import sun.misc.BASE64Encoder;


/**
 * 图形验证码
 * @author Administrator
 *
 */
@Service
public class ImgCodeServiceImpl implements ImgCodeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgCodeServiceImpl.class);
	
	
	@Autowired
	RedisUtils redisUtils;
	
	
	/**
	 * 生成图形验证码
	 */
	@Override
	public R createImgCode(Map<String, Object> map) {
		//校验参数类型
		if(!"createImgCode".equals(StringUtil.getMapValue(map, "interface_type"))){
			return R.error(CommonCodeConstant.COMMON_CODE_999995,CommonCodeConstant.COMMON_MSG_999995);
		}
		String png_base64 = null;
		ByteArrayOutputStream baos = null;
		try{
			if(!InterfaceTypeConstant.interface_type_createImgCode.equals(StringUtil.getMapValue(map, "interface_type"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999995,CommonCodeConstant.COMMON_MSG_999995);
			}
			String img_id = StringUtil.getDateTimeAndRandomForID();//图形验证码随机数ID（字母数字混合）
			ImgCodeUtil vCode = new ImgCodeUtil(100,30,4,0);//验证码对象
			String img_code = vCode.getCode();//图形验证码内容
			baos = new ByteArrayOutputStream();//io流
	        ImageIO.write(vCode.getBuffImg(), "png", baos);//写入流中
	        byte[] bytes = baos.toByteArray();//转换成字节
	        BASE64Encoder encoder = new BASE64Encoder();
	        png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
	        png_base64 = "data:image/png;base64,"+png_base64;
	        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
	        
	        String redis_img_code_key = RedisNameConstants.img_code+img_id;
	        System.out.println("验证码===="+img_code);
	        //存入redis
	        redisUtils.set(redis_img_code_key, img_code, SysParamConstant.verification_code_seconds);
			
	        Map<String, Object> respondMap = new HashMap<>();
	        respondMap.put("img_id", img_id);
	        respondMap.put("img_io", png_base64);
	        System.out.println("验证码信息===="+respondMap);
	        
	        return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999880,MsgImgCodeConstant.MESSAGE_MSG_999880,respondMap);
		}catch(Exception e){
			LOGGER.error("ImgCodeServiceImpl -- createImgCodeOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999884,MsgImgCodeConstant.MESSAGE_MSG_999884);
		}finally{
			if(baos!=null){
				try {
					baos.close();
				} catch (IOException e) {
					LOGGER.error("ImgCodeServiceImpl -- createImgCodeOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999883,MsgImgCodeConstant.MESSAGE_MSG_999883);
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
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999882,MsgImgCodeConstant.MESSAGE_MSG_999882);
			}
			if(!redis_img_code.toString().toUpperCase().equals(StringUtil.getMapValue(map, "img_code").toUpperCase())){
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999881,MsgImgCodeConstant.MESSAGE_MSG_999881);
			}
			redisUtils.remove(redis_img_code_key);//校验通过，删除
			return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999879,MsgImgCodeConstant.MESSAGE_MSG_999879);
		} catch (Exception e) {
			LOGGER.error("ImgCodeServiceImpl -- checkImgCode方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999878,MsgImgCodeConstant.MESSAGE_MSG_999878);
		}
	}
	
}

package com.example.longecological.service.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.constant.SysParamCodeConstants;
import com.example.longecological.constant.VerifyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.common.VerifyRecordMapper;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.message.BanyanPalmDemoUtil;
import com.example.longecological.utils.string.MapDataTool;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 短信验证码相关service
 * @author Administrator
 *
 */
@Service
@Transactional
public class VerifyRecordService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VerifyRecordService.class);
	
	@Autowired
	private SysParamService sysParamService;
	@Autowired
	private SysFunctionLockParamService sysFunctionLockParamService;
	
	@Autowired
	VerifyRecordMapper verifyRecordMapper;
	
	@Autowired
	SendEmailService sendEmailService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 发送短信验证码
	 * @param id：用户id
	 * @param busType：业务类型
	 * @param accType：接受设备类型
	 * @param account：账号
	 * @param system：系统类型
	 * @param msgTemplate：短信模板类型
	 * @return
	 */
	public R transmit(String id,String user_name,String busType,String accType,String account,String system,String msgTemplate){
		int i=0;
		try {
			//根据参数代码查询系统参数：验证码每小时发送次数限制
			int verifySendHourLimit = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_verifySendHourLimit));
			//根据参数代码查询系统参数：验证码每天发送次数限制
			int verifySendDayLimit = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_verifySendDayLimit));
			//根据参数代码查询系统参数：验证码发送时长(单位/分)<发了之后过多久再次发>
			int verifySendDuration = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_verifySendDuration));
			//根据参数代码查询系统参数：验证码失效时长(单位/分)<发了之后不验证多久失效>
			int verifyInvalidDuration = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_verifyInvalidDuration));
			
			//拿到发送账号在当前系统  最后发送的验证码
			Map<String, Object> lastSendMap = getInfoLastForSendCheck(user_name,account,accType,busType,system);
			if(lastSendMap!=null) {
				if(!contrastTime(MapDataTool.getDate("send_time", lastSendMap), verifySendDuration)) {//如果两个时间差了十分钟以上
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999895, MsgImgCodeConstant.MESSAGE_MSG_999895);
				}
			}
			//拿到1个小时的发送次数
			int hourSendNum=hourSendNum(user_name,account,system);
			if(hourSendNum>verifySendHourLimit) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999894, MsgImgCodeConstant.MESSAGE_MSG_999894);
			}
			//拿到24个小时的发送次数
			int daySendNum=daySendNum(user_name,account,system);
			if(daySendNum>verifySendDayLimit) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999893, MsgImgCodeConstant.MESSAGE_MSG_999893);
			}
			
			Map<String, Object> VerifyRecordMap=new HashMap<>();
			VerifyRecordMap.put("user_id", id);//用户ID
			VerifyRecordMap.put("system_type", system);//系统类型
			VerifyRecordMap.put("bus_type", busType);//业务类型
			VerifyRecordMap.put("acc_type", accType);//账号类型 1手机 2邮箱
			VerifyRecordMap.put("account", account);//账号
			VerifyRecordMap.put("user_name", user_name);//账号
			VerifyRecordMap.put("code", StringUtil.getCode(6));//验证码
			VerifyRecordMap.put("create_time", TimeUtil.get_format5(new Date()));//发送时间
			VerifyRecordMap.put("send_time", TimeUtil.get_format5(new Date()));//发送时间
			VerifyRecordMap.put("invalid_time", getInvalidTime(verifyInvalidDuration));//失效时间
			VerifyRecordMap.put("msg_template", msgTemplate);//短信模板
			//发送code
			R sendResult = sendCode(accType, account,VerifyRecordMap.get("code").toString(),msgTemplate);
			if(!Boolean.valueOf(sendResult.get(R.SUCCESS_TAG).toString())) {
				return sendResult;
			}
			//邮箱类型
			if(VerifyConstant.EmailAccType.equals(accType)) {
				VerifyRecordMap.put("msg_template", sendResult.get("data"));//发送邮箱
			}
			i=verifyRecordMapper.addVerifyRecord(VerifyRecordMap);
			if(i != 1){
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999892, MsgImgCodeConstant.MESSAGE_MSG_999892);
			}
			return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999897,MsgImgCodeConstant.MESSAGE_MSG_999897);
		} catch (Exception e) {
			LOGGER.error("VerifyRecordService -- transmit方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999891, MsgImgCodeConstant.MESSAGE_MSG_999891);
		}
	}
	
	
	/**
	 * 对比验证码
	 * @param ajaxJson 
	 * @param user：用户
	 * @param busType：业务类型
	 * @param accType：获取类型（1：手机:2：邮箱）
	 * @param account：获取账号
	 * @return
	 */
	public R compare(String id,String user_name,String busType,String accType,String account,String code,String system){
		//万能验证码功能是否开放
//		String allSmsCodeOpenLock = sysFunctionLockParamService.getFunctionLockParamByCode(SysFunctionLockParamConstants.sys_function_lock_param_code_allSmsCodeOpenLock);
//		if(TypeStatusConstant.sys_function_lock_param_lock_1.equals(allSmsCodeOpenLock)) {
			if("999999".equals(code)) {
				return R.ok(MsgImgCodeConstant.MESSAGE_CODE_999897,MsgImgCodeConstant.MESSAGE_MSG_999897);
			}
//		}

		int i=0;
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("user_id", id);//用户ID
		condition.put("system_type", system);//系统类型
		condition.put("bus_type", busType);//业务类型
		condition.put("acc_type", accType);//账号类型 1手机 2邮箱
		condition.put("account", account);//账号
		condition.put("user_name", user_name);//账号
		try {
			//拿到发送账号在当前系统  最后发送的验证码
			Map<String, Object> lastSendMap = verifyRecordMapper.getInfolast(condition);
			//请发送验证码
			if(lastSendMap==null) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999890, MsgImgCodeConstant.MESSAGE_MSG_999890);
			}
			//验证码不正确
			if(!code.equals(lastSendMap.get("code").toString())) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999889, MsgImgCodeConstant.MESSAGE_MSG_999889);
			}
			//如果已经被验证 
			if(VerifyConstant.verifyStatus_1.equals(lastSendMap.get("status").toString())) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999888, MsgImgCodeConstant.MESSAGE_MSG_999888);
			}
			//验证码已经失效
			if(contrastTime(MapDataTool.getDate("invalid_time", lastSendMap),0)) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999887, MsgImgCodeConstant.MESSAGE_MSG_999887);
			}
			//如果用户输入的验证码是一样的 改变该验证码的状态
			lastSendMap.put("status", VerifyConstant.verifyStatus_1);//验证码状态：已被验证
			lastSendMap.put("verify_time", TimeUtil.get_format5(new Date()));//验证时间
			i=verifyRecordMapper.update(lastSendMap);//标记为已被验证
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999886, MsgImgCodeConstant.MESSAGE_MSG_999886);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("VerifyRecordService -- compare方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999885, MsgImgCodeConstant.MESSAGE_MSG_999885);
		}
	}

	
	/**
	 * <用于发送验证>
	 * 拿到发送账号在当前系统  最后发送的验证
	 * @param account
	 * @param system2 
	 * @return
	 */
	public Map<String, Object> getInfoLastForSendCheck(String user_name,String account,String accType,String busType,String system) {
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("user_name", user_name);
		condition.put("system_type", system);
		condition.put("account", account);
		condition.put("bus_type", busType);
		condition.put("acc_type", accType);
		return verifyRecordMapper.getInfolast(condition);
	}
	
	
	/**
	 * 验证某个时间是否与当前时间差了某分钟
	 * 比如 验证2018-06-15 16:18:45与当前时间是否差了十分钟
	 */
	public static boolean contrastTime(Date contrastTime,int minute) {
		Calendar nowCal = Calendar.getInstance();
		long now=nowCal.getTimeInMillis();//现在时间
		nowCal.setTime(contrastTime);
		long lastly=nowCal.getTimeInMillis();//最后一条验证的时间
		if((now-lastly)>(minute*60*1000)) {//如果两个时间差了十分钟以上
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 拿到1个小时的发送次数
	 * @param account
	 * @return
	 */
	public int hourSendNum(String user_name,String account,String system){
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("user_name", user_name);
		condition.put("system_type", system);
		condition.put("account", account);
		condition.put("nowTime", sdf.format(new Date()));
		condition.put("hour", 1);
		return verifyRecordMapper.getPeriodCount(condition);
	}
	
	
	/**
	 * 拿到24个小时的发送次数
	 * @param account
	 * @return
	 */
	public int daySendNum(String user_name,String account,String system){
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("user_name", user_name);
		condition.put("system_type", system);
		condition.put("account", account);
		condition.put("nowTime", sdf.format(new Date()));
		condition.put("hour", 24);
		return verifyRecordMapper.getPeriodCount(condition);
	}
	
	
	/**
	 * 得到失效时间
	 * @param verifyInvalidDuration 
	 * @return
	 */
	private static String getInvalidTime(int verifyInvalidDuration) {
		Calendar nowTime =Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, verifyInvalidDuration);
		return TimeUtil.get_format5(nowTime.getTime());
	}
	
	
	/**
	 * 发送验证码
	 * @param accType
	 * @param account
	 * @param code
	 * @param ajaxJson 
	 */
	private R sendCode(String accType,String account,String code,String msgTemplate) {
		//短信验证
		if (VerifyConstant.MobileAccType.equals(accType)) {
			//短信验证
			return BanyanPalmDemoUtil.sendMesg(account, code);
		} else {
			//邮箱验证码
			return sendEmailService.sendEmail(account, code);
		}
	}
	

}

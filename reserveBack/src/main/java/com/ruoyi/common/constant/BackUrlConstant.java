package com.ruoyi.common.constant;

import java.util.HashMap;

public class BackUrlConstant {
	
	/**
	 * 后台URL跳转
	 */
	public static final HashMap<String, String> BackUrlMap = new HashMap<String, String>();  
	static {  
		//重置后台账户密码
		BackUrlMap.put(VerifyConstant.AuthVerfiy_ResetUserPwd, "system/user/resetPwd");
		
		//修改系统参数
		BackUrlMap.put(VerifyConstant.AuthVerfiy_EditSysParam, "develop/sysParam/edit");
		
		//修改系统开关参数
		BackUrlMap.put(VerifyConstant.AuthVerfiy_EditSysFunctionLockParam, "develop/sysFunctionLockParam/edit");
		
		//冻结解冻用户信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SysFreezeUserInfo, "develop/userInfo/sysFreeze");
		//修改用户信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_EditUserInfo, "develop/userInfo/edit");
		
		//公司拨款页面
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AddUserSysReward, "develop/userSysReward/add");
		//公司扣款页面
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AddUserSysRecycle, "develop/userSysRecycle/add");
		
		//批量删除恢复商品
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SysDelGoodsInfo, "develop/goodsInfo/sysDel");
		//批量上下架商品
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SysReleaseGoodsInfo, "develop/goodsInfo/sysRelease");

		
		//批量审核余额充值记录
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SysAuditUserRechargeOffline, "develop/userRechargeOffline/sysAudit");
		//批量审核捐赠记录
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SysAuditUserDonateOffline, "develop/userDonateOffline/sysAudit");
		//批量审核用户余额提现记录审核
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SysAuditUserCash, "develop/userCash/sysAudit");
		//删除产品包
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SysDelSysProduct, "develop/sysProduct/sysDel");
		//上下架产品包
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SysReleaseSysProduct, "develop/sysProduct/sysRelease");
		
		//平移用户
		BackUrlMap.put(VerifyConstant.AuthVerfiy_TransUser, "redirect:/develop/userInfo/toTransUser");
		//新增业绩
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AddPerformance, "redirect:/develop/userInfo/toAddPerformance");
		//分享参数
		BackUrlMap.put(VerifyConstant.AuthVerfiy_EditSysBenefitParamAlgebra, "develop/sysBenefitParamAlgebra/edit");
		//阶段参数
		BackUrlMap.put(VerifyConstant.AuthVerfiy_EditSysBenefitParamDay, "develop/sysBenefitParamDay/edit");
		//年度参数
		BackUrlMap.put(VerifyConstant.AuthVerfiy_EditSysBenefitParamYear, "develop/sysBenefitParamYear/edit");
		
	}
	
}

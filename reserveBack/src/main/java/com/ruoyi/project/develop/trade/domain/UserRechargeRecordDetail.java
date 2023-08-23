package com.ruoyi.project.develop.trade.domain;

public class UserRechargeRecordDetail {
	
	/**
	 * 余额充值记录对象
	 */
	public UserRechargeOnline userRechargeRecord;
	
	/**
	 * 余额充值支付对象
	 */
	public Object userRechargeRecordPayInfo;

	public UserRechargeOnline getUserRechargeRecord() {
		return userRechargeRecord;
	}

	public void setUserRechargeRecord(UserRechargeOnline userRechargeRecord) {
		this.userRechargeRecord = userRechargeRecord;
	}

	public Object getUserRechargeRecordPayInfo() {
		return userRechargeRecordPayInfo;
	}

	public void setUserRechargeRecordPayInfo(Object userRechargeRecordPayInfo) {
		this.userRechargeRecordPayInfo = userRechargeRecordPayInfo;
	}
	
	
}

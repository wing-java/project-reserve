package com.example.longecological.entity.coupons;

public class UserCoupons {
	
	//用户优惠券编号
	private String user_coupons_id;
	//用户编号
	private String user_id;
	//店铺编号
	private String supplier_id;
	//优惠券编号
	private String coupons_id;
	//优惠券金额
	private String amount;
	//使用条件(订单满多少金额）
	private String use_amount;
	//开始时间
	private String start_date;
	//结束时间
	private String end_date;
	//使用状态（0：未使用  1：已使用）
	private String use_status;
	public String getCoupons_id() {
		return coupons_id;
	}
	public void setCoupons_id(String coupons_id) {
		this.coupons_id = coupons_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUse_amount() {
		return use_amount;
	}
	public void setUse_amount(String use_amount) {
		this.use_amount = use_amount;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getUse_status() {
		return use_status;
	}
	public void setUse_status(String use_status) {
		this.use_status = use_status;
	}
	public String getUser_coupons_id() {
		return user_coupons_id;
	}
	public void setUser_coupons_id(String user_coupons_id) {
		this.user_coupons_id = user_coupons_id;
	}
	@Override
	public String toString() {
		return "UserCoupons [user_coupons_id=" + user_coupons_id + ", user_id=" + user_id + ", supplier_id="
				+ supplier_id + ", coupons_id=" + coupons_id + ", amount=" + amount + ", use_amount=" + use_amount
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", use_status=" + use_status + "]";
	}
}

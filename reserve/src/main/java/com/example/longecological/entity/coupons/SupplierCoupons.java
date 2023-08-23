package com.example.longecological.entity.coupons;

public class SupplierCoupons {
	
	//优惠券编号
	private String coupons_id;
	//店铺编号
	private String supplier_id;
	//优惠券金额
	private String amount;
	//使用条件(订单满多少金额）
	private String use_amount;
	//优惠券数量
	private String num;
	//已领取数量
	private String recevie_num;
	//开始时间
	private String start_date;
	//结束时间
	private String end_date;
	//删除状态（0：未删除  1：已删除）
	private String del_status;
	public String getCoupons_id() {
		return coupons_id;
	}
	public void setCoupons_id(String coupons_id) {
		this.coupons_id = coupons_id;
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getRecevie_num() {
		return recevie_num;
	}
	public void setRecevie_num(String recevie_num) {
		this.recevie_num = recevie_num;
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
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	@Override
	public String toString() {
		return "SupplierCoupons [coupons_id=" + coupons_id + ", supplier_id=" + supplier_id + ", amount=" + amount
				+ ", use_amount=" + use_amount + ", num=" + num + ", recevie_num=" + recevie_num + ", start_date="
				+ start_date + ", end_date=" + end_date + ", del_status=" + del_status + "]";
	}
	
}

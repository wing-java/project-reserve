package com.example.longecological.entity.coupons;

import java.util.List;

public class UserSupplier {

	//店铺编号
	private String supplier_id;
	//店铺名称
	private String supplier_name;
	//店铺logo
	private String supplier_logo;
	//查询类型
	private String type;
	//查询类型
	private String sys_date;

	//用户优惠券列表
	List<UserCoupons> userCouponsList;

	public String getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_logo() {
		return supplier_logo;
	}

	public void setSupplier_logo(String supplier_logo) {
		this.supplier_logo = supplier_logo;
	}

	public List<UserCoupons> getUserCouponsList() {
		return userCouponsList;
	}

	public void setUserCouponsList(List<UserCoupons> userCouponsList) {
		this.userCouponsList = userCouponsList;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSys_date() {
		return sys_date;
	}

	public void setSys_date(String sys_date) {
		this.sys_date = sys_date;
	}

	@Override
	public String toString() {
		return "UserSupplier [supplier_id=" + supplier_id + ", supplier_name=" + supplier_name + ", supplier_logo="
				+ supplier_logo + ", type=" + type + ", sys_date=" + sys_date + ", userCouponsList=" + userCouponsList
				+ "]";
	}

}

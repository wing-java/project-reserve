package com.example.longecological.entity.coupons;

import java.util.List;

public class ShopSupplier {

	//店铺编号
	private String supplier_id;
	//店铺名称
	private String supplier_name;
	//店铺logo
	private String supplier_logo;
	
	//用户优惠券列表
	List<SupplierCoupons> supplierCouponsList;

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

	public List<SupplierCoupons> getSupplierCouponsList() {
		return supplierCouponsList;
	}

	public void setSupplierCouponsList(List<SupplierCoupons> supplierCouponsList) {
		this.supplierCouponsList = supplierCouponsList;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	@Override
	public String toString() {
		return "ShopSupplier [supplier_id=" + supplier_id + ", supplier_name=" + supplier_name + ", supplier_logo="
				+ supplier_logo + ", supplierCouponsList=" + supplierCouponsList + "]";
	}

}

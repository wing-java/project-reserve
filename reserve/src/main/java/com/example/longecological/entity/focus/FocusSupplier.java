package com.example.longecological.entity.focus;

import java.util.List;

public class FocusSupplier {
	
	//关注编号
	private String focus_id;
	//用户编号
	private String user_id;
	//商家编号
	private String supplier_id;
	//商家名称
	private String supplier_name;
	//商家logo
	private String supplier_logo;
	//商品列表
	List<SupplierGoods> supplierGoodsList;
	public String getFocus_id() {
		return focus_id;
	}
	public void setFocus_id(String focus_id) {
		this.focus_id = focus_id;
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
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getSupplier_logo() {
		return supplier_logo;
	}
	public void setSupplier_logo(String supplier_logo) {
		this.supplier_logo = supplier_logo;
	}
	public List<SupplierGoods> getSupplierGoodsList() {
		return supplierGoodsList;
	}
	public void setSupplierGoodsList(List<SupplierGoods> supplierGoodsList) {
		this.supplierGoodsList = supplierGoodsList;
	}
	@Override
	public String toString() {
		return "FocusSupplier [focus_id=" + focus_id + ", user_id=" + user_id + ", supplier_id=" + supplier_id
				+ ", supplier_name=" + supplier_name + ", supplier_logo=" + supplier_logo + ", supplierGoodsList="
				+ supplierGoodsList + "]";
	}
	
}	

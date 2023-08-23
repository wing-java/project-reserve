package com.example.longecological.entity.cart;

import java.util.List;

public class SupplierCart {
	
	//商家编号
	private String supplier_id;
	//商家名称
	private String supplier_name;
	//商家logo
	private String supplier_logo;
	//用户编号
	private String user_id;
	//商品列表
	List<CartGoods> cartGoodsList;
	
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
	public List<CartGoods> getCartGoodsList() {
		return cartGoodsList;
	}
	public void setCartGoodsList(List<CartGoods> cartGoodsList) {
		this.cartGoodsList = cartGoodsList;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "SupplierCart [supplier_id=" + supplier_id + ", supplier_name=" + supplier_name + ", supplier_logo="
				+ supplier_logo + ", user_id=" + user_id + ", cartGoodsList=" + cartGoodsList + "]";
	}
}	

package com.example.longecological.entity.focus;

public class SupplierGoods {
	
	//商品编号
	private String goods_id;
	//商家编号
	private String supplier_id;
	//商品名称
	private String goods_name;
	//商品展示图
	private String goods_show;
	public String getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_show() {
		return goods_show;
	}
	public void setGoods_show(String goods_show) {
		this.goods_show = goods_show;
	}
	
	@Override
	public String toString() {
		return "SupplierGoods [supplier_id=" + supplier_id + ", goods_id=" + goods_id + ", goods_name=" + goods_name
				+ ", goods_show=" + goods_show + "]";
	}
	
}

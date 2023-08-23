package com.example.longecological.entity.category;

import java.io.Serializable;


/**
 * 足迹
 * @author Administrator
 *
 */
public class SecondCategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//分类名称
	private String category_name;
	//父级编号
	private String parent_id;
	//排序
	private String order_num;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	@Override
	public String toString() {
		return "SecondCategory [id=" + id + ", category_name=" + category_name + ", parent_id=" + parent_id
				+ ", order_num=" + order_num + "]";
	}
}
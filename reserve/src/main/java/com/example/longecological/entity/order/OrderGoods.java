package com.example.longecological.entity.order;

import java.io.Serializable;

public class OrderGoods implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 364428960558252959L;
	private String id;//编号
	private String order_id;//订单号
	private String character_type;//规格类型
	private String goods_id;//商品编号
	private String goods_detail_id;//商品详情编号
	private String goods_name;//商品名称
	private String goods_num;//商品数量
	private String goods_detail_show;//商品展示图
	private String character_value;//属性值
	private String express_cash;//快递费
	private String underline_cash;//划线价
	private String cash;//交易价
	private String gift_info;//相关赠品
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCharacter_type() {
		return character_type;
	}
	public void setCharacter_type(String character_type) {
		this.character_type = character_type;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_detail_id() {
		return goods_detail_id;
	}
	public void setGoods_detail_id(String goods_detail_id) {
		this.goods_detail_id = goods_detail_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	public String getGoods_detail_show() {
		return goods_detail_show;
	}
	public void setGoods_detail_show(String goods_detail_show) {
		this.goods_detail_show = goods_detail_show;
	}
	public String getCharacter_value() {
		return character_value;
	}
	public void setCharacter_value(String character_value) {
		this.character_value = character_value;
	}
	public String getExpress_cash() {
		return express_cash;
	}
	public void setExpress_cash(String express_cash) {
		this.express_cash = express_cash;
	}
	public String getUnderline_cash() {
		return underline_cash;
	}
	public void setUnderline_cash(String underline_cash) {
		this.underline_cash = underline_cash;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	public String getGift_info() {
		return gift_info;
	}
	public void setGift_info(String gift_info) {
		this.gift_info = gift_info;
	}
	@Override
	public String toString() {
		return "OrderGoods [id=" + id + ", order_id=" + order_id + ", character_type=" + character_type + ", goods_id="
				+ goods_id + ", goods_detail_id=" + goods_detail_id + ", goods_name=" + goods_name + ", goods_num="
				+ goods_num + ", goods_detail_show=" + goods_detail_show + ", character_value=" + character_value
				+ ", express_cash=" + express_cash + ", underline_cash=" + underline_cash + ", cash=" + cash
				+ ", gift_info=" + gift_info + "]";
	}
	
}

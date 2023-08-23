package com.example.longecological.entity.order;

import java.io.Serializable;
import java.util.List;

public class OrderTemp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6157207862905111705L;
	private String id;//编号
	private String order_id;//订单号
	private String user_id;//用户编号
	private String supplier_id;//供应商编号
	private String supplier_name;//供应商名称
	private String supplier_logo;//供应商logo
	private String goods_num;//商品数量
	private String cash_num;//订单金额
	private String express_cash;//快递金额
	private String total_cash_num;//总金额
	private String cre_date;//创建日期
	private String cre_time;//创建时间
	private String sys_date;//系统日期
	private String oper_type;
	private List<OrderGoodsTemp> orderGoodsList;//订单商品
	private List<OrderCoupons> orderCouponsList;//订单优惠券
	
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
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	public String getCash_num() {
		return cash_num;
	}
	public void setCash_num(String cash_num) {
		this.cash_num = cash_num;
	}
	public String getExpress_cash() {
		return express_cash;
	}
	public void setExpress_cash(String express_cash) {
		this.express_cash = express_cash;
	}
	public String getCre_date() {
		return cre_date;
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	public String getCre_time() {
		return cre_time;
	}
	public void setCre_time(String cre_time) {
		this.cre_time = cre_time;
	}
	public String getTotal_cash_num() {
		return total_cash_num;
	}
	public void setTotal_cash_num(String total_cash_num) {
		this.total_cash_num = total_cash_num;
	}
	public String getSys_date() {
		return sys_date;
	}
	public void setSys_date(String sys_date) {
		this.sys_date = sys_date;
	}
	public List<OrderGoodsTemp> getOrderGoodsList() {
		return orderGoodsList;
	}
	public void setOrderGoodsList(List<OrderGoodsTemp> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}
	public List<OrderCoupons> getOrderCouponsList() {
		return orderCouponsList;
	}
	public void setOrderCouponsList(List<OrderCoupons> orderCouponsList) {
		this.orderCouponsList = orderCouponsList;
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
	public String getOper_type() {
		return oper_type;
	}
	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}
	@Override
	public String toString() {
		return "OrderTemp [id=" + id + ", order_id=" + order_id + ", user_id=" + user_id + ", supplier_id="
				+ supplier_id + ", supplier_name=" + supplier_name + ", supplier_logo=" + supplier_logo + ", goods_num="
				+ goods_num + ", cash_num=" + cash_num + ", express_cash=" + express_cash + ", total_cash_num="
				+ total_cash_num + ", cre_date=" + cre_date + ", cre_time=" + cre_time + ", sys_date=" + sys_date
				+ ", oper_type=" + oper_type + ", orderGoodsList=" + orderGoodsList + ", orderCouponsList="
				+ orderCouponsList + "]";
	}
}
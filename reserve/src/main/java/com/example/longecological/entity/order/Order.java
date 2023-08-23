package com.example.longecological.entity.order;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{

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
	private String total_cash_num;//订单总金额
	private String real_cash_num;//真实支付信息
	
	private String name;//收货人姓名
	private String tel;//收货人电话
	private String address;//收货人地址
	private String note;//留言备注
	
	private String out_trade_no;//商户订单号
	private String shipper_id;//物流编号
	
	private String user_coupons_id;//用户优惠券编号
	private String coupons_amount;//优惠券金额
	private String order_pay_type;//订单支付方式
	private String pay_num;//支付数量（支付宝、微信）
	private String pay_balance_num;//支付余额
	
	private String status;//状态
	private String refund_status;//退款状态
	private String evaluate_status;//评价状态
	private String del;//删除状态
	
	private String remark;//操作备注
	private String cre_date;
	private String cre_time;
	private String up_date;
	private String up_time;
	private String pay_date;
	private String pay_time;
	private String send_date;
	private String send_time;
	private String receive_date;
	private String receive_time;
	private String over_time;
	
	private List<OrderGoods> orderGoodsList;
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
	public List<OrderGoods> getOrderGoodsList() {
		return orderGoodsList;
	}
	public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCash_num() {
		return cash_num;
	}
	public void setCash_num(String cash_num) {
		this.cash_num = cash_num;
	}
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
	public String getUp_time() {
		return up_time;
	}
	public void setUp_time(String up_time) {
		this.up_time = up_time;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getExpress_cash() {
		return express_cash;
	}
	public void setExpress_cash(String express_cash) {
		this.express_cash = express_cash;
	}
	public String getTotal_cash_num() {
		return total_cash_num;
	}
	public void setTotal_cash_num(String total_cash_num) {
		this.total_cash_num = total_cash_num;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getShipper_id() {
		return shipper_id;
	}
	public void setShipper_id(String shipper_id) {
		this.shipper_id = shipper_id;
	}
	public String getOrder_pay_type() {
		return order_pay_type;
	}
	public void setOrder_pay_type(String order_pay_type) {
		this.order_pay_type = order_pay_type;
	}
	public String getPay_num() {
		return pay_num;
	}
	public void setPay_num(String pay_num) {
		this.pay_num = pay_num;
	}
	public String getPay_balance_num() {
		return pay_balance_num;
	}
	public void setPay_balance_num(String pay_balance_num) {
		this.pay_balance_num = pay_balance_num;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public String getEvaluate_status() {
		return evaluate_status;
	}
	public void setEvaluate_status(String evaluate_status) {
		this.evaluate_status = evaluate_status;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public String getReceive_date() {
		return receive_date;
	}
	public void setReceive_date(String receive_date) {
		this.receive_date = receive_date;
	}
	public String getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}
	public String getOver_time() {
		return over_time;
	}
	public void setOver_time(String over_time) {
		this.over_time = over_time;
	}
	public String getReal_cash_num() {
		return real_cash_num;
	}
	public void setReal_cash_num(String real_cash_num) {
		this.real_cash_num = real_cash_num;
	}
	public String getUser_coupons_id() {
		return user_coupons_id;
	}
	public void setUser_coupons_id(String user_coupons_id) {
		this.user_coupons_id = user_coupons_id;
	}
	public String getCoupons_amount() {
		return coupons_amount;
	}
	public void setCoupons_amount(String coupons_amount) {
		this.coupons_amount = coupons_amount;
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
	@Override
	public String toString() {
		return "Order [id=" + id + ", order_id=" + order_id + ", user_id=" + user_id + ", supplier_id=" + supplier_id
				+ ", supplier_name=" + supplier_name + ", supplier_logo=" + supplier_logo + ", goods_num=" + goods_num
				+ ", cash_num=" + cash_num + ", express_cash=" + express_cash + ", total_cash_num=" + total_cash_num
				+ ", real_cash_num=" + real_cash_num + ", name=" + name + ", tel=" + tel + ", address=" + address
				+ ", note=" + note + ", out_trade_no=" + out_trade_no + ", shipper_id=" + shipper_id
				+ ", user_coupons_id=" + user_coupons_id + ", coupons_amount=" + coupons_amount + ", order_pay_type="
				+ order_pay_type + ", pay_num=" + pay_num + ", pay_balance_num=" + pay_balance_num + ", status="
				+ status + ", refund_status=" + refund_status + ", evaluate_status=" + evaluate_status + ", del=" + del
				+ ", remark=" + remark + ", cre_date=" + cre_date + ", cre_time=" + cre_time + ", up_date=" + up_date
				+ ", up_time=" + up_time + ", pay_date=" + pay_date + ", pay_time=" + pay_time + ", send_date="
				+ send_date + ", send_time=" + send_time + ", receive_date=" + receive_date + ", receive_time="
				+ receive_time + ", over_time=" + over_time + ", orderGoodsList=" + orderGoodsList + "]";
	}
}
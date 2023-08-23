package com.ruoyi.project.develop.product.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_sys_deposit_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserProductOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 参数编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String user_id;
	/** 账号 */
	@Excel(name = "账号")
	private String sys_user_account;
	/** UID */
	@Excel(name = "UID")
	private String uid;
	/** 用户注册类型 */
	@Excel(name = "用户注册类型", readConverterExp = "1=手机,2=邮箱")
	private String register_type;
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	/** 邮箱 */
	@Excel(name = "邮箱")
	private String user_email;
	/** 昵称 */
	@Excel(name = "昵称 ")
	private String nick_name;
	
	/** 订单号 */
	@Excel(name = "订单号")
	private String order_no;
	/** 数量 */
	@Excel(name = "数量")
	private String goods_num;
	/** 商品总价 */
	@Excel(name = "商品总价")
	private String cash_num;
	/** 股权 */
	@Excel(name = "股权")
	private String sharestock_num;
	/** 股权编号 */
	@Excel(name = "股权编号")
	private String sharestock_no;
	
	/** 年度收益日期 */
	@Excel(name = "年度收益日期")
	private String init_date1;
	/** 阶段收益日期 */
	@Excel(name = "阶段收益日期")
	private String init_date2;
	
	/** 商品名称 */
	@Excel(name = "商品名称")
	private String goods_name;
	/** 商品编号 */
	@Excel(name = "商品编号")
	private String product_id;
	/** 商品单价 */
	@Excel(name = "商品单价")
	private String goods_price;

	/** 支付方式 */
	@Excel(name = "支付方式", readConverterExp = "01=支付宝,02=微信,03=余额")
	private String order_pay_type;
	/** 支付余额 */
	@Excel(name = "支付余额")
	private String pay_num;
	/** 支付余额 */
	@Excel(name = "支付余额")
	private String pay_balance_num;
	
	/** 状态 */
	@Excel(name = "状态", readConverterExp = "00=待付款,09=交易完成")
	private String status;
	/** 删除状态 */
	@Excel(name = "删除状态", readConverterExp = "0=未删除,1=已删除")
	private String del;
	/** 结算状态 */
	@Excel(name = "结算状态", readConverterExp = "00=待结算,02=结算中,09=结算成功")
	private String settle_status;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	/** 支付时间 */
	@Excel(name = "支付时间")
	private String pay_date;
	/** 更新者 */
	@Excel(name = "更新者")
	private String update_by;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSys_user_account() {
		return sys_user_account;
	}
	public void setSys_user_account(String sys_user_account) {
		this.sys_user_account = sys_user_account;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRegister_type() {
		return register_type;
	}
	public void setRegister_type(String register_type) {
		this.register_type = register_type;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
	public String getSharestock_num() {
		return sharestock_num;
	}
	public void setSharestock_num(String sharestock_num) {
		this.sharestock_num = sharestock_num;
	}
	public String getSharestock_no() {
		return sharestock_no;
	}
	public void setSharestock_no(String sharestock_no) {
		this.sharestock_no = sharestock_no;
	}
	public String getInit_date1() {
		return init_date1;
	}
	public void setInit_date1(String init_date1) {
		this.init_date1 = init_date1;
	}
	public String getInit_date2() {
		return init_date2;
	}
	public void setInit_date2(String init_date2) {
		this.init_date2 = init_date2;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getSettle_status() {
		return settle_status;
	}
	public void setSettle_status(String settle_status) {
		this.settle_status = settle_status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCre_date() {
		return cre_date;
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	@Override
	public String toString() {
		return "UserProductOrder [id=" + id + ", user_id=" + user_id + ", sys_user_account=" + sys_user_account
				+ ", uid=" + uid + ", register_type=" + register_type + ", user_tel=" + user_tel + ", user_email="
				+ user_email + ", nick_name=" + nick_name + ", order_no=" + order_no + ", goods_num=" + goods_num
				+ ", cash_num=" + cash_num + ", sharestock_num=" + sharestock_num + ", sharestock_no=" + sharestock_no
				+ ", init_date1=" + init_date1 + ", init_date2=" + init_date2 + ", goods_name=" + goods_name
				+ ", product_id=" + product_id + ", goods_price=" + goods_price + ", order_pay_type=" + order_pay_type
				+ ", pay_num=" + pay_num + ", pay_balance_num=" + pay_balance_num + ", status=" + status + ", del="
				+ del + ", settle_status=" + settle_status + ", remark=" + remark + ", cre_date=" + cre_date
				+ ", up_date=" + up_date + ", pay_date=" + pay_date + ", update_by=" + update_by + "]";
	}
	
}

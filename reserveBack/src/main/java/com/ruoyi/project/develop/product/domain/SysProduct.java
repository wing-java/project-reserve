package com.ruoyi.project.develop.product.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_sys_deposit_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SysProduct extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 参数编号 */
	@Excel(name = "编号")
	private Integer id;
	/** 商品名称 */
	@Excel(name = "商品名称")
	private String goods_name;
	/** 商品展示图 */
	//@Excel(name = "商品展示图")
	private String goods_show;
	/** 商品导航图 */
	//@Excel(name = "商品导航图")
	private String goods_navigation;
	/** 商品描述图 */
	//@Excel(name = "商品描述图")
	private String goods_describe;
	/** 库存数量 */
	@Excel(name = "库存数量")
	private String goods_stock_num;
	/** 销量 */
	@Excel(name = "销量")
	private String goods_sales_num;
	/** 产品标签 */
	@Excel(name = "产品标签")
	private String label;
	/** 商品金额 */
	@Excel(name = "商品金额")
	private String goods_price;
	/** 快递费 */
	@Excel(name = "股权数量")
	private String sharestock_num;
	/** 商品状态 */
	@Excel(name = "商品状态", readConverterExp = "00=待上架,08=已上架,09=已下架")
	private String goods_status;
	/** 删除状态 */
	@Excel(name = "删除状态", readConverterExp = "0=未删除,1=已删除")
	private String del_status;
	/** 排序字段 */
	@Excel(name = "排序字段")
	private String order_num;
	
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	/** 创建者 */
	@Excel(name = "创建者")
	private String create_by;
	/** 更新者 */
	@Excel(name = "更新者")
	private String update_by;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getGoods_navigation() {
		return goods_navigation;
	}
	public void setGoods_navigation(String goods_navigation) {
		this.goods_navigation = goods_navigation;
	}
	public String getGoods_describe() {
		return goods_describe;
	}
	public void setGoods_describe(String goods_describe) {
		this.goods_describe = goods_describe;
	}
	public String getGoods_stock_num() {
		return goods_stock_num;
	}
	public void setGoods_stock_num(String goods_stock_num) {
		this.goods_stock_num = goods_stock_num;
	}
	public String getGoods_sales_num() {
		return goods_sales_num;
	}
	public void setGoods_sales_num(String goods_sales_num) {
		this.goods_sales_num = goods_sales_num;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getSharestock_num() {
		return sharestock_num;
	}
	public void setSharestock_num(String sharestock_num) {
		this.sharestock_num = sharestock_num;
	}
	public String getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
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
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	@Override
	public String toString() {
		return "SysProduct [id=" + id + ", goods_name=" + goods_name + ", goods_show=" + goods_show
				+ ", goods_navigation=" + goods_navigation + ", goods_describe=" + goods_describe + ", goods_stock_num="
				+ goods_stock_num + ", goods_sales_num=" + goods_sales_num + ", label=" + label + ", goods_price="
				+ goods_price + ", sharestock_num=" + sharestock_num + ", goods_status=" + goods_status
				+ ", del_status=" + del_status + ", order_num=" + order_num + ", remark=" + remark + ", cre_date="
				+ cre_date + ", up_date=" + up_date + ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}
	
}

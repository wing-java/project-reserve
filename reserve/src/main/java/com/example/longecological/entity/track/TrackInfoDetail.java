package com.example.longecological.entity.track;

import java.io.Serializable;

/**
 * 足迹详情
 * @author Administrator
 *
 */
public class TrackInfoDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//足迹编号
	private String track_id; 
	//商品编号
	private String goods_id; 
	//商品名称
	private String goods_name;
	//商品展示图
	private String goods_show;
	//最小现金
	private String cash_min;
	//快递金额
	private String express_cash;
	//市场价
	private String underline_cash;
	public String getTrack_id() {
		return track_id;
	}
	public void setTrack_id(String track_id) {
		this.track_id = track_id;
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
	public String getCash_min() {
		return cash_min;
	}
	public void setCash_min(String cash_min) {
		this.cash_min = cash_min;
	}
	public String getExpress_cash() {
		return express_cash;
	}
	public void setExpress_cash(String express_cash) {
		this.express_cash = express_cash;
	}
}
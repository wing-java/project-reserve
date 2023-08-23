package com.example.longecological.entity.track;

import java.io.Serializable;
import java.util.List;


/**
 * 足迹
 * @author Administrator
 *
 */
public class TrackInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//用户编号
	private String user_id;
	//足迹日期
	private String track_day;
	//足迹日期
	private String track_day_desc;
	//足迹列表
	private List<TrackInfoDetail> trackInfoDetailList;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTrack_day() {
		return track_day;
	}
	public void setTrack_day(String track_day) {
		this.track_day = track_day;
	}
	public List<TrackInfoDetail> getTrackInfoDetailList() {
		return trackInfoDetailList;
	}
	public void setTrackInfoDetailList(List<TrackInfoDetail> trackInfoDetailList) {
		this.trackInfoDetailList = trackInfoDetailList;
	}
	public String getTrack_day_desc() {
		return track_day_desc;
	}
	public void setTrack_day_desc(String track_day_desc) {
		this.track_day_desc = track_day_desc;
	}
	
}
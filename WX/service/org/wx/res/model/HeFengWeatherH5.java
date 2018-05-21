package org.wx.res.model;

import java.sql.Timestamp;
import java.util.Map;

import org.wx.res.util.WxUtil;

public class HeFengWeatherH5 {

	private String id;
	private String cityid;
	private String cityname;
	private String data;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private boolean isHistory;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	public boolean isHistory() {
		return isHistory;
	}
	public void setHistory(boolean isHistory) {
		this.isHistory = isHistory;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public static HeFengWeatherH5 createModel(Map map){
		HeFengWeatherH5 m = new HeFengWeatherH5();
		m.setId(WxUtil.obj2Str(map.get("id")));
		m.setCityid(WxUtil.obj2Str(map.get("cityid")));
		m.setCityname(WxUtil.obj2Str(map.get("cityname")));
		m.setData(WxUtil.obj2Str(map.get("data")));
		return m;
	}
	
}

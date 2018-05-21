package org.wx.res.weather.service;

import java.sql.Timestamp;
import java.util.Map;

import org.wx.res.util.WxUtil;

public class HFCondition {

	private String id;
	private String code;
	private String ename;
	private String icon;
	private String name;

	private Timestamp createDate;
	private Timestamp modifyDate;

	private boolean isDownload;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public boolean isDownload() {
		return isDownload;
	}

	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}

	public static HFCondition createModel(Map map) {
		HFCondition m = new HFCondition();
		m.setId(WxUtil.obj2Str(map.get("id")));
		m.setCode(WxUtil.obj2Str(map.get("code")));
		m.setEname(WxUtil.obj2Str(map.get("ename")));
		m.setName(WxUtil.obj2Str(map.get("name")));
		m.setIcon(WxUtil.obj2Str(map.get("icon")));

		return m;
	}
}

package org.wx.res.model;

import java.sql.Timestamp;
import java.util.Map;

import cn.edu.myself.study.test.Utils;

public class ViewSpot {

	private String id;
	private String name;
	private String location;
	private String description;
	private Timestamp createDate;
	private Timestamp modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public static ViewSpot createModel(Map map) {
		ViewSpot model = new ViewSpot();
		model.setId(Utils.obj2Str(map.get("id")));
		model.setName(Utils.obj2Str(map.get("name")));
		model.setLocation(Utils.obj2Str(map.get("location")));
		model.setDescription(Utils.obj2Str(map.get("description")));
		model.setCreateDate(Utils.obj2Dt(map.get("createDate")));
		model.setModifyDate(Utils.obj2Dt(map.get("modifyDate")));
		return model;
	}
}

package org.wx.res.model;

import java.sql.Timestamp;
import java.util.Map;

import org.wx.res.util.WxUtil;

public class HFCity {

	private String id;
	private String code;
	private String ename;
	private String name;
	private String countryCode;
	private String countryName;
	private String countryEname;
	private String eprovince;
	private String province;
	private String superior;
	private String esuperior;
	private String longitudete;
	private String latitudete;

	private Timestamp createDate;
	private Timestamp modifyDate;

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

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getEprovince() {
		return eprovince;
	}

	public void setEprovince(String eprovince) {
		this.eprovince = eprovince;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSuperior() {
		return superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public String getEsuperior() {
		return esuperior;
	}

	public void setEsuperior(String esuperior) {
		this.esuperior = esuperior;
	}

	public String getLongitudete() {
		return longitudete;
	}

	public void setLongitudete(String longitudete) {
		this.longitudete = longitudete;
	}

	public String getLatitudete() {
		return latitudete;
	}

	public void setLatitudete(String latitudete) {
		this.latitudete = latitudete;
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
	
	public String getCountryEname() {
		return countryEname;
	}

	public void setCountryEname(String countryEname) {
		this.countryEname = countryEname;
	}

	public static HFCity createModel(Map map){
		HFCity m = new HFCity();
		m.setId(WxUtil.obj2Str(map.get("id")));
		m.setCode(WxUtil.obj2Str(map.get("code")));
		m.setEname(WxUtil.obj2Str(map.get("ename")));
		m.setName(WxUtil.obj2Str(map.get("name")));
		m.setCountryCode(WxUtil.obj2Str(map.get("countryCode")));
		m.setCountryName(WxUtil.obj2Str(map.get("countryName")));
		m.setCountryName(WxUtil.obj2Str(map.get("countryEname")));
		m.setEprovince(WxUtil.obj2Str(map.get("eprovince")));
		m.setProvince(WxUtil.obj2Str(map.get("province")));
		m.setSuperior(WxUtil.obj2Str(map.get("superior")));
		m.setEsuperior(WxUtil.obj2Str(map.get("esuperior")));
		m.setLatitudete(WxUtil.obj2Str(map.get("latitudete")));
		m.setLongitudete(WxUtil.obj2Str(map.get("longitudete")));
		return m;
	}
}

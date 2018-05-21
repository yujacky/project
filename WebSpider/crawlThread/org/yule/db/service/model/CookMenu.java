package org.yule.db.service.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CookMenu {

	private String id;
	private String name;
	private String descr;
	private String stuff;
	private String content;
	private String url;
	private String tips;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private String foodType;
	
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
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getStuff() {
		return stuff;
	}
	public void setStuff(String stuff) {
		this.stuff = stuff;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
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
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	
	public static CookMenu createModel(String mid,
									   String mname,
									   String mdescr,
									   String mstuff,
									   String mcontent,
									   String murl,
									   String mtips,
									   String mfoodType){
		CookMenu m = new CookMenu();
		m.setId(mid);
		m.setName(mname);
		m.setStuff(mstuff);
		m.setContent(mcontent);
		m.setDescr(mdescr);
		m.setUrl(murl);
		m.setTips(mtips);
		m.setFoodType(mfoodType);
		
		return m;
	}
}

package org.yule.db.service.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CodeList {

	private String id;
	private String name;
	private String ename;
	private String alias;
	private String code;
	private String phonePreffix;
	private String timeDif;
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
	
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhonePreffix() {
		return phonePreffix;
	}
	public void setPhonePreffix(String phonePreffix) {
		this.phonePreffix = phonePreffix;
	}
	public String getTimeDif() {
		return timeDif;
	}
	public void setTimeDif(String timeDif) {
		this.timeDif = timeDif;
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
	
	public static CodeList createModel(String mid,
										String mename,
										String mname,
										String malias,
										String mcode,
										String mphonePreffix,
										String mtimeDif,
										Timestamp mcreateDate,
										Timestamp mmodifyDate){
		CodeList m = new CodeList();
		m.setId(mid);
		m.setName(mname);
		m.setEname(mename);
		m.setAlias(malias);
		m.setCode(mcode);
		m.setPhonePreffix(mphonePreffix);
		m.setTimeDif(mtimeDif);
		m.setCreateDate(mcreateDate);
		m.setModifyDate(mmodifyDate);
		
		return m;
	}
}

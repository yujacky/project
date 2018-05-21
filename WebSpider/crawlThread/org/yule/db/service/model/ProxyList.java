package org.yule.db.service.model;

import java.sql.Timestamp;


public class ProxyList {

	private String id;
	private String countryCode;
	private String ipAddr;
	private String port;
	private String serveName;
	private String type;
	private String speed;
	private String connectWait;
	private String aliveDate;
	private boolean isAnony;
	private boolean isUsable;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getServeName() {
		return serveName;
	}
	public void setServeName(String serveName) {
		this.serveName = serveName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getConnectWait() {
		return connectWait;
	}
	public void setConnectWait(String connectWait) {
		this.connectWait = connectWait;
	}
	public String getAliveDate() {
		return aliveDate;
	}
	public void setAliveDate(String aliveDate) {
		this.aliveDate = aliveDate;
	}
	public boolean isAnony() {
		return isAnony;
	}
	public void setAnony(boolean isAnony) {
		this.isAnony = isAnony;
	}
	public boolean isUsable() {
		return isUsable;
	}
	public void setUsable(boolean isUsable) {
		this.isUsable = isUsable;
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
	
	public ProxyList createModel(String mid,
								 String mcountryCode,
								 String mipAddr,
								 String mport,
								 String mserveName,
								 String mtype,
								 String mspeed,
								 String mconnectWait,
								 String maliveDate,
								 boolean misAnony,
								 boolean misUsable,
								 Timestamp mcreateDate,
								 Timestamp mmodifyDate){
		ProxyList m = new ProxyList();
		m.setId(mid);
		m.setCountryCode(mcountryCode);
		m.setIpAddr(mipAddr);
		m.setPort(mport);
		m.setServeName(mserveName);
		m.setType(mtype);
		m.setSpeed(mspeed);
		m.setConnectWait(mconnectWait);
		m.setAliveDate(maliveDate);
		m.setAnony(misAnony);
		m.setUsable(misUsable);
		m.setCreateDate(mcreateDate);
		m.setModifyDate(mmodifyDate);
		return m;
	}
}

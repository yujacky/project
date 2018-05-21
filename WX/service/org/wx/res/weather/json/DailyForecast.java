package org.wx.res.weather.json;

public class DailyForecast {
	
	private Wind wind;
	private String hum;
	private String pcpn;
	private Astro astro;
	private String uv;
	private Tmp tmp;
	private String pop;
	private String pres;
	private String date;
	private Cond cond;
	private String vis;
	
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public String getHum() {
		return hum;
	}
	public void setHum(String hum) {
		this.hum = hum;
	}
	public String getPcpn() {
		return pcpn;
	}
	public void setPcpn(String pcpn) {
		this.pcpn = pcpn;
	}
	public Astro getAstro() {
		return astro;
	}
	public void setAstro(Astro astro) {
		this.astro = astro;
	}
	public String getUv() {
		return uv;
	}
	public void setUv(String uv) {
		this.uv = uv;
	}
	public Tmp getTmp() {
		return tmp;
	}
	public void setTmp(Tmp tmp) {
		this.tmp = tmp;
	}
	public String getPop() {
		return pop;
	}
	public void setPop(String pop) {
		this.pop = pop;
	}
	public String getPres() {
		return pres;
	}
	public void setPres(String pres) {
		this.pres = pres;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Cond getCond() {
		return cond;
	}
	public void setCond(Cond cond) {
		this.cond = cond;
	}
	public String getVis() {
		return vis;
	}
	public void setVis(String vis) {
		this.vis = vis;
	}
	
}

package org.wx.res.weather.json;

public class Now {

	private Wind wind;
	private String hum;
	private String pcpn;
	private String fl;
	private String tmp;
	private String pres;
	private Condn cond;
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
	public String getFl() {
		return fl;
	}
	public void setFl(String fl) {
		this.fl = fl;
	}
	public String getTmp() {
		return tmp;
	}
	public void setTmp(String tmp) {
		this.tmp = tmp;
	}
	public String getPres() {
		return pres;
	}
	public void setPres(String pres) {
		this.pres = pres;
	}
	public Condn getCond() {
		return cond;
	}
	public void setCond(Condn cond) {
		this.cond = cond;
	}
	public String getVis() {
		return vis;
	}
	public void setVis(String vis) {
		this.vis = vis;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("温度："+tmp+"\n");
		sb.append("风向:"+wind.getDir()+"\n");
		sb.append("天气状况："+cond.getTxt());
		return sb.toString();
	}
}

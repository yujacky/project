package org.wx.res.weather.json;

import java.util.List;

public class WeatherH5 {

	//--3天的天气预报信息
	private List<DailyForecast> daily_forecast;
	//--状态码
	private String status;
	//--现在的天气信息
	private Now now;
	//--基本信息
	private Basic basic;
	//--空气质量指数信息
	private Aqi aqi;
	//--建议
	private Suggestion suggestion;
	
	public List<DailyForecast> getDaily_forecast() {
		return daily_forecast;
	}
	public void setDaily_forecast(List<DailyForecast> daily_forecast) {
		this.daily_forecast = daily_forecast;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Now getNow() {
		return now;
	}
	public void setNow(Now now) {
		this.now = now;
	}
	public Basic getBasic() {
		return basic;
	}
	public void setBasic(Basic basic) {
		this.basic = basic;
	}
	public Aqi getAqi() {
		return aqi;
	}
	public void setAqi(Aqi aqi) {
		this.aqi = aqi;
	}
	public Suggestion getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}
	
}

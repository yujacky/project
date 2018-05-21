package org.wx.res.weather.service;

public class HeFengConstant {

	public static final String STATUS_OK = "ok";
	public static final String STATUS_INVALID_KEY = "invalid key";
	public static final String STATUS_UNKNOW_CITY = "unknow city";
	public static final String STATUS_NO_DATA="no data for this location";
	public static final String STATUS_REQUESTS_DENY="no more requests";
	public static final String STATUS_PARAM_INVALID="param invalid";
	public static final String STATUS_TO_FAST = "too fast";
	public static final String STATUS_ANR = "anr";
	public static final String STATUS_PERMISSION_DENIED = "permission denied";
	
	public static final String KEY = "8a77ed3382144efc9c345784a20855d8";
	public static final String URL = "https://free-api.heweather.com/v5/weather?city=yourcity&key=yourkey";
	public static final String PARAM_CITY="yourcity";
	public static final String PARAM_KEY="yourkey";
	
	public static class HeFengConfig {
		public static String MAX_REQ_MIN = "200"; 
		public static String MAX_REQ_DAY = "4000";
		
		public static String getMAX_REQ_MIN() {
			return MAX_REQ_MIN;
		}
		public static void setMAX_REQ_MIN(String mAX_REQ_MIN) {
			MAX_REQ_MIN = mAX_REQ_MIN;
		}
		public static String getMAX_REQ_DAY() {
			return MAX_REQ_DAY;
		}
		public static void setMAX_REQ_DAY(String mAX_REQ_DAY) {
			MAX_REQ_DAY = mAX_REQ_DAY;
		}
		
		
	}
}

package org.wx.res.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.wx.res.model.HFCity;
import org.wx.res.model.HeFengWeatherH5;
import org.wx.res.msg.dao.HFCityDao;
import org.wx.res.msg.dao.HeFengWeatherDao;
import org.wx.res.util.MD5Util;
import org.wx.res.weather.json.DailyForecast;
import org.wx.res.weather.json.Now;
import org.wx.res.weather.json.WeatherH5;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.myself.study.test.Utils;

@Component
public class HFCityService implements InitializingBean{

	private static final Logger log = Logger.getLogger(HFCityService.class);

	private Map<String ,String > cache = new HashMap<String ,String>();
	
	@Resource
	private HFCityDao hfcDao;

	public String getCodeByName(String city){
		return cache.get(MD5Util.getMD5(city));
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		List<HFCity> list = hfcDao.getAllData();
		for(HFCity city :list){
			cache.put(MD5Util.getMD5(city.getName()), city.getCode());
		}
	}
	
}

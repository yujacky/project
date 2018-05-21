package org.wx.res.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.wx.res.model.HeFengWeatherH5;
import org.wx.res.msg.dao.HeFengWeatherDao;
import org.wx.res.service.crawl.BaiduWeatherCrawl;
import org.wx.res.service.crawl.BingWeatherCrawl;
import org.wx.res.weather.json.DailyForecast;
import org.wx.res.weather.json.Now;
import org.wx.res.weather.json.WeatherH5;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.myself.study.test.Utils;

@Component
public class HFWeatherService {

	private static final Logger log = Logger.getLogger(HFWeatherService.class);
	@Resource
	private HeFengWeatherDao hfDao;
	@Resource
	private HFCityService cDao;
	
	public String getWeatherCondition(String city){
		String ret = "😳"; //-- 一脸蒙蔽表情
		city = city.substring(1);
		if(Utils.isStrEmpty(city)){
			return ret;
		}
		String code = cDao.getCodeByName(city);
		if(Utils.isStrEmpty(code)){
			return ret;
		}
		HeFengWeatherH5 weather = hfDao.getWeather(code);
		WeatherH5 model = str2Obj(weather.getData());
		ret = analyzor2Text(model);
		return ret;
	}
	
	
	/**
	 * 转化 和风天气 返回的json数据
	 * @param text
	 * @return
	 */
	private WeatherH5 str2Obj(String text){
		JSONObject weather = JSON.parseObject(text);
		String w = weather.getString("HeWeather5");
		WeatherH5 weatherh5 = JSON.parseObject(w.substring(1, w.length()-1), WeatherH5.class);
		return weatherh5;
	}
	
	/**
	 * 天气json数据解析并生成string
	 * @param model
	 * @return
	 */
	private String analyzor2Text(WeatherH5 model){
		StringBuffer sb = new StringBuffer();
		BaiduWeatherCrawl wc = new BaiduWeatherCrawl();
		Map<String,String>map = wc.getWeatherLiveByCity(model.getBasic().getCity());
		sb.append("城市:").append(model.getBasic().getCity()).append("\n");
		
//		Now now = model.getNow();
//		sb.append("实时风向：").append(now.getWind().getDir()).append("\n");
//		sb.append("实时能见度：").append(now.getVis()).append(" km ").append("\n");
		sb.append("实时温度：").append(map.get("tmp")).append("\n");
		sb.append("实时天气状况：").append(map.get("sky")).append("\n");
		
		sb.append("----- 未来3天 -----").append("\n");;
		List<DailyForecast> list = model.getDaily_forecast();
		for(DailyForecast day:list){
			sb.append("--- 日期:").append(day.getDate()).append(" ---\n");
			sb.append("风向：").append(day.getWind().getDir()).append("\n");
			sb.append("能见度：").append(day.getVis()).append(" km ").append("\n");
			sb.append("最高温度：").append(day.getTmp().getMax()).append("℃").append("\n");
			sb.append("最低温度：").append(day.getTmp().getMin()).append("℃").append("\n");
			sb.append("白天：").append(day.getCond().getTxt_d()).append("\n");
			sb.append("夜晚:").append(day.getCond().getTxt_n()).append("\n");
		}
		
		System.out.println(sb);
		return sb.toString();
	}
}

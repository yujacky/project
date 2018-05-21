package org.wx.res.weather.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.wx.res.weather.json.WeatherH5;

import cn.edu.myself.study.test.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class HeFengInterface {
	
	//private ThreadLocal local = new ThreadLocal();
	//private AtomicInteger pool = new AtomicInteger();
	public String getJsonFromH5(String city){
		String req = HeFengConstant.URL;
		req = req.replace(HeFengConstant.PARAM_CITY, city)
				 .replace(HeFengConstant.PARAM_KEY, HeFengConstant.KEY);
		System.out.println(req);
		System.out.println("begin -- " + req);
		StringBuffer buf = new StringBuffer();
		
		try {
			URL url = new URL(req);
			URLConnection con  = url.openConnection();
			InputStreamReader fr = new InputStreamReader(con.getInputStream());
			BufferedReader br = new BufferedReader(fr);
			String str= null;
			
			while((str = br.readLine()) != null){
				buf.append(str);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return buf.toString();
	}

	public WeatherH5 getWeatherByCityOrCode(String city){
		WeatherH5 weatherh5 = str2Obj(getJsonFromH5(city));
		return weatherh5;
	}
	
	private WeatherH5 str2Obj(String text){
		JSONObject weather = JSON.parseObject(text);
		String w = weather.getString("HeWeather5");
		WeatherH5 weatherh5 = JSON.parseObject(w.substring(1, w.length()-1), WeatherH5.class);
		return weatherh5;
	}
	
	public boolean getAllCityData() {
		boolean ret =false;
		HFCityDao hfDao = new HFCityDao();
		int start = 0;
		int len = Integer.parseInt(HeFengConstant.HeFengConfig.MAX_REQ_MIN);
		
		int cnt = hfDao.queryQuantity();
		if(cnt > Integer.parseInt(HeFengConstant.HeFengConfig.MAX_REQ_DAY)){
			cnt = Integer.parseInt(HeFengConstant.HeFengConfig.MAX_REQ_DAY);
		}
		
		int times = (int)Math.ceil(BigDecimal.valueOf(cnt)
				   .divide(BigDecimal.valueOf(len))
				   .doubleValue());
		try{
			while(times-- > 0){
				
				
				List<HFCity> list = hfDao.pageQueryCityCode(start, Integer.parseInt(HeFengConstant.HeFengConfig.MAX_REQ_MIN));
				for(HFCity obj:list){
					String data = getJsonFromH5(obj.getCode());
					String status = str2Obj(data).getStatus();
					
					if(HeFengConstant.STATUS_OK.equals(status)){
						HFWeatherDao hfwDao = new HFWeatherDao();
						Map <String,Object> map =new HashMap <String,Object>();
						map.put("id", Utils.getUUID());
						map.put("cityid", obj.getCode());
						map.put("cityname", obj.getName());
						map.put("data", data);
						hfwDao.insert(HeFengWeatherH5.createModel(map));
						hfwDao.close();
					}
				}
				
				start+=len;
				Thread.sleep(60 * 1000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("End -- ");
		hfDao.close();
		return ret;
	}
	
	public boolean analyzorData(String cityName) throws Exception{
		if(Utils.isStrEmpty(cityName)) 
			throw new Exception("cityname can not be null!");
		
		HFWeatherDao hf = new HFWeatherDao();
		HeFengWeatherH5 hfw = hf.queryByCityName(cityName);
		WeatherH5 obj = str2Obj(hfw.getData());
		System.out.println(obj.getNow().toString());
		return true;
	}
	public static void main(String[] args) {

//			String path = HeFengInterface.class.getResource("/").toURI().getPath().toString();
//			File file = new File(path+"/META-INF/res/ret");
//			System.out.println(Math.ceil(BigDecimal.valueOf(3180)
//								   .divide(BigDecimal.valueOf(200))
//								   .doubleValue()));
		
		HeFengInterface hf = new HeFengInterface();
		hf.getAllCityData();
//		try {
//			new HeFengInterface().analyzorData("韶关");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}

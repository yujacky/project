package org.wx.res.service.crawl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class BaiduWeatherCrawl {

	private final int TIME = 3000;
	
	private AtomicInteger err = new AtomicInteger(0);
	public Map<String,String> getWeatherLiveByCity(String city) {
		Map<String,String> ret = new HashMap<String,String>();
		
		try{
			String seed = createBaiduUrl(city+"天气");
			Document doc = Jsoup.parse(new URL(seed), TIME);
			String tmp = doc.select("span.wa-sg-weather-currentTemp").text();
			String sky = doc.select("span.wa-sg-weather-currentWeather").text();
			
			if(Util.isEmptyString(tmp) || Util.isEmptyString(sky)){
				err.incrementAndGet();
				System.out.println(doc.body());
			}
			
			ret.put("tmp", tmp+"C");
			ret.put("sky", sky);
			System.out.println(ret);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private String createBaiduUrl(String keyword) throws Exception {
        return String.format("https://m.baidu.com/s?wd=%s", URLEncoder.encode(keyword, "utf-8"));
    }
    
	private String genUrlByKey(String key){
		String keyword = URLEncoder.encode(key);
		return String.format("https://www.baidu.com/s?wd=%s&ie=utf-8&pn=0&tn=baidu", keyword);
		//return String.format("https://m.baidu.com/s?word=%s", keyword);
	}
	
	private String getRealUrlFromBaiduUrl(String url){
        Connection.Response res = null;  
        int itimeout = 60000;  
        try {  
            res = Jsoup.connect(url).timeout(itimeout).method(Connection.Method.GET).followRedirects(false).execute();
            System.out.println(res.headers());
            System.out.println(res.body());
            System.out.println(res.cookies());
            return res.header("Location");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
	}
	
	public void testquery(String key){
		String seed = genUrlByKey(key);
//		Connection.Response res = null;  
//        int itimeout = 60000; 
		
		try {
			String path = BaiduWeatherCrawl.class.getResource("/").toURI().getPath().toString()+"META-INF/res/test.txt";
			System.out.println(path);
			File file = new File(path);
			if(!file.exists()) file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			
			Document doc = Jsoup.parse(new URL(seed), TIME);
			fos.write(doc.html().getBytes("UTF-8"));
			fos.close();
			file.delete();
			System.out.println(doc.html());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} 
		
	}
	public static void main(String[] args) {
//		WeatherCrawl c = new WeatherCrawl();
//		List<Long> list = new ArrayList<Long>();
//		long startTal = System.currentTimeMillis();
//		for(int i = 0;i<10000;i++){
//			long start = System.currentTimeMillis();
//			System.out.println("crawl begin ------");
//			c.getWeatherLiveByCity("乐昌");
//			long cost = System.currentTimeMillis() - start;
//			System.out.println("crawl end ------  use time :" + cost);
//			list.add(cost);
//		}
//		
//		long all = 0;
//		for(Long l:list){
//			all += l;
//		}
//		
//		System.out.println("cost all -----" + ( System.currentTimeMillis() - startTal) + " avg: "+ (all/list.size()));
		BaiduWeatherCrawl c = new BaiduWeatherCrawl();
		Long total = new Long(0);
		int len = 0;
		long start = System.currentTimeMillis();
		for(int i = 0;i<100000;i++){
			long st = System.currentTimeMillis();
			c.getWeatherLiveByCity("乐昌");
			long dif1 = System.currentTimeMillis() - st;
			total += dif1;
			len++;
			System.out.println("获取天气耗时 ： " + dif1);
		}
		
		long dif2 = System.currentTimeMillis() - start;
		
		System.out.println(" 线程共耗时 :" + dif2 + " 平均耗时 : " + (total / len));
		System.err.println(" 错误次数 ：" + c.err.get());
	}
}

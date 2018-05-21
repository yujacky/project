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

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BingWeatherCrawl {

	private final int TIME = 3000;
	
	public Map<String,String> getWeatherLiveByCity(String city) {
		Map<String,String> ret = new HashMap<String,String>();
		
		try{
			String seed = createBingUrl(city+"天气",1);
			Document doc = Jsoup.parse(new URL(seed), TIME);
			String tmp = doc.select("div.w2_temperature").text();
			String sky = doc.select("div.w2_daysky").text();
			ret.put("tmp", tmp);
			ret.put("sky", sky);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private String createBingUrl(String keyword, int pageNum) throws Exception {
        int first = pageNum * 10 - 9;
        keyword = URLEncoder.encode(keyword, "utf-8");
        return String.format("http://cn.bing.com/search?q=%s&first=%s", keyword, first);
    }
    
	private String genUrlByKey(String key){
		String keyword = URLEncoder.encode(key);
		//return String.format("https://www.baidu.com/s?wd=%s&ie=utf-8&pn=0&tn=baidu", keyword);
		return String.format("https://m.baidu.com/s?word=%s", keyword);
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
			String path = BingWeatherCrawl.class.getResource("/").toURI().getPath().toString()+"META-INF/res/test.txt";
			System.out.println(path);
			File file = new File(path);
			if(!file.exists()) file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			Document doc = Jsoup.parse(new URL(seed), TIME);
			fos.write(doc.html().getBytes("UTF-8"));
			fos.close();
			file.delete();
			//System.out.println(doc.html());
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
		
		BingWeatherCrawl c = new BingWeatherCrawl();
		c.testquery("天气预报");
		
	}
}

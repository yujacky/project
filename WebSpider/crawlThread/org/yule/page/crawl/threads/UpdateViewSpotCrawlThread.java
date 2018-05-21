package org.yule.page.crawl.threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.yule.db.service.dao.ViewSpotDao;
import org.yule.db.service.model.ViewSpot;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;
import cn.edu.myself.study.test.Utils;

public class UpdateViewSpotCrawlThread extends Thread{
	
	private String seed = "https://baike.baidu.com/search?word=%s&pn=0&rn=0&enc=utf8";
	
	private Map<String,String> map = null;
	
	private int start = 0;
	private int pageLen = 0;
	
	private int sleep = 0;
	
	private long paraLen = 500;
	
	private int timeout = 5000;
	
//	public UpdateViewSpotCrawlThread(int start,int pageLen,int sleep) {
//		initConf(start,pageLen,sleep);
//		initMap();
//		initSleep();
//	}
	
	public UpdateViewSpotCrawlThread() {
		initMap();
		initSleep();
	}
	
	private String genBaikeUrl(String key){
		return String.format(seed, URLEncoder.encode(key));
	}
	
	private void initConf(int start,int pageLen,int sleep){
		this.start=start;
		this.pageLen=pageLen;
		this.sleep=sleep;
	}
	
	private void initSleep(){
		Random r = new Random(System.currentTimeMillis());
		sleep = r.nextInt(2000) + 1000;
	}
	
	private void initMap(){
		if(null == map){
			map = new HashMap<String,String>();
		}
		
		map.put("Accept", "*/*;q=0.8");
		map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
		//map.put("Accept-Encoding", "gzip, deflate, br");
		map.put("Accept-Language", "zh-CN,zh;q=0.8");
		map.put("Connection", "keep-alive");
		map.put("Referer", "baike.baidu.com");
        map.put("Host", "baike.baidu.com");
        map.put("Cache-Control","max-age=0");
	}
	
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageLen() {
		return pageLen;
	}

	public void setPageLen(int pageLen) {
		this.pageLen = pageLen;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}
	
	public String regularUrl(String url,String host){
		if(Utils.regex(url, "^/item/.*")){
			return host+url;
		}
		return url;
	}
	
	public Document analyzor2Doc(String seed) {
		Response res = null;
		Document doc = null;
		
		//--规则化url
		String url = regularUrl(seed,"https://baike.baidu.com");
		
		if(Utils.isStrEmpty(url.trim())){
			System.err.println("analyzor2Doc() error url: " + url);
			return doc;
		}

		try {
			res = Jsoup.connect(url).timeout(5000).headers(map).execute();
			String html = new String(res.bodyAsBytes(),"UTF-8");
			doc = Jsoup.parse(html);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}
	
	private boolean analyzor(Document doc,String id) throws IOException{
		boolean ret = false;
		
		String url = doc.select("a.result-title").first().attr("href");
		Document result = analyzor2Doc(url);
		if(null == result) return ret;
		
		StringBuffer sb = new StringBuffer();
		Elements para = result.select("div.para");
		
		for(Element p:para){
			if(sb.length() > paraLen) break;
			sb.append("<para>").append(p.text()).append("</para>");
		}
		
		if(sb.length() < 1){
			Elements summary = result.select("div.lemma-summary");
			if(!summary.isEmpty()){
				sb.append(summary.first().text());
			}else{
				sb.append("无相关介绍。");
			}
		}
		
		ViewSpotDao dao = new ViewSpotDao();
		Map<String, String> m = new HashMap<String, String>();
		m.put("id", id);
		m.put("description", sb.toString());
		boolean update = dao.updateDesc(ViewSpot.createModel(m));
		ret = true;
		dao.close();

		if(!update){
			System.err.println("analyzor update fail ： id - " + id);
		}
		
		return ret;
	}
	
	@Override
	public void run() {
		long st = System.currentTimeMillis();//-- 线程开始时间
		
		ViewSpotDao dao = new ViewSpotDao();
		List<ViewSpot> list = dao.pageQueryViewSpot(start, pageLen);
		//List<ViewSpot> list = dao.queryById("33D035E1593946C9A95C1018E821B5BC");
		dao.close();
		
		for(ViewSpot vs :list){
			try {
				long stt = System.currentTimeMillis();//-- 每个update 开始时间
				Document doc = analyzor2Doc(genBaikeUrl(vs.getLocation()+" "+vs.getName()));
				if(null == doc) continue;
				
				Elements noresult = doc.select("div.no-result");
				if(!noresult.isEmpty()) {
					doc = analyzor2Doc(genBaikeUrl(vs.getName()));
					if(null == doc) continue;
					
					Elements noresult1 = doc.select("div.no-result");
					if(!noresult1.isEmpty()){
						System.err.println("Id : " + vs.getId() +" thread update fail : seed name crawl no data!");
						continue;
					}
				}
				
				boolean ret = analyzor(doc,vs.getId());
				System.out.println("Id : " + vs.getId() + " thread update cost " + Utils.longToTime(System.currentTimeMillis() - stt) );
				
				sleep(4000);
				
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getId() + " thread end. cost " + Utils.longToTime(System.currentTimeMillis() - st));
	}
	
	public static void main(String[] args) {
		File outFile = new File("log/out4.txt");
		File errFile = new File("log/err4.txt");
		try {
			PrintStream out = new PrintStream(new FileOutputStream(outFile));
			PrintStream err = new PrintStream(new FileOutputStream(errFile));
			System.setOut(out);
			System.setErr(err);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		ViewSpotDao dao = new ViewSpotDao();
		long total = dao.viewSpotCount(); //--总数
		dao.close();
		int pageLen = 100; //-- 每页数量
		int totalPage = Utils.chufaH(total,pageLen); //--页数
		
		for(int i=0;i<totalPage;i++){
			UpdateViewSpotCrawlThread t = new UpdateViewSpotCrawlThread ();
			t.setPageLen(pageLen);
			t.setStart(i * pageLen);
			t.start();
			try {
				t.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//UpdateViewSpotCrawlThread t =new UpdateViewSpotCrawlThread();
		//t.analyzor2Doc("https://baike.baidu.com/item/%E6%AD%A6%E9%BE%99%E9%87%8E%E6%88%98%E6%B4%BB%E5%8A%A8%E4%B8%AD%E5%BF%83/10565349");
		//Document doc = Jsoup.parse(new URL("https://baike.baidu.com/item/%E6%AD%A6%E9%BE%99%E9%87%8E%E6%88%98%E6%B4%BB%E5%8A%A8%E4%B8%AD%E5%BF%83/10565349"), 3000);
		//System.out.println(doc.text());
	}
}



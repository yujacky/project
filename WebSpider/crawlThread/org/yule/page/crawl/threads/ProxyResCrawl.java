package org.yule.page.crawl.threads;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.yule.db.service.dao.ProxyDao;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;
import cn.edu.myself.study.test.Utils;

public class ProxyResCrawl extends RamCrawler{

	//String site = "http://www.xicidaili.com/";
	String wt = "http://www.xicidaili.com/wt/";
	String nn = "http://www.xicidaili.com/nn/";
	String nt = "http://www.xicidaili.com/nt/";
	String wn = "http://www.xicidaili.com/wn/";
	public ProxyResCrawl() {
		setAutoParse(false);
		setThreads(10);
		
		for(int i=1;i<10;i++){
			addSeed(wt+i);
			addSeed(nn+i);
			addSeed(nt+i);
			addSeed(wn+i);
		}
		
	}
	
	@Override
	public void visit(Page page, CrawlDatums next) {
		Elements eles = page.select("table#ip_list").select("tr");
		
		for(Element e:eles){
			if("th".equals(e.child(0).tagName())) continue;
			
			String id = Utils.getUUID();
			String country = null;
			if(!e.child(0).select("img").isEmpty()){
				country = e.child(0).select("img").first().attr("alt");
			}
			String ipAddr = e.child(1).text();
			String port = e.child(2).text();
			String serveName = e.child(3).text();
			boolean isAnony = transToBool(e.child(4).text());
			String type = e.child(5).text();
			String speed = e.child(6).select("div.bar").first().attr("title");
			String connectWait = e.child(7).select("div.bar").first().attr("title");
			String aliveDate = e.child(8).text();
			boolean isUsable = testConnect(ipAddr, port);
			
			ProxyDao dao = new ProxyDao();
			dao.insert(id, country, ipAddr, port, serveName, type, speed, connectWait, aliveDate, isAnony, isUsable);
			dao.close();
		}
	}

	public static void main(String[] args) {
		ProxyResCrawl crawl = new ProxyResCrawl();
		try {
			crawl.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean transToBool(String text){
		if ( "高匿".equals(text)) return true;
		else if("透明".equals("text")) return false;
		
		return false;
	}
	
	public boolean testConnect(String addr,String port){
		String seed = "https://www.baidu.com/";
		boolean ret = false;
		try {
			URL url = new URL(seed);
			url.openConnection(new Proxy(Type.HTTP,new InetSocketAddress(addr,Integer.parseInt(port))));
			URLConnection con = url.openConnection();
			con.setConnectTimeout(3000);
			con.connect();
			String retStr = con.getHeaderField(0);
			if(retStr.indexOf("200")>-1){
				ret = true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			ret = false;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ret = false;
		} catch (IOException e) {
			e.printStackTrace();
			ret = false;
		}
		
		return ret;
	}
}

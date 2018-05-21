package org.yule.page.crawl.threads;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.yule.db.service.dao.ViewSpotDao;
import org.yule.db.service.model.ViewSpot;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;
import cn.edu.myself.study.test.Utils;

public class TripLocationCrawl extends RamCrawler{
	
	private String reg1 = "^http://m.meet99.com/lvyou-.*";
	
	private String reg2 = "^http://www.auyou.com/mudidi/mudidilist-[0-9]{1,2}.html";
	public TripLocationCrawl(String seed) throws MalformedURLException {
		this.setMaxExecuteCount(3);
		this.conf.setExecuteInterval(8000);
		this.addSeed(new CrawlDatum(seed).type("index"));
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		analyzor1(page,next);
	}
	
	public void analyzor(Page page, CrawlDatums next){
		Elements boxs = page.select("div.roundbox>a[style=margin-right:8px;]");
		if(!boxs.isEmpty()){
			for(Element tag:boxs){
				String url = tag.attr("abs:href");
				String text = tag.text();
				if(Utils.regex(url, reg1)){
					String location = page.meta("location");
					if(Utils.isStrEmpty(location)) location = "";
					
					next.add(new CrawlDatum(url).meta("location",location+text).meta("referer", page.url()));
				}
			}
		}else{
			if(!Utils.isStrEmpty(page.meta("location"))){
				Map<String,Object> map = new HashMap<String,Object>();
				String id = Utils.getUUID();
				String location = page.meta("location");
				String name = page.select("div.box").select("div.bar").attr("m");
				
				map.put("id", id);
				map.put("location", location);
				map.put("name", name);
				
				ViewSpotDao dao = new ViewSpotDao();
				dao.insert(ViewSpot.createModel(map));
				dao.close();
			}
		}
	}
	
	public void analyzor1(Page page, CrawlDatums next){
		
		if(page.matchType("index")){
			Elements areas = page.select("map>area[shape='rect']");
			for(Element area : areas){
				String url = area.attr("abs:href");
				if(Utils.regex(url, reg2)){
					next.add(new CrawlDatum(url).type("data").meta("referer", page.url()));
				}
			}
		}else if(page.matchType("data")){
			Elements items = page.select("div.txt1").select("table.tb_jd_list>tbody");
			
			for(Element item: items){
				String id = Utils.getUUID();
				String name = item.select("tr").get(0).select("td[width='355']").select("strong>a").text();
				
				String location = getLocationStr(item.select("tr").get(2).child(0).select("span").text());
				if(Utils.isStrEmpty(location)){
					location = item.select("tr").get(0).select("td[width='355']").select("a.a3").text();
				}
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", id);
				map.put("location", location);
				map.put("name", name);
				
				ViewSpotDao dao = new ViewSpotDao();
				dao.insert(ViewSpot.createModel(map));
				dao.close();
			}
			
			Elements nexts = page.select("div.txt1>p").select("a");
			for(Element nt:nexts){
				if("下一页".equals(nt.text())){
					next.add(new CrawlDatum(nt.attr("abs:href")).type("data").meta("referer", page.url()));
				}
			}
		}

		
	}
	
	private String getLocationStr(String text){
		if(Utils.isStrEmpty(text)) return null;
		
		String[] tmp = text.split(":");
		String loc = null;
		if(tmp.length>1) {
			loc= tmp[1];
			return loc.trim();
		}
		
		return null;
	}
	
////	@Override
//	public void execute(CrawlDatum datum, CrawlDatums next) throws Exception {
//		HtmlUnitDriver driver = new HtmlUnitDriver();
//		
//		//driver.setJavascriptEnabled(true);
//		//driver.setProxy("110.73.42.167",8123);
//		driver.get(datum.url());
//		Page page = new Page(datum, 200, "text/html", driver.getPageSource().getBytes());
//		visitor.visit(page, next);
//		
//	}
	public static void main(String[] args) throws Exception {
		//String seed = "http://m.meet99.com/lvyou";
		String seed = "http://www.auyou.com/mudidi/";
		TripLocationCrawl crawl = new TripLocationCrawl(seed);
		crawl.setThreads(2);
		crawl.start(100);
	}
}

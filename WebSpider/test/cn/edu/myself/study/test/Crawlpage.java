package cn.edu.myself.study.test;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.alibaba.fastjson.JSONObject;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * Crawling news from hfut news
 *
 * @author hu
 */
public class Crawlpage extends BreadthCrawler {
    public Crawlpage(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        /*
         * regex
         * ^http://home.meishichina.com/recipe-.*.html$
         * ^https://www.xinshipu.com/zuofa/.*
         * ^http://tools.2345.com/meishi/food/.*.htm$
         */
        //this.addSeed("http://home.meishichina.com/recipe-350139.html");
        //this.addSeed("http://home.meishichina.com/recipe-348858.html");
        //this.addSeed("https://www.xinshipu.com/zuofa/685941");
        //this.addSeed("http://tools.2345.com/meishi/food/15463.htm");
        //this.addSeed("http://www.xicidaili.com/nn/5");
        this.addSeed("http://www.98bk.com/cycx/ip1/");
        setThreads(1);
        getConf().setTopN(1);
//        setResumable(true);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.url();

//        if (page.matchType("list")) {
//            next.add(page.links("div[class=' col-lg-8 '] li>a")).type("content");
//        }else if(page.matchType("content")) {
//            String title = page.select("div[id=Article]>h2").first().text();
//            String content = page.selectText("div#artibody", 0);
//
//            title = getConf().getString("title_prefix") + title;
//            System.out.println("URL:\n" + url);
//            System.out.println("title:\n" + title);
//            System.out.println("content:\n" + content);
//            
//            DBHelp dbHelp = new DBHelp();
//            WebCrawlDataModel model = new WebCrawlDataModel();
//            model.setId(getUUID());
//            model.setTitle(title);
//            model.setContent(content);
//            model.setUrl(url);
//            model.setType("news");
//            dbHelp.insert(model);
//            dbHelp.close();
//        }
//        try {
//    		String name = page.selectText("h1.fl#stitle");
//    		//if (null == name || "".equals(name)) return false;
//    		
//    		String descr = page.selectText("dl.des>dd>span#sintro");
//    		String tips = page.selectText("span#stips");
//    		
//    		StringBuffer stuffs = new StringBuffer();
//    		Elements zStuff = page.select("div.material>ul>li.ingtmgr");
//    		Elements fStuff = page.select("div.material>ul>li.ingtbur");
//    		
//    		for(Element z :zStuff){
//    			stuffs.append(z.text()).append(",");
//    		}
//    		for(Element f :fStuff){
//    			stuffs.append(f.text()).append(",");
//    		}
//    		
//    		StringBuffer step = new StringBuffer();
//    		JSONObject jsonStep = new JSONObject();
//    		Elements steps = page.select("dl.step>dd");
//    		
//    		System.out.println("name:" +name +" descr:" +descr +" tips:" +tips+" stuffs:"+stuffs.toString()+" steps:"+steps.text());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String id = Utils.getUUID();
//		String name = page.select("h1.font18.no-overflow").first().text();
//		
//		String descr = null;
//		String stuffs = null;
//		String steps = null;
//		String tips = null;
//		
//		Element root = page.select("div.bpannel.mt20.p15.re-steps").first();
//		Elements eles = root.children();
//		for(Element e : eles){
//			if("简介".equals(e.child(0).text()))
//				descr = e.child(1).text();
//			else if("材料".equals(e.child(0).text()))
//				stuffs = e.child(1).text();
//			else if("做法".equals(e.child(0).text())){
//				JSONObject jsonStep = new JSONObject();
//				Elements step = e.child(1).getElementsByTag("p");
//				Elements stepImg = e.child(1).getElementsByTag("img");
//				int len = 0;
//				
//				for(Element s:step){
//					if(null == s.text() || "".equals(s.text())) continue;
//					
//					Map<String,String> map =new HashMap<String,String>();
//					map.put("step", s.text());
//					if(null != stepImg && null != stepImg.get(len)){
//						String imgUrl = stepImg.get(len).attr("abs:src");
//						String imgId = Utils.getUUID();
//						map.put("imgId", imgId);
//						System.out.println(imgUrl);
//						//to do insert img to db
//					}
//					
//					jsonStep.put(len+"", map);
//					len++;
//				}
//				steps = jsonStep.toJSONString();
//			}else if("小诀窍".equals(e.child(0).text()))
//				tips = e.child(1).text();
//		}
//		
//		String imgUrl = page.select("img.photo").first().attr("abs:src");
//		
//		System.out.println("name:"+name +" descr:"+descr+" stuff:"+stuffs+" step:"+steps+" tips:"+tips );
        System.out.println(page.html());
        System.out.println(page.select("strong"));
    }
	@Override
	public void execute(CrawlDatum datum, CrawlDatums next) throws Exception {
		HtmlUnitDriver driver = new HtmlUnitDriver();
		
		driver.setProxy("121.31.103.185", 8123);
		driver.get(datum.url());
		Page page = new Page(datum, 200, null, driver.getPageSource().getBytes());
		visitor.visit(page, next);
	}
	
    public static void main(String[] args) throws Exception {
    	Crawlpage crawler = new Crawlpage("crawl", false);
        crawler.start(1);
    }

    public boolean testConnect(String url){
    	String seed = "http://www.98bk.com/cycx/ip1/";
    	boolean ret = false;
    	try {
    		Document doc = Jsoup.parse(new URL(url), 3000);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return ret;
    }
}

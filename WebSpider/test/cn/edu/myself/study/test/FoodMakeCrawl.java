package cn.edu.myself.study.test;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.model.WebCrawlDataModel;
import org.mysql.util.DBHelp;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.mysql.jdbc.PreparedStatement;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import cn.edu.hfut.dmic.webcollector.crawler.AutoParseCrawler;
import cn.edu.hfut.dmic.webcollector.crawler.Crawler;
import cn.edu.hfut.dmic.webcollector.fetcher.Executor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.Proxys;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;

//searchKey 美食制作
public class FoodMakeCrawl extends RamCrawler{

	private final String IMAGE_URL = "http://yujunxian.imwork.net/getImage.do?id=";
	private RegexRule regs = new RegexRule();
	
	//private Proxys proxys=new Proxys();
	private String urlRegex = "^http://.*/recipe-.*|" +
			  "^http://.*/recipe/.*|" +
			  "^http://.*/CaiPu/.*|"+
			  "^http://.*/zuofa/.*|"+
			  "^http://.*/cookbook/.*|"+
			  "^http://.*/meishi/.*";
	
	private String contentRegex =".*\u505a\u6cd5.*|.*\u6b65\u9aa4.*";
	
	public FoodMakeCrawl(String searchkey) {
		super();
		String url = genUrlByKey(searchkey);
		this.setThreads(30);
		this.addSeed(new CrawlDatum(url, "search_list").meta("depth", 1));
//		proxys.add("121.12.42.37", 61234);
//		proxys.add("116.249.233.69", 8118);
//		proxys.add("120.78.15.63", 80);
//		this.addRegex("^http://.*/recipe-.*");
//		this.addRegex("^http://.*/recipe/.*");
//		this.addRegex("^http://.*/CaiPu/.*");
//		this.addRegex("^http://.*/zuofa/.*");
//		this.addRegex("^http://.*/cookbook/.*"); 
//		addRegex("^((?!www.douguo.com).)*$"); //匹配不包含
	}
	
//	@Override
//	public Page getResponse(CrawlDatum crawlDatum) throws Exception {
//		   HttpRequest request = new HttpRequest(crawlDatum);
//		   request.setProxy(proxys.nextRandom());
//		   return request.responsePage();
//	}
	
	@Override
	public void visit(Page page, CrawlDatums next) {
		System.out.println("page depth : " + page.meta("depth"));
		
		if(page.matchType("search_list")){
			Elements urlElems = page.select("h3[class=t]>a");
			
			for(int idx= 0;idx < urlElems.size();idx ++){
				String rdUrl = urlElems.get(idx).attr("abs:href");
				String url = getRealUrlFromBaiduUrl(rdUrl);
				if(!regex(url,"^((?!www.douguo.com).)*$")) continue;
				next.add(new CrawlDatum(url, "protal"));
			}
		} 
//		else if(page.matchType("protal")){
//			Links ls = page.links(false);
//			
//			for(int n=0;n<ls.size();n++){
//				next.add(new CrawlDatum(ls.get(n), "item_content"));
//			}
//		} else if(page.matchType("item_content")){
//			try {
//				String pageId = Utils.getUUID();
//				News news = ContentExtractor.getNewsByDoc(page.doc());
//				//-- 解析内容
//				Element ele=news.getContentElement();
//				//-- 下载并转换图像URL
//				Elements imgs = ele.getElementsByTag("img");
//				if(!imgs.isEmpty()){
//					for(int i=0;i<imgs.size();i++){
//						String imgUrl = imgs.get(i).attr("abs:src");
//						System.out.println("imgurl:"+imgUrl);
//						String imageId = downloadImg(imgUrl, pageId);
//						imgs.get(i).attr("src", IMAGE_URL+imageId);
//					}
//				}
//				WebCrawlDataModel model = new WebCrawlDataModel();
//				this.saveBody(model.createModel(pageId, new String(news.getTitle().getBytes(),"UTF-8"), 
//									filterTags(new String(ele.html().getBytes(),"UTF-8")) , page.url(), "food"));
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("页面解析失败！");
//			}
//			
//			Links ls = page.links(false);
//			ls.filterByRegex(regs);
//			for(int n=0;n<ls.size();n++){
//				next.add(new CrawlDatum(ls.get(n), "item_content"));
//				System.out.println(ls.get(n));
//			}
//		}
		
		//visitor.visit(page, next);
		else if(page.matchType("protal")){
			
			Links links = page.links();
			for(int i = 0;i < links.size();i++){
				next.add(new CrawlDatum(links.get(i), "item_content"));
			}
		}else if(page.matchType("item_content")){
			if(page.matchUrl(urlRegex)){
				try {
					analyzor(page);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			Links links = page.links();
			for(int i = 0;i < links.size();i++){
				next.add(new CrawlDatum(links.get(i), "item_content"));
			}
		}
		
	}
	
	@Override
	protected void afterParse(Page page, CrawlDatums next) {
		//--给种子添加深度
        int depth;
        if(page.meta("depth")==null){
            depth=1;
        }else{
            depth=Integer.valueOf(page.meta("depth"));
        }
        depth++;
        for(CrawlDatum datum:next){
            datum.meta("depth", depth+"");
        }
	}
	/**
	 * 符合规则的页面分析
	 * @param page
	 * @return
	 * @throws Exception
	 */
	private boolean analyzor(Page page) throws Exception{
		String pageId = Utils.getUUID();
		News news = ContentExtractor.getNewsByDoc(page.doc());
		
		if(!chineseRegex(news.getContent(),contentRegex)) return false;
		
		//-- 解析内容
		Element ele=news.getContentElement();
		//-- 下载并转换图像URL
		Elements imgs = ele.getElementsByTag("img");
		if(!imgs.isEmpty()){
			for(int i=0;i<imgs.size();i++){
				String imgUrl = imgs.get(i).attr("abs:src");
				System.out.println("imgurl:"+imgUrl);
				String imageId = downloadImg(imgUrl, pageId);
				imgs.get(i).attr("src", IMAGE_URL+imageId);
			}
		}
		
		WebCrawlDataModel model = new WebCrawlDataModel();
		this.saveBody(model.createModel(pageId, new String(news.getTitle().getBytes(),"UTF-8"), 
							filterTags(new String(ele.html().getBytes(),"UTF-8")) , page.url(), "food"));
		return true;
	}
	/**
	 * 中文词 做法 和 步骤 匹配
	 * @param text
	 * @return
	 */
	private boolean chineseRegex(String text,String regex){
        Pattern p=Pattern.compile(regex,Pattern.UNICODE_CASE); 
        Matcher m=p.matcher(text); 
        return m.find();
	}
	
	/**
	 * 正则式 匹配
	 * @param text
	 * @return
	 */
	private boolean regex(String text,String regex){
        Pattern p=Pattern.compile(regex); 
        Matcher m=p.matcher(text); 
        return m.find();
	}
	
	private String filterTags(String html) {
			String regEx_a="<a[^>]*?>[\\s\\S]*?<\\/a>"; //定义a的正则表达式 
	        Pattern p_script=Pattern.compile(regEx_a,Pattern.CASE_INSENSITIVE); 
	        Matcher m_script=p_script.matcher(html); 
	        html=m_script.replaceAll(""); //过滤a标签 
	        
	        return html;
	}	

	private String genUrlByKey(String key){
		String keyword = key;
		return String.format("https://www.baidu.com/s?wd=%s&ie=utf-8&pn=0&tn=baidu", keyword);
	}
	
	private String getRealUrlFromBaiduUrl(String url){
        Connection.Response res = null;  
        int itimeout = 60000;  
        try {  
            res = Jsoup.connect(url).timeout(itimeout).method(Connection.Method.GET).followRedirects(false).execute();
            return res.header("Location");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
	}
	
	/**
	 * 将图片下载存入数据库
	 * @param url
	 * @param referPageId
	 * @return
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 */
	private String downloadImg(String url,String referPageId) {
		String imageId = Utils.getUUID();	
		File file =new File("tmp/"+imageId+Utils.StringFilter(url));
		FileOutputStream fos = null;
		FileInputStream fis =null;
		InputStream is = null;
		StringBuffer picName = new StringBuffer();
		byte[] b =new byte[1024];
		int length = 0;
		int len = 0;
		try {
			if (!file.exists()) file.createTempFile(imageId, Utils.StringFilter(url));
			fos = new FileOutputStream(file);
			picName.append(imageId).append(Utils.StringFilter(url));
			URL img = new URL(url);
			is = img.openStream();
			
			while((length = is.read(b))>0){
				len += length;
				fos.write(b, 0, length);
			}
			fos.close();
			is.close();
			
			//todo save the img to mysqldatabase;
			ImageUtils iu = new ImageUtils();
			fis = new FileInputStream(file);
			if(iu.insertImg(imageId, referPageId,picName.toString(), "food", fis,len))
				file.delete();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(null != fos){
					fos.close();
				}
				if(null != is){
					is.close();
				}
				if(null != fis){
					fis.close();
				}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		return imageId;
	}
	
	/**
	 * 网页数据插入
	 * @param model
	 * @return
	 */
	private boolean saveBody(WebCrawlDataModel model){
		boolean ret= false;
		DBHelp dbHelp = new DBHelp();
		dbHelp.insert(model);
		dbHelp.close();
		return ret;
	}
	
	public static void main(String[] args) {
		String key = "美食制作";
		FoodMakeCrawl crawl = new FoodMakeCrawl(key);
//        Executor executor=new FoodMakeCrawl(key) {
//            @Override
//            public void execute(CrawlDatum datum, CrawlDatums next) throws Exception {
//                HtmlUnitDriver driver = new HtmlUnitDriver();
//                driver.setJavascriptEnabled(true);
//                driver.get(datum.getUrl());
//                Page page = new Page(datum,200,"",driver.getPageSource().getBytes());
//                page.charset("utf-8");
//                visitor.visit(page, next);
//                if (autoParse && !regexRule.isEmpty()) {
//                    parseLink(page, next);
//                }
//                afterParse(page, next);
//            }
//
//			@Override
//			public void visit(Page page, CrawlDatums next) {
//				visit(page, next);
//			}
//        };
        
//		crawl.setExecutor(executor);
		try {
			crawl.start(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package org.yule.page.crawl.threads;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Condition.Step;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.yule.db.service.dao.CookMenuDao;
import org.yule.db.service.dao.ImageLibDao;
import org.yule.db.service.model.CookMenu;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;
import cn.edu.myself.study.test.ImageUtils;
import cn.edu.myself.study.test.Utils;

public class RefinedFoodCrawl extends RamCrawler{

//	private String seedsRegex = "^http://www.haodou.com.*/recipe/.*";
//	private String globalReg = "^http://www.haodou.com.*";

	private String seedsRegex = "^https://www.xinshipu.com.*/zuofa/.*";
	private String globalReg = "^https://www.xinshipu.com.*";
//	private String imgMark = "[img]";
	
	HtmlUnitDriver driver = new HtmlUnitDriver();
	
	public RefinedFoodCrawl(String key) {
		addSeed(new CrawlDatum(key,"protal").meta("depth", 1));
		setThreads(20);
		setAutoParse(false);
		//addRegex(globalReg);
	}
	
	@Override
	public void visit(Page page, CrawlDatums next) {
		Links links = page.links().filterByRegex(globalReg);
		
		if(page.matchType("cookmenu")){
			if(!analyzor1(page)){
				System.out.println("页面解析出错！ url：" +page.url());
			}
		} 
		
		if(links.isEmpty()) return;
		
		for(int idx=0;idx<links.size();idx++){
			String seed = links.get(idx).toString();
			if(Utils.regex(seed, seedsRegex)){
				next.add(new CrawlDatum(seed).type("cookmenu").meta("depth", 2));
			}else {
				next.add(new CrawlDatum(seed).type("searchPage").meta("depth", 2));
			}
		}
	}

	@Override
	public void execute(CrawlDatum datum, CrawlDatums next) throws Exception {
		driver.get(datum.url());
		Page page = new Page(datum, 200, "text/html", driver.getPageSource().getBytes());
		visitor.visit(page, next);
		afterParse(page, next);
	}
	
	/**
	 * http://www.haodou.com 页面解析器
	 * @param html
	 * @return 
	 */
	private boolean analyzor(Page page){
		String id = Utils.getUUID();
		String url = page.url();
		String name = page.selectText("h1.fl#stitle");
		if (null == name || "".equals(name)) return false;
		
		String descr = page.selectText("dl.des>dd>span#sintro");
		String tips = page.selectText("span#stips");
		
		StringBuffer stuffs = new StringBuffer();
		Elements zStuff = page.select("div.material>ul>li.ingtmgr");
		Elements fStuff = page.select("div.material>ul>li.ingtbur");
		
		for(Element z :zStuff){
			stuffs.append(z.text()).append(",");
		}
		for(Element f :fStuff){
			stuffs.append(f.text()).append(",");
		}
		
		StringBuffer step = new StringBuffer();
		JSONObject jsonStep = new JSONObject();
		Elements steps = page.select("dl.step>dd");
		int len = 1;
		for(Element s :steps){
			Map<String,String> map =new HashMap<String,String>();
			
			String imgId = Utils.getUUID();
			
			String imgUrl = s.getElementsByTag("img").first().attr("abs:src");
			String content = s.getElementsByTag("p").first().text();
			
			map.put("img", imgId);
			map.put("step", content);
			jsonStep.put(len+"", map);
			
			//--todo download img and save
			//downloadAndInsertImg(imgUrl, imgId, id);
			len++;
		}
		step.append(jsonStep.toJSONString());

		//--to save body
		CookMenuDao dao = new CookMenuDao();
		CookMenu model = CookMenu.createModel(id, name, descr, stuffs.toString(), step.toString(), url, tips, "");
		//dao.insert(model);
		dao.close();
		
		return true;
	}
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	public boolean analyzor1(Page page){
		String id = Utils.getUUID();
		String url = page.url();
		String name = page.selectText("h1.font18.no-overflow");
		if (null == name || "".equals(name)) return false;
		
		String descr = null;
		String stuffs = null;
		String steps = null;
		String tips = null;
		
		Element root = page.select("div.bpannel.mt20.p15.re-steps").first();
		Elements eles = root.children();
		for(Element e : eles){
			if("简介".equals(e.child(0).text()))
				descr = e.child(1).text();
			else if("材料".equals(e.child(0).text()))
				stuffs = e.child(1).text();
			else if("做法".equals(e.child(0).text())){
				JSONObject jsonStep = new JSONObject();
				Elements step = e.child(1).getElementsByTag("p");
				Elements stepImg = e.child(1).getElementsByTag("img");
				int len = 0;
				
				for(Element s:step){
					if(null == s.text() || "".equals(s.text())) continue;
					
					Map<String,String> map =new HashMap<String,String>();
					map.put("step", s.text());
					if(null != stepImg && stepImg.size() > 0){
						String iUrl = stepImg.get(len).attr("abs:src");
						String imgId = Utils.getUUID();
						map.put("img", imgId);
						//to do insert img to db
						downloadAndInsertImg(iUrl,imgId,id);
					}
					
					jsonStep.put(len+1+"", map);
					len++;
				}
				steps = jsonStep.toJSONString();
			}else if("小诀窍".equals(e.child(0).text()))
				tips = e.child(1).text();
		}
		
		String imgUrl = page.select("img.photo").first().attr("abs:src");	
		String imgId = Utils.getUUID();
		downloadAndInsertImg(imgUrl,imgId,id);
		
		//--to save body
		CookMenuDao dao = new CookMenuDao();
		CookMenu model = CookMenu.createModel(id, name, descr, stuffs.toString(), steps, url, tips, "");
		dao.insert(model);
		dao.close();
		
		return true;
	}
	
	/**
	 * 将图片下载存入数据库
	 * @param url
	 * @param referPageId
	 * @return
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 */
	private String downloadAndInsertImg(String url,String imgId,String referPageId) {
		File file =new File("tmp/"+imgId+Utils.StringFilter(url));
		FileOutputStream fos = null;
		FileInputStream fis =null;
		InputStream is = null;
		StringBuffer picName = new StringBuffer();
		byte[] b =new byte[1024];
		int length = 0;
		int len = 0;
		try {
			if (!file.exists()) file.createTempFile(imgId, Utils.StringFilter(url));
			fos = new FileOutputStream(file);
			picName.append(imgId).append(Utils.StringFilter(url));
			URL img = new URL(url);
			is = img.openStream();
			
			while((length = is.read(b))>0){
				len += length;
				fos.write(b, 0, length);
			}
			fos.close();
			is.close();
			
			//todo save the img to mysqldatabase;
			ImageLibDao dao = new ImageLibDao();
			fis = new FileInputStream(file);
			if(dao.insertImg(imgId, referPageId,picName.toString(), "food", fis,len))
				file.delete();
			dao.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		return imgId;
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
	
	public static void main(String[] args) {
		//String url = "http://www.haodou.com/";
		String url = "https://www.xinshipu.com/";
		RefinedFoodCrawl crawl = new RefinedFoodCrawl(url);
		try {
			crawl.start(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

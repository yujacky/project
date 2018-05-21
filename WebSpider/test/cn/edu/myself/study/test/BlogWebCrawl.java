package cn.edu.myself.study.test;



import org.apache.log4j.Logger;
import org.jsoup.select.Elements;
import org.mysql.util.CommonUtil;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class BlogWebCrawl extends BreadthCrawler{
	
	private static Logger log = Logger.getLogger(BlogWebCrawl.class);
	
	public BlogWebCrawl(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		for(int i=1;i<11;i++){
			this.addSeed("https://www.cnblogs.com/news/"+i,"list");
		}
        setThreads(20);
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		
		if(page.matchType("list")){
			Elements titles = page.select(".titlelnk");
			Elements shortContents = page.select(".post_item_summary");
			Elements postDates = page.select("div.post_item_foot");

			for(int idx= 0 ;idx < titles.size();idx++){
				String url = titles.attr("abs:href");
				String title = titles.get(idx).text().trim();
				String shortContent = shortContents.get(idx).text().trim();
				String postDate = handlePostDateText(postDates.get(idx).text().trim());
				
				System.out.println("title : "+title +" shortContent:"+shortContent +" postDate:"+postDate);
				
				next.add(new CrawlDatum(url, "content").meta("id", CommonUtil.getUUID()));
			} 
		}else if(page.matchType("content")){
			String id = page.meta("id");
			String title = page.select("div#news_title>a").first().text().trim();
			String content = page.select("div#news_body").first().text();
			
			System.out.println(" id :" +id + "title : "+title +" content:"+content );
		}
	}
	
	public static void main(String[] args) {
		String crawlPath = "tmp";
		BlogWebCrawl crawl = new BlogWebCrawl(crawlPath, true);
		
		try {
			crawl.start(2);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
    private String handlePostDateText(String tmp){
    	StringBuffer sb = new StringBuffer();
    	String []str = tmp.split(" ");
    	
    	if(null != str && null !=str[2] && null != str[3]){
    		return sb.append(str[2]).append(" ").append(str[3]).append(":00").toString();
    	}
    	return null;
    }
}

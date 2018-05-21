package org.yule.page.crawl.threads;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.yule.db.service.dao.CodeDao;
import org.yule.db.service.model.CodeList;

import cn.edu.myself.study.test.Utils;

public class CodeCrawl {
	public static void main(String[] args) {
		String nRegex = "^[0-9]*$";
		
//		String url = "data/1.html";
		String url1 = "data/2.html";

		try {

			
//			Document doc = Jsoup.parse(new File(url), "UTF-8");
//			Elements elements =  doc.select("div.Normal#ess_ctr817_HtmlModule_lblContent").select("table[border=1]").select("tr"); //>table[border=1]>tr
//			
			Document doc1 = Jsoup.parse(new File(url1),"utf-8");
			Elements elements1 =  doc1.select("div.contc#oContent>table").select("tr");//>table>tr
			
			CodeDao cDao = new CodeDao();
//			for(Element e:elements){
//				if(Utils.regex(e.child(0).text(), nRegex)){
//					String id = Utils.getUUID();
//					CodeList code = CodeList.createModel(id,null ,e.child(1).text(), null, e.child(0).text(), null, null, null, null);
//					cDao.insert(code);
//				}
//			}
			
			int len = 0;
			for(Element e: elements1){
				if (len == 0) {
					len++;
					continue;
				}
				
				CodeList code = CodeList.createModel(null,e.child(0).text(), e.child(1).text(), e.child(2).text(), null, e.child(3).text(), e.child(4).text(), null, new Timestamp(System.currentTimeMillis()));
				cDao.update(code);
				len++;
			}
			
			cDao.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

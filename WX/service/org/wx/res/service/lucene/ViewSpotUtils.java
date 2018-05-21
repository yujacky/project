package org.wx.res.service.lucene;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wx.res.service.ViewSpotService;
import org.yule.db.service.dao.ViewSpotDao;
import org.yule.db.service.model.ViewSpot;

import cn.edu.myself.study.test.Utils;

public class ViewSpotUtils {
	
	public final static String indexFile =  "/service/META-INF/res/cache/viewspot_lucene";
	
	public final static String indexUpFile =  "/META-INF/res/cache/viewspot_lucene";
	/**
	 * 创建viewspot 表索引
	 */
	public void createLuceneIndex(){
		
			try{
				String path =  ViewSpotUtils.class.getResource("/").toURI().getPath().toString()+ indexUpFile;
				Directory dir = FSDirectory.getDirectory(path,false);
				IndexReader.unlock(dir); 
				Analyzer an = new StandardAnalyzer();
				IndexWriter iw = new IndexWriter(dir,an,true);
				
				long start = System.currentTimeMillis();
				ViewSpotDao vs = new ViewSpotDao();
				List<ViewSpot> list = vs.getAll();
				vs.close();
				System.out.println("select time :" + (System.currentTimeMillis() - start) +" total :" + list.size());
				for(ViewSpot o :list){
					Document doc = new Document();
					
					Field fieldId = new Field("id",o.getId(),Store.YES,Index.UN_TOKENIZED);
					Field fieldLoc = new Field("location",o.getLocation(),Store.YES,Index.TOKENIZED);
					Field fieldName = new Field("name",o.getName(),Store.YES,Index.TOKENIZED);
					doc.add(fieldId);
					doc.add(fieldLoc);
					doc.add(fieldName);
					iw.addDocument(doc);
				}
				
				System.out.println("Diff time : " +(System.currentTimeMillis() - start));
				iw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		
	}
	
	/**
	 * 查询索引
	 * @param key
	 * @return
	 */
	public static List<String> searchByKey(String key){
		List<String>list = new ArrayList<String>();
		try{
			String path =  ViewSpotUtils.class.getResource("/").toURI().getPath().toString()+ indexUpFile;
			Directory dir = FSDirectory.getDirectory(path, false);
			Analyzer an = new StandardAnalyzer();
			IndexSearcher is = new IndexSearcher(IndexReader.open(dir));
			
			QueryParser parser = new QueryParser("location", an);
			Query q = parser.parse(key);
			long startS = System.currentTimeMillis();
			Hits hits = is.search(q);
			
			Iterator it = hits.iterator();
			while(it.hasNext()){
				Hit hit = (Hit)it.next();
				//System.out.println(hit.get("id") + " " +hit.get("contain") +" "+ " score :"+hit.getScore());
				list.add(hit.get("id"));
			}
			is.close();
			System.out.println("search time : " +(System.currentTimeMillis() - startS));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 添加，更新 viewspot 数据 索引
	 * @param list
	 * @return
	 */
	public static boolean updateViewSpotIndex(List<ViewSpot> list){
		boolean ret = false;
		
		try {
			String path =  ViewSpotUtils.class.getResource("/").toURI().getPath().toString()+ indexUpFile;
			
			Directory dir = FSDirectory.getDirectory(path, false);
			Analyzer an = new StandardAnalyzer();
			IndexWriter iw = new IndexWriter(dir,an,false);
			
			if(!Utils.isEmptyList(list)){
				for(ViewSpot vs : list){
					Document doc = new Document();
					Field fieldId = new Field("id",vs.getId(),Store.YES,Index.UN_TOKENIZED);
					Field fieldLoc = new Field("contain",vs.getName()+" "+vs.getLocation(),Store.YES,Index.TOKENIZED);

					doc.add(fieldId);
					doc.add(fieldLoc);
					
					iw.addDocument(doc);
				}
			}
			iw.optimize();
			iw.close();
			ret = true;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	public static void main(String[] args){
		ViewSpotUtils u = new ViewSpotUtils();
		u.createLuceneIndex();
//		try {
//			String path = System.getProperty("user.dir") + indexFile;
//			System.out.println(path);
//			File file = new File (path);
//			for(String f :file.list()){
//				System.out.println(f.toString());
//			}
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		} 

	}
}

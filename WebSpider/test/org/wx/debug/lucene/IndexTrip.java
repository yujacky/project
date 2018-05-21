package org.wx.debug.lucene;

import java.io.IOException;
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
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.yule.db.service.dao.ViewSpotDao;
import org.yule.db.service.model.ViewSpot;

import cn.edu.myself.study.test.Utils;

///----webspider 项目下
public class IndexTrip {
	
	public static void main(String[] args) {
		IndexTrip it = new IndexTrip();
		it.main();
	}
	
	public void main(){
		try {
			Directory dir= FSDirectory.getDirectory("lucene");
			Analyzer an = new StandardAnalyzer();
//			IndexWriter iw = new IndexWriter(dir,an,true);
			
//			long start = System.currentTimeMillis();
//			ViewSpotDao vs = new ViewSpotDao();
//			List<ViewSpot> list = vs.getAll();
//			vs.close();
//			System.out.println("select time :" + (System.currentTimeMillis() - start) +" total :" + list.size());
//			for(ViewSpot o :list){
//				Document doc = new Document();
//				
//				Field fieldId = new Field("id",o.getId(),Store.YES,Index.UN_TOKENIZED);
//				Field fieldLoc = new Field("contain",o.getName()+" "+o.getLocation(),Store.YES,Index.TOKENIZED);
//
//				doc.add(fieldId);
//				doc.add(fieldLoc);
//				
//				iw.addDocument(doc);
//			}
//			
//			System.out.println("Diff time : " +(System.currentTimeMillis() - start));
//			iw.optimize();
//			iw.close();
			
			IndexSearcher is = new IndexSearcher(IndexReader.open(dir));
			
			QueryParser parser = new QueryParser("contain", an);
			Query q = parser.parse("南沙");
			long startS = System.currentTimeMillis();
			Hits hits = is.search(q);
			
			Iterator it = hits.iterator();
			StringBuffer ids = new StringBuffer();
			while(it.hasNext()){
				Hit hit = (Hit)it.next();
				System.out.println(hit.get("id") + " " +hit.get("contain") +" "+ " score :"+hit.getScore());
				ids.append("'").append(hit.get("id")).append("'").append(",");
			}
			if(!Utils.isStrEmpty(ids.toString())){
				ViewSpotDao vs = new ViewSpotDao();
				List<ViewSpot> list = vs.queryInIds(ids.substring(0, ids.length()-1));
				
//				for(ViewSpot o : list){
//					System.out.println(o.getId());
//				}
			}
			System.out.println("search time : " +(System.currentTimeMillis() - startS));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void doMain(){
		long start = System.currentTimeMillis();
		ViewSpotDao vs = new ViewSpotDao();
		List<ViewSpot> list = vs.getTestAll();
		
		for(ViewSpot o : list){
			System.out.println(o.getId());
		}
		System.out.println("Diff time : " +(System.currentTimeMillis() - start));
	}
}

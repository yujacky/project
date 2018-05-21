package org.wx.res.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.wx.res.model.ViewSpot;
import org.wx.res.msg.dao.ViewSpotDao;
import org.wx.res.service.lucene.ViewSpotUtils;

import cn.edu.myself.study.test.Utils;

@Component
public class ViewSpotService  implements InitializingBean{

	@Resource
	private ViewSpotDao viewSpotDao;
	
	private String emoj1 = "😳";
	
	private final int  limit = 2024;
	
	private final String charset = "UTF-8";
	public String genViewSpotReplyByKey(String key){
		String term = key.substring(1);
		if(Utils.isStrEmpty(term)){
			return emoj1;
		}
		
		List<ViewSpot> list = getViewSpotByKey(term);
		
		if(Utils.isEmptyList(list)){
			return "抱歉，没有景点";
		}
		StringBuffer sb = new StringBuffer();
		int bufsize = 0;
		for(int i=0;i<list.size();i++){
			String num =  String.valueOf(i+1);
			String sym =  ". ";
			String name = list.get(i).getName();
			String line = "\n";
			//-- 计算字符长度，限制在limit
			if((bufsize = calc(bufsize,num,sym,name,line)) > limit) {
				sb.append("  更多...");
				break;
			}
			
			sb.append(num).append(sym).append(name).append(line);
		}
		
		System.out.println(sb);
		return sb.toString();
	}
	

	private int calc(int bufsize, String num, String sym, String name,String line) {
		try {
			bufsize += num.getBytes(charset).length ;
			bufsize += sym.getBytes(charset).length ;
			bufsize += name.getBytes(charset).length ;
			bufsize += line.getBytes(charset).length ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return bufsize;
	}


	private List<ViewSpot> getViewSpotByKey(String key){
		List<String> ids = ViewSpotUtils.searchByKey(key);
		
		if(ids.isEmpty()) return null;
		
		List<ViewSpot> list = viewSpotDao.getDataInIds(ids);
		return list;
	}
	
	private ViewSpot getViewSpotById(String id){
		ViewSpot obj = viewSpotDao.getDataById(id);
		return obj;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ViewSpotUtils u = new ViewSpotUtils();
		u.createLuceneIndex();
	}
}

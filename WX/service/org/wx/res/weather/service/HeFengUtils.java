package org.wx.res.weather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.wx.res.util.WxUtil;

public class HeFengUtils {

	int TYPE1 = 1;
	int TYPE2 = 2;
	//--数据行
	int datal1 = 3;
	int datal2 = 2;
	
	public static void main(String[] args) {
		
		//城市/地区编码	英文	中文	国家代码	国家英文	国家中文	省英文	省中文	所属上级市英文	所属上级市中文	纬度	经度
		String city = "http://cdn.heweather.com/china-city-list.txt";
		//coloumn 代码	中文	英文	ICON
		String wetherInfo = "http://cdn.heweather.com/condition-code.txt";
		
		HeFengUtils util = new HeFengUtils();
		try {
			util.prase(wetherInfo, util.TYPE2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void prase(String seed,int type) throws IOException{
		URL url = new URL(seed);
		URLConnection con = url.openConnection();
		InputStreamReader bis = new InputStreamReader(con.getInputStream());
		BufferedReader br = new BufferedReader(bis);
		String str = null;
		int line = 1;
		
		if(TYPE1 == type){
			while((str = br.readLine()) != null){
				if(line <= datal1) {line++;continue;}
					
				HFCity model = HFCity.createModel(genHFCityMap(str));
				
				HFCityDao hfdao= new HFCityDao();
				hfdao.insert(model);
				hfdao.close();
				
				line++;
			}
		}else if(TYPE2 == type){
			while((str = br.readLine()) != null){
				if(line <= datal2) {line++;continue;}
					
				HFCondition model = HFCondition.createModel(genHFConditionMap(str));
				
				HFConditionDao hfdao= new HFConditionDao();
				hfdao.insert(model);
				hfdao.close();
				
				line++;
			}
		}
	}
	
	public Map<String,Object> genHFCityMap(String text){
		String id = WxUtil.genUUID();
		Map <String,Object> map = new HashMap<String,Object>();
		
		String []str = text.split("	");
		if (str.length != 12) return map;
		//城市/地区编码	英文	中文	国家代码	国家英文	国家中文	省英文	省中文	所属上级市英文	所属上级市中文	纬度	经度
		map.put("id", id);
		map.put("code", str[0]);
		map.put("ename", str[1]);
		map.put("name", str[2]);
		map.put("countryCode", str[3]);
		map.put("countryEname", str[4]);
		map.put("countryName", str[5]);
		map.put("eprovince", str[6]);
		map.put("province", str[7]);
		map.put("superior", str[8]);
		map.put("esuperior", str[9]);
		map.put("latitudete", str[10]);
		map.put("longitudete", str[11]);

		return map;
	}
	
	public Map<String,Object> genHFConditionMap(String text){
		String id = WxUtil.genUUID();
		Map <String,Object> map = new HashMap<String,Object>();
		
		String []str = text.split("	");
		if (str.length != 4) return map;
		map.put("id", id);
		map.put("code", str[0]);
		map.put("name", str[1]);
		map.put("ename", str[2]);
		map.put("icon", str[3]);

		return map;
	}
	
	public boolean downloadImg(){
		return false;
	}
}

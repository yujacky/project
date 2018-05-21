package org.wx.res.weather.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yule.db.service.dao.BaseDao;

import com.mysql.jdbc.PreparedStatement;

public class HFWeatherDao extends BaseDao{

	public HFWeatherDao() {
	}
	
	public boolean insert(HeFengWeatherH5 model){
		boolean ret = false;
		String sql = "insert into hefengweatherh5(id,cityid,cityname,data) values (?,?,?,?)";
		
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, model.getId());
			pst.setString(2, model.getCityid());
			pst.setString(3, model.getCityname());
			System.out.println(model.getData().length());
			pst.setString(4, model.getData());
			
			pst.execute();
			
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return ret;
	}
	
	public	HeFengWeatherH5 queryByCityName(String cityname){
		String sql = "select * from hefengweatherh5 where cityname = ? limit 1";
		HeFengWeatherH5 hf = null;
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, cityname);
			pst.execute();
			
			ResultSet rs =pst.getResultSet();
			while(rs.next()){
				hf = new HeFengWeatherH5();
				hf.setCityname(cityname);
				hf.setData(rs.getString("data"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hf;
	}
}

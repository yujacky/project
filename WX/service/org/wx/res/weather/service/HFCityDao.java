package org.wx.res.weather.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.yule.db.service.dao.BaseDao;
import com.mysql.jdbc.PreparedStatement;

public class HFCityDao extends BaseDao{
	
	public HFCityDao() {
	}
	
	public boolean insert(HFCity model){
		String sql = "insert into hefengcity(id,code,ename,name,countrycode,countryname,eprovince,province,superior,esuperior,longitudete,latitudete)" +
				" value (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, model.getId());
			pst.setString(2, model.getCode());
			pst.setString(3, model.getEname());
			pst.setString(4, model.getName());
			pst.setString(5, model.getCountryCode());
			pst.setString(6, model.getCountryName());
			pst.setString(7, model.getEprovince());
			pst.setString(8, model.getProvince());
			pst.setString(9, model.getSuperior());
			pst.setString(10, model.getEsuperior());
			pst.setString(11, model.getLongitudete());
			pst.setString(12, model.getLatitudete());
			
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<HFCity> pageQueryCityCode(int start,int queryLen){
		String sql = "select code,name from hefengcity order by createdate asc limit ?,? ";
		List<HFCity> list = new ArrayList<HFCity>();
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, start);
			pst.setInt(2, queryLen);
			pst.execute();
			
			ResultSet rs =pst.getResultSet();
			while(rs.next()){
				HFCity obj = new HFCity();
				obj.setCode(rs.getString("code"));
				obj.setName(rs.getString("name"));
				
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int queryQuantity(){
		int ret = 0;
		String sql = "select count(id) as cnt from hefengcity ";
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.execute();
			ResultSet results = pst.getResultSet();
			
			if(results.first()){
				ret = results.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		HFCityDao dao = new HFCityDao();
		System.out.println(dao.pageQueryCityCode(100,10));
		dao.close();
	}
}

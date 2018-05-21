package org.yule.db.service.dao;

import java.sql.SQLException;

import org.yule.db.service.model.CookMenu;

import com.mysql.jdbc.PreparedStatement;

public class CookMenuDao extends BaseDao{

	public CookMenuDao(){

	}
	
	public boolean insert(CookMenu model){
		String sql = "insert into cookmenu(id,name,descr,stuff,content,url,tips)" +
				" values(?,?,?,?,?,?,?)";
		boolean ret = false;
		
		try {

			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, model.getId());
			pst.setString(2, model.getName());
			pst.setString(3, model.getDescr());
			pst.setString(4, model.getStuff());
			pst.setString(5, model.getContent());
			pst.setString(6, model.getUrl());
			pst.setString(7, model.getTips());
			ret= pst.execute();
			
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

}

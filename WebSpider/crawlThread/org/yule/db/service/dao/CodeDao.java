package org.yule.db.service.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.yule.db.service.model.CodeList;

import cn.edu.myself.study.test.Utils;

import com.mysql.jdbc.PreparedStatement;

public class CodeDao extends BaseDao{

	public CodeDao() {
	}
	
	public String queryNameByAlias(String alias){
		String ret = null;
		String sql = "Select code From codelist Where alias = ?";
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, alias);
			ResultSet model =pst.executeQuery();

			if(model.next()) ret = model.getString("code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public boolean insert(CodeList model){
		String sql = "insert into codelist(id,name,code)" +
				" values(?,?,?)";
		boolean ret = false;
		
		try {

			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, model.getId());
			pst.setString(2, model.getName());
			pst.setString(3, model.getCode());
			ret= pst.execute();
			
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public boolean update(CodeList model){
		String sql = "update codelist ";
		StringBuffer sb = new StringBuffer();
		boolean ret = false;
		
		if(!Utils.isStrEmpty(model.getEname())){
			sb.append("  ename=").append(" ? ").append(",");
		}
		if(!Utils.isStrEmpty(model.getAlias())){
			sb.append("  alias=").append(" ? ").append(",");
		}
		if(!Utils.isStrEmpty(model.getPhonePreffix())){
			sb.append("  phonePreffix=").append(" ? ").append(",");
		}
		if(!Utils.isStrEmpty(model.getTimeDif())){
			sb.append("  timeDif=").append(" ? ").append(",");
		}
		if(!Utils.isDateEmpty(model.getModifyDate())){
			sb.append("  modifyDate=").append(" ? ").append(",");
		}
		sql = sb.insert(0, " set ").insert(0, sql).substring(0, sb.length()-1) + " where name = ?";
		System.out.println(sql);
		try {

			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, model.getEname());
			pst.setString(2, model.getAlias());
			pst.setString(3, model.getPhonePreffix());
			pst.setString(4, model.getTimeDif());
			pst.setTimestamp(5, model.getModifyDate());
			pst.setString(6, model.getName());
			
			System.out.println(pst.asSql());
			ret= pst.execute();
			
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}

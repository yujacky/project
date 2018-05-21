package org.yule.db.service.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yule.db.service.model.ViewSpot;
import org.yule.page.crawl.threads.UpdateViewSpotCrawlThread;

import cn.edu.myself.study.test.Utils;

import com.mysql.jdbc.PreparedStatement;

public class ViewSpotDao extends BaseDao{

	public ViewSpotDao() {
	}
	
	public boolean insert(ViewSpot model){
		String sql = "insert into ViewSpot(id,name,location)" +
				" values(?,?,?)";
		boolean ret = false;
		
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, model.getId());
			pst.setString(2, model.getName());
			pst.setString(3, model.getLocation());
			ret= pst.execute();
			
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public boolean updateDesc(ViewSpot model){
		if(Utils.isStrEmpty(model.getId())) return false;
		
		String sql = "update ViewSpot ";
		StringBuffer sb = new StringBuffer();
		boolean ret = false;
		
		if(!Utils.isStrEmpty(model.getName())){
			sb.append("  name=").append(" ? ").append(",");
		}
		if(!Utils.isStrEmpty(model.getLocation())){
			sb.append("  location=").append(" ? ").append(",");
		}
		if(!Utils.isStrEmpty(model.getDescription())){
			sb.append("  description=").append(" ? ").append(",");
		}
	//	if(!Utils.isDateEmpty(model.getModifyDate())){
			sb.append("  modifyDate=").append(" ? ").append(",");
	//	}
		sql = sb.insert(0, " set ").insert(0, sql).substring(0, sb.length()-1) + " where id = ?";
		System.out.println(sql);
		try {
			conn.setAutoCommit(false);
			pst = (PreparedStatement) conn.prepareStatement(sql);
//			pst.setString(1, model.getName());
//			pst.setString(2, model.getLocation());
			pst.setString(1, model.getDescription());
			pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pst.setString(3, model.getId());
			
			System.out.println(pst.asSql());
			ret= pst.executeUpdate() > 0;
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public List<ViewSpot> pageQueryViewSpot(int start,int queryLen){
		String sql = "select id,name,location from viewspot where description is null order by createdate asc limit ?,? ";
		List<ViewSpot> list = new ArrayList<ViewSpot>();
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, start);
			pst.setInt(2, queryLen);
			pst.execute();
			
			ResultSet rs =pst.getResultSet();
			while(rs.next()){
				ViewSpot obj = new ViewSpot();
				obj.setId(rs.getString("id"));
				obj.setName(rs.getString("name"));
				obj.setLocation(rs.getString("location"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<ViewSpot> queryById(String id){
		String sql = "select id,name,location from viewspot where id =?  ";
		List<ViewSpot> list = new ArrayList<ViewSpot>();
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.execute();
			
			ResultSet rs =pst.getResultSet();
			while(rs.next()){
				ViewSpot obj = new ViewSpot();
				obj.setId(rs.getString("id"));
				obj.setName(rs.getString("name"));
				obj.setLocation(rs.getString("location"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<ViewSpot> queryInIds(String ids){
		String sql = "select id,name,location from viewspot where id in (%s)  ";
		sql = String.format(sql, ids);
		List<ViewSpot> list = new ArrayList<ViewSpot>();
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.execute();
			System.out.println(pst.asSql());
			ResultSet rs =pst.getResultSet();
			while(rs.next()){
				ViewSpot obj = new ViewSpot();
				obj.setId(rs.getString("id"));
				obj.setName(rs.getString("name"));
				obj.setLocation(rs.getString("location"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<ViewSpot> getAll(){
		String sql = "select id,name,location,description from viewspot";
		List<ViewSpot> list = new ArrayList<ViewSpot>();
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.execute();
			
			ResultSet rs =pst.getResultSet();
			while(rs.next()){
				ViewSpot obj = new ViewSpot();
				obj.setId(rs.getString("id"));
				obj.setName(rs.getString("name"));
				obj.setLocation(rs.getString("location"));
				obj.setDescription(rs.getString("description"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<ViewSpot> getTestAll(){
		String sql = "select id,name,location from viewspot where location like '%广州%'";
		List<ViewSpot> list = new ArrayList<ViewSpot>();
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.execute();
			
			ResultSet rs =pst.getResultSet();
			while(rs.next()){
				ViewSpot obj = new ViewSpot();
				obj.setId(rs.getString("id"));
				obj.setName(rs.getString("name"));
				obj.setLocation(rs.getString("location"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public long viewSpotCount(){
		String sql = "select count(id) as cnt from viewspot where description is null";
		long ret = 0;
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.execute();
			
			ResultSet rs =pst.getResultSet();
			while(rs.next()){
				ret = rs.getLong("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
//		ViewSpotDao vs = new ViewSpotDao();
//		System.out.println(vs.viewSpotCount());
		UpdateViewSpotCrawlThread t = new UpdateViewSpotCrawlThread ();
		t.start();
	}
}

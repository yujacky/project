package org.yule.db.service.dao;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.model.WebCrawlDataModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class BaseDao {
	public static final String url = "jdbc:mysql://127.0.0.1:3309/webdata?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "rootadmin";

	protected Connection conn = null;
	protected PreparedStatement pst = null;

	public BaseDao(){
		init();
	}
	
	private void init(){
		if(null == conn){
			try {
				Class.forName(name);
				conn = (Connection) DriverManager.getConnection(url, user, password);// 获取连接
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {// 指定连接类型
				e.printStackTrace();
			}
			
		}
	}
	
	public void close() {
		try {
			if(null!=this.conn && !this.conn.isClosed()){
				this.conn.close();
			}
			if(null!=this.pst && !this.pst.isClosed()){
				this.pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public boolean insert(WebCrawlDataModel model){
//		String sql = "insert into webcrawldata(id,title,content,url,typedata)values(?,?,?,?,?)";
//		boolean ret = false;
//		
//		try {
//
//			pst = (PreparedStatement) conn.prepareStatement(sql);
//			pst.setString(1, model.getId());
//			pst.setString(2, model.getTitle());
//			pst.setString(3, model.getContent());
//			pst.setString(4, model.getUrl());
//			pst.setString(5, model.getType());
//			ret= pst.execute();
//			
//			ret = true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return ret;
//	}
	
	public boolean insertImg(String id,String pageId,String picName,String desc,FileInputStream fis,int len){
		boolean ret = false;
		String sql = "insert into imageLib(id,pageId,descr,image,picName) values (?,?,?,?,?)";

		try {
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pageId);
			pst.setString(3, desc);
			pst.setBinaryStream(4, fis, len);
			pst.setString(5,picName);
			ret = pst.execute();
			ret =true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}
	
    public static synchronized String getUUID(){
    	String idString = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    	return idString;
    }
	
//	public static void main(String[] args) {
//        DBHelp dbHelp;
//		try {
//			dbHelp = new DBHelp();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			return;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return;
//		}
//		
//        WebCrawlDataModel model = new WebCrawlDataModel();
//        model.setId(getUUID());
//        model.setTitle("test");
//        model.setContent("test");
//        model.setUrl("test");
//        model.setType("news");
//        dbHelp.insert(model);
//        dbHelp.close();
//	}
}

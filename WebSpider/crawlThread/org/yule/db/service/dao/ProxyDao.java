package org.yule.db.service.dao;

import java.sql.SQLException;

import cn.edu.myself.study.test.Utils;

import com.mysql.jdbc.PreparedStatement;

public class ProxyDao extends BaseDao{

	public ProxyDao(){
	}
	
	
	public boolean insert(String id,String name,String ipaddr,String port,String serveName,String type,String speed,String connectWait,String aliveDate,boolean isAnony,boolean isUsable){
		boolean ret = false;
		CodeDao cDao = new CodeDao();
		String countryCode = cDao.queryNameByAlias(name);
		cDao.close();
		
		//if(Utils.isStrEmpty(countryCode)) return ret;
		
		String sql = "insert into ProxyList(id,countryCode,ipaddr,port,serveName,type,speed,connectWait,aliveDate,isAnony,isUsable) " +
								" values (?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, countryCode);
			pst.setString(3, ipaddr);
			pst.setString(4, port);
			pst.setString(5,serveName);
			pst.setString(6,type);
			pst.setString(7,speed);
			pst.setString(8,connectWait);
			pst.setString(9,aliveDate);
			pst.setBoolean(10,isAnony);
			pst.setBoolean(11,isUsable);
			ret = pst.execute();
			ret =true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}

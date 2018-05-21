package org.wx.res.weather.service;

import java.sql.SQLException;
import org.yule.db.service.dao.BaseDao;
import com.mysql.jdbc.PreparedStatement;

public class HFConditionDao extends BaseDao{
	
	public HFConditionDao() {
	}
	
	public boolean insert(HFCondition model){
		String sql = "insert into hefengcondition(id,code,ename,name,icon)" +
				" value (?,?,?,?,?)";
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, model.getId());
			pst.setString(2, model.getCode());
			pst.setString(3, model.getEname());
			pst.setString(4, model.getName());
			pst.setString(5, model.getIcon());
			
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}

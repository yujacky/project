package org.yule.db.service.dao;

import java.io.FileInputStream;
import java.sql.SQLException;

import org.model.ImageLib;
import com.mysql.jdbc.PreparedStatement;

public class ImageLibDao extends BaseDao{

	public ImageLibDao() {
	}

	public boolean insert(ImageLib model,FileInputStream fis,int len){
		boolean ret = false;
		String sql = "insert into imageLib(id,pageId,descr,image,picName) values (?,?,?,?,?)";

		try {
			pst=(PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, model.getId());
			pst.setString(2, model.getPageId());
			pst.setString(3, model.getDescr());
			pst.setBinaryStream(4, fis, len);
			pst.setString(5,model.getPicName());
			ret = pst.execute();
			ret =true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
}

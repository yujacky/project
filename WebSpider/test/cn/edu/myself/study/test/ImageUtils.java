package cn.edu.myself.study.test;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.mysql.util.DBHelp;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.edu.hfut.dmic.webcollector.util.MysqlHelper;


public class ImageUtils {
	
	public ImageUtils() throws PropertyVetoException {
		
	}
	
	public boolean insertImg(String id,String pageId,String picName,String desc,FileInputStream fis,int len) throws SQLException{
		DBHelp dbhelp = new DBHelp();
		dbhelp.insertImg(id, pageId, picName, desc, fis, len);
		dbhelp.close();
		
		return true;
	}
	
	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		File file =new File(path);
		File [] files = file.listFiles();
		for(int i = 0 ;i < files.length; i++){
			if(regex(files[i].getName())){
				System.out.println(files[i].getName());
				if(files[i].delete()){
					System.out.println("删除成功");
				}
			}
		}
	}
	
	public static boolean regex(String text){
		String reg = ".*\\.gif$|.*\\.jpg$|.*\\.JPG$|.*\\.PNG$|.*\\.png$|.*\\.jpeg$|.*\\.JPEG$|.*\\.BMP";
        Pattern p=Pattern.compile(reg); 
        Matcher m=p.matcher(text); 
        return m.find();
	}
}

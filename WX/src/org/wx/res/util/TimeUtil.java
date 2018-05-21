package org.wx.res.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeUtil {

	/**
	 * 取得系统时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getSystemTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String sysTime = df.format(new Date(System.currentTimeMillis()));// new Date()为获取当前系统时间
		
		return sysTime;
	}
	
	/**
	 * 取得系统时间数
	 * @return
	 */
	public static String getSystemMillie(){
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(System.currentTimeMillis());
		
		return sbBuffer.toString();
	}
}

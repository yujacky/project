package org.wx.res.task;

import java.util.TimerTask;

/**
 * 定时获取access_token 每20分钟刷新
 * @author Administrator
 *
 */
public class GainAccessTokenTask extends TimerTask{
	
	public static String accessToken = null;
	@Override
	public void run() {
		System.out.println("GainAccessTokenTask test!");
	}
	
}

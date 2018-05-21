package org.wx.debug.WxService;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.wx.res.gen.Xml.ReplyXmlMsgUtil;

public class WxServiceDebug {

	public static void main(String[] args) {
		String msg = ReplyXmlMsgUtil.genTextMsg("1", "2", "", "@广州");
		String seed = "http://localhost:8080/WX/wxIndex.do";
		try {
			URL url = new URL(seed);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			OutputStream os = con.getOutputStream();
			OutputStreamWriter oos = new OutputStreamWriter(os);
			oos.write(msg);
			oos.flush();
			con.connect();
			//InputStream is = con.getInputStream();
			System.out.println(msg);
			InputStream is = con.getInputStream();
			os.close();
			is.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}

package cn.edu.myself.study.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.Proxy.Type;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HttpPage {

	public static void main(String[] args) {
//		String seed = "http://api.xicidaili.com/free016.txt";
//		
//		try {
//			URL url = new URL(seed);
////			InputStream in = url.openStream();
//			URLConnection con = url.openConnection();
//			con.connect();
//			String header = con.getHeaderField(0);
//			System.out.println(header);
//			if(header.indexOf("200")>-1){
//				System.out.println("ok");
//			}
//			System.out.println("userInfo:"+url.getUserInfo());
//			System.out.println("userInfo:"+con.getDefaultUseCaches());
//			
////			InputStreamReader isr = new InputStreamReader(in);
////			BufferedReader br = new BufferedReader(isr);
////			String str = null;
////			while((str = br.readLine())!=null){
////				System.out.println(str);
////			}
//			
//			Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();
//			
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String addr = "121.31.103.185";
//		String port = "8123";
//		if(!testConnect(addr,port)) {
//			System.out.println("代理不可用！");
//			return;
//		}
//		try{
////			System.setProperty("http.proxyHost","122.96.59.105");
////			System.setProperty("http.proxyPort","83");
//			String seed = "http://www.xicidaili.com/";
//			URL url = new URL(seed);
//			URLConnection con = url.openConnection();
//			con.setConnectTimeout(3000);
//			con.setAllowUserInteraction(true);
//			//con.connect();
//			InputStream in =con.getInputStream();
//			InputStreamReader isr = new InputStreamReader(in);
//			BufferedReader br = new BufferedReader(isr);
//			String str = null;
//			while((str = br.readLine())!=null){
//				System.out.println(str);
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		//testConnect("110.73.42.167","8123");
		
//		String seed = genUrlByKey("广州天气");
////		Connection.Response res = null;  
////        int itimeout = 60000; 
//		
//		try {
//			String path = "data/test.txt";
//			System.out.println(path);
//			File file = new File(path);
//			if(!file.exists()) file.createNewFile();
//			FileOutputStream fos = new FileOutputStream(file);
//			
//			Document doc = Jsoup.parse(new URL(seed), 3000);
//			fos.write(doc.html().getBytes("UTF-8"));
//			fos.close();
//			//System.out.println(doc.html());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
		String str = "黑龙江省民族博物馆成立于1988年，是以哈尔滨文庙为馆舍，建立了全国第一家省级专业性的民族博物馆。是我国东北地区现存规模最大、保存最为完整的一座祭祀孔子的庙宇。1996年被国务院批准为国家重点文物保护单位。"
				+"黑龙江省民族博物馆是以哈尔滨文庙为馆舍，建立了全国第一家省级专业性的民族博物馆。1985年12月25日，全国政协副主席、中国佛教协会会长赵朴初先生应邀题写了\"黑龙江民族博物馆\"馆名。";
		System.out.println(str.length());
	}
	
	private static String genUrlByKey(String str) {
		 return String.format("https://m.baidu.com/s?word=%s", str);
	}

	public static boolean testConnect(String addr,String port){
		String seed = "https://www.baidu.com/";
		boolean ret = false;
		try {
			URL url = new URL(seed);
			url.openConnection(new Proxy(Type.HTTP,new InetSocketAddress(addr,Integer.parseInt(port))));
			URLConnection con = url.openConnection();
			con.setConnectTimeout(3000);
			con.connect();
			String retStr = con.getHeaderField(0);
			if(retStr.indexOf("200")>-1){
				
				System.out.println("成功!");
				ret = true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static boolean test(){
		boolean ret = false;
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		driver.setProxy("121.31.103.185", 8123);
		String page = driver.getPageSource();
		System.out.println(page);
		
		return true;
	}
}

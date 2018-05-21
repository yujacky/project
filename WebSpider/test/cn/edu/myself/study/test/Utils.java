package cn.edu.myself.study.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Utils {

	public static String StringFilter(String str) throws PatternSyntaxException { 
		// 获取 .jpg .jpeg .bmp .gif .png
		String regEx="\\.{1}[Jj]{1}[Pp]{1}[Gg]{1}|\\.{1}[Bb]{1}[Mm]{1}[Pp]{1}|\\.{1}[Jj]{1}[Pp]{1}[Ee]{1}[Gg]{1}|\\.{1}[Gg]{1}[Ii]{1}[Ff]{1}|\\.{1}[Pp]{1}[Nn]{1}[Gg]{1}"; 
		String ret = null;
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		while(m.find()){
			ret = m.group();
		}
		return ret;
	} 
	
    public synchronized static String getUUID(){
    	String idString = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    	return idString;
    }
    
    /**
     * 过滤a标签
     * 
     * @param html
     * @return
     */
    public String filterTags(String html) {
		String regEx_a="<a[^>]*?>[\\s\\S]*?<\\/a>"; //定义a的正则表达式 
        Pattern p_script=Pattern.compile(regEx_a,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(html); 
        html=m_script.replaceAll(""); //过滤a标签 
        
        return html;
    }	
    
	/**
	 * 正则式 匹配
	 * @param text
	 * @return
	 */
	public static boolean regex(String text,String regex){
        Pattern p=Pattern.compile(regex); 
        Matcher m=p.matcher(text); 
        return m.find();
	}
	
	public static String getMatchStr(String text,String regex){
        return Pattern.compile(regex).matcher(text).replaceAll("");
	}
	
	public static boolean isStrEmpty(String text){
		if(null != text && !"".equals(text)) return false;
		else return true;
	}
	
	public static boolean isDateEmpty(Timestamp date){
		if(null != date && !"".equals(date.toString())) return false;
		else return true;
	}
	
	public static String obj2Str(Object obj){
		if(null!=obj && !"".equals(obj)) return obj.toString();
		return null;
	}
	
	public static Timestamp obj2Dt(Object obj){
		if(null!=obj && !"".equals(obj)) return (Timestamp)obj;
		return null;
	}
	
	/**
	 * unicode 转中文
	 */
	public static String unicodeToCn(String unicode) {
	    /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
	    String[] strs = unicode.split("\\\\u");
	    String returnStr = "";
	    // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
	    for (int i = 1; i < strs.length; i++) {
	      returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
	    }
	    return returnStr;
	}
	
	/**
	 * 中文转unicode
	 * @param cn
	 * @return
	 */
	public static String cnToUnicode(String cn) {
	    char[] chars = cn.toCharArray();
	    String returnStr = "";
	    for (int i = 0; i < chars.length; i++) {
	      returnStr += "\\u" + Integer.toString(chars[i], 16);
	    }
	    return returnStr;
	}
	
	//-- 向上取整除法
	public static int chufaH(long total,int len){
		int result = (int)Math.ceil(BigDecimal.valueOf(total)
				   .divide(BigDecimal.valueOf(len),BigDecimal.ROUND_HALF_UP)
				   .doubleValue());
		return result;
	}
	
	//-- 向下取整除法
	public static int chufaL(long total,int len){
		int result = (int)Math.floor(BigDecimal.valueOf(total)
				   .divide(BigDecimal.valueOf(len),BigDecimal.ROUND_HALF_UP)
				   .doubleValue());
		return result;
	}
	//--时间转换相关函数
	public static String stampToDate(String s){
	    String res;
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    long lt = new Long(s);
	    Date date = new Date(lt);
	    res = simpleDateFormat.format(date);
	    return res;
	}
	
	public static String longToDate(long s){
	    String res;
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    long lt = new Long(s);
	    Date date = new Date(lt);
	    res = simpleDateFormat.format(date);
	    return res;
	}
	
	public static String longToTime(long str){
		StringBuffer sb = new StringBuffer();
		int hour = 0;
		int min = 0;
		int sec = 0;
		
		int s = 1000;
		int m = 60 * s;
		int h = 60 * m;
		
		long result = str;
		
		do{
			if(result >= h){
				hour = chufaL(result, h);
				result = result%h;
			}else if(result >= m){
				min = chufaL(result, m);
				result = result%m;
			}else if(result >=s){
				sec = chufaL(result, s);
				result = result%s;
			}
		}while(result >= s);
		
		return sb.append(hour).append("小时").append(min).append("分").append(sec).append(".").append(result).toString();
	}
	
	public static boolean isEmptyList(List list){
		boolean ret = true;
		
		if(null != list && list.size() > 0 ){
			ret = false;
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
//		File errFile = new File("log/tm.txt");
//		PrintStream err = null;
//		try {
//			err = new PrintStream(new FileOutputStream(errFile));
//			System.setOut(err);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		
//		Random r = new Random(System.currentTimeMillis());
//		StringBuffer sb = new StringBuffer();
//		for(int n=0;n<6;n++){
//			int i = r.nextInt(49);
//			sb.append(i+1).append(" ");
//		}
//		System.out.println(sb);
//		
//		if(null != err){
//			err.close();
//		}
//		System.out.println(regex("26C","[^0-9]"));
//		System.out.println(getMatchStr("26C","[^0-9]"));
		List list = null ;
		System.out.println(isEmptyList(list));
	}
}

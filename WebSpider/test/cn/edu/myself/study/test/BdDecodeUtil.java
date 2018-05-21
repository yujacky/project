package cn.edu.myself.study.test;

import java.io.UnsupportedEncodingException;

public class BdDecodeUtil {

	public static String base64encodechars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private String seed = "http://sina.lt/api.php?url=%s&action=restore";
	public static Integer [] base64decodechars = new Integer[]{
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
			52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
			-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
			-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
			41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};
	
	public static String base64encode(String str) {
		//var out, i, len;
		String out= "";
		int i;
		int len;
		int c1, c2, c3;
		len = str.length();

		i = 0;
		while (i < len) {
			c1 = str.codePointAt(i++) & 0xff;
			if (i == len) {
				out += base64encodechars.charAt(c1 >> 2);
				out += base64encodechars.charAt((c1 & 0x3) << 4);
				out += "==";
				break;
			}
			c2 = str.codePointAt(i++);
			if (i == len) {
				out += base64encodechars.charAt(c1 >> 2);
				out += base64encodechars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xf0) >> 4));
				out += base64encodechars.charAt((c2 & 0xf) << 2);
				out += "=";
				break;
			}
			c3 = str.codePointAt(i++);
			out += base64encodechars.charAt(c1 >> 2);
			out += base64encodechars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xf0) >> 4));
			out += base64encodechars.charAt(((c2 & 0xf) << 2) | ((c3 & 0xc0) >> 6));
			out += base64encodechars.charAt(c3 & 0x3f);
		}
		return out;
	}
	
	public static String utf16to8(String str) {
		String out;
		int i, len, c;
		out = "";
		len = str.length();
		for (i = 0; i < len; i++) {
			c = str.codePointAt(i);
			if ((c >= 0x0001) && (c <= 0x007f)) {
				out += (char)str.codePointAt(i);
			} else if (c > 0x07ff) {
				out += (char)(0xe0 | ((c >> 12) & 0x0f));
				out += (char)(0x80 | ((c >> 6) & 0x3f));
				out += (char)(0x80 | ((c >> 0) & 0x3f));
			} else {
				out += (char)(0xc0 | ((c >> 6) & 0x1f));
				out += (char)(0x80 | ((c >> 0) & 0x3f));
			}
		}
		return out;
	}
	
	public static String utf16toutf8(String str){
		String s = null;
		try {
			s = new String(str.getBytes("UTF-16"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	 /** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     */  
//    public static String base64Encode(byte[] bytes){  
//        String base64 = Base64.encodeBase64URLSafeString(bytes);
//        return base64;  
//    }  
      
    /** 
     * base 64 decode 
     * @param base64Code 待解码的base 64 code 
     * @return 解码后的byte[] 
     * @throws Exception 
     */  
//    public static byte[] base64Decode(String base64Code) throws Exception{  
//        return Base64.decodeBase64(base64Code.getBytes());
//    }  
//    
//    public static String change (String url) throws Exception{
//    	return base64Encode(new String(url.getBytes("UTF-16"),"UTF-8").getBytes());
//    }
//    
	public static String bdUrlDecode(String url){
		return base64encode(utf16to8(url));
	}
    public static void main(String[] args) {
    	String str = "http://www.baidu.com/link?url=Dqgb3Y6h19cQVSnsReglYnyqrZtcYMIgsyauqL-5Fh7x3BN7PsA6kP8vmDBaNrwiLO01YuQZQOLPq8H_ktdHDa";
		try {
			System.out.println(bdUrlDecode(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

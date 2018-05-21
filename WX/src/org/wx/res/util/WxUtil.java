package org.wx.res.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.UUID;

import org.wx.res.constant.Constant;

public class WxUtil {

    //** 8 位 UCS 转换格式     
    public static final String UTF_8 = "UTF-8";
    
    /**
     * 验证签名
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp,
            String nonce) {
        String[] arr = new String[] { Constant.TOKEN, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        //Arrays.sort(arr);
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     * 
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * 
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    public static void sort(String a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
    
    /**
     * 生成唯一 ID
     * @return
     */
    public static String genUUID(){
    	return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
    
    /**
     * 获取msgId
     * @return
     */
    public static synchronized String wxGetID(){
    	StringBuffer sb = new StringBuffer();
    	sb.append(System.currentTimeMillis());
    	
    	return sb.toString();
    }
    
    /**
     * 字符串编码转换的实现方法
     * @param str    待转换的字符串
     * @param oldCharset    源字符集
     * @param newCharset    目标字符集
     * @throws UnsupportedEncodingException 
     */
    public static String changeCharset(String str) throws UnsupportedEncodingException {
        if(str != null) {
            //用源字符编码解码字符串
            byte[] bs = str.getBytes();
            return new String(bs, UTF_8);
        }
        return null;
    }
    
	public static String obj2Str(Object obj){
		if(null!=obj && !"".equals(obj)) return obj.toString();
		return null;
	}
	
	public static Timestamp obj2Dt(Object obj){
		if(null!=obj && !"".equals(obj)) return (Timestamp)obj;
		return null;
	}
}

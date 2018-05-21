package org.wx.res.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mysql.jdbc.StringUtils;

public class XMLUtil {
	
	private static final String UTF_8 = "UTF-8";
	/**
	 * 解析XML
	 * @param str
	 * @return
	 * @throws DocumentException
	 * @throws UnsupportedEncodingException 
	 */
    public static Document parse(String str) throws DocumentException, UnsupportedEncodingException {
    	//-- 读取信息
    	System.out.println(str);
    	Document document = DocumentHelper.parseText(str);
        return document;
    }
    
    /**
     * 取出XML对应信息
     * @param doc
     * @param name
     * @return
     */
    public static String getInfo(Document doc,String name){
    	if(null != doc && !StringUtils.isNullOrEmpty(name)){
    		Element root = doc.getRootElement();
    		return root.element(name).getTextTrim();
    	}
    	
    	return null;
    }
    
    
}

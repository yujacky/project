package org.mysql.util;

import java.util.UUID;

public class CommonUtil {

    public synchronized static String getUUID(){
    	String idString = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    	return idString;
    }
    
}

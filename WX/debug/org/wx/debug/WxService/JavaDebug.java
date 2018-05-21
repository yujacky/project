package org.wx.debug.WxService;

import java.util.LinkedList;
import java.util.Random;

public class JavaDebug {
	public static void main(String[] aaa) {
		LinkedList list=new LinkedList();
		list.add("A"); 
		list.add(1,"B");
		String s=(String)list.get(1);
		System.out.println(s);
	}
	
	public void getRam(){
		Random r = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		for(int n=0;n<6;n++){
			int i = r.nextInt(49);
			sb.append(i+1).append(" ");
		}
		System.out.println(sb);
	}
}

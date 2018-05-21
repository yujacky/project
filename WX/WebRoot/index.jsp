<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	
	response.setHeader("Cache-Control", "no-cache");  
	response.setHeader("Cache-Control", "no-store");  
	response.setHeader("Pragma", "no-cache");  
	response.setDateHeader("Expires", 0);   
	
	response.sendRedirect(path + "/index.do");
%>

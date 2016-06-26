<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.shiro.*"%>
<%@ page import="org.apache.shiro.subject.*"%>

<%
	Subject subject = SecurityUtils.getSubject();
	Boolean logined = subject.isAuthenticated();
	if (subject.isAuthenticated()) {
		logined = true;
	} else {
		logined = false;
	}
	request.setAttribute("isLogined", logined);
%>
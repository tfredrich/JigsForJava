<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/include/base.jspf"  %>
<html>
	<head>
		<title>Welcome to Jigs for Java!</title>
	</head>

	<body>
		<jigs:image alt="Jigs for Java" src="/images/logo.png" />
		Hello from Jigs for Java!
		<a href="${contextPath}/jigs/info/view.action">Click here</a> to start.
	</body>
</html>
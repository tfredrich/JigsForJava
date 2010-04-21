<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%@ taglib prefix="sitemesh-decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="sitemesh-page" uri="http://www.opensymphony.com/sitemesh/page" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><sitemesh-decorator:title default="Welcome to Jigs for Java" /></title>
		<link href="${contextPath}/stylesheets/style.css" rel="stylesheet" type="text/css">
		<link rel="SHORTCUT ICON" href="${contextPath}/images/favicon.ico">
		<script type="text/javascript" src="${contextPath}/javascript/prototype.js"></script>
		<script type="text/javascript" src="${contextPath}/javascript/scriptaculous.js"></script>
		<script type="text/javascript" src="${contextPath}/javascript/jigs.js"></script>
		<script type="text/javascript" src="${contextPath}/javascript/application.js"></script>
		<sitemesh-decorator:head />
	</head>
	<body id="insideBody" onload="<sitemesh-decorator:getProperty property="body.onload" />">
		<sitemesh-decorator:body />
	</body>
</html>
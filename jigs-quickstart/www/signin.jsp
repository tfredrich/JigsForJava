<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/view/include/base.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>JigsForJava Sign-in</title>
	</head>
	<body>
		<jigs:image alt="Jigs for Java" src="/images/logo.png" />
		<h1>Sign In</h1>
		<form action="/jigs-quickstart/login.action" method="post">
			<p>Please enter your user name and password.</p>
			User Name: <input type="text" name="username"><br>
			Password: <input type="password" name="password"><br>
			<input type="submit">
		</form>
	</body>
</html>
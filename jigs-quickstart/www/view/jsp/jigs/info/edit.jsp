<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/include/base.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Jigs for Java</title>
</head>
<body>
	Jump right in and edit your stuff.
	<jigs-form:form name="abcd" controller="/jigs/info" action="save" method="post">
		<input>
		<jigs-form:textarea path="textarea" maxlength="100" rows="3" cols="20" />
		<input type="submit" value="save">
	</jigs-form:form>
	<jigs:link controller="/jigs/info" action="view">Cancel</jigs:link> to view.
	<jigs-form:submit form="abcd" controller="/jigs/info" action="cancel" value="Cancel" confirm="Lose your changes?" />
</body>
</html>
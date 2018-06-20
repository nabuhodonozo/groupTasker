<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/nav.jspf" %>
<h1>Login</h1>
<form name='f' action="login" method='POST'>
	<table>
		<tr>
			<td>User:</td>
			<td><input type='text' name='username' value=''></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type='password' name='password' /></td>
		</tr>
		<tr>
			<td><input name="submit" type="submit" value="submit" /></td>
		</tr>
	</table>
</form>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
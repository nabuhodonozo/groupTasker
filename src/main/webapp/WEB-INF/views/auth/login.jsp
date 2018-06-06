<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<title>Insert title here</title>
<style>
	span{
		color: red;
	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/nav.jspf" %>
	<form:form method="post" modelAttribute="userLoginData">
		<div><label><form:input path="login"/>    login       </label></div>
		<div><form:errors path="login"/></div>
		<div><label><form:input type="password" path="password"/>   password </label></div>
		<div><form:errors path="password"/></div>
		<div><label><input type="submit" value="Login"/>                      </label></div>
		add here register option or maby do header and inject it with links...
	</form:form>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
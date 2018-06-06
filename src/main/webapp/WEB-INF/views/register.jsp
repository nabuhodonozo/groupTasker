<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="jspf/header.jspf" %>
<title>Insert title here</title>
<style>
	span{
		color: red;
	}
</style>
</head>
<body>
<%@ include file="jspf/nav.jspf" %>
<form:form method="post" modelAttribute="user">
		<div><label> <form:input path="login"/> login</label>          </div>
		<div><form:errors path="login"/></div>
		<div><label> <form:password path="password"/> password</label>          </div>
		<div><form:errors path="password"/></div>
		<div><label> <form:input type="email" path="email"/>  email  </label>          </div>
		<div><form:errors path="email"/></div>
		<div><label> <input type="submit" value="register"/>        </label>          </div>
</form:form>
<%@ include file="jspf/footer.jspf" %>
</body>
</html>
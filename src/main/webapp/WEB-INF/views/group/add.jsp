<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/header.jspf" %>
<title>Insert title here</title>
</head>
<body>
<%@ include file="../jspf/nav.jspf" %>
	<form:form method="post" modelAttribute="group">
		<form:input path="name"/>
		<form:errors path="name" />
		<input type="submit" value="Add group"/>
	</form:form>
<%@ include file="../jspf/footer.jspf" %>
</body>
</html>
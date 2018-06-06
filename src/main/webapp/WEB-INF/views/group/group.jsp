<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/header.jspf" %>
<title>Insert title here</title>
</head>
<body>
<%@ include file="../jspf/nav.jspf" %>
	${group.name}
	<c:forEach items="${tasks}" var="task">
		${task}<br>
		
		<a href="#">if owner of task or admin of group allow del</a><br>
		<a href="#">if owner or admin allow makin in done</a>
	
	</c:forEach>
	<form:form method="post" modelAttribute="task"><!-- mby form taglib -->
		<form:input path="description"/>
		<input type="submit" value="Add task"/>	
	</form:form>
<%@ include file="../jspf/footer.jspf" %>
</body>
</html>
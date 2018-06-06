<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/nav.jspf" %>
	${group.name}<br><br>
	<c:forEach items="${group.tasks}" var="task">
		${task}
		
		<a href="#">del</a><!-- if owner of task or admin of group allow del -->
		<a href="#">done</a> <!-- if owner or admin allow makin in done -->
		<!-- add comment / reply option -->
	<br>
	</c:forEach>
	<form:form method="post" modelAttribute="task"><!-- mby form taglib -->
		<form:input path="description"/>
		<input type="submit" value="Add task"/>	
	</form:form>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>	
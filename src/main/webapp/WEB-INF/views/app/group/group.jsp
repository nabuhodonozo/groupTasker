<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<title>${group.name}</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/nav.jspf" %>
	<c:forEach items="${group.tasks}" var="task">
		<div>
			${task}
			<a href="#">del</a><!-- if owner of task or admin of group allow del -->
			<a href="#">done</a> <!-- if owner or admin allow makin in done -->
			<!-- add comment / reply option 
				add leave group option / edit name / manage group
			-->
		</div>
	</c:forEach>
	<form:form method="post" modelAttribute="task">
		<form:input path="description"/>
		<input type="submit" value="Add task"/>	
	</form:form>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
<!-- TODO //I know it's not the best idea to give comments here

JSP OR CONTROLLER GIVES ERROR

java.lang.IllegalArgumentException: Model object must not be null

but everthing is working .... check it out later

 -->
</html>	
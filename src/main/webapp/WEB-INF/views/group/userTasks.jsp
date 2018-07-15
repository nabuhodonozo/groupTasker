<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<title>User tasks</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/nav.jspf" %>
	<c:forEach items="${tasks}" var="task">
		<div style="border: 1px solid black">
			<p>
				<!-- here group name -->
			</p>
			<p>
				${task.completed} ${task.user.login }
			</p>
			<p>
				${task.description}
			</p>
		</div>
	</c:forEach>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>	
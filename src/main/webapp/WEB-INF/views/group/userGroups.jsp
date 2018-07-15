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
	<c:forEach items="${groups}" var="group">
		<a href="<c:url value="/group/${group.name}"/>">${group.name}</a><br>
	</c:forEach>
	<form:form method="post" modelAttribute="group">
		<div></div><form:input path="name"/>			 </div>
		<div><form:errors path="name" />				 </div>
		<div><input type="submit" value="Add group"/>    </div>
	</form:form>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>	
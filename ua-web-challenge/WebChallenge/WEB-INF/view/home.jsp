<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Generate sitemap.xml</title>
</head>
<body>
	<h1>Welcome</h1>
	<form action="url">
		http:///www.<input name="urlName" type="text" size="15"> <input
			value="generate" type="submit">
	</form>
	<p>
		<c:forEach items="${url}" var="r">
			<a href="downloadfile"><c:out value="${r}"></c:out></a>
		</c:forEach>
	</p>
</body>
</html>
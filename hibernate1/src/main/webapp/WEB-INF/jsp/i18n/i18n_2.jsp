<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>i18n_2.jsp</title>
</head>
<body>
	<h2>Hello World!</h2>

	<fmt:message key="i18n.password"></fmt:message>
	
	<br/>
	<a href="${pageContext.request.contextPath }/i18n/i18n_1">i18n_1.jsp</a>
	

</body>
</html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>i18n_1.jsp</title>
</head>
<body>
	<h2>Hello World!</h2>

	<fmt:message key="i18n.username"></fmt:message>
	
	<br/>
	<a href="${pageContext.request.contextPath }/i18n/i18n_2">i18n_2.jsp</a>
	
	<a href="${pageContext.request.contextPath }/i18n?locale=zh_CN">中文</a>
	<a href="${pageContext.request.contextPath }/i18n?locale=en_US">英文</a>
	
	<spring:message code="welcome"></spring:message>
	
	<br/>
	
	<h1>${requestScope.user.name }</h1>
	<h1>${requestScope.customerInfo.name }</h1>
	
		<input type="button" id="btn" value="btn1"/>
	<div>
		<table class="table table-striped table-bordered">
			<thead><tr><th>id</th><th>custNo</th><th>name</th><th>phone</th><th>birthday</th><th>createDate</th></tr></thead>
			<tbody id="tbody"></tbody>
		</table>
	</div>

	
</body>
 <link href="${pageContext.request.contextPath }/css/bootstrap/css/bootstrap.min.css"  rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/require/require.js" 
		data-main="${pageContext.request.contextPath }/js/myjs/com.js"></script>
</html>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello World!</title>
</head>
<body>
	<h2>Hello World!</h2>

	<c:forEach items="${findAll }" var="cust">
		<li>${cust }</li>
	</c:forEach>
	
	<a href="${pageContext.request.contextPath }/i18n/i18n_1">i18n_1.jsp</a>
	<a href="${pageContext.request.contextPath }/i18n/i18n_2">i18n_2.jsp</a>
	
	<br/>
	<div>
		<input type="text" id="username" value="文本框一"/>
	</div>
	<input type="button" id="btn" value="btn1"/>
	<div>
		<table class="table table-striped table-bordered">
			<thead><tr><th>id</th><th>custNo</th><th>name</th><th>phone</th><th>birthday</th><th>createDate</th></tr></thead>
			<tbody id="tbody"></tbody>
		</table>
	</div>
</body>
 <link href="${pageContext.request.contextPath }/css/bootstrap/css/bootstrap.min.css"  rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/sea/sea.js"></script>
<script type="text/javascript">
	seajs.config({
		base : "/",
		alias:{
			"jquery": "js/jquery.min",
			'bootstrap':'js/bootstrap/js/bootstrap.min',
			'index':'js/myjs/index'
		},
		preload : [Function.prototype.bind ? '' : 'es5-safe',
			    	this.JSON ? '' : 'json','jquery']
	});
	console.log("hello SeaJS");
	seajs.use(['jquery',"index"],function($,index){
//		$('#username').val('张三丰');
		index.getBtnValue();
	//	index.getTextValue();
	});
	console.log("goodbye SeaJS");
</script>
</html>

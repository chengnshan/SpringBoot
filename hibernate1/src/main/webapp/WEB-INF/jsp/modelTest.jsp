<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
</head>
<script type="text/javascript">
        $(function(){
            $("#modelTest").on("click",function(){
                window.location.href="<%=basePath%>modelTest.do";
            })
            
            console.log("modelTest.do");
        });
  </script>
<body>

	<input type="button" id="modelTest" value="测试">

	
    <br/>
    <input type="text" value="${Myuser.name }">
    <input type="text" value="${Myuser.email }">
    <input type="text" value="${MyCustomerInfo.birthday }">
    <input type="text" value="${MyCustomerInfo.phone }">
    
     <br/>
     <br/>
     <input type="text" value="${user.name }">
    <input type="text" value="${user.email }">
    <input type="text" value="${pojo }">
    
    <h2>${sessionScope.Myuser.name }</h2>
    <h2>${sessionScope.user.name }</h2>
</body>
</html>
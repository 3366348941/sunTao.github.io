<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//动态获取协议路径,创建base标签，让所有的静态资源查询工作，都从webapp根目录下去找!
String urlPath=request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<%--base标签介绍：base标签为页面上的所有链接规定默认地址或默认目标。浏览器会从当前文档的 URL
	中提取相应的元素来填写相对 URL,构成完整的请求地址--%>
	<base href="<%=urlPath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

</head>
<body>
	<img src="image/home.png" style="position: relative;top: -10px; left: -10px;"/>
</body>
</html>
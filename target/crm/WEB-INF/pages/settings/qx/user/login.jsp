<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--让当前所有静态资源，从工程的根目录去查询资源--%>
<%
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
//request.getScheme()  获取协议
	//request.getServerName()  获取ip
	//request.getServerPort()  获取端口
	//request.getContextPath()  获取当前项目根
	//留意最后要："/"
%>
<html>
<%--使用base标签，引用动态地址basePath--%>
<base href="<%=basePath%>">
<head>
	<meta charset="UTF-8">
	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//当页面加载完后..执行
		$(function () {
			//一、异步请求验证用户登录【采用单击事件】
			$("#login_button").click(function () {
				//需求一：登录验证
				//1.1获取用户输入：账户、密码、单选框【十天免登录】
				var act = $("#login_act").val();
				var pwd = $("#login_pwd").val();
				var avoid = $("#login_avoid_check").prop("checked");

				//1.2验证用户是否填写账户、密码
				if(pwd==""){
					$("#msg").html("密码不能为空！");
					return;
				}else{
					$("#msg").html("");
				}
				if(act==""){
					$("#msg").html("账户不能为空！");
					return;
				}else{
					$("#msg").html("");
				}
				//1.2通过ajax异步请求，请求controller：settings/qx/user/doLoin.do
				$.ajax({
					url:'settings/qx/user/doLoin.do',//请求url地址
					data:{
						userAct:act,
						userPwd:pwd,
						isRemPwd:avoid
					},//请求携带参数
					type:'post',//请求方式
					dataType:'json',//请求参数类型
					success:function (data) {//获取响应回调函数,获取响应信息
						if(data.code=="1"){
							window.location.href='workbench/index.do';
						}else{
							$("#msg").html(data.massage)
						}
					},
					beforeSend:function () {//在异步请求发送之间执行该函数，该函数结果boolean， true是ajax请求正常发送，false时ajax请求不发送。
						$("#msg").html("正在努力登陆中，请耐心哦~~");
						return true;
					}

				})

			})

			//用户按下回车，也会触发：登录【按钮事件】 范围是整个页面！
			//location 代表地址栏
			//window 代表整个页面
			$(window).keydown(function (e) {
				if(e.keyCode==13){//如果用户点击了ACll码键值为13【enter键 】 后，就进行登录
					$("#login_button").click();
				}
			})

		})
	</script>
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
	<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
	<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
	<div style="position: absolute; top: 0px; right: 60px;">
		<div class="page-header">
			<h1>登录</h1>
		</div>
		<form action="/" class="form-horizontal" role="form">
			<div class="form-group form-group-lg">
				<div style="width: 350px;">
					<input class="form-control" id="login_act" type="text" value="${cookie.loginAct.value}" placeholder="用户名">
				</div>
				<div style="width: 350px; position: relative;top: 20px;">
					<input class="form-control" id="login_pwd" type="password" value="${cookie.loginPwd.value}" placeholder="密码">
				</div>
				<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
					<label>
						<c:if test="${not empty cookie.loginAct and not empty cookie.loginPwd}">
							<input type="checkbox"  id="login_avoid_check" checked>
						</c:if>
						<c:if test="${empty cookie.loginAct or empty cookie.loginPwd}">
							<input type="checkbox"  id="login_avoid_check" >
						</c:if>
						十天内免登录
					</label>
					&nbsp;&nbsp;
					<span id="msg"  style="color: tomato"></span>
				</div>
				<button type="button" id="login_button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
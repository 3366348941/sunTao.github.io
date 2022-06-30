<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<script type="text/javascript">
		/*
		window对象代表整个浏览器窗口
		document对象只代表展示栏
		封装成对象后， 可通过js代码操作浏览器
		location浏览器的地址栏区域
		href 代表地址，这里使用href跳转，和用户在浏览器的地址栏访问url效果完全一样！
		所以：当前跳转的页面login.html在WEB-INF下，受保护，不可访问!

        直接 访问userController中toLogin方法的url,为何不用添加根：
		因为这个index.jsp是从首页转发过来的，有个规则就是不加/就是你的当前页面的地址加你的目标地址
		构成新地址，所以这个href就是个完整的地址
		故：这种写法是当前请求路径和该路径进行拼接
		* */
		window.location.href ="settings/qx/user/toLogin.do";
	</script>

hello word!

</body>
</html>
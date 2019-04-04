<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Baoji
  Date: 2019/4/3
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
function _change() {
    var img=document.getElementById("img");
    img.src="<c:url value='/vcode.jpg'/>?a="+new Date().getTime();
}
</script>
<head>
    <title>用户登录</title>
</head>
<body>
<h1>登录</h1>
<p style="color: red;font-weight: 900">${msg}</p>
<form action="<c:url value='/LoginServlet' />" method="post">
    用户名：<input type="text" name="username" value="${user.username}"/><br/>
    密  码：<input type="password" name="password" value="${user.password}"/><br/>
    <input type="submit" value="登录"/><br/>
    <a href="<c:url value='/user/regist.jsp' />">点击这里注册</a>
</form>
</body>
</html>

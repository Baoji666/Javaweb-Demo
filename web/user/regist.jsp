<%--
  Created by IntelliJ IDEA.
  User: Baoji
  Date: 2019/4/3
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>用户注册</title>
<script>
    function _change() {
        /*
        1、得到img元素
        2、修改其src为/mtweb00/vcode.jpg
        【但由于浏览器缓存了原先的图片，点击后浏览器会返回之前一样的缓存图片】
        【故在后面加上一个参数，以示区别】
         */
        var img=document.getElementById("img");
        img.src="<c:url value='/vcode.jpg'/>?a="+new Date().getTime();
    }
</script>
<head>
</head>
<body>
<h1>注册</h1>
<p style="color: red;font-weight: 900">${msg}</p>
<form action="<c:url value='/RegistServlet' />" method="post">
    用户名：<input type="text" name="username" value="${user.username}"/>${errors.username}<br/>
    密  码：<input type="password" name="password" value="${user.password}"/>${errors.password}<br/>
    验证码：<input type="text" name="verifyCode" value="${user.verifyCode}" size="3"/><br/>
            <img id="img" src="<c:url value='/vcode.jpg'/>"/>
            <a href="javascript:_change()">换一张</a><br/>${errors.verifyCode}
            <input type="submit" value="注册"/>
</form>

</body>
</html>

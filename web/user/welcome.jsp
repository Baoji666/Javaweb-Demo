<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Baoji
  Date: 2019/4/3
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>欢迎登录本系统</h1>
<%--下面主要是为了防止用户直接访问该页面--%>
<%--因此需要验证session域中，该用户是否存在--%>
<%--但是如果一个网站有很多页面，登录之后，访问这些页面都要这么来限制就很麻烦了--%>
<%--因此后面的过滤器filter会帮助解决这个问题--%>
<c:choose>
    <c:when test="${empty sessionScope.sessionUser}">
        <a href="<c:url value='/user/login.jsp'/> ">
            您还没有登录，点击进入登录页面
        </a>
    </c:when>
    <c:otherwise>
        ${sessionScope.sessionUser.username}
    </c:otherwise>
</c:choose>
</body>
</html>

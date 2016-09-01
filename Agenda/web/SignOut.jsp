<%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/9/1
  Time: 下午3:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie clear = new Cookie("userName", null);
    clear.setMaxAge(0);
    clear.setPath("/");
    response.addCookie(clear);
    response.sendRedirect("LogIn.jsp");
%>
</body>
</html>

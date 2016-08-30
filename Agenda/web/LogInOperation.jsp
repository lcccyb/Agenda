<%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/8/30
  Time: 上午11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="src.AgendaService" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    AgendaService m_service = new AgendaService();
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    if (m_service.userLogIn(userName, password)) {
        response.sendRedirect("main.jsp");
    } else {
        response.sendRedirect("LogIn.jsp");
    }
%>
</body>
</html>

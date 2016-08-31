<%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/8/30
  Time: 上午8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="src.AgendaService" language="java" %>
<html>
<head>
    <title>Login in</title>
</head>
<body>
<%
    AgendaService m_service = new AgendaService();
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    if (m_service.userRegister(userName, password, email, phone)) {
        response.sendRedirect("LogIn.jsp");
    } else {
        response.sendRedirect("LogIn.jsp");
    }
%>
</body>
</html>

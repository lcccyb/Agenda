<%@ page import="src.AgendaService" %><%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/8/31
  Time: 下午8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    AgendaService m_service = new AgendaService();
    if (m_service.changeEmail(userName, password, email)) {
        response.sendRedirect("ChangeEmail_success.jsp");
    } else {
        response.sendRedirect("ChangeEmail_error.jsp");
    }
%>
</body>
</html>

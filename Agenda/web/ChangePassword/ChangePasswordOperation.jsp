<%@ page import="src.AgendaService" %><%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/8/31
  Time: 下午8:35
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
    String oldPassword = request.getParameter("oldPassword");
    String newPassword = request.getParameter("newPassword");
    String newPassword_confirm = request.getParameter("newPassword-confirm");
    AgendaService m_service = new AgendaService();
    if (!newPassword.equals(newPassword_confirm)) {
        response.sendRedirect("ChangePassword_error.jsp");
    }
    if (m_service.changePassword(userName, oldPassword, newPassword)) {
        response.sendRedirect("ChangePassword_success.jsp");
    } else {
        response.sendRedirect("ChangePassword_error.jsp");
    }
%>
</body>
</html>

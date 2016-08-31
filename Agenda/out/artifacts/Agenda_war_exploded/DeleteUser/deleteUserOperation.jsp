<%@ page import="src.AgendaService" %><%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/8/31
  Time: 下午9:19
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
    String password_confirm = request.getParameter("Password_confirm");
    String confirm_info = request.getParameter("confirmInfo");
    String cancel = request.getParameter("cancel");
    if (cancel.equals("cancel")) {
        response.sendRedirect("../main.jsp");
        return;
    }
    AgendaService m_service = new AgendaService();
    if (!password.equals(password_confirm) || !confirm_info.equals("I really wanna delete user!")) {
        response.sendRedirect("deleteUser_error.jsp");
        return;
    }
    if (m_service.deleteUser(userName, password)) {
        response.sendRedirect("../Agenda.jsp");
    } else {
        response.sendRedirect("deleteUser_error.jsp");
    }
%>
</body>
</html>

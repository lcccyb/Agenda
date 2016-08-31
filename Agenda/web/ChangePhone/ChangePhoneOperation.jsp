<%@ page import="src.AgendaService" %><%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/8/31
  Time: 下午9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    AgendaService m_service = new AgendaService();
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    String phone = request.getParameter("newPhone");
    if (m_service.changePhone(userName, password, phone)) {
        response.sendRedirect("ChangePhone_success.jsp");
    } else {
        response.sendRedirect("ChangePhone_error.jsp");
    }
%>
</body>
</html>

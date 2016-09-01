<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/9/1
  Time: 下午8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="src.*" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body class="container">
<jsp:include page="nav.jsp"/>
<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    for (Cookie each : cookies) {
        if (each.getName().equals("userName")) {
            userName = each.getValue();
        }
    }
    AgendaService m_service = new AgendaService();
    int id = Integer.parseInt(request.getParameter("id"));
    ArrayList<Meeting> list = null;
    if (id == 1) {
        list = m_service.listAllMeetings(userName);
    } else {
        list = m_service.listAllParticipatorMeetings(userName);
    }
    if (list.size() == 0) {
        out.print("<h3 class=\"center\">No Meeting recorded!</h3>");
    } else {
        %>
        <table class="table table-hover table table-striped">
        <thead>
        <tr>
        <th>Sponsor</th>
        <th>Title</th>
        <th>StartDate</th>
        <th>EndDate</th>
        <th>Participator</th>
        </tr>
        </thead>
        <tbody>

<% for (Meeting each : list) { %>
<tr>
<td>
    <%=each.getSponsor()%>
</td>
<td>
    <%=each.getTitle()%>
</td>
<td>
    <%=each.getStartDate().toString()%>
</td>
<td>
    <%=each.getEndDate().toString()%>
</td>
<td>
    <%=each.getParticipator().toString()%>
</td>
</tr>
<%}%>
</tbody>
</table>
<%
    }
%>


</body>
</html>

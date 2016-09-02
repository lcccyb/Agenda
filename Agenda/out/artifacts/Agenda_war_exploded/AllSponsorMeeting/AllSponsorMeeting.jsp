<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/9/1
  Time: 下午8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="src.*, java.util.*" language="java" %>
<%@ page import="src.Date"%>
<html>
<head>
    <title>Title</title>
</head>
<meta charset="utf-8">
<title>Agenda - Free Meetings Administration System</title>
<body>
<div class="container">
<jsp:include page="../Navigation.jsp"/>
    <h3 class="demo-panel-title">Select</h3>
    <form action="/AllMeeting" method="post">
        <select class="form-control select select-primary" data-toggle="select" name="type" id="type">
            <option value="1">All Sponsor Meetings</option>
            <option value="2">All Participator</option>
        </select>
        <select class="form-control select select-primary" data-toggle="select" name="sort" id="sort">
            <option value="1">Starting with the latest</option>
            <option value="2">Reverse chronological order </option>
        </select>
        <input class="btn btn-success" type="submit" value="search"/>
    </form>
<%
    ArrayList<Meeting> list = (ArrayList<Meeting>) request.getSession().getAttribute("list");
    if (list == null || list.size() == 0) {
        out.print("<h3>No Meeting</h3>");
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
</div>
<br>
<br>
<br>
<br><br>
<br><br>
<br><br>
<br><br>
<br>
<br>
<br>





<jsp:include page="../Footer.jsp"/>
</body>
</html>

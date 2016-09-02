<%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/9/2
  Time: 上午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="src.*, java.util.*" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../Navigation.jsp"/>
<div class="container">
    <h3 class="demo-panel-title">Query Meeting by Title</h3>
    <form action="/QueryMeetingbyInterval" method="post">
        Input Start Date:<br>
        <small>As the format YYYY-MM-DD/HH:MM</small>
        <input type="text" value="" placeholder="Start Date!" class="form-control input-lg" name="startDate" style="width: 200px;" />
        <br>
        Input End Date:<br>
        <small>As the format YYYY-MM-DD/HH:MM</small>
        <input type="text" value="" placeholder="End Date!" class="form-control input-lg" name="endDate" style="width: 200px;" />
        <br>
        <input class="btn btn-success" value="Search!" type="submit"/>
    </form>
    <%
        ArrayList<Meeting> list = (ArrayList<Meeting>)request.getSession().getAttribute("list");
        if (list == null || list.size() == 0) {
            out.print("<h3>No Meeting recorded!</h3>");
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
<jsp:include page="../Footer.jsp"/>
</body>
</html>

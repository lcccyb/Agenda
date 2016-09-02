<%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/9/2
  Time: 上午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</div>
<jsp:include page="../Footer.jsp"/>
</body>
</html>

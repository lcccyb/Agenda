<%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/9/2
  Time: 上午9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
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
</div>
</body>
<br>
<br>
<br>
<br>
<br>
<br><br>
<br>
<br>
<br>
<br>
<br>

<jsp:include page="../Footer.jsp"/>
</html>

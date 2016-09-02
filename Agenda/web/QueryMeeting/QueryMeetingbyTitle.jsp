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
    <form action="/QueryMeetingbyTitle" method="post">
    Input Title: <input type="text" value="" placeholder="Title!" class="form-control input-lg" name="title" style="width: 200px;" />
        <br>
        <input class="btn btn-success" value="Submit!" type="submit"/>
    </form>
</div>
<jsp:include page="../Footer.jsp"/>
</body>
</html>

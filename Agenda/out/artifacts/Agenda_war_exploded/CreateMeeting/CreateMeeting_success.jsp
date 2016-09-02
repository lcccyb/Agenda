<%--
  Created by IntelliJ IDEA.
  User: yanzexin
  Date: 16/9/1
  Time: 下午6:34
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
    <h3 class="demo-panel-title">Create Meeting</h3>
    <form action="/CreateMeeting" method="post">
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-feedback">
                Input the title: <input type="text" value="" placeholder="Title!" class="form-control input-lg" name="title" />
            </div>
        </div>
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-feedback">
                Input Start Date:<br>
                <small>as the format:"YYYY-MM-DD/HH:MM"</small>
                <input type="text" value="" placeholder="start date!" class="form-control input-lg" name="startDate" />
            </div>
        </div>
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-feedback">
                Input End Date:<br>
                <small>as the format:"YYYY-MM-DD/HH:MM"</small>
                <input type="text" value="" placeholder="end date!" class="form-control input-lg" name="endDate" />
            </div>
        </div>
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-feedback">
                Input Meeting participator:<br>
                <input name="participator" class="tagsinput" data-role="tagsinput" value="Input Participator and Enter!" style="width: 300px" />
            </div>
        </div>
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-feedback">
                <label class="alert-success" style="position: relative; margin-left: 160px; width: auto">Create Meeting Succeed!</label>
            </div>
        </div>
        <input class="btn btn-success" value="Submit!" type="submit" style="position: relative; margin-left: 300px; margin-top: 40px; width: 300px">
        <input class="btn btn-danger" value="Reset" type="reset" style="position: relative; margin-top: 40px; margin-left: 300px; width: 300px">
    </form>
</div>
<jsp:include page="../Footer.jsp"/>
</body>
</html>

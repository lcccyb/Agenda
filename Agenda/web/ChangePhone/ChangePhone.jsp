<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Agenda - Free Meetings Adminstration System</title>
</head>
<body>
<div class="container">
    <jsp:include page="../Navigation.jsp"/>
    <h3 class="demo-panel-title">Change Phone</h3>
    <form action="/ChangePhoneCheck" method="post">
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-error">
                Input Your Password: <input type="password" value="" placeholder="Password!" class="form-control" name="password" />
                <span class="input-icon fui-check-inverted"></span>
            </div>
        </div>
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-feedback">
                Input Your new Phone: <input type="text" value="" placeholder="Phone!" class="form-control" name="newPhone" />
                <span class="input-icon fui-check-inverted"></span>
            </div>
        </div>
        <input class="btn btn-success" value="Submit!" type="submit" style="position: relative; margin-left: 300px; margin-top: 40px; width: 300px">
        <input class="btn btn-danger" value="Reset" type="reset" style="position: relative; margin-top: 40px; margin-left: 300px; width: 300px">
    </form>
</div>
<jsp:include page="../Footer.jsp"/>
</body>
</html>
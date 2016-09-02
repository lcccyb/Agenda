<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Agenda - Free Meetings Adminstration System</title>
    <meta name="description" content="Flat UI Kit Free is a Twitter Bootstrap Framework design and Theme, this responsive framework includes a PSD and HTML version."/>
</head>
<body>
<div class="container">
    <jsp:include page="../Navigation.jsp"/>
    <h3 class="demo-panel-title">Delete User!</h3><br>
        <h4 class="alert-danger" style="width: 400px">This is extremely important.</h4>
        <form action="/DeleteUser" method="post">
            <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
                <div class="form-group has-success">
                    Input Your UserName: <input type="text" value="" placeholder="UserName!" class="form-control" name="userName" />
                    <span class="input-icon fui-check-inverted"></span>
                </div>
            </div>
            <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
                <div class="form-group has-error">
                    Input Your new Password: <input type="password" value="" placeholder="Password!" class="form-control" name="Password" />
                    <span class="input-icon fui-check-inverted"></span>
                </div>
            </div>
            <br>
            <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
                <div class="form-group has-error">
                    Please confirm your password: <input type="password" value="" placeholder="Password!" class="form-control" name="Password_confirm" />
                    <span class="input-icon fui-check-inverted"></span>
                </div>
            </div>
            <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
                <div class="form-group has-warning">
                    Please input:<br>
                    <small>I really wanna delete user!</small><input type="text" value="" placeholder="Warnning!" class="form-control" name="confirmInfo" />
                    <span class="input-icon fui-check-inverted"></span>
                </div>
            </div>
            <label class="alert-danger" style="position: relative; margin-left: 310px; margin-top: 40px;">  Delete User Failed! Please try again!  </label>
            <br>
            <input class="btn btn-success" value="Yes!" type="submit" style="position: relative; margin-left: 300px; margin-top: 20px; width: 300px">
            <input class="btn btn-danger" value="Sorry! I regret!" type="reset" style="position: relative; margin-top: 20px; margin-left: 300px; width: 300px">
        </form>
</div>
<jsp:include page="../Footer.jsp"/>


<script>
    videojs.options.flash.swf = "dist/js/vendors/video-js.swf"
</script>
</body>
</html>
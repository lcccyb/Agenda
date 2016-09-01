<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Agenda - Free Meetings Adminstration System</title>
    <meta name="description" content="Flat UI Kit Free is a Twitter Bootstrap Framework design and Theme, this responsive framework includes a PSD and HTML version."/>

    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

    <!-- Loading Bootstrap -->
    <link href="../dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="..//dist/css/flat-ui.css" rel="stylesheet">
    <link href="../docs/assets/css/demo.css" rel="stylesheet">

    <link rel="shortcut icon" href="../img/favicon.ico">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="../dist/js/vendor/html5shiv.js"></script>
    <script src="../dist/js/vendor/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <jsp:include page="nav.jsp"/>
    <form action="${pageContext.request.contextPath}/ChangePasswordCheck" method="post">
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-success">
                Input Your UserName: <input type="text" value="" placeholder="UserName!" class="form-control" name="userName" />
                <span class="input-icon fui-check-inverted"></span>
            </div>
        </div>
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-error">
                Input Your old Password: <input type="password" value="" placeholder="Password!" class="form-control" name="oldPassword" />
                <span class="input-icon fui-check-inverted"></span>
            </div>
        </div>
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-error">
                Input Your new Password: <input type="password" value="" placeholder="Password!" class="form-control" name="newPassword" />
                <span class="input-icon fui-check-inverted"></span>
            </div>
        </div>
        <br>
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-error">
                Please confirm your password!: <input type="password" value="" placeholder="Password!" class="form-control" name="newPassword-confirm" />
                <span class="input-icon fui-check-inverted"></span>
            </div>
        </div>
        <label class="alert-success" style="position: relative; margin-left: 350px; margin-top: 40px;">  Change Password succeed!  </label>
        <br>
        <input class="btn btn-success" value="Submit!" type="submit" style="position: relative; margin-left: 300px; margin-top: 40px; width: 300px">
        <input class="btn btn-danger" value="Reset" type="reset" style="position: relative; margin-top: 40px; margin-left: 300px; width: 300px">
    </form>
</div>
<jsp:include page="Footer.jsp"/>
<script src="../dist/js/vendor/jquery.min.js"></script>
<script src="../dist/js/vendor/video.js"></script>
<script src="../dist/js/flat-ui.min.js"></script>
<script src="../docs/assets/js/application.js"></script>

<script>
    videojs.options.flash.swf = "dist/js/vendors/video-js.swf"
</script>
</body>
</html>
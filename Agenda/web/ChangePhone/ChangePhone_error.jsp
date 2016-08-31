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
    <link href="../dist/css/flat-ui.css" rel="stylesheet">
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
    <br>
    <div class="row demo-row">
        <div class="col-xs-12">
            <nav class="navbar navbar-inverse navbar-embossed" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                        <span class="sr-only">Toggle navigation</span>
                    </button>
                    <a class="navbar-brand" href="#">Agenda</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse-01">
                    <ul class="nav navbar-nav navbar-left">
                        <li><a href="#fakelink">Meetings<span class="navbar-unread">1</span></a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Account <b class="caret"></b></a>
                            <span class="dropdown-arrow"></span>
                            <ul class="dropdown-menu">
                                <li><a href="../ChangePassword/ChangePassword.jsp" class="active">Change Password</a></li>
                                <li><a href="../ChangeEmailChangeEmail.jsp">Change Email</a></li>
                                <li><a href="ChangePhone.jsp">Change Phone</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Delete Account</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Create Meeting</a></li>
                        <li><a href="#fakelink">About Us</a></li>
                    </ul>
                    <form class="navbar-form navbar-right" action="#" role="search">
                        <div class="form-group">
                            <div class="input-group">
                                <input class="form-control" id="navbarInput-01" type="search" placeholder="Search">
                    <span class="input-group-btn">
                      <button type="submit" class="btn"><span class="fui-search"></span></button>
                    </span>
                            </div>
                        </div>
                    </form>
                </div><!-- /.navbar-collapse -->
            </nav><!-- /navbar -->
        </div>
    </div> <!-- /row -->
    <h3 class="demo-panel-title">Change Phone</h3>
    <form action="ChangePhoneOperation.jsp" method="post">
        <div class="col-xs-3" style="width: 500px; position:relative; margin-left: 200px;">
            <div class="form-group has-success">
                Input Your UserName: <input type="text" value="" placeholder="UserName!" class="form-control" name="userName" />
                <span class="input-icon fui-check-inverted"></span>
            </div>
        </div>
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
        <label class="alert-danger" style="position: relative; margin-left: 310px; margin-top: 40px;">  Change phone Failed! Please try again!  </label>
        <br>
        <input class="btn btn-success" value="Submit!" type="submit" style="position: relative; margin-left: 300px; margin-top: 40px; width: 300px">
        <input class="btn btn-danger" value="Reset" type="reset" style="position: relative; margin-top: 40px; margin-left: 300px; width: 300px">
    </form>
</div>
<script src="../dist/js/vendor/jquery.min.js"></script>
<script src="../dist/js/vendor/video.js"></script>
<script src="../dist/js/flat-ui.min.js"></script>
<script src="../docs/assets/js/application.js"></script>

<script>
    videojs.options.flash.swf = "dist/js/vendors/video-js.swf"
</script>
</body>
</html>
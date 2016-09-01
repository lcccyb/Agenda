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
<%
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
        response.sendRedirect("http://localhost:8080/Agenda.jsp");
    }
    for (Cookie each :
            cookies) {
        if (each.getName().equals("userName") && each.getValue() == null) {
            response.sendRedirect("http://localhost:8080/Agenda.jsp");
        }
    }
%>
<div class="container">
    <br>
    <div class="row demo-row">
        <div class="col-xs-12">
            <nav class="navbar navbar-inverse navbar-embossed" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                        <span class="sr-only">Toggle navigation</span>
                    </button>
                    <a class="navbar-brand" href="main.jsp">Agenda</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse-01">
                    <ul class="nav navbar-nav navbar-left">
                        <li><a href="../CreateMeeting/CreateMeeting.jsp">Create Meeting<span class="navbar-unread">1</span></a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Meeting <b class="caret"></b></a>
                            <span class="dropdown-arrow"></span>
                            <ul class="dropdown-menu">
                                <li><a href="#">All Sponsor Meeting</a></li>
                                <li><a href="#">All Participator Meeting</a></li>
                                <li><a href="#">Query Meeting by Title</a></li>
                                <li><a href="#">Query Meeting by Interval</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Profile <b class="caret"></b></a>
                            <span class="dropdown-arrow"></span>
                            <ul class="dropdown-menu">
                                <li><a href="../ChangePassword/ChangePassword.jsp">Change Password</a></li>
                                <li><a href="../ChangeEmail/ChangeEmail.jsp">Change Email</a></li>
                                <li><a href="../ChangePhone/ChangePhone.jsp">Change Phone</a></li>
                                <li><a href="../SignOut.jsp">Sign out!</a></li>
                                <li class="divider"></li>
                                <li><a href="../DeleteUser/deleteUser.jsp">Delete Account</a></li>
                            </ul>
                        </li>
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
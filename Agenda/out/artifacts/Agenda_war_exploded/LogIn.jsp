<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Agenda - Free Meetings Adminstration System</title>
  <meta name="description" content="Flat UI Kit Free is a Twitter Bootstrap Framework design and
    Theme, this responsive framework includes a PSD and HTML version."/>

  <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

  <!-- Loading Bootstrap -->
  <link href="dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Loading Flat UI -->
  <link href="dist/css/flat-ui.css" rel="stylesheet">
  <link href="docs/assets/css/demo.css" rel="stylesheet">

  <link rel="shortcut icon" href="img/favicon.ico">

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
  <!--[if lt IE 9]>
  <script src="dist/js/vendor/html5shiv.js"></script>
  <script src="dist/js/vendor/respond.min.js"></script>
  <![endif]-->
  <%@ page language="java" contentType="text/html; charset=UTF-8"
           pageEncoding="UTF-8"%>
</head>
<script type="text/javascript">
  /**
   * @return {boolean}
   */
  function Validate() {
    var input_name = document.getElementById("userName");
    var input_pass = document.getElementById("password");
    var form = document.getElementById("form");
    var div_pass = document.getElementById("login-pass");
    var check = document.getElementById("check");
    if (input_name.value.trim() == "") {
      if (input_name.labels.length == 0) {
        var label = document.createElement("label");
        label.setAttribute("for", "userName");
        form.insertBefore(label, div_pass);
        input_name.labels[0].innerHTML = "<small>please input your userName</small>";
        input_name.labels[0].setAttribute("style", "color:red; text:center");
      }
      return false;
    } else if (input_name.labels.length == 1) {
      form.removeChild(input_name.labels[0]);
    }
    if (input_pass.value.trim() == "") {
      if (input_pass.labels.length == 0) {
        var label_pass = document.createElement("label");
        label_pass.setAttribute("for", "password");
        form.insertBefore(label_pass, check);
        input_pass.labels[0].innerHTML = "<small>please input your password</small>";
        input_pass.labels[0].setAttribute("style", "color:red; text:center");
      }
      return false;
    } else if (input_pass.labels.length == 1) {
      form.removeChild(input_pass.labels[0]);
    }
    return true;
  }
</script>
<body>
<div class="container" style="width:978px;">
  <h2 class="demo-logo">Agenda
    <small>Free Meetings Administration System</small>
  </h2>
  <div class="login">
    <div class="login-screen">
      <div class="login-icon">
        <img src="img/login/icon.png" alt="Welcome to Mail App" />
        <h4>Welcome to <small>Agenda</small></h4>
      </div>
      <div class="login-form" style="position:absolute; top:100px; width:400px;">
        <form action="/LogInCheck" method="post" id="form">
        <div class="form-group" id="login-name">
          <input type="text" class="form-control login-field" value="" placeholder="Enter your name" id="userName" name="userName" />
          <label class="login-field-icon fui-user" for="login-name"></label>
        </div>
        <div class="form-group" id="login-pass">
          <input type="password" class="form-control login-field" value="" placeholder="Password" id="password" name="password"/>
          <label class="login-field-icon fui-lock" for="login-lock"></label>
        </div>
          <input type="submit" class="btn btn-primary btn-lg btn-block" id="check" value="Log in" onclick="return Validate()" >
        <a class="btn btn-block btn-lg btn-info" href="Register.jsp">Register</a>
        <a class="login-link" href="#">Lost your password?</a>
        </form>
      </div>
    </div>
  </div>
</div>
<footer>
  <div class="container">
    <div class="row">
      <div class="col-xs-7">
        <h3 class="footer-title">
          About us!
        </h3>
        <p>Do you like Agenda?<br>
          If you have some suggestion, please send us an Email or you can review in our github!
          <a href="https://github.com/Staryyan/Agenda" target="_blank">https://github.com/Staryyan/Agenda</a>
        </p>
      </div>
    </div>
  </div>
</footer>
</body>
</html>
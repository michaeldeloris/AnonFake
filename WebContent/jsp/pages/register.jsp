<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - AnonFake</title>
</head>
  <body>
    <%@include file="../components/header/doDisplayHeader.jspf"%>
  
    <form action="register" id="register_form" method="post">
      <div class="form">
        <label class="form_label" for="username">Username: </label>
        <div class="form_input">
          <input class="input" type="text" name="username">
        </div>
      </div>
      <div class="form">
        <label class="form_label" for="password">Password: </label>
        <div class="form_input">
          <input class="input" type="password" name="password">
        </div>
      </div>
      <div class="form">
        <label class="form_label" for="password_confirm">Confirm password: </label>
        <div class="form_input">
          <input class="input" type="password" name="password_confirm">
        </div>
      </div>
      <div class="alert alert-warning text-center">Warning! Remember your password! If you lose it, you will never be able to access your account again (we don't store e-mail addresses).</div>
    </form>
    
    <button class="btn btn-primary" onclick="document.getElementById('register_form').submit();">CREATE ACCOUNT</button>
  
    <%@include file="../components/footer/doDisplayFooter.jspf"%>
  </body>
</html>
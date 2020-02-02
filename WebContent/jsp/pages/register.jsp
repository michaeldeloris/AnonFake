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
  
    <form action="register" method="post">
      <div class="form-group">
        <label for="username">Username: </label>
        <input type="text" name="username">
      </div>
      <div class="form-group">
        <label for="password">Password: </label>
        <input type="password" name="password">
      </div>
      <div class="form-group">
        <label for="password_confirm">Confirm password: </label>
        <input type="password" name="password_confirm">
      </div>
      <div class="alert alert-warning text-center">Warning! Remember your password! If you lose it, you will never be able to access your account again (we don't store e-mail addresses).</div>
      <input type="submit" value="Create account">
    </form>
  
  </body>
</html>
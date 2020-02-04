<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>anonfile clone </title>
</head>
  <body>
    <%@include file="../components/header/doDisplayHeader.jspf"%>
    <div class="login">
      <div class="page_subtitle">
        No account? <a href="register">Register here.</a>
      </div>
      
      <form action="login" id="login_form" method="post">
        <div class="form_login username"> 
          <div class="form_label">
            Username:
          </div>
          <div class="form_input">
            <input class="input" type="text" name="username"/>
          </div>
        </div>
        <div class="form_login pwd"> 
          <div class="form_label">
            Password:
          </div>
          <div class="form_input">
            <input class="input" type="password" name="password"/>
          </div>
        </div>
      </form>
      
      <button class="btn btn-primary" onclick="window.getElementById('login_form').submit();">LOGIN</button>
    </div>
    <%@include file="../components/footer/doDisplayFooter.jspf"%>
  </body>
</html>
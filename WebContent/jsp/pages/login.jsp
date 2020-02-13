<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>anonfile clone</title>
  <link rel="shortcut icon" href="favicon.ico"/>
</head>

  <%
    String error = (String) request.getAttribute("error");
  %>

  <body>
    <%@include file="../components/header/doDisplayHeader.jspf"%>
    <div class="login">
      <div class="page_subtitle">
        No account? <a href="register">Register here.</a>
      </div>
      
      <% if(error != null ) { %>
        <div class="alert alert-danger-alt text-center">
          <%= error %>
        </div>
      <% } %>
      
      <form action="login" id="login_form" method="post">
        <div class="form username"> 
          <div class="form_label">
            Username:
          </div>
          <div class="form_input">
            <input class="input" type="text" name="username"/>
          </div>
        </div>
        <div class="form pwd"> 
          <div class="form_label">
            Password:
          </div>
          <div class="form_input">
            <input class="input" type="password" name="password"/>
          </div>
        </div>
      </form>
      
      <button class="btn btn-primary" onclick="document.getElementById('login_form').submit();">LOGIN</button>
    </div>
    <%@include file="../components/footer/doDisplayFooter.jspf"%>
  </body>
</html>
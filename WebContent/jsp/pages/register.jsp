<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - AnonFake</title>
</head>

  <%
    String errorType = (String) request.getAttribute("errorAttribute");
    String error = (String) request.getAttribute("error");
  %>

  <body>
    <%@include file="../components/header/doDisplayHeader.jspf"%>
    
    <div class="register">
    
      <% if(error != null ) { %>
        <div class="alert alert-danger-alt text-center">
          <% if(errorType.equals("username") || errorType.equals("password")) { %>
            Registration failed. Check the fields below.
          <% }else { %>
            Unknown error. Please contact an administrator.
          <% } %>
        </div>
      <% } %>
    
      <form action="register" id="register_form" method="post">
      
        <div class="form">
          <label class="form_label" for="username">Username: </label>
          <div class="form_input">
            <input class="input" type="text" name="username">
          </div>
          <span class="text-danger">
            <% if(errorType != null && errorType.equals("username")) { %>
              <%= error %>
            <% } %>
          </span>
        </div>
        
        <div class="form">
          <label class="form_label" for="password">Password: </label>
          <div class="form_input">
            <input class="input" type="password" name="password">
          </div>
          <span class="text-danger">
            <% if(errorType != null && errorType.equals("password")) { %>
              <%= error %>
            <% } %>
          </span>
        </div>
        
        <div class="form">
          <label class="form_label" for="password_confirm">Confirm password: </label>
          <div class="form_input">
            <input class="input" type="password" name="password_confirm">
          </div>
          <span class="text-danger">
            <% if(errorType != null && errorType.equals("password")) { %>
              <%= error %>
            <% } %>
          </span>
        </div>
        
      </form>
      
      <div class="alert alert-warning text-center warning-btn">
        Warning! Remember your password! If you lose it, you will never be able to access your account again (we don't store e-mail addresses).
      </div>
      
      <button class="btn btn-primary" onclick="document.getElementById('register_form').submit();">CREATE ACCOUNT</button>
    </div>
  
    <%@include file="../components/footer/doDisplayFooter.jspf"%>
  </body>
</html>
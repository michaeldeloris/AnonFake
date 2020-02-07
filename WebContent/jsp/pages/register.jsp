<%@page import="util.errors.Error"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - AnonFake</title>
</head>

  <%
    String uNameError = (String) request.getAttribute("username_error");
    String pwdError = (String) request.getAttribute("pwd_error");
    String unknownError = (String) request.getAttribute("unknown_error");
    System.out.println(uNameError);
    boolean error = false;
    if(uNameError != null || pwdError != null || unknownError != null) {
      error = true;
    }
  %>

  <body>
    <%@include file="../components/header/doDisplayHeader.jspf"%>
    
    <div class="register">
    
      <% if(error) { %>
        <div class="alert alert-danger-alt text-center">
          <% if(unknownError == null) { %>
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
          <% if(uNameError != null) { %>
            <span class="text-danger">
            <% if(uNameError.equals(Error.USERNAME_ALREADY_TAKEN.toString())) { %>
              Username already exists.
            <% }else if(uNameError.equals(Error.NO_USERNAME_GIVEN.toString())) { %>
              You must enter a username.
            <% } %>
            </span>
          <% } %>
        </div>
        
        <div class="form">
          <label class="form_label" for="password">Password: </label>
          <div class="form_input">
            <input class="input" type="password" name="password">
          </div>
          <% if(pwdError != null) { %>
            <span class="text-danger">
            <% if(pwdError.equals(Error.MISSMATCH_PASSWORDS.toString())) { %>
              Passwords doesn't match.
            <% }else if(pwdError.equals(Error.NO_PASSWORD_GIVEN.toString())) { %>
              You must enter a password.
            <% } %>
            </span>
          <% } %>
        </div>
        
        <div class="form">
          <label class="form_label" for="password_confirm">Confirm password: </label>
          <div class="form_input">
            <input class="input" type="password" name="password_confirm">
          </div>
          <% if(pwdError != null) { %>
            <span class="text-danger">
            <% if(pwdError.equals(Error.MISSMATCH_PASSWORDS.toString())) { %>
              Passwords doesn't match.
            <% }else if(pwdError.equals(Error.NO_PASSWORD_GIVEN.toString())) { %>
              You must enter a password.
            <% } %>
            </span>
          <% } %>
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
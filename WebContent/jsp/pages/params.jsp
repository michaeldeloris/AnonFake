<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <%
  String error = (String) request.getAttribute("error");
  String success = (String) request.getAttribute("success");
  %>
  
  <head>
  <meta charset="ISO-8859-1">
  <title>Insert title here</title>
  </head>
  <body>
    <span> Connection to database. </span>
    
    <form action="params" id="db_form" method="post">
      <div class="form url"> 
        <div class="form_label">
          URL:
        </div>
        <div class="form_input">
          <input class="input" type="text" name="url"/>
        </div>
      </div>
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
      <input type="submit" />
    </form>
    
    <% if(error != null) { %>
      <span style="color: red"> <%= error %></span>
    <% } else if(success != null) { %>
      <span style="color: green"> <%= success %></span>
    <% } %>
    
    <a href="./">Home</a>
  </body>
</html>

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
    
    <div class="page_subtitle">
      No account ? <a href="register">Register here.</a>
    </div>
    
    <form action="login" method="post">
      <div> username :
        <input type="text" name="username"/>
      </div>
      <div> password :
        <input type="password" name="password"/>
      </div>
      <input type="submit" name="submit" value="submit"/>
    </form>
  </body>
</html>
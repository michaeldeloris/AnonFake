<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>404 - Not Found!</title>
</head>
<body>
  <%@include file="../components/header/doDisplayHeader.jspf"%>
  
  <%
    System.out.println(request.getAttribute("javax.servlet.forward.request_uri"));
  %>
  
  <div class="error">
    <div class="title">
      The file you are looking for does not exist!
    </div>
    <div class="description">
      The file you were looking for could not be found.
    </div>
  </div>
  <%@include file="../components/footer/doDisplayFooter.jspf"%>
</body>
</html>
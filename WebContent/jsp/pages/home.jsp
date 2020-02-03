<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Anonymous File Upload - AnonFake</title>
  </head>
  
  <body class="home">
    <%@include file="../components/header/doDisplayHeader.jspf"%>
    
    <button class="btn upload_button" onclick="document.getElementById('upload_file').click();"> UPLOAD </button>
    
    <div class="upload_form">
      <form action="./" method="post">
        <input type="file" name="upload_file" id="upload_file" onchange="submit();">
      </form>
    </div>
    
    <div class="text homepage_text">
      Upload your files anonymously and free on AnonFiles</br>
      We offer you 20 GB filesize limit and unlimited bandwidth.
    </div>
    <div class="subtext">
      Developer? Check out our <a href="docs/api">API</a>
    </div>
    
    <%@include file="../components/footer/doDisplayFooter.jspf"%>
  </body>
</html>

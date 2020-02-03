<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <link rel="stylesheet/less" type="text/css" href="style/main.less" />
  <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/2.5.1/less.min.js"></script>

  <head>
  <meta charset="ISO-8859-1">
  <title>Anonymous File Upload - AnonFake</title>
  </head>
  
  <body>
    <%@include file="../components/header/doDisplayHeader.jspf"%>
    
    <div class="upload_button">
      <form action="./" method="post">
        <input type="file" name="upload_file">
        <input type="submit">
      </form>
    </div>
    
    <div class="text homepage_text">
      Upload your files anonymously and free on AnonFiles
      We offer you 20 GB filesize limit and unlimited bandwidth.
    </div>
    <div class="subtext">
      Developer? Check out our <a href="docs/api">API</a>
    </div>
    
    <%@include file="../components/footer/doDisplayFooter.jspf"%>
  </body>
  
</html>
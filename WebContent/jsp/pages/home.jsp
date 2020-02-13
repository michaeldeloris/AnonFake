<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Anonymous File Upload - AnonFake</title>
    <link rel="shortcut icon" href="favicon.ico"/>
  </head>
  
  <%
    String fileName = (String) request.getAttribute("filename");
    String fileKey = (String) request.getAttribute("filekey");
  %>
  
  <body class="home">
    <%@include file="../components/header/doDisplayHeader.jspf"%>
    
    <button class="btn btn-primary upload_btn" onclick="document.getElementById('upload_file').click();">
      <span class="glyphicon glyphicon-upload"></span>
      UPLOAD 
      <span class="glyphicon glyphicon-upload"></span>
    </button>
    
    <div class="upload_form">
      <form action="./" method="post" enctype="multipart/form-data">
        <input type="file" name="upload_file" id="upload_file" onchange="submit();">
      </form>
    </div>
    
    <%if(fileName != null && fileKey != null) { %>
      <%
      StringBuffer test = request.getRequestURL();
      String uri = request.getRequestURI();
      String ctx = request.getContextPath();
      String baseurl = test.substring(0, test.length() - uri.length() + ctx.length()) + "/";
      String downloadURL = baseurl + fileKey + "/" + fileName; 
      %>
      <div class="uploaded-file">
        <div class="title">
          <%= fileName %>
        </div>
        <div class="url">
          <span class="copy-url-wrapper" onclick="copyLink()"><span class="glyphicon glyphicon-copy"></span><strong>Copy</strong></span>
          <input class="form-control upload-file-input" id="download_input" type="text" value="<%= downloadURL %>" readonly>
        </div>
      </div>
    <% } %>
    <div class="text homepage_text">
      <% if(fileName == null || fileKey == null) { %>
        Upload your files anonymously and free on AnonFiles</br>
        We offer you 20 GB filesize limit and unlimited bandwidth.
      <% } %>
      <div class="subtext">
        Developer? Check out our <a href="docs/api">API</a>
      </div>
    </div>
    
    <%@include file="../components/footer/doDisplayFooter.jspf"%>
  </body>
</html>

<script type="text/javascript">
function copyLink() {
  /* Get the text field */
  var copyText = document.getElementById("download_input");

  /* Select the text field */
  copyText.select();
  copyText.setSelectionRange(0, 99999); /*For mobile devices*/

  /* Copy the text inside the text field */
  document.execCommand("copy");
}
</script>

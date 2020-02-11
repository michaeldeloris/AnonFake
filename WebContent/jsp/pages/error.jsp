<%@page import="util.files.DownloadManager"%>
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
    System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "{}");
    String uri = request.getAttribute("javax.servlet.forward.request_uri").toString();
    ServletContext ctx= request.getServletContext();
    HttpServletRequest req = DownloadManager.retrieveFile(request, ctx, uri);
    String error = (String) req.getAttribute("error");
    String fileName = (String) req.getAttribute("filename");
    String filePath = (String) req.getAttribute("filepath");
  %>
  <% if(error != null) { %>
    <div class="error">
      <div class="title">
        <%= error %>
      </div>
      <div class="description">
        The file you were looking for could not be found.
      </div>
    </div>
  <% } %>
  <% if(fileName != null && filePath != null) { %>
    <div class="file">
      <div class="title">
        <%= fileName %>
      </div>
      <%
        String dlUrl = java.net.URLEncoder.encode("anonfake/downloadServlet?filename=" + fileName + "?filepath=" + filePath,"UTF-8").replace("+","%20");
      %>
      <a href="/<%= dlUrl %>">DOWNLOAD</a>
    </div>
  <% } %>
  <%@include file="../components/footer/doDisplayFooter.jspf"%>
</body>
</html>
package servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String filename = (String) request.getAttribute("filename");
    String filepath = (String) request.getAttribute("filepath");
    
    if(filename == null || filepath == null) {
      return;
    }
    
    response.setContentType("APPLICATION/OCTET-STREAM");
    response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
    
    FileInputStream fileInputStream = new FileInputStream(filepath + filename);
                
    int i;
    while ((i=fileInputStream.read()) != -1) {
      out.write(i);
    }
    fileInputStream.close();
    out.close();
	}
}

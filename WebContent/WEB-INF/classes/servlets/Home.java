package servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import util.Constants;
import util.upload.UploadManager;

/**
 * Servlet implementation class Home
 */
@MultipartConfig
@WebServlet("")
public class Home extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page_title", Constants.TITLE_HOME);
		request.getRequestDispatcher("/jsp/pages/home.jsp").forward(request, response);
	}

  @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  UploadManager um = new UploadManager();
	  
	  Set<Part> parts = new HashSet<>();
	  try {
	    parts = (Set<Part>) request.getParts();
	    um.upload(getServletContext().getRealPath(""), parts);
	  } catch(ServletException | IOException e) {
	    System.out.println(e.getMessage());
	  }
	  
		try {
			response.sendRedirect("./");
    } catch(IOException e) {
      e.printStackTrace();
    }
	}
}

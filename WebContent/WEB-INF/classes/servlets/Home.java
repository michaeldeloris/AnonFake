package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	  um.upload(getServletContext().getRealPath(""), request);
	  
		try {
			response.sendRedirect("./");
    } catch(IOException e) {
      e.printStackTrace();
    }
	}
}

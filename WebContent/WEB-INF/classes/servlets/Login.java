package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Constants;
import util.members.MembersManager;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page_title", Constants.TITLE_LOGIN);
		request.getRequestDispatcher("/jsp/pages/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setAttribute("page_title", Constants.TITLE_LOGIN);
	  
	  String username = request.getParameter("username");
	  String password = request.getParameter("password");
	  try {
      request = MembersManager.logMember(getServletContext(), username, password, request);
    } catch (SQLException e1) {
      e1.printStackTrace();
      System.out.println(e1.getMessage());
    }
	  
	  if(request.getAttribute("error") == null) {
	    HttpSession session = request.getSession();
	    session.setAttribute("USERSESSID", username);
	    response.sendRedirect("./");
	    return;
	  }
		
		try {
		  request.getRequestDispatcher("/jsp/pages/login.jsp").forward(request, response);
		} catch(IOException e) {
		  System.out.println(e.getMessage()); //TODO : use logger
		}
	}
}

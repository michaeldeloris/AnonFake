package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Constants;
import util.database.DbManager;
import util.members.MembersManager;
import util.errors.Error;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setAttribute("page_title", Constants.TITLE_REGISTER);
		request.getRequestDispatcher("/jsp/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setAttribute("page_title", Constants.TITLE_REGISTER);
	  
    String username = (String) request.getParameter("username");
    String password = (String) request.getParameter("password");
    String confirm = (String) request.getParameter("password_confirm");
    try {
      request = MembersManager.registerMember(username, password, confirm, request);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    redirect(request, response);
	}
	
	private void redirect(HttpServletRequest req, HttpServletResponse res) {
	  try {
	    if(req.getAttribute("error") == null) {
	      req.setAttribute("page_title", Constants.TITLE_HOME);
	      req.getRequestDispatcher("/jsp/pages/home.jsp").forward(req, res);
	    }else {
	      req.getRequestDispatcher("/jsp/pages/register.jsp").forward(req, res);
	    }
    } catch(ServletException | IOException e) {
      System.out.println(e.getMessage());
    }
	}
}

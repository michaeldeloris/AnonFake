package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.database.DbManager;
import util.members.MembersManager;

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
		request.getRequestDispatcher("/jsp/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Connection connection = DbManager.getConnection();
    if(connection == null) {
      response.setStatus(500);
      redirect(response);
    }
    
    String username = (String) request.getParameter("username");
    String password = (String) request.getParameter("password");
    String confirm = (String) request.getParameter("password_confirm");
    try {
      MembersManager.registerMember(username, password, confirm);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    redirect(response);
	}
	
	private void redirect(HttpServletResponse response) {
	  try {
      response.sendRedirect("register");
    } catch(IOException e) {
      System.out.println(e.getMessage()); //TODO : use logger
    }
	}
}

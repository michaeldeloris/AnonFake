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

/**
 * Servlet implementation class Parameters
 */
@WebServlet("/params")
public class Parameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  if(request.getSession().getAttribute("USERSESSID") == null) {
	    request.getRequestDispatcher("/jsp/pages/login.jsp").forward(request, response);
	  } else {
	    request.getRequestDispatcher("/jsp/pages/params.jsp").forward(request, response);
	  }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String url = request.getParameter("url");
	  String username = request.getParameter("username");
    String password = request.getParameter("password");
    
    DbManager dbm = DbManager.getInstance();
    Connection conn = null;
    try {
      conn = dbm.getConnection(url, username, password);
    } catch (SQLException e) {
      request.setAttribute("error", "Connection failed.");
    }
	  
    if(conn != null) {
      dbm.updateCredentials(request.getServletContext(), url, username, password);
      request.setAttribute("success", "Connected successfully");
    }
    request.getRequestDispatcher("/jsp/pages/params.jsp").forward(request, response);
	}
}

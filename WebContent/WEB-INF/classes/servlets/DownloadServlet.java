package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.database.DbManager;
import util.errors.Error;
import util.files.UploadManager;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String key = (String) request.getParameter("key");
    
    if(key == null) {
      return;
    }
    String filename = getFileName(key);
    if(filename == null) {
      return;
    }
    
    response.setContentType("APPLICATION/OCTET-STREAM");
    response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
    File file = new File(getServletContext().getRealPath("") + UploadManager.PATH + File.separator + filename);
    FileInputStream fileInputStream = new FileInputStream(file);
    
    int i;
    while ((i=fileInputStream.read()) != -1) {
      out.write(i);
    }
    fileInputStream.close();
    out.close();
	}
	
	private String getFileName(String key) {
	  DbManager dbm = DbManager.getInstance();
    try {
      Connection conn = dbm.getConnection(getServletContext());
      Map<String, String> credentials = dbm.getLineFromValue(conn, UploadManager.TABLE_NAME, UploadManager.colsNames[2], key);
      Set<Entry<String, String>> set = credentials.entrySet();
      Iterator<Entry<String, String>> iterator = set.iterator();
      
      while(iterator.hasNext()) {
        Map.Entry<String, String> mentry = (Map.Entry<String, String>)iterator.next();
        if(mentry.getKey().equals("filename")) {
          return mentry.getValue();
        }
      }
    } catch (SQLException | IOException e) {
      System.out.println(e.getMessage());
    }
    return null;
	}
}

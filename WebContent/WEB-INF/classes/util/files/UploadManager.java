package util.files;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import util.database.DbManager;
import util.errors.Error;

public class UploadManager {
  
  public static final String TABLE_NAME = "files";
  public static final String[] colsNames = {"username", "filename", "key", "filepath"};
  public static final String[] colsTypes = {"VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)"};
  
  public static final String PATH = "upload";

  public HttpServletRequest upload(HttpServletRequest req, ServletContext ctx, List<Part> parts, String username) throws IOException {
    String ctxPath = ctx.getRealPath("");
    String path = getPath(ctxPath);
    for(Part part : parts) {
      String fileName = part.getSubmittedFileName();
      part.write(path + File.separator + fileName);
    }
    
    DbManager dbm = DbManager.getInstance();
    Connection conn = null;
    try {
       conn = dbm.getConnection(ctx);
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    
    if(conn == null) {
      req.setAttribute("error", Error.DATABASE_UNREACHABLE.toString());
      return req;
    }
    
    try {
      if(!dbm.tableExists(conn, TABLE_NAME)) {
        addFilesTable(conn);
      }
      for(Part part : parts) {
        System.out.println(ctxPath + PATH + part.getSubmittedFileName());
        dbm.addLine(conn, TABLE_NAME, username, part.getSubmittedFileName(), getRandomKey(), ctxPath + PATH + part.getSubmittedFileName());
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      req.setAttribute("error", Error.DATABASE_UNREACHABLE.toString());
    }
    
    return req;
  }
  
  private void addFilesTable(Connection conn) throws SQLException {
    int length = colsNames.length;
    String[][] cols = new String[length][length];
    for(int i = 0; i < length; i++) {
      cols[i][0] = colsNames[i];
      cols[i][1] = colsTypes[i];
    }
    DbManager.getInstance().createTable(conn, "files", cols);
  }
  
  private String getPath(String ctxPath) {
    String uploadPath = ctxPath + PATH;
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists()) {
      uploadDir.mkdir(); //create directory
    }
    return uploadPath;
  }
  
  private String getRandomKey() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
 
    return random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
  }
}

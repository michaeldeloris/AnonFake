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
  public static final String[] colsNames = {"username", "filename", "key"};
  public static final String[] colsTypes = {"VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)"};
  
  public static final String PATH = "upload";

  public HttpServletRequest upload(HttpServletRequest req, ServletContext ctx, List<Part> parts, String username) throws IOException {
    String ctxPath = ctx.getRealPath("");
    String path = getPath(ctxPath);
    String fileName = null;
    int i = 0;
    for(Part part : parts) {
      fileName = part.getSubmittedFileName();
      String extension = fileName.substring(fileName.lastIndexOf("."));
      String filePath = path + File.separator + fileName;
      File tempFile = new File(filePath);
      while(tempFile.exists()) {
        i++;
        fileName = fileName.substring(0, fileName.lastIndexOf(".")) + Integer.toString(i) + extension;
        filePath = path + File.separator + fileName;
        tempFile = new File(filePath);
      }
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
        dbm.addPrimaryKey(conn, TABLE_NAME, colsNames[2]);
      }
      for(Part part : parts) {
        String key = getRandomKey();
        dbm.addLine(conn, TABLE_NAME, username, fileName, key);
        req.setAttribute("filename", fileName);
        req.setAttribute("filekey", key);
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

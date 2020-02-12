package util.files;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import util.database.DbManager;
import util.errors.Error;

public class DownloadManager {
  
  public static HttpServletRequest retrieveFile(HttpServletRequest req, ServletContext ctx, String path) {
    String[] parts = path.split("/");
    if(parts.length < 3) {
      req.setAttribute("error", Error.NOT_FOUND.toString());
      return req;
    }
    
    DbManager dbm = DbManager.getInstance();
    try {
      Connection conn = dbm.getConnection(ctx);
      Map<String, String> credentials = dbm.getLineFromValue(conn, UploadManager.TABLE_NAME, UploadManager.colsNames[2], parts[2]);
      if(credentials == null) {
        req.setAttribute("error", Error.NOT_FOUND.toString());
        return req;
      }
      Set<Entry<String, String>> set = credentials.entrySet();
      Iterator<Entry<String, String>> iterator = set.iterator();
      
      String fileName = null;
      String key = null;
      while(iterator.hasNext()) {
        Map.Entry<String, String> mentry = (Map.Entry<String, String>)iterator.next();
        if(mentry.getKey().equals("filename")) {
          fileName = mentry.getValue();
        }else if(mentry.getKey().equals("key")) {
          key = mentry.getValue();
        }
      }
      if(fileName != null && key != null) {
        req.setAttribute("filename", fileName);
        req.setAttribute("key", key);
        return req;
      }
    } catch (SQLException | IOException e) {
      req.setAttribute("error", Error.NOT_FOUND.toString());
      return req;
    }
    req.setAttribute("error", Error.NOT_FOUND.toString());
    return req;
  }
}

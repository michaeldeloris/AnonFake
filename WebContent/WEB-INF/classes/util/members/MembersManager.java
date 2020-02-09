package util.members;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import util.database.DbManager;
import util.errors.Error;

public class MembersManager {
  
  private static final String TABLE_NAME = "members";
  private static final String[] colsNames = {"username", "password"};
  private static final String[] colsTypes = {"VARCHAR(255)", "VARCHAR(255)"};
  
  public static HttpServletRequest registerMember(String name, String pwd, String confirmPwd, HttpServletRequest req) throws SQLException {
    if(!pwd.equals(confirmPwd)) {
      req.setAttribute("errorAttribute", "password");
      req.setAttribute("error", Error.MISSMATCH_PASSWORDS.toString());
      return req;
    }
    
    Connection conn = DbManager.getConnection();
    if(!DbManager.tableExists(conn, TABLE_NAME)) {
      addMemberTable(conn);
      DbManager.addPrimaryKey(conn, TABLE_NAME, colsNames[0]);
    }
    
    try {
      String credentials[] = {name, hash(pwd)};
      DbManager.addLine(conn, TABLE_NAME, credentials);
    } catch (SQLException e) {
      if(e.getSQLState().equals("23505")) {
        req.setAttribute("errorAttribute", "username");
        req.setAttribute("error", Error.USERNAME_ALREADY_TAKEN.toString());
      } else {
        req.setAttribute("errorAttribute", "unknown");
        req.setAttribute("error", Error.UNKNOWN_ERROR.toString());
      }
    }
    return req;
  }
  
  public static HttpServletRequest logMember(String name, String pwd, HttpServletRequest req) throws SQLException {
    Connection conn = DbManager.getConnection();
    Map<String, String> credentials = new HashMap<>();
    try {
      credentials = DbManager.getLineFromValue(conn, TABLE_NAME, colsNames[0], name);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    
    String dbPwd = null;
    Set<Entry<String, String>> set = credentials.entrySet();
    Iterator<Entry<String, String>> iterator = set.iterator();
    while(iterator.hasNext()) {
      Map.Entry<String, String> mentry = (Map.Entry<String, String>)iterator.next();
      if(mentry.getKey().equals("password")) {
        dbPwd = (String) mentry.getValue();
      }
    }
    
    if(credentials.size() < 1 || !BCrypt.checkpw(pwd, dbPwd)) {
      req.setAttribute("error", Error.CANNOT_LOG.toString());
    }
    return req;
  }
  
  private static void addMemberTable(Connection conn) throws SQLException {
    int length = colsNames.length;
    String[][] cols = new String[length][length];
    for(int i = 0; i < length; i++) {
      cols[i][0] = colsNames[i];
      cols[i][1] = colsTypes[i];
    }
    DbManager.createTable(conn, TABLE_NAME, cols);
  }
  
  private static String hash(String str) {
    String salt = BCrypt.gensalt();
    return BCrypt.hashpw(str, salt);
  }
}

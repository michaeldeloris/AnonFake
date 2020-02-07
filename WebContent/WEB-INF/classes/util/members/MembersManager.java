package util.members;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import util.database.DbManager;
import util.errors.Error;

public class MembersManager {
  
  private static final String tableName = "members";
  private static final String colsNames[] = {"username", "password"};
  private static final String colsTypes[] = {"VARCHAR(255)", "VARCHAR(255)"};
  
  public static HttpServletRequest registerMember(String name, String pwd, String confirmPwd, HttpServletRequest req) throws SQLException {
    if(!pwd.equals(confirmPwd)) {
      req.setAttribute("errorAttribute", "password");
      req.setAttribute("error", Error.MISSMATCH_PASSWORDS.toString());
      return req;
    }
    
    Connection conn = DbManager.getConnection();
    if(!DbManager.tableExists(conn, tableName)) {
      addMemberTable(conn);
      DbManager.addPrimaryKey(conn, tableName, colsNames[0]);
    }
    
    try {
      String credentials[] = {name, hash(pwd)};
      DbManager.addLine(conn, tableName, credentials);
    } catch (SQLException e) {
      System.out.println(e.getSQLState().toString());
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
  
  private static void addMemberTable(Connection conn) throws SQLException {
    int length = colsNames.length;
    String[][] cols = new String[length][length];
    for(int i = 0; i < length; i++) {
      cols[i][0] = colsNames[i];
      cols[i][1] = colsTypes[i];
    }
    DbManager.createTable(conn, tableName, cols);
  }
  
  private static String hash(String str) {
    String salt = BCrypt.gensalt();
    return BCrypt.hashpw(str, salt);
  }
}

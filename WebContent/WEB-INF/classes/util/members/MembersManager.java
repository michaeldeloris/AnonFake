package util.members;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import util.database.DbManager;
import util.errors.Error;

public class MembersManager {
  
  private static final String tableName = "members";
  private static final String colsNames[] = {"username", "password"};
  private static final String colsTypes[] = {"VARCHAR(40)", "VARCHAR(40)"};
  
  public static HttpServletRequest registerMember(String name, String pwd, String confirmPwd, HttpServletRequest req) throws SQLException {
    if(!pwd.equals(confirmPwd)) {
      req.setAttribute("errorAttribute", "password");
      req.setAttribute("error", Error.MISSMATCH_PASSWORDS.toString());
      return req;
    }
    
    Connection conn = DbManager.getConnection();
    if(!DbManager.tableExists(conn, tableName)) {
      addMemberTable(conn);
    }
    
    try {
      String credentials[] = {name, pwd};
      DbManager.addLine(conn, tableName, credentials);
    } catch (SQLException e) {
      
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
}

package util.members;

import java.sql.Connection;
import java.sql.SQLException;

import util.database.DbManager;

public class MembersManager {
  
  private static final String tableName = "members";
  private static final String colsNames[] = {"username", "password"};
  private static final String colsTypes[] = {"VARCHAR(40)", "VARCHAR(40)"};
  
  public static void registerMember(String name, String pwd, String confirmPwd) throws SQLException {
    if(!pwd.equals(confirmPwd)) {
      System.out.println("Password and confirmation not equals.");
      return;
    }
    
    Connection conn = DbManager.getConnection();
    if(!DbManager.tableExists(conn, tableName)) {
      addMemberTable(conn);
    }
    
    try {
      String credentials[] = {name, pwd};
      DbManager.addLine(conn, tableName, credentials);
    } catch (SQLException e) {
      e.printStackTrace();
    }
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

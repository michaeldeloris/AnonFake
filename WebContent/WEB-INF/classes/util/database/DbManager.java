package util.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DbManager {
  
  private static final String URL = "jdbc:postgresql://127.0.0.1:5432/anonfakedb";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "lyonnais";
  
  public static Connection getConnection() {
    try {
      Properties props = new Properties();
      props.setProperty("user", USERNAME);
      props.setProperty("password", PASSWORD);
      return DriverManager.getConnection(URL, props);
    } catch(SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static void addLine(Connection conn, String tableName, String... values) throws SQLException {
    Statement stmt = conn.createStatement();
    String sql = RequestsDispenser.getInsert(tableName, values);
    stmt.executeUpdate(sql);
    stmt.close();
  }
  
  public static Map<String, String> getLineFromValue(Connection conn, String tableName, String colName, String value) throws SQLException {
    Statement stmt = conn.createStatement();
    String sql = RequestsDispenser.getSelectWhere(tableName, colName, value);
    ResultSet rs = null;
    try {
       rs = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    Map<String, String> cols = new HashMap<>();
    ResultSetMetaData rsmeta = rs.getMetaData();
    while (rs.next()) {
      for(int i = 1; i <= rsmeta.getColumnCount(); i++) {
        cols.put(rsmeta.getColumnName(i), rs.getString(i));
      }
    }
    stmt.close();
    return cols;
  }
  
  public static void createTable(Connection conn, String tableName, String[][] cols) throws SQLException {
    Statement stmt = conn.createStatement();
    String sql = RequestsDispenser.getTableCreate(tableName, cols);
    stmt.executeUpdate(sql);
    stmt.close();
  }
  
  public static void addPrimaryKey(Connection conn, String tableName, String colName) throws SQLException {
    Statement stmt = conn.createStatement();
    String sql = RequestsDispenser.getAddPrimaryKey(tableName, colName);
    stmt.executeUpdate(sql);
    stmt.close();
  }
  
  public static boolean tableExists(Connection conn, String name) throws SQLException {
    DatabaseMetaData md = conn.getMetaData();
    ResultSet result = md.getTables(null, null, "%", null);
    while (result.next()) {
      if(result.getString(3).equals(name)) {
        return true;
      }
    }
    return false;
  }
}

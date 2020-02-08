package util.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class DbManager {
  
  private static final String url = "jdbc:postgresql://127.0.0.1:5432/anonfakedb";
  private static final String username = "postgres";
  private static final String password = "lyonnais";
  
  public static Connection getConnection() {
    try {
      Properties props = new Properties();
      props.setProperty("user", username);
      props.setProperty("password", password);
      return DriverManager.getConnection(url, props);
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
  
  public static Set<String> getLineFromValue(Connection conn, String tableName, String colName, String value) throws SQLException {
    Statement stmt = conn.createStatement();
    String sql = RequestsDispenser.getSelectWhere(tableName, colName, value);
    ResultSet rs = null;
    try {
       rs = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    Set<String> cols = new HashSet<>();
    ResultSetMetaData rsmeta = rs.getMetaData();
    int test = rsmeta.getColumnCount();
    while (rs.next()) {
      for(int i = 1; i <= test; i++) {
        cols.add(rs.getString(i));
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

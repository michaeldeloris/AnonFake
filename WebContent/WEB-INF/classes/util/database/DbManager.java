package util.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import javax.servlet.ServletContext;

import org.apache.catalina.ant.ReloadTask;

import util.Constants;

public class DbManager {
  
  private static final DbManager instance = new DbManager();
  private String url = null;
  private String username = null;
  private String password = null;

  private DbManager() { }

  public static final DbManager getInstance() 
  {
      return instance;
  }
  
  public void updateCredentials(ServletContext ctx, String url, String username, String password) {
    String fullPath = ctx.getRealPath(Constants.PATH_PROPS);
    try(OutputStream output = new FileOutputStream(fullPath)) {
      Properties props = new Properties();
      
      props.setProperty("db.url", url);
      props.setProperty("db.user", username);
      props.setProperty("db.password", password);
      
      props.store(output, "Database params");
      reloadCredentials(fullPath);
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
  
  private void reloadCredentials(String path) {
    try (InputStream input = new FileInputStream(path)) {
      Properties props = new Properties();
      
      props.load(input);

      url = props.getProperty("db.url");
      username = props.getProperty("db.user");
      password = props.getProperty("db.password");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public Connection getConnection(String url, String username, String password) throws SQLException {
    Properties props = new Properties();
    props.setProperty("user", username);
    props.setProperty("password", password);
    return DriverManager.getConnection(url, props);
  }
  
  public Connection getConnection() throws SQLException {
    return getConnection(url, username, password);
  }
  
  public void addLine(Connection conn, String tableName, String... values) throws SQLException {
    Statement stmt = conn.createStatement();
    String sql = RequestsDispenser.getInsert(tableName, values);
    stmt.executeUpdate(sql);
    stmt.close();
  }
  
  public Map<String, String> getLineFromValue(Connection conn, String tableName, String colName, String value) throws SQLException {
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
  
  public void createTable(Connection conn, String tableName, String[][] cols) throws SQLException {
    Statement stmt = conn.createStatement();
    String sql = RequestsDispenser.getTableCreate(tableName, cols);
    stmt.executeUpdate(sql);
    stmt.close();
  }
  
  public void addPrimaryKey(Connection conn, String tableName, String colName) throws SQLException {
    Statement stmt = conn.createStatement();
    String sql = RequestsDispenser.getAddPrimaryKey(tableName, colName);
    stmt.executeUpdate(sql);
    stmt.close();
  }
  
  public boolean tableExists(Connection conn, String name) throws SQLException {
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

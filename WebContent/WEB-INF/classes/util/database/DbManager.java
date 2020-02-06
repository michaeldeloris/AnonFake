package util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
  
}

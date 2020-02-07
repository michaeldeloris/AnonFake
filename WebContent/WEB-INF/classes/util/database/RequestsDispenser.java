package util.database;

public class RequestsDispenser {

  public static String getInsert(String tableName, String... values) {
    StringBuilder request = new StringBuilder(); 
    request.append("INSERT INTO " + tableName + " VALUES (");
    for(int i = 0; i < values.length; i++) {
      request.append("\'" + values[i] + "\'");
      if(i != values.length -1) {
        request.append(", ");
      }
    }
    request.append(");");
    return request.toString();
  }
  
  public static String getTableCreate(String tableName, String[][] cols) {
    StringBuilder request = new StringBuilder();
    request.append("CREATE TABLE " + tableName + " (");
    for(int i = 0; i < cols.length; i++) {
      for(int j = 0; j < 2; j++) {
        request.append(" " + cols[i][j]);
      }
      if(i != cols.length -1) {
        request.append(",");
      } else {
        request.append(")");
      }
    }
    return request.toString();
  }
}

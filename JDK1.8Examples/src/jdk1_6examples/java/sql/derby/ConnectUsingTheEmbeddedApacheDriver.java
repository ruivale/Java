package jdk1_6examples.java.sql.derby;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectUsingTheEmbeddedApacheDriver {
  static Connection conn;

  public static void main(String[] args) {
    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    String connectionURL = "jdbc:derby:myDatabase;create=true";
    String createString = "CREATE TABLE Employee (NAME VARCHAR(32) NOT NULL, ADDRESS VARCHAR(50) NOT NULL)";
    try {
      Class.forName(driver);
    } catch (java.lang.ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection(connectionURL);
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(createString);

      PreparedStatement psInsert = conn.prepareStatement("insert into Employee values (?,?)");

      psInsert.setString(1, args[0]);
      psInsert.setString(2, args[1]);

      psInsert.executeUpdate();

      Statement stmt2 = conn.createStatement();
      ResultSet rs = stmt2.executeQuery("select * from Employee");
      int num = 0;
      while (rs.next()) {
        System.out.println(++num + ": Name: " + rs.getString(1) + "\n Address" + rs.getString(2));
      }
      rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

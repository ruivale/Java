package exp.jdbc.blobs;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Blob;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ObtainAnImageFromDB {

  public static void main(final String[] args) {
    try {
      long before = System.currentTimeMillis();

      Class.forName("oracle.jdbc.driver.OracleDriver");

      Connection conn = DriverManager.getConnection(
          "jdbc:oracle:thin:@172.18.56.231:1521:STV",
          "inoss_stv",
          "inoss");

      Statement stmt = conn.createStatement();

      String sqlQuery = "select * from T_IG_IMAGES";

      // Execute the SQL query.
      ResultSet rset = stmt.executeQuery(sqlQuery);

      while (rset.next()) {
        Blob blob = rset.getBlob("FB_IMAGE");
        System.out.println("blob.length()=" + blob.length());
        break;
      }

      long after = System.currentTimeMillis();

      long timeTook = after - before;

      System.out.println("timeTook=" + timeTook + ".\n");


    } catch (Exception ex) {
      ex.printStackTrace();
    }


  }
}

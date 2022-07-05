package exp.databases.tables;

import java.net.URL;
import java.sql.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ShowDBTables  {
  static myConnection theConn;

  public static void main (String args[]) {
    new ShowDBTables().doit();
    }

  public void doit() {
    theConn = new myConnection();
    theConn.Connect2Db("myDSN", "user", "password");
    try {
     ResultSet rs1;
     ResultSet rs2;
     String ss, tblnames = "";
     DatabaseMetaData dmd = theConn.dbConn.getMetaData();
     rs1 = dmd.getSchemas();
     while(rs1.next()) {
       ss = rs1.getString(1);
       rs2 = dmd.getTables(null,ss, "%",null);
       while(rs2.next())
          tblnames += rs2.getString(3) + " " + rs2.getString(4) + "\n\r";
       }
     System.out.println("Tables :");
     System.out.println(tblnames);
     }
    catch (Exception e) { e.printStackTrace(); }
   }
  }

class myConnection {
  Connection dbConn = null;
  void Connect2Db(String db, String user, String passw) {
    try {
      Driver d =
        (Driver)Class.forName
          ("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
      // URL corresponding to the ODBC DSN
      String URL = "jdbc:odbc:" + db;
      // DB logon
      dbConn =
         DriverManager.getConnection(URL, user, passw);
      }
    catch (Exception e) {
      e.printStackTrace();
      }
    }

  void Disconnect2Db() {
    try {
      dbConn.close();
      }
    catch (Exception e) {
      e.printStackTrace();
      }
    }
  }
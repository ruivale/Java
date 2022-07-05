// %1132847340125:exp.jdbc.jtable%
package exp.jdbc.jtable;


import java.io.*;

import java.sql.*;

import java.util.*;

import javax.swing.*;
import javax.swing.table.*;
import exp.layouts.BorderLayoutTests;
import java.awt.BorderLayout;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class TestJDBCTable {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param conn  Insert doc ...
   *
   * @return  Insert doc ...
   *
   * @throws SQLException  Insert doc ...
   */
  public static String createSampleTable(Connection conn)
      throws SQLException {
    Statement statement = conn.createStatement();

    // drop table if it exists
    try {
      statement.execute("DROP TABLE EMPLOYEES");
    } catch(SQLException sqle) {
      sqle.printStackTrace(); // if table !exists
    }

    statement.execute(
      "CREATE TABLE EMPLOYEES " +
      "(Name CHAR(20), Title CHAR(30), Salary INT)");
    statement.execute(
      "INSERT INTO EMPLOYEES VALUES " + "('Jill', 'CEO', 200000 )");
    statement.execute(
      "INSERT INTO EMPLOYEES VALUES " + "('Bob', 'VP', 195000 )");
    statement.execute(
      "INSERT INTO EMPLOYEES VALUES " + "('Omar', 'VP', 190000 )");
    statement.execute(
      "INSERT INTO EMPLOYEES VALUES " + "('Amy', 'Software Engineer', 50000 )");
    statement.execute(
      "INSERT INTO EMPLOYEES VALUES " +
      "('Greg', 'Software Engineer', 45000 )");

    statement.close();

    return "EMPLOYEES";
  }

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    try {

      /**
      //   driver, url, user, and pass can be passed in as
      //   system properties "jdbctable.driver",
      //   "jdbctable.url", "jdbctable.user", and
      //   "jdbctable.pass", or specified in a file
      //   called "jdbctable.properties" in current
      //   directory

      Properties testProps = new Properties();
      String     ddriver = System.getProperty("jdbctable.driver");
      String     durl    = System.getProperty("jdbctable.url");
      String     duser   = System.getProperty("jdbctable.user");
      String     dpass   = System.getProperty("jdbctable.pass");

      if(ddriver!=null) {
        testProps.setProperty(
          "jdbctable.driver",
          ddriver);
      }

      if(durl!=null) {
        testProps.setProperty(
          "jdbctable.url",
          durl);
      }

      if(duser!=null) {
        testProps.setProperty(
          "jdbctable.user",
          duser);
      }

      if(dpass!=null) {
        testProps.setProperty(
          "jdbctable.pass",
          dpass);
      }

      try {
        testProps.load(new FileInputStream(new File("jdbctable.properties")));
      } catch(Exception e) {
        ;
      } // ignore FNF, etc.

      System.out.println("Test Properties:");
      testProps.list(System.out);

// now get a connection
// note care to replace nulls with empty strings
      Class.forName(testProps.getProperty("jdbctable.driver"))
           .newInstance();

      String url = testProps.getProperty("jdbctable.url");
      url = ((url==null)
        ? ""
        : url);

      String user = testProps.getProperty("jdbctable.user");
      user = ((user==null)
        ? ""
        : user);

      String pass = testProps.getProperty("jdbctable.pass");
      pass = ((pass==null)
        ? ""
        : pass);


      Connection conn = DriverManager.getConnection(
          url,
          user,
          pass);
      /**/



      try {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn = DriverManager.getConnection(
          "jdbc:oracle:thin:@172.18.56.14:1521:STV",
          "inoss_stv",
          "inoss");

          // create db table to use
          //String tableName = createSampleTable(conn);

        // get a model for this db table and add to a JTable
          TableModel mod = new JDBCTableModel(conn, "t_ig_groups");
          JTable jtable = new JTable(mod);
          JScrollPane scroller = new JScrollPane(jtable);
          JFrame frame = new JFrame("JDBCTableModel demo");
          frame.getContentPane().setLayout(new BorderLayout());
          frame.getContentPane()
              .add(scroller, BorderLayout.CENTER);
          frame.setBounds(100,100,400,350);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setVisible(true);

          conn.close();

       } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
       }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}

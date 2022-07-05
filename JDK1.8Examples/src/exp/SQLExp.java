
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;


import java.sql.*;
import java.util.*;


public class SQLExp {

  public SQLExp() {

    try{

/*
    String url = "jdbc:odbc:Metro";
    Statement stmt;
    Connection con;
    String query = "select * from Map";

    try {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

    } catch(java.lang.ClassNotFoundException e) {
      System.err.print("ClassNotFoundException: ");
      System.err.println(e.getMessage());
    }
*/

/***********************************
 *
 * TESTE PARA A API DO RPBOURRET PARA COMUNICAÇÕES ENTRE BD E A
 * UTILIZAÇÃO DE XML
 *
 */

          Connection conn;
          PreparedStatement p;
          ResultSet rs;

          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          conn = DriverManager.getConnection("jdbc:odbc:Metro");

          p = conn.prepareStatement("select * from Map");

System.out.println("0");

          rs = p.executeQuery();

System.out.println("1");

          rs.close();

System.out.println("2");

          rs = p.executeQuery();

System.out.println("3");



/*
    try {
      Class.forName("org.gjt.mm.mysql.Driver");
    }catch(java.lang.ClassNotFoundException e) {
      System.err.print("ClassNotFoundException: ");
      System.err.println(e.getMessage());
    }

    String url = "jdbc:mysql://172.18.50.167/Aplicacao_Controladora";

    try{
      Connection con = DriverManager.getConnection(url, "root", "slamdoor");
*/


    }catch(Exception e){
      System.out.println("Deu erro no main!");
    }

  }

  public static void main(String[] args) {
    SQLExp SQLExp1 = new SQLExp();
  }
}

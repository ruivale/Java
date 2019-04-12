/**
 * <p>
 * Classname: package jdk1_6examples.java.sql.preparedstatements.TestPreparedStat
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.java.sql.preparedstatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;




/**
 * <p>
 * Description:
 * Comparing the PreparedStatement against normal/basic Statement.
 * If multiple the DB request is used multiple times, the precompiled statement
 * version, i.e. the PreparedStatement, is preferable to the normal/basic
 * statement.
 * If the DB request is used only once, according to the tests I made, the
 * normal/basic statement is faster.
 *
 * </p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class TestPreparedStat {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(TestPreparedStat.class.getName());

 /**
  *
  * @param iChoice
  */
  public TestPreparedStat(final int iChoice, final int iUserId){

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch(ClassNotFoundException e) {
      e.printStackTrace();
    }

    if(iChoice == 1){ // +/- 380 millis
      final List<String> list = getUserPermsUsingPreparedStatements(iUserId);
      for(String string : list) {
        System.out.println(string);
      }
    }else if(iChoice == 2){ // +/- 345 millis
      final List<String> list = getUserPermsUsingResultSet(iUserId);
      for(String string : list) {
        System.out.println(string);
      }
    }
    System.out.println("\n-------------------------------------------------\n");

  }

  private List<String> getUserPermsUsingPreparedStatements(final int iUserId){
    final List<String> list = new ArrayList<String>();
    Connection conn = null;
    PreparedStatement ps=null;
    ResultSet rs=null;

    try {
      conn = DriverManager.getConnection(
          "jdbc:oracle:thin:@172.18.56.84:1521:STV",
          "inoss_stv",
          "inoss");

      ps= conn.prepareStatement(
        "select * "+
        "from T_IG_PERMISSIONS "+
        "where FN_ID_USER=?");

      ps.setInt(1, iUserId);
      rs=ps.executeQuery();

      while(rs.next()){
        list.add("item: "+iUserId+" -> "+
            rs.getInt("FN_IDEQUIPIG")+
            rs.getInt("FN_ID_ZONEIG")+
            rs.getInt("FN_ID_STATIONIG")+
            rs.getInt("FN_ID_GROUPIG"));
      }

    } catch(SQLException e) {
      /* log SQL error on an internal log */
    } finally {
      if(rs!=null) {
        try { rs.close(); } catch(Exception e) {}
      }
      if(ps!=null) {
        try { ps.close(); } catch(Exception e) {}
      }
      if(conn!=null) {
        try { conn.close(); } catch(Exception e) {}
      }
    }

    return list;
  }

  private List<String> getUserPermsUsingResultSet(final int iUserId){
    final List<String> list = new ArrayList<String>();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs=null;

    try {
      conn = DriverManager.getConnection(
          "jdbc:oracle:thin:@172.18.56.84:1521:STV",
          "inoss_stv",
          "inoss");

      stmt = conn.createStatement();

      // Execute the SQL query.
      rs = stmt.executeQuery(
          "select * "+
          "from T_IG_PERMISSIONS "+
          "where FN_ID_USER="+iUserId);

      while(rs.next()){
        list.add("item: "+iUserId+" -> "+
            rs.getInt("FN_IDEQUIPIG")+
            rs.getInt("FN_ID_ZONEIG")+
            rs.getInt("FN_ID_STATIONIG")+
            rs.getInt("FN_ID_GROUPIG"));
      }

    } catch(SQLException e) {
      /* log SQL error on an internal log */
    } finally {
      if(rs!=null) {
        try { rs.close(); } catch(Exception e) {}
      }
      if(stmt!=null) {
        try { stmt.close(); } catch(Exception e) {}
      }
      if(conn!=null) {
        try { conn.close(); } catch(Exception e) {}
      }
    }

    return list;
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("TestPreparedStat").append("").toString();
  }

  public static void main(final String[] args){
    final int choice = 1;
    final int iUserId = 9001;

    long before = System.currentTimeMillis();
    final TestPreparedStat clazz = new TestPreparedStat(choice, iUserId);
    long after = System.currentTimeMillis();
    System.out.println("It took "+(after-before)+" millis.");
  }
}

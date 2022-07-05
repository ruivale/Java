/**
 * <p>
 * Classname: exp.jdbc.oracle.v12c.TestingOracleConn
 * </p>
 *
 * <p>Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package exp.jdbc.oracle.v12c;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Nov 5, 2014, 4:16:36 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class TestingOracleConn {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(TestingOracleConn.class.getName());


 /**
  * The TestingOracleConn default constructor.
  */
  public TestingOracleConn(final String strURL, final String strUser, final String strPass){
    Connection conn = null;

    long before = 0;
    long after = 0;

    try {
      System.out.println("Class.forName(\"oracle.jdbc.driver.OracleDriver\")");
      Class.forName("oracle.jdbc.driver.OracleDriver");

      DriverManager.setLoginTimeout(10000);

      System.out.println("DriverManager.getConnection(..) with login timeout of "+
                         DriverManager.getLoginTimeout()+" millis.");
      before = System.currentTimeMillis();

      // jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=tsid-rac1-vip)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=tsid-rac2-vip)(PORT=1521))(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=BDCLEAN)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=10)(DELAY=5))))
      conn = DriverManager.getConnection(strURL, strUser, strPass);
      after = System.currentTimeMillis();

      System.out.println("conn.getMetaData()");
      final DatabaseMetaData meta = conn.getMetaData();

      System.out.println("Got DB " +
                         meta.getDatabaseProductName()+" "+
                         meta.getDatabaseProductVersion());

    }catch(ClassNotFoundException | SQLException e){
      after = System.currentTimeMillis();
      e.printStackTrace();

    }finally{
      if(conn != null){
        try {
          conn.close();
        } catch (SQLException sQLException) {
          sQLException.printStackTrace();
        }
      }

      System.out.println("\nIt took "+(after-before)+" millis.");

    }
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("TestingOracleConn").append("").toString();
  }

  public static void main(final String[] args){
    final String strAddress = "172.18.56.31";
    final String strURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = "+
                          strAddress+")(PORT = 1521)))(CONNECT_DATA =(SERVICE_NAME = DBODEN)))";
    final String strUser = "inoss_ercom";
    final String strPass = "inoss";
    
    final TestingOracleConn clazz = 
        new TestingOracleConn(
            args!=null && args.length > 0? args[0]: strURL, 
            strUser, 
            strPass);
  }
}

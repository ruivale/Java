/**
 * <p>
 * Classname: exp.log.LogpTests
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
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
package exp.log;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Dec 11, 2015, 5:18:00 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class LogpTests {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(LogpTests.class.getName());

  /**
   * The LogpTests default constructor.
   */
  public LogpTests() {

    LOGGER.log(Level.FINE, DiagnosisMessages.systemHealthStatus());

    LOGGER.log(Level.FINE, DiagnosisMessages::systemHealthStatus);

  }

  public static void main(final String[] args) {
    final LogpTests clazz = new LogpTests();
  }

}

  class DiagnosisMessages {

    static String systemHealthStatus() {
      System.err.println("systemHealthStatus");
      return "systemHealthStatus";
    }
  }

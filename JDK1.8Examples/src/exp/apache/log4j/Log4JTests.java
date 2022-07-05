/**
 * <p>
 * Classname: exp.apache.log4j.Log4JTests
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
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
package exp.apache.log4j;

import org.apache.logging.log4j.LogManager;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Nov 24, 2017, 3:39:57 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class Log4JTests {

  /* Get actual class name to be printed on */
  static final org.apache.logging.log4j.Logger Log = LogManager.getLogger(Log4JTests.class.getName());

  String msg;

  public Log4JTests() {

    try {
      Log.error("garbage: " + msg);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      Log.error("garbage: {}", msg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    final Log4JTests clazz = new Log4JTests();
  }
}

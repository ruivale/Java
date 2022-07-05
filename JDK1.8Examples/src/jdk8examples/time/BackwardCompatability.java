/**
 * <p>
 * Classname: jdk8examples.datetime.BackwardCompatability
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2019 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package jdk8examples.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class BackwardCompatability {

  public static void main(String args[]) {
    BackwardCompatability java8tester = new BackwardCompatability();
    java8tester.testBackwardCompatability();
  }

  public void testBackwardCompatability() {
    //Get the current date
    Date currentDate = new Date();
    System.out.println("Current date: " + currentDate);

    //Get the instant of current date in terms of milliseconds
    Instant now = currentDate.toInstant();
    ZoneId currentZone = ZoneId.systemDefault();

    LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZone);
    System.out.println("Local date: " + localDateTime);

    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZone);
    System.out.println("Zoned date: " + zonedDateTime);
  }
}

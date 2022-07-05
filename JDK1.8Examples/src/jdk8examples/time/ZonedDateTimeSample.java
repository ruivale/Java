/**
 * <p>
 * Classname: jdk8examples.datetime.ZonedDateTime
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

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ZonedDateTimeSample {

  public static void main(String args[]) {
    ZonedDateTimeSample java8tester = new ZonedDateTimeSample();
    java8tester.testZonedDateTime();
  }

  public void testZonedDateTime() {
    // Get the current date and time
    ZonedDateTime date1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
    System.out.println("date1: " + date1);

    ZoneId id = ZoneId.of("Europe/Paris");
    System.out.println("ZoneId: " + id);

    ZoneId currentZone = ZoneId.systemDefault();
    System.out.println("CurrentZone: " + currentZone);

    
//    date1.
//    ZonedDateTime.now(ZoneId.of("Europe/Lisbon"));
  }
}

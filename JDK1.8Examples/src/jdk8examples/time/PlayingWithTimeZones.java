/**
 * <p>
 * Classname: jdk8examples.dateandtime.PlayingWithTimeZones
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class PlayingWithTimeZones {

  static DateTimeFormatter globalFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mma z");
  static DateTimeFormatter etFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mma 'ET'");

  static ZoneId istZoneId = ZoneId.of("Asia/Kolkata");
  static ZoneId etZoneId = ZoneId.of("America/New_York");

  public static void main(final String[] args) {
    LocalDateTime currentDateTime = LocalDateTime.now();

    ZonedDateTime currentISTime = currentDateTime.atZone(istZoneId);				//India Time
    ZonedDateTime currentETime = currentISTime.withZoneSameInstant(etZoneId);		//ET Time

    System.out.println(globalFormat.format(currentISTime));
    System.out.println(etFormat.format(currentETime));
  }
}

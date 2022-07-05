/**
 * <p>
 * Classname: exp.java.time.zone.DifferentTimeZoneExample2
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
package jdk8examples.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class DifferentTimeZoneExample2 {

  public static void main(String[] args) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");
    //Convert String to LocalDateTime
    String date = "2016-08-22 14:30";
    LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    System.out.println("LocalDateTime : " + format.format(ldt));
    //Paris, 2016 Apr-Oct = DST, UTC+2, other months UTC+1
    //UTC+2
    ZonedDateTime parisDateTime = ldt.atZone(ZoneId.of("Europe/Paris"));
    System.out.println("Depart : " + format.format(parisDateTime));
    //hard code a zoneoffset like this, UTC-5
    ZoneOffset nyOffSet = ZoneOffset.of("-05:00");
    ZonedDateTime nyDateTime = parisDateTime.withZoneSameInstant(nyOffSet).plusHours(8).plusMinutes(10);
    System.out.println("Arrive : " + format.format(nyDateTime));
    System.out.println("\n---Detail---");
    System.out.println("Depart : " + parisDateTime);
    System.out.println("Arrive : " + nyDateTime);
  }
}

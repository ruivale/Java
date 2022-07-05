/**
 * <p>
 * Classname: exp.java.time.zone.DifferentTimeZoneExample1
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
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class DifferentTimeZoneExample1 {

  public static void main(String[] args) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");
    LocalDateTime ldt = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30);
    System.out.println("LocalDateTime : " + format.format(ldt));
    //UTC+8
    ZonedDateTime klDateTime = ldt.atZone(ZoneId.of("Asia/Kuala_Lumpur"));
    System.out.println("Depart : " + format.format(klDateTime));
    //UTC+9 and flight duration = 7 hours
    ZonedDateTime japanDateTime = klDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo")).plusHours(7);
    System.out.println("Arrive : " + format.format(japanDateTime));
    System.out.println("\n---Detail---");
    System.out.println("Depart : " + klDateTime);
    System.out.println("Arrive : " + japanDateTime);
  }
}

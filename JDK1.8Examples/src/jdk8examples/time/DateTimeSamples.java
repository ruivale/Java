/**
 * <p>
 * Classname: jdk8examples.datetime.DateTimeSamples
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class DateTimeSamples {

  public static void main(String args[]) {
    DateTimeSamples dateTimeSamples = new DateTimeSamples();
    dateTimeSamples.testLocalDateTime();
  }

  public void testLocalDateTime() {
    // Get the current date and time
    LocalDateTime currentTime = LocalDateTime.now();
    System.out.println("Current DateTime: " + currentTime);

    LocalDate date1 = currentTime.toLocalDate();
    System.out.println("date1: " + date1);

    Month month = currentTime.getMonth();
    int day = currentTime.getDayOfMonth();
    int seconds = currentTime.getSecond();

    System.out.println("Month: " + month + "day: " + day + "seconds: " + seconds);

    LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
    System.out.println("date2: " + date2);

    //12 december 2014
    LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
    System.out.println("date3: " + date3);

    //22 hour 15 minutes
    LocalTime date4 = LocalTime.of(22, 15);
    System.out.println("date4: " + date4);

    //parse a string
    LocalTime date5 = LocalTime.parse("20:15:30");
    System.out.println("date5: " + date5);
  }
}

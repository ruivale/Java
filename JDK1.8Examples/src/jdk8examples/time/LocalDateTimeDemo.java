/**
 * <p>
 * Classname: jdk8examples.dateandtime.LocalDateTimeDemo
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;


/**
 * 
 * @author 2334
 */
public class LocalDateTimeDemo {

  public static void main(String[] args) {

    /** ** LocalDate is Date Without Time in Java8 *** */
    /** ** LocalTime is Time Without Date in Java8 *** */
    /** ** LocalDateTime is both Date and Time e.g. LocalDate + LocalTime but without TimeZone information *** */
    /** ** EXAMPLE #1 *** */
    /** ** LocalDateTime.now() creates a LocalDateTieme object with Current Date and Time *** */
    LocalDateTime rightNow = LocalDateTime.now();
    System.out.println("Current DateTime?= " + rightNow + "\n");

    /** ** Formatting the Date using ISO_LOCAL_DATE *** */
    String isoDate = rightNow.format(DateTimeFormatter.ISO_LOCAL_DATE);
    System.out.println("ISO Formatted Date?= " + isoDate + "\n");

    /** ** Formatting the Date using PATTERN *** */
    String pattern = "dd-MMM-yyyy HH:mm:ss";
    String patternDate = rightNow.format(DateTimeFormatter.ofPattern(pattern));
    System.out.println("Pattern Formatted DateTime?= " + patternDate + "\n");

    /** ** EXAMPLE #2 *** */
    /** ** LocalDateTime.of() method is a factory method to create LocalDateTiem with Specific Date and Time *** */
    LocalDateTime sDateTime = LocalDateTime.of(2017, Month.DECEMBER, 22, 21, 30, 40);
    System.out.println("Some DateTime?= " + sDateTime + "\n");

    /** ** EXAMPLE #3 *** */
    /** ** Developers can also create LocalDateTime object by combining LocalDate and LocalTime *** */
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    LocalDateTime fromDateAndTime = LocalDateTime.of(currentDate, currentTime);
    System.out.println("LocalDateTime created by combining 'LocalDate' and 'LocalTime'?= "
                       + fromDateAndTime + "\n");

    /** ** EXAMPLE #4 *** */
    /** ** Developers can also retrieve LocalDate and LocalTime from LocalDateTime *** */
    LocalDate retrievedDate = fromDateAndTime.toLocalDate();
    LocalTime retrievedTime = fromDateAndTime.toLocalTime();
    System.out.println("Retrieved LocalDate?= " + retrievedDate + ", Retrieved LocalTime?= "
                       + retrievedTime);
  }
}

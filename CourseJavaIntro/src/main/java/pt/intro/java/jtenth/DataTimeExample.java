/**
 * <pre>
 * Java intro.
 * 
 * Classname: pt.intro.java.jtenth.DataTimeExample
 * Copyright (C) 2024 RGV
 * Email: ruivale at gmail dot com
 *
 * This is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * </pre>
 */
package pt.intro.java.jtenth;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * <pre>
 * Description:
 * Converting or parsing a String to date and time can be performed using a set of parse() methods.
 * Converting date and time to String can be performed using the toString() or format() methods.
 * 
 *   Letter  Description           Presentation  Example
 *   y       year                 year          1994; 94
 *   M       month of year        number/text   7; 07; Jul; July; J
 *   W       week of month        number        4
 *   E       day of week          text          Tue; Tuesday; T
 *   d       day of month         number        15
 *   H       hour of day          number        22
 *   m       minute of hour       number        34
 *   s       second of minute     number        55
 *   S       fraction of second   number        345
 *   z       time zone name       zone-name     Pacific Standard Time; PST
 *   Z       zone offset          zone-offset   -0800
 *   V       time zone id (JDK 8) zone-id       America/Los_Angeles; Z; -08:30
 *
 * Some available formatting patterns:
 *   Pattern                    Example
 *   yyyy-MM-dd                 2019-02-24
 *   MM-dd-yyyy                 02-24-2019
 *   MMM-dd-yyyy                Feb-24-2019
 *   dd-MM-yy                   24-02-19
 *   dd.MM.yyyy                 24.02.2019
 *   yyyy-MM-dd HH:mm:ss        2019-02-24 11:26:26
 *   yyyy-MM-dd HH:mm:ssSSS     2019-02-24 11:36:32743
 *   yyyy-MM-dd HH:mm:ssZ       2019-02-24 11:40:35+0200
 *   yyyy-MM-dd HH:mm:ss z      2019-02-24 11:45:03 EET
 *   E MMM yyyy HH:mm:ss.SSSZ   Sun Feb 2019 11:46:32.393+0200
 *   yyyy-MM-dd HH:mm:ss VV     2019-02-24 11:45:41 Europe/Athens
 * 
 * </pre>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class DataTimeExample {

  /**
   * Use system-defined patterns to create DateTime.
   */
  private static void fromStringToDateTime() {
    System.out.println("\nFrom a \"String\" to date and time:");
    
    // Pattern: DateTimeFormatter.ISO_LOCAL_DATE, i.e., yyyy-MM-dd
    String strPattern = "2020-06-01";
    final LocalDate localDate = LocalDate.parse(strPattern);
    System.out.println("\tLocal date (Pattern: " + strPattern + "): " + localDate);
        
    // Pattern: DateTimeFormatter.ISO_LOCAL_TIME, i.e., HH:mm or HH:mm:ss
    strPattern = "12:23:44";
    final LocalTime localTime = LocalTime.parse(strPattern);
    System.out.println("\tLocal time (Pattern: " + strPattern + "): " + localTime);
    
    // Pattern: DateTimeFormatter.ISO_LOCAL_DATE_TIME, i.e., yyyy-MM-ddTHH:mm:ss
    strPattern = "2020-06-01T11:20:15";
    LocalDateTime localDateTime = LocalDateTime.parse(strPattern);
    System.out.println("\tLocal date and time (Pattern: " + strPattern + "): " + localDateTime);        
    // Pattern: DateTimeFormatter.ISO_LOCAL_DATE_TIME, i.e., yyyy-MM-ddTHH:mm
    strPattern = "2020-06-01T11:20";
    localDateTime = LocalDateTime.parse(strPattern);
    System.out.println("\tLocal date and time (Pattern: " + strPattern + "): " + localDateTime);        
  }
  
  /**
   * Use custom patterns to create DateTime.
   */
  private static void fromUserPatternStringsToDateTime() {
    System.out.println("\nFrom a custom pattern to date and time:");
    
    String strDTPattern = "dd.MM.yyyy";
    String strDTValue = "01.06.2020";
    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(strDTPattern);
    final LocalDate localDateFormatted = LocalDate.parse(strDTValue, dateFormatter);    
    System.out.println("\tLocal date (Value: " + strDTValue + ", Pattern: " + strDTPattern + "): " + localDateFormatted);
    
    strDTPattern = "HH|mm|ss";
    strDTValue = "12|23|44";
    final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(strDTPattern);
    final LocalTime localTimeFormatted = LocalTime.parse(strDTValue, timeFormatter);
    System.out.println("\tLocal time (Value: " + strDTValue + ", Pattern: " + strDTPattern + "): " + localTimeFormatted);

    strDTPattern = "dd.MM.yyyy, HH:mm:ss";
    strDTValue = "01.06.2020, 11:20:15";
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(strDTPattern);
    final LocalDateTime localDateTimeFormatted = LocalDateTime.parse(strDTValue, dateTimeFormatter);    
    System.out.println("\tLocal date and time (Value: " + strDTValue + ", Pattern: " + strDTPattern + "): " + localDateTimeFormatted);
  }
  
  /**
   * Get the current date and time.
   */
  private static void getNow() {
    System.out.println("\n\"Now\":");
    final LocalDate localDate = LocalDate.now();
    final LocalTime localTime = LocalTime.now();    
    System.out.println("\tLocal date: " + localDate + ", Local time: " + localTime);
  }

  /**
   * Returns a LocalDateTime using the two parameters received: LocalDate and LocalTime.
   * 
   * @param localDate the LocalDate to be used.
   * @param localTime the LocalTime to be used.
   * @return the LocalDateTime.
   */
  public static LocalDateTime of(LocalDate localDate, LocalTime localTime) {
    return LocalDateTime.of(localDate, localTime);
  }
  
  /**
   * Get date and time from a LocalDate and LocalTime class.
   */
  private static void getDateTimeFromNow() {
    System.out.println("\nGet date and time with \"Now\":");
    final LocalDate localDate = LocalDate.now();
    final LocalTime localTime = LocalTime.now();    
    LocalDateTime localDateTime = DataTimeExample.of(localDate, localTime);
    System.out.println("\tLocal date and time: " + localDateTime + ".");
  }
  
  /**
   * Using the "Instant" class
   */
  private static void instants() {
    System.out.println("\nInstants:");
    Instant timestamp = Instant.now();
    System.out.println("\tNow: " + timestamp);
    
    Instant twoHoursLater = timestamp.plus(2, ChronoUnit.HOURS);
    System.out.println("\tTwoHoursLater: " + twoHoursLater);
    
    Instant tenMinutesEarlier = timestamp.minus(10, ChronoUnit.MINUTES);
    System.out.println("\tTenMinutesEarlier: " + tenMinutesEarlier);

    final String strCharSeq = "2024-02-24T14:31:33.197021300Z";
    Instant timestampFromString = Instant.parse(strCharSeq);
    System.out.println("\t\"Now\" from a \"String\" (\"" + strCharSeq + "\"): " + timestampFromString);
    
    Instant timestampNow = Instant.now();
    Instant timestampNowPlusTenSecs = timestampNow.plusSeconds(10);
    System.out.println("\t\"Now\": " + timestampNow + " \"TenSecondsLater\": " + timestampNowPlusTenSecs);
    boolean isAfter = timestampNow.isAfter(timestampNowPlusTenSecs); // false
    System.out.println("\t\t\"Now\" is after \"TenSecondsLater\"? " + isAfter);
    boolean isBefore = timestampNow.isBefore(timestampNowPlusTenSecs); // true    
    System.out.println("\t\t\"Now\" is before \"TenSecondsLater\"? " + isBefore);    
    long difference = timestampNow.until(timestampNowPlusTenSecs, ChronoUnit.SECONDS);
    System.out.println("\t\tThe difference between \"Now\" and \"TenSecondsLater\" is " + difference + " seconds.");
    
    Instant startInstant = Instant.parse("2015-11-03T12:11:30.00Z");
    Instant endInstant = Instant.parse("2016-12-06T15:17:10.00Z");
    Duration durationBetweenInstant = Duration.between(startInstant, endInstant);    
    System.out.println("\tThe duration between the instants " + startInstant + " and " + endInstant +
        " is " + durationBetweenInstant.getSeconds() + " seconds.");
  }




  public static void main(final String[] args) {
    System.out.println("\n\n\nDate and time examples:\n");
    
    DataTimeExample.fromStringToDateTime();
    DataTimeExample.fromUserPatternStringsToDateTime();
    DataTimeExample.getNow();
    DataTimeExample.getDateTimeFromNow();
    DataTimeExample.instants();
    
    System.out.println("\n\n\n");
  }
}
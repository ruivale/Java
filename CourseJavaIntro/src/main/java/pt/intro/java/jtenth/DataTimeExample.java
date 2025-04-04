/**
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
 */
package pt.intro.java.jtenth;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 * <p>
 * Description:
 * A conversão ou análise de String para data e hora pode ser realizada através de um conjunto de métodos parse(). 
 * A conversão de data e hora para String pode ser realizada através dos métodos toString() ou format().
 * 
 * 
 * Letter Meaning Presentation Example
 *   y year year 1994; 94
 *   M month of year number/text 7; 07; Jul; July; J
 *   W week of month number 4
 *   E day of week text Tue; Tuesday; T
 *   d day of month number 15
 *   H hour of day number 22
 *   m minute of hour number 34
 *   s second of minute number 55
 *   S fraction of second number 345
 *   z time zone name zone-name Pacific Standard Time; PST
 *   Z zone offset zone-offset -0800
 *   V time zone id (JDK 8) zone-id America/Los_Angeles; Z; -08:30
 *
 * Some format pattern examples are available in the following table: 
 * Pattern                  Example
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
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class DataTimeExample {

  /**
   * Usar padrões definidos no sistema para criar DateTime.
   */
  public static void fromStringToDateTime(){
    System.out.println("\nfromStringToDatetime:");
    
    // Padrão: DateTimeFormatter.ISO_LOCAL_DATE, i.e., yyyy-MM-dd
    final LocalDate localDate = LocalDate.parse("2020-06-01");
    System.out.println("LocalDate: "+localDate);
        
    // Padrão: DateTimeFormatter.ISO_LOCAL_TIME, i.e., HH:mm ou HH:mm:ss
    final LocalTime localTime = LocalTime.parse("12:23:44");
    System.out.println("LocalTime: "+localTime);
    
    // Padrão: DateTimeFormatter.ISO_LOCAL_DATE_TIME, i.e., yyyy-MM-ddTHH:mm:ss
    LocalDateTime localDateTime = LocalDateTime.parse("2020-06-01T11:20:15");
    System.out.println("LocalDateTime: "+localDateTime);        
    // Padrão: DateTimeFormatter.ISO_LOCAL_DATE_TIME, i.e., yyyy-MM-ddTHH:mm
    localDateTime = LocalDateTime.parse("2020-06-01T11:20");
    System.out.println("LocalDateTime: "+localDateTime);        
  }
  
  
  /**
   * Usar padrões nossos para criar DateTime.
   */
  public static void fromUserPatternStringsToDateTime(){
    System.out.println("\nfromUserPatternStringsToDateTime:");
    
    String strDTPattern = "dd.MM.yyyy";
    String strDTValue = "01.06.2020";
    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(strDTPattern);
    final LocalDate localDateFormatted = LocalDate.parse(strDTValue, dateFormatter);    
    System.out.println("LocalDate (Value: "+strDTValue+", Pattern: "+strDTPattern+"): " + localDateFormatted);
    
    strDTPattern = "HH|mm|ss";
    strDTValue = "12|23|44";
    final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(strDTPattern);
    final LocalTime localTimeFormatted = LocalTime.parse(strDTValue, timeFormatter);
    System.out.println("LocalTime (Value: "+strDTValue+", Pattern: "+strDTPattern+"): "+localTimeFormatted);

    strDTPattern = "dd.MM.yyyy, HH:mm:ss";
    strDTValue = "01.06.2020, 11:20:15";
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(strDTPattern);
    final LocalDateTime localDateTimeFormatted = LocalDateTime.parse(strDTValue, dateTimeFormatter);    
    System.out.println("LocalDateTime (Value: "+strDTValue+", Pattern: "+strDTPattern+"): " + localDateTimeFormatted);
  }
  
  
  /**
   * Obter a data e a hora actuais.
   */
  public static void getNow() {
    System.out.println("\ngetNow:");
    final LocalDate localDate = LocalDate.now();
    final LocalTime localTime = LocalTime.now();    
    System.out.println("LocalDate: " + localDate+", LocalTime: "+localTime);
  }


  public static void main(final String[] args){
    System.out.println("\n\n\nDataTimeExample:\n");
    
    DataTimeExample.fromStringToDateTime();
    DataTimeExample.fromUserPatternStringsToDateTime();
    DataTimeExample.getNow();
    
    System.out.println("\n\n\n");
  }
}

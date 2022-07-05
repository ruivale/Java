/**
 * <p>
 * Classname: jdk8examples.datetime.ChronoUnitSamples
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
import java.time.temporal.ChronoUnit;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ChronoUnitSamples {

  public static void main(String args[]) {
    ChronoUnitSamples java8tester = new ChronoUnitSamples();
    java8tester.testChromoUnits();
  }

  public void testChromoUnits() {
    //Get the current date
    LocalDate today = LocalDate.now();
    System.out.println("Current date: " + today);

    //add 1 week to the current date
    LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
    System.out.println("Next week: " + nextWeek);

    //add 1 month to the current date
    LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
    System.out.println("Next month: " + nextMonth);

    //add 1 year to the current date
    LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
    System.out.println("Next year: " + nextYear);

    //add 10 years to the current date
    LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
    System.out.println("Date after ten year: " + nextDecade);
  }
}

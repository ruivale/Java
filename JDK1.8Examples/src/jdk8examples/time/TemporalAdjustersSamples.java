/**
 * <p>
 * Classname: jdk8examples.datetime.TemporalAdjustersSamples
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class TemporalAdjustersSamples {

  public static void main(String args[]) {
    TemporalAdjustersSamples java8tester = new TemporalAdjustersSamples();
    java8tester.testAdjusters();
  }

  public void testAdjusters() {
    //Get the current date
    LocalDate date1 = LocalDate.now();
    System.out.println("Current date: " + date1);

    //get the next tuesday
    LocalDate nextTuesday = date1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
    System.out.println("Next Tuesday on : " + nextTuesday);

    //get the second saturday of next month
    LocalDate firstInYear = LocalDate.of(date1.getYear(), date1.getMonth(), 1);
    LocalDate secondSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(
      DayOfWeek.SATURDAY)).with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    System.out.println("Second Saturday on : " + secondSaturday);
  }
}

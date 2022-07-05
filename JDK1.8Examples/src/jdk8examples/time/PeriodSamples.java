/**
 * <p>
 * Classname: jdk8examples.datetime.PediodSamples
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

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class PeriodSamples {

  public static void main(String args[]) {
    PeriodSamples java8tester = new PeriodSamples();
    java8tester.testPeriod();
    java8tester.testDuration();
  }

  public void testPeriod() {
    //Get the current date
    LocalDate date1 = LocalDate.now();
    System.out.println("Current date: " + date1);

    //add 1 month to the current date
    LocalDate date2 = date1.plus(1, ChronoUnit.MONTHS);
    System.out.println("Next month: " + date2);

    Period period = Period.between(date2, date1);
    System.out.println("Period: " + period);
  }

  public void testDuration() {
    LocalTime time1 = LocalTime.now();
    Duration twoHours = Duration.ofHours(2);

    LocalTime time2 = time1.plus(twoHours);
    Duration duration = Duration.between(time1, time2);

    System.out.println("Duration: " + duration);
  }
}

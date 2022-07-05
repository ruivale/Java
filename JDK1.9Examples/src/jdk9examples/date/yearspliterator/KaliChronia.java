/**
 * <p>
 * Classname: jdk9examples.date.yearspliterator.KaliChronia
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
package jdk9examples.date.yearspliterator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Since we always iterate back by exactly one year, each LocalDate is DISTINCT. The sequence of
 * values returned is always the same and cannot be changed, hence IMMUTABLE. We never get null
 * back, so NONNULL. It is SORTED, by reverse natural order. SORTED implies ORDERED.
 * 
 * Let's have a look at which New Year Days since 1900 had the characteristic of falling on a Monday:
 */
public class KaliChronia {

  public static void main(String... args) {
    Stream<LocalDate> newYearDays = StreamSupport.stream(
      new YearSpliterator(
        LocalDate.of(2018, Month.JANUARY, 1)), false);
    newYearDays
      .filter(day -> day.getDayOfWeek() == DayOfWeek.MONDAY)
      .takeWhile(day -> day.getYear() >= 1900) // Java 9
      .forEach(System.out::println);
  }
}

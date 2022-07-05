/**
 * <p>
 * Classname: jdk8examples.dateandtime.DateAndTimeSamples
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Dec 29, 2015, 6:31:26 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class DateAndTimeSamples {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(DateAndTimeSamples.class.getName());

  /**
   * The DateAndTimeSamples default constructor.
   */
  public DateAndTimeSamples() {

    {
      final LocalDate today = LocalDate.parse("2014-02-27");
      System.out.println(today);
      final LocalDate bday = LocalDate.of(2014, 3, 18);
      System.out.println(bday);



      final LocalDate oneMonthFromNow = today.plusDays(30);
      assertTrue(oneMonthFromNow.isEqual(LocalDate.parse("2014-03-29")));
      final LocalDate nextMonth = today.plusMonths(1);
      assertTrue(nextMonth.isEqual(LocalDate.parse("2014-03-27")));
      final LocalDate future = today.plus(4, ChronoUnit.WEEKS);
      assertTrue(future.isEqual(LocalDate.parse("2014-03-27")));



      assertThat(today.minusWeeks(1).toString(), is("2014-02-20"));
      assertThat(today.minusMonths(2).toString(), is("2013-12-27"));
      assertThat(today.minusYears(4).toString(), is("2010-02-27"));
      final Period twoMonths = Period.ofMonths(2);
      assertThat(today.minus(twoMonths).toString(), is("2013-12-27"));



      final LocalDate vacationStart = LocalDate.parse("2014-07-04");
      final Period timeUntilVacation = today.until(vacationStart);
      assertThat(timeUntilVacation.getMonths(), is(4));
      assertThat(timeUntilVacation.getDays(), is(7));
      assertThat(today.until(vacationStart, ChronoUnit.DAYS), is(127L));
      final LocalDate libraryBookDue = LocalDate.parse("2000-03-18");
      assertThat(today.until(libraryBookDue).isNegative(), is(true));
      assertThat(today.until(libraryBookDue, ChronoUnit.DAYS), is(-5094L));
      final LocalDate christmas = LocalDate.parse("2014-12-25");
      assertThat(today.until(christmas, ChronoUnit.DAYS), is(301L));


      System.out.println();


      //Uses the system clock using the default time-zone.
      final ZonedDateTime zdt = ZonedDateTime.now();
      System.out.println(zdt);
      //Uses the system clock with the specified time-zone
      final ZonedDateTime zdtLx = ZonedDateTime.now(ZoneId.of("Europe/Lisbon"));
      System.out.println(zdtLx);


      System.out.println();


      OffsetDateTime odt = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("-4"));
      System.out.println(odt);
      OffsetTime ot = OffsetTime.ofInstant(Instant.now(), ZoneId.of("America/Los_Angeles"));
      System.out.println(ot);


      System.out.println();


      //Returns Clock with default time-zone
      final Clock defaultClock = Clock.systemDefaultZone();
      System.out.println(defaultClock);
      //Clock with desired time-zone
      Clock clockChicago = Clock.system(ZoneId.of("America/Chicago"));
      System.out.println(clockChicago);


      System.out.println();


      final Clock wholeMinuteClock = Clock.tickMinutes(ZoneId.of("Europe/Athens"));
      System.out.println(wholeMinuteClock);
      final Clock wholeSecondClock = Clock.tickSeconds(ZoneId.of("Europe/Prague"));
      System.out.println(wholeSecondClock);


      System.out.println();


      //Record the current moment on the time-line from the system clock
      final Instant now = Instant.now();
      System.out.println(now);
      //Record the current instant with the given Clock instance
      final Instant nowChicago = Instant.now(clockChicago);
      System.out.println(nowChicago);


      System.out.println();
    }


    {
      final LocalDateTime today = LocalDateTime.parse("2014-03-12T19:36:33");
      final Period sixDays = Period.ofDays(6);
      final LocalDateTime nextWeek = today.plus(sixDays);
      assertThat(nextWeek.toString(), is("2014-03-18T19:36:33"));
    }
    {
      final LocalDate today = LocalDate.parse("2014-03-12");
      final Period twoWeeks = Period.ofWeeks(2);
      final LocalDate past = today.minus(twoWeeks);
      assertThat(past.toString(), is("2014-02-26"));
    }

    {
      final LocalDate twins = LocalDate.parse("2003-11-18");
      final LocalDate mayhem = LocalDate.parse("2009-06-01");
      final Period timeBetween = Period.between(twins,mayhem);
      assertThat(timeBetween.getYears(), is(5));
      assertThat(timeBetween.getMonths(), is(6));
      assertThat(timeBetween.getDays(), is(14));
    }

    {
      final Duration oneHourThirtyMinutes = Duration.ofHours(1).plusMinutes(30);
      final OffsetTime now = OffsetTime.parse("10:15:30-05:00");
      final OffsetTime later = now.plus(oneHourThirtyMinutes);
      assertThat(later.toString(),is("11:45:30-05:00"));
      final Duration threeHours = Duration.parse("PT3H");
      final LocalTime twoPM = LocalTime.parse("14:00:00");
      final LocalTime fivePM = twoPM.plus(threeHours);
      assertThat(fivePM.toString(),is("17:00"));
    }

    {
      final OffsetTime offsetTime = OffsetTime.parse("13:34:00+01:00");
      final LocalTime earlier = LocalTime.parse("09:30:25");
      final LocalTime later = LocalTime.parse("15:33:47");
      final Duration timeSpan = Duration.between(earlier,later);
      final OffsetTime adjustedOffsetTime = offsetTime.minus(timeSpan);
      assertThat(adjustedOffsetTime.toString(),is("07:30:38+01:00"));
    }

    {
      final LocalTime earlier = LocalTime.parse("09:30:25");
      final LocalTime later = LocalTime.parse("15:33:47");
      final Duration timeSpan = Duration.between(earlier, later);
      assertThat(timeSpan.toString(), is("PT6H3M22S"));
      assertThat(LocalTime.MIDNIGHT.plus(timeSpan).toString(), is("06:03:22"));
    }



    {
      ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
    }

    {
      // OffsetDateTime is a date and time with a resolved offset.
      // This is useful for serializing data into a database and also should be used as the
      // serialization format for logging time stamps if you have servers in different time zones.
      // OffsetTime is a time with a resolved offset, as shown in Listing 9.
      ZoneOffset offset = ZoneOffset.of("+2:00");
      OffsetTime time = OffsetTime.now();
      // changes offset, while keeping the same point on the timeline
      OffsetTime sameTimeDifferentOffset = time.withOffsetSameInstant(offset);
      // changes the offset, and updates the point on the timeline
      OffsetTime changeTimeWithNewOffset = time.withOffsetSameLocal(offset);
      // Can also create new object with altered fields as before
      changeTimeWithNewOffset.withHour(3).plusSeconds(2);
    }

  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  @Override
  public String toString() {
    return new StringBuffer("DateAndTimeSamples").append("").toString();
  }

  public static void main(final String[] args) {
    final DateAndTimeSamples clazz = new DateAndTimeSamples();
  }
}

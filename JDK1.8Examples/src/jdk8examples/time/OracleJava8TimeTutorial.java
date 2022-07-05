package jdk8examples.time;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * <p>
 * Description:
 *
 * The core of the Date-Time API is the java.time package. The classes defined in java.time base
 * their calendar system on the ISO calendar, which is the world standard for representing date and
 * time. The ISO calendar follows the proleptic Gregorian rules. The Gregorian calendar was
 * introduced in 1582; in the proleptic Gregorian calendar, dates are extended backwards from that
 * time to create a consistent, unified timeline and to simplify date calculations.
 *
 * This lesson covers the following topics: Overview This section compares the concepts of human
 * time and machine time provides a table of the primary temporal-based classes in the java.time
 * package.
 *
 * DayOfWeek and Month Enums This section discusses the enum that defines the days of the week
 * (DayOfWeek) and the enum that defines months (Month).
 *
 * Date Classes This section shows the temporal-based classes that deal only with dates, without
 * time or time zones. The four classes are LocalDate, YearMonth, MonthDay and Year.
 *
 * Date and Time Classes This section presents the LocalTime and LocalDateTime classes, which deal
 * with time, and date and time, respectively, but without time zones.
 *
 * Time Zone and Offset Classes This section discusses the temporal-based classes that store time
 * zone (or time zone offset) information, ZonedDateTime, OffsetDateTime, and OffsetTime. The
 * supporting classes, ZoneId, ZoneRules, and ZoneOffset, are also discussed.
 *
 * Instant Class This section discusses the Instant class, which represents an instantaneous moment
 * on the timeline.
 *
 * Parsing and Formatting This section provides an overview of how to use the predefined formatters
 * to format and parse date and time values.
 *
 * The Temporal Package This section presents an overview of the java.time.temporal package, which
 * supports the temporal classes, fields (TemporalField and ChronoField) and units (TemporalUnit and
 * ChronoUnit). This section also explains how to use a temporal adjuster to retrieve an adjusted
 * time value, such as "the first Tuesday after April 11", and how to perform a temporal query.
 * The java.time.temporal package provides a collection of interfaces, classes, and enums that 
 * support date and time code and, in particular, date and time calculations. These interfaces are 
 * intended to be used at the lowest level. Typical application code should declare variables and 
 * parameters in terms of the concrete type, such as LocalDate or ZonedDateTime, and not in terms 
 * of the Temporal interface. This is exactly the same as declaring a variable of type String, and 
 * not of type CharSequence.
 * (https://docs.oracle.com/javase/tutorial/datetime/iso/temporal.html)
 *
 * Period and Duration This section explains how to calculate an amount of time, using both the
 * Period and Duration classes, as well as the ChronoUnit.between method.
 *
 * Clock This section provides a brief overview of the Clock class. You can use this class to
 * provide an alternative clock to the system clock.
 * Most temporal-based objects provide a no-argument now() method that provides the current date 
 * and time using the system clock and the default time zone. These temporal-based objects also 
 * provide a one-argument now(Clock) method that allows you to pass in an alternative Clock.
 * The current date and time depends on the time-zone and, for globalized applications, a Clock 
 * is necessary to ensure that the date/time is created with the correct time-zone. So, although 
 * the use of the Clock class is optional, this feature allows you to test your code for other 
 * time zones, or by using a fixed clock, where time does not change.
 * The Clock class is abstract, so you cannot create an instance of it. The following factory 
 * methods can be useful for testing.
 *    Clock.offset(Clock, Duration) returns a clock that is offset by the specified Duration.
 *    Clock.systemUTC() returns a clock representing the Greenwich/UTC time zone.
 *    Clock.fixed(Instant, ZoneId) always returns the same Instant. For this clock, time stands still.
 *
 * Non-ISO Date Conversion This section explains how to convert from a date in the ISO calendar
 * system to a date in a non-ISO calendar system, such as a JapaneseDate or a ThaiBuddhistDate.
 * (https://docs.oracle.com/javase/tutorial/datetime/iso/nonIso.html)
 *
 * Legacy Date-Time Code This section offers some tips on how to convert older java.util.Date and
 * java.util.Calendar code to the Date-Time API.
 * (https://docs.oracle.com/javase/tutorial/datetime/iso/legacy.html)
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class OracleJava8TimeTutorial {

  /**
   * The OracleJava8TimeTutorial default constructor.
   */
  public OracleJava8TimeTutorial() {

    {
      // The Date-Time API provides a fluent interface, making the code easy to read. 
      // Because most methods do not allow parameters with a null value and do not return 
      // a null value, method calls can be chained together and the resulting code can be 
      // quickly understood. For example:
      LocalDate today = LocalDate.now();
      LocalDate payday = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
      System.out.println("Today: " + today + " Payday: " + payday);
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Most of the classes in the Date-Time API create objects that are immutable, meaning 
      // that, after the object is created, it cannot be modified. To alter the value of an 
      // immutable object, a new object must be constructed as a modified copy of the original. 
      // This also means that the Date-Time API is, by definition, thread-safe. This affects 
      // the API in that most of the methods used to create date or time objects are prefixed 
      // with of, from, or with, rather than constructors, and there are no set methods. 
      // For example:
      LocalDate dateOfBirth = LocalDate.of(2012, Month.MAY, 14);
      LocalDate firstBirthday = dateOfBirth.plusYears(1);
      System.out.println("Date of birth: " + dateOfBirth + " First birthday: " + firstBirthday);
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // DayOfWeek
      System.out.printf("DayOfWeek.MONDAY.plus(3)= %s%n", DayOfWeek.MONDAY.plus(3));
      DayOfWeek dow = DayOfWeek.MONDAY;
      Locale locale = Locale.getDefault();
      System.out.println("DayOfWeek= " + dow + " Locale=" + locale);
      System.out.println("\t" + dow.getDisplayName(TextStyle.FULL, locale));
      System.out.println("\t" + dow.getDisplayName(TextStyle.NARROW, locale));
      System.out.println("\t" + dow.getDisplayName(TextStyle.SHORT, locale));
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Month
      System.out.printf("Month.FEBRUARY.maxLength()= %d%n", Month.FEBRUARY.maxLength());

      Month month = Month.AUGUST;
      Locale locale = Locale.getDefault();
      System.out.println("Month= " + month + " Locale=" + locale);
      System.out.println("\t" + month.getDisplayName(TextStyle.FULL, locale));
      System.out.println("\t" + month.getDisplayName(TextStyle.NARROW, locale));
      System.out.println("\t" + month.getDisplayName(TextStyle.SHORT, locale));

    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // LocalDate
      //  A LocalDate represents a year-month-day in the ISO calendar and is useful for 
      //  representing a date without a time. You might use a LocalDate to track a significant 
      //  event, such as a birth date or wedding date. The following examples use the of and with 
      //  methods to create instances of LocalDate:

      LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
      LocalDate nextWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
      System.out.println("LocalDate= " + date + " NextWed= " + nextWed);

      DayOfWeek dotw = LocalDate.of(2012, Month.JULY, 9).getDayOfWeek();
      System.out.println("LocalDate.of(2012, Month.JULY, 9).getDayOfWeek()= " + dotw);

      LocalDate date2 = LocalDate.of(2000, Month.NOVEMBER, 20);
      TemporalAdjuster adj = TemporalAdjusters.next(DayOfWeek.WEDNESDAY);
      LocalDate nextWed2 = date2.with(adj);
      System.out.printf("For the date of %s, the next Wednesday is %s.%n", date2, nextWed2);
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // YearMonth
      //  The YearMonth class represents the month of a specific year. The following example uses 
      //  the YearMonth.lengthOfMonth() method to determine the number of days for several year 
      //  and month combinations.

      YearMonth date = YearMonth.now();
      System.out.printf("lengthOfMonth() %s: %d%n", date, date.lengthOfMonth());

      YearMonth date2 = YearMonth.of(2010, Month.FEBRUARY);
      System.out.printf("lengthOfMonth() %s: %d%n", date2, date2.lengthOfMonth());

      YearMonth date3 = YearMonth.of(2012, Month.FEBRUARY);
      System.out.printf("lengthOfMonth() %s: %d%n", date3, date3.lengthOfMonth());
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // MonthDay
      //  The MonthDay class represents the day of a particular month, such as New Year's Day 
      //  on January 1. The following example uses the MonthDay.isValidYear method to determine 
      //  if February 29 is valid for the year 2010. The call returns false, confirming that 
      //  2010 is not a leap year.
      MonthDay date = MonthDay.of(Month.FEBRUARY, 29);
      boolean validLeapYear = date.isValidYear(2010);
      System.out.println("Date= " + date + " date.isValidYear(2010)?" + validLeapYear);
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Year
      // The Year class represents a year. The following example uses the Year.isLeap method to 
      // determine if the given year is a leap year. The call returns true, confirming that 2012 
      // is a leap year.
      boolean validLeapYear = Year.of(2012).isLeap();
      System.out.println("Year.of(2012).isLeap()? " + validLeapYear);
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // LocalTime
      //  The LocalTime class is similar to the other classes whose names are prefixed with Local, 
      //  but deals in time only. This class is useful for representing human-based time of day, 
      //  such as movie times, or the opening and closing times of the local library. It could also 
      //  be used to create a digital clock, as shown in the following example.
      //  The LocalTime class does not store time zone or daylight saving time information.

      LocalTime thisSec;

      for (int i = 0; i < 3; i++) {
        thisSec = LocalTime.now();
        System.out.printf("%s:%s:%s%n", thisSec.getHour(), thisSec.getMinute(), thisSec.getSecond());
        try {
          Thread.sleep(786);
        } catch (InterruptedException iex) {
        }
      }

    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // LocalDateTime
      //  The class that handles both date and time, without a time zone, is LocalDateTime, one of 
      //  the core classes of the Date-Time API. This class is used to represent date (month-day-year) 
      //  together with time (hour-minute-second-nanosecond) and is, in effect, a combination of 
      //  LocalDate with LocalTime. This class can be used to represent a specific event, such as 
      //  the first race for the Louis Vuitton Cup Finals in the America's Cup Challenger Series, 
      //  which began at 1:10 p.m. on August 17, 2013. Note that this means 1:10 p.m. in local time. 
      //  To include a time zone, you must use a ZonedDateTime or an OffsetDateTime, as discussed 
      //  in Time Zone and Offset Classes.
      //  In addition to the now method that every temporal-based class provides, the LocalDateTime 
      //  class has various of methods (or methods prefixed with of) that create an instance of 
      //  LocalDateTime. There is a from method that converts an instance from another temporal 
      //  format to a LocalDateTime instance. There are also methods for adding or subtracting 
      //  hours, minutes, days, weeks, and months. The following example shows a few of these 
      //  methods. The date-time expressions are in bold:

      System.out.printf("now: %s%n", LocalDateTime.now());

      System.out.printf("Apr 15, 1994 @ 11:30am: %s%n",
        LocalDateTime.of(1994, Month.APRIL, 15, 11, 30));

      System.out.printf("now (from Instant): %s%n",
        LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));

      System.out.printf("6 months from now: %s%n",
        LocalDateTime.now().plusMonths(6));

      System.out.printf("6 months ago: %s%n",
        LocalDateTime.now().minusMonths(6));
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Time Zone and Offset Classes
      //  A time zone is a region of the earth where the same standard time is used. Each time zone 
      //  is described by an identifier and usually has the format region/city (Asia/Tokyo) and an 
      //  offset from Greenwich/UTC time. For example, the offset for Tokyo is +09:00.
      //
      //  ZoneId and ZoneOffset
      //  The Date-Time API provides two classes for specifying a time zone or an offset:
      //  ZoneId specifies a time zone identifier and provides rules for converting between an 
      //  Instant and a LocalDateTime. ZoneOffset specifies a time zone offset from Greenwich/UTC time.
      //  Offsets from Greenwich/UTC time are usually defined in whole hours, but there are exceptions. 
      //  The following code, from the TimeZoneId example, prints a list of all time zones that use 
      //  offsets from Greenwich/UTC that are not defined in whole hours.

      Set<String> allZones = ZoneId.getAvailableZoneIds();
      LocalDateTime dt = LocalDateTime.now();

      // Create a List using the set of zones and sort it.
      List<String> zoneList = new ArrayList<String>(allZones);
      Collections.sort(zoneList);


      for (String s : zoneList) {
          ZoneId zone = ZoneId.of(s);
          ZonedDateTime zdt = dt.atZone(zone);
          ZoneOffset offset = zdt.getOffset();
          int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
          String out = String.format("%25s %10s%n", zone, offset);

          // Write only time zones that do not have a whole hour offset
          // to standard out.
          if (secondsOfHour != 0) {
              System.out.printf(out);
          }
      }

    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // The Date-Time Classes
      //  The Date-Time API provides three temporal-based classes that work with time zones:
      //    ZonedDateTime handles a date and time with a corresponding time zone with a time zone 
      //                  offset from Greenwich/UTC.
      //    OffsetDateTime handles a date and time with a corresponding time zone offset from 
      //                   Greenwich/UTC, without a time zone ID.
      //    OffsetTime handles time with a corresponding time zone offset from Greenwich/UTC, 
      //               without a time zone ID.
      //
      //  When would you use OffsetDateTime instead of ZonedDateTime? If you are writing complex 
      //  software that models its own rules for date and time calculations based on geographic 
      //  locations, or if you are storing time-stamps in a database that track only absolute 
      //  offsets from Greenwich/UTC time, then you might want to use OffsetDateTime. Also, XML 
      //  and other network formats define date-time transfer as OffsetDateTime or OffsetTime.
      //
      //  Although all three classes maintain an offset from Greenwich/UTC time, only ZonedDateTime 
      //  uses the ZoneRules, part of the java.time.zone package, to determine how an offset varies 
      //  for a particular time zone. For example, most time zones experience a gap (typically of 
      //  1 hour) when moving the clock forward to daylight saving time, and a time overlap when 
      //  moving the clock back to standard time and the last hour before the transition is repeated. 
      //  The ZonedDateTime class accommodates this scenario, whereas the OffsetDateTime and 
      //  OffsetTime classes, which do not have access to the ZoneRules, do not.
      //
      // ZonedDateTime
      //  The ZonedDateTime class, in effect, combines the LocalDateTime class with the ZoneId class. 
      //  It is used to represent a full date (year, month, day) and time (hour, minute, second, 
      //  nanosecond) with a time zone (region/city, such as Europe/Paris).
      //
      //  The following code, from the Flight example, defines the departure time for a flight from 
      //  San Francisco to Tokyo as a ZonedDateTime in the America/Los Angeles time zone. The 
      //  withZoneSameInstant and plusMinutes methods are used to create an instance of ZonedDateTime 
      //  that represents the projected arrival time in Tokyo, after the 650 minute flight. The 
      //  ZoneRules.isDaylightSavings method determines whether it is daylight saving time when the 
      //  flight arrives in Tokyo.
      //
      //  A DateTimeFormatter object is used to format the ZonedDateTime instances for printing:

      DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");

      // Leaving from San Francisco on July 20, 2013, at 7:30 p.m.
      LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
      ZoneId leavingZone = ZoneId.of("America/Los_Angeles"); 
      ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

      try {
          String out1 = departure.format(format);
          System.out.printf("LEAVING:  %s (%s)%n", out1, leavingZone);
      } catch (DateTimeException exc) {
          System.out.printf("%s can't be formatted!%n", departure);
          throw exc;
      }

      // Flight is 10 hours and 50 minutes, or 650 minutes
      ZoneId arrivingZone = ZoneId.of("Asia/Tokyo"); 
      ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone)
                                       .plusMinutes(650);

      try {
          String out2 = arrival.format(format);
          System.out.printf("ARRIVING: %s (%s)%n", out2, arrivingZone);
      } catch (DateTimeException exc) {
          System.out.printf("%s can't be formatted!%n", arrival);
          throw exc;
      }

      if (arrivingZone.getRules().isDaylightSavings(arrival.toInstant())) {
          System.out.printf("  (%s daylight saving time will be in effect.)%n", arrivingZone);
      } else {
          System.out.printf("  (%s standard time will be in effect.)%n", arrivingZone);
      }

    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // OffsetDateTime
      //  The OffsetDateTime class, in effect, combines the LocalDateTime class with the ZoneOffset 
      //  class. It is used to represent a full date (year, month, day) and time (hour, minute, 
      //  second, nanosecond) with an offset from Greenwich/UTC time (+/-hours:minutes, such as 
      //  +06:00 or -08:00).
      //
      //  The following example uses OffsetDateTime with the TemporalAdjuster.lastDay method to 
      //  find the last Thursday in July 2013.
      //
      // Find the last Thursday in July 2013.
      LocalDateTime localDate = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
      ZoneOffset offset = ZoneOffset.of("-08:00");

      OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
      OffsetDateTime lastThursday = offsetDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
      System.out.printf("The last Thursday in July 2013 is the %sth.%n", lastThursday.getDayOfMonth());

    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Instant Class
      //  One of the core classes of the Date-Time API is the Instant class, which represents the 
      //  start of a nanosecond on the timeline. This class is useful for generating a time stamp 
      //  to represent machine time.
      //  A value returned from the Instant class counts time beginning from the first second 
      //  of January 1, 1970 (1970-01-01T00:00:00Z) also called the EPOCH. An instant that occurs 
      //  before the epoch has a negative value, and an instant that occurs after the epoch has a 
      //  positive value.
      //  The other constants provided by the Instant class are MIN, representing the smallest 
      //  possible (far past) instant, and MAX, representing the largest (far future) instant.
      //
      //  The Instant class provides a variety of methods for manipulating an Instant. There are 
      //  plus and minus methods for adding or subtracting time. The following code adds 1 hour 
      //  to the current time:
      Instant now = Instant.now();
      Instant oneHourLater = now.plus(1, ChronoUnit.HOURS);
      System.out.println("Now= "+now+" OneHourLater= "+oneHourLater);
      
      //  There are methods for comparing instants, such as isAfter and isBefore. The until method 
      //  returns how much time exists between two Instant objects. The following line of code 
      //  reports how many seconds have occurred since the beginning of the Java epoch.
      long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(), ChronoUnit.SECONDS);
      System.out.println("SecondsFromEpoch= "+secondsFromEpoch);
      
      //  The Instant class does not work with human units of time, such as years, months, or days. 
      //  If you want to perform calculations in those units, you can convert an Instant to another 
      //  class, such as LocalDateTime or ZonedDateTime, by binding the Instant with a time zone. 
      //  You can then access the value in the desired units. The following code converts an Instant 
      //  to a LocalDateTime object using the ofInstant method and the default time zone, and then 
      //  prints out the date and time in a more readable form:
      Instant timestamp = Instant.now();
      LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
      System.out.printf("LocalDateTime now w/ default time zone: %s %d %d at %d:%d%n", 
                        ldt.getMonth(), ldt.getDayOfMonth(), ldt.getYear(), ldt.getHour(), ldt.getMinute());
      
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Parsing
      String input = "21 11 2018";
      try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate date = LocalDate.parse(input, formatter);
        System.out.printf("%s%n", date);
        
      } catch (DateTimeParseException exc) {
        System.out.printf("%s is not parsable!%n", input);
      }
 
      // Formatting
      ZoneId leavingZone = ZoneId.systemDefault();
      LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);      
      ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

      try {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy  hh:mm a");
        String out = departure.format(format);
        System.out.printf("LEAVING:  %s (%s)%n", out, leavingZone);
        
      } catch (DateTimeException exc) {
        System.out.printf("%s can't be formatted!%n", departure);
      }
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Temporal Adjuster
      //  The TemporalAdjuster interface, in the java.time.temporal package, provides methods that 
      //  take a Temporal value and return an adjusted value. The adjusters can be used with any of 
      //  the temporal-based types.
      //  If an adjuster is used with a ZonedDateTime, then a new date is computed that preserves 
      //  the original time and time zone values.
      //
      // Predefined Adjusters
      //  The TemporalAdjusters class (note the plural) provides a set of predefined adjusters for 
      //  finding the first or last day of the month, the first or last day of the year, the last 
      //  Wednesday of the month, or the first Tuesday after a specific date, to name a few examples. 
      //  The predefined adjusters are defined as static methods and are designed to be used with 
      //  the static import statement.
      //
      //  The following example uses several TemporalAdjusters methods, in conjunction with the 
      //  with method defined in the temporal-based classes, to compute new dates based on the 
      //  original date of 15 October 2000:
      LocalDate date = LocalDate.of(2000, Month.OCTOBER, 15);
      DayOfWeek dotw = date.getDayOfWeek();
      System.out.printf("%s is on a %s%n", date, dotw);

      System.out.printf("first day of Month: %s%n",
                        date.with(TemporalAdjusters.firstDayOfMonth()));
      System.out.printf("first Monday of Month: %s%n",
                        date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
      System.out.printf("last day of Month: %s%n",
                        date.with(TemporalAdjusters.lastDayOfMonth()));
      System.out.printf("first day of next Month: %s%n",
                        date.with(TemporalAdjusters.firstDayOfNextMonth()));
      System.out.printf("first day of next Year: %s%n",
                        date.with(TemporalAdjusters.firstDayOfNextYear()));
      System.out.printf("first day of Year: %s%n",
                        date.with(TemporalAdjusters.firstDayOfYear()));
      
      
      // Custom Adjusters
      //  You can also create your own custom adjuster. To do this, you create a class that 
      //  implements the TemporalAdjuster interface with a adjustInto(Temporal) method. The 
      //  PaydayAdjuster class from the NextPayday example is a custom adjuster. The PaydayAdjuster 
      //  evaluates the passed-in date and returns the next payday, assuming that payday occurs 
      //  twice a month: on the 15th, and again on the last day of the month. If the computed date 
      //  occurs on a weekend, then the previous Friday is used. The current calendar year is assumed.

      
      
      //  The adjuster is invoked in the same manner as a predefined adjuster, using the with method. 
      //  The following line of code is from the NextPayday example:
      LocalDate nextPayday = date.with(new PaydayAdjuster());
      System.out.println("\n[Custom adjuster (PaydayAdjuster)] Date= "+date+" NextPayDay= "+nextPayday+".");

    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Predefined Queries
      //  The TemporalQueries class (note the plural) provides several predefined queries, 
      //  including methods that are useful when the application cannot identify the type of 
      //  temporal-based object. As with the adjusters, the predefined queries are defined as 
      //  static methods and are designed to be used with the static import statement.
      //
      //  The precision query, for example, returns the smallest ChronoUnit that can be returned 
      //  by a particular temporal-based object. The following example uses the precision query 
      //  on several types of temporal-based objects:
      TemporalQuery<TemporalUnit> query = TemporalQueries.precision();
      System.out.printf("LocalDate precision is %s%n", LocalDate.now().query(query));
      System.out.printf("LocalDateTime precision is %s%n", LocalDateTime.now().query(query));
      System.out.printf("Year precision is %s%n", Year.now().query(query));
      System.out.printf("YearMonth precision is %s%n", YearMonth.now().query(query));
      System.out.printf("Instant precision is %s%n", Instant.now().query(query));
      
      // Custom Queries
      //  You can also create your own custom queries. One way to do this is to create a class that 
      //  implements the TemporalQuery interface with the queryFrom(TemporalAccessor) method. 
      //  The CheckDate example implements two custom queries. The first custom query can be found 
      //  in the FamilyVacations class, which implements the TemporalQuery interface. The queryFrom 
      //  method compares the passed-in date against scheduled vacation dates and returns TRUE if 
      //  it falls within those date ranges.
    }
    System.out.println("-------------------------------------------------------------------------");
    {
      // Period and Duration
      //  When you write code to specify an amount of time, use the class or method that best meets 
      //  your needs: the Duration class, Period class, or the ChronoUnit.between method. A Duration 
      //  measures an amount of time using time-based values (seconds, nanoseconds). A Period uses 
      //  date-based values (years, months, days).
      //
      // Note: a Duration of one day is exactly 24 hours long. A Period of one day, when added to a 
      //       ZonedDateTime, may vary according to the time zone. For example, if it occurs on the 
      //       first or last day of daylight saving time.
      //
      // Duration
      //  A Duration is most suitable in situations that measure machine-based time, such as code 
      //  that uses an Instant object. A Duration object is measured in seconds or nanoseconds and 
      //  does not use date-based constructs such as years, months, and days, though the class 
      //  provides methods that convert to days, hours, and minutes. A Duration can have a negative 
      //  value, if it is created with an end point that occurs before the start point.
      //
      //  The following code calculates, in nanoseconds, the duration between two instants:      
      System.out.println("\nDuration");
      Instant t1 = Instant.now();
      Instant t2 = t1.plus(1, ChronoUnit.HOURS);
      long ss = Duration.between(t1, t2).getSeconds();
      long ns = Duration.between(t1, t2).toNanos();
      System.out.println("Between "+t1+" and "+t2+" there're "+ss+" seconds which R "+ns+" nanos. ;-)");
      //  The following code adds 10 seconds to an Instant:

      Instant start = Instant.now();
      Duration gap = Duration.ofSeconds(10);
      Instant later = start.plus(gap);
      System.out.println("Start= "+start+" w/ gap of "+gap+" seconds gives us "+later);
      
      //  A Duration is not connected to the timeline, in that it does not track time zones or 
      //  daylight saving time. Adding a Duration equivalent to 1 day to a ZonedDateTime results 
      //  in exactly 24 hours being added, regardless of daylight saving time or other time 
      //  differences that might result.
      
      
      // ChronoUnit
      //  The ChronoUnit enum, discussed in the The Temporal Package, defines the units used to 
      //  measure time. The ChronoUnit.between method is useful when you want to measure an amount 
      //  of time in a single unit of time only, such as days or seconds. The between method works 
      //  with all temporal-based objects, but it returns the amount in a single unit only. The 
      //  following code calculates the gap, in milliseconds, between two time-stamps:
      Instant previous, current;
      current = Instant.now();
      previous = current.plus(-1, ChronoUnit.MINUTES);

      if (previous != null) {
          long between = ChronoUnit.MILLIS.between(previous, current);
          System.out.println("[ChronoUnit] Between "+previous+" and "+current+" we've "+between+" millis.");
      }
      
      
      
      System.out.println("\nPeriod");
      
      // Period
      //  To define an amount of time with date-based values (years, months, days), use the Period 
      //  class. The Period class provides various get methods, such as getMonths, getDays, and 
      //  getYears, so that you can extract the amount of time from the period.
      //
      //  The total period of time is represented by all three units together: months, days, and 
      //  years. To present the amount of time measured in a single unit of time, such as days, 
      //  you can use the ChronoUnit.between method.
      //
      //  The following code reports how old you are, assuming that you were born on January 1, 1960. 
      //  The Period class is used to determine the time in years, months, and days. The same period, 
      //  in total days, is determined by using the ChronoUnit.between method and is displayed in 
      //  parentheses:
      LocalDate today = LocalDate.now();
      LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);

      Period p = Period.between(birthday, today);
      long p2 = ChronoUnit.DAYS.between(birthday, today);
      System.out.println("[BirthDay:"+birthday+"] You are " + p.getYears() + " years, " + p.getMonths() +
                         " months, and " + p.getDays() +
                         " days old. (" + p2 + " days total)");
      
      //  To calculate how long it is until your next birthday, you could use the following code from 
      //  the Birthday example. The Period class is used to determine the value in months and days. 
      //  The ChronoUnit.between method returns the value in total days and is displayed in parentheses.
      birthday = LocalDate.of(1960, Month.JANUARY, 1);
      LocalDate nextBDay = birthday.withYear(today.getYear());

      //If your birthday has occurred this year already, add 1 to the year.
      if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
          nextBDay = nextBDay.plusYears(1);
      }

      p = Period.between(today, nextBDay);
      p2 = ChronoUnit.DAYS.between(today, nextBDay);
      System.out.println("[BirthDay:"+birthday+"] There are " + p.getMonths() + " months, and " +
                         p.getDays() + " days until your next birthday. (" +
                         p2 + " total)");
            
    }

  }

  public static void main(final String[] args) {
    final OracleJava8TimeTutorial clazz = new OracleJava8TimeTutorial();
  }
}

class PaydayAdjuster implements TemporalAdjuster {
/**
       * The adjustInto method accepts a Temporal instance and returns an adjusted LocalDate. 
       * If the passed in parameter is not a LocalDate, then a DateTimeException is thrown.
       */
      public Temporal adjustInto(Temporal input) {
          LocalDate date = LocalDate.from(input);
          int day;
          
          if (date.getDayOfMonth() < 15) {
              day = 15;
          } else {
              day = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
          }
          
          date = date.withDayOfMonth(day);
          
          if (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
              date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
          }

          return input.with(date);
      }  
}
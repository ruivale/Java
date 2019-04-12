/**
 * <p>
 * Classname:  jdk1_6examples.java.lang.calendar.CalendarDemo
 * </p>
 *
 * <p>Copyright: Copyright (c) 2010 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.java.lang.calendar;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class CalendarDemo {
   /** The Date we are about to format */
   Date timeNow;
   /** A calendar formatting object, used throughout. Note that
    * other forms of the Calendar constructor let you pass in
    * Locale, TimeZone, or both, or yy,mm,dd,[hh, mm [, ss]]
    * You can also set your own Daylight Saving rules, fiddle
    * the Gregorian cutover of 1582, and probably the phase of the moon!
    */
   Calendar calendar = new GregorianCalendar();

   public static void main(String[] a) {
      new CalendarDemo().format();
   }

   /** Construct a CalendarDemo object with the current date/time */
   CalendarDemo() {
      timeNow = new Date(/*2010, 5, 21, 0, 0, 0 */);
   }

   public void format() {

      // Tell the calendar what date/time to format
      calendar.setTime(timeNow);

      // print out most of the known fields
      System.out.println("ERA: " + calendar.get(Calendar.ERA));
      System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
      System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
      System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
      System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
      System.out.println("DATE: " + calendar.get(Calendar.DATE));
      System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
      System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
      System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
      System.out.println("DAY_OF_WEEK_IN_MONTH: "
                + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
      System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
      System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
      System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
      System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
      System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
      System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
      System.out.println("ZONE_OFFSET: "
                + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000)));
      System.out.println("DST_OFFSET: "
                + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000)));
   }
}

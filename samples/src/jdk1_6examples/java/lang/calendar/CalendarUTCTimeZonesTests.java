/**
 * <p>
 * Classname:  jdk1_6examples.java.lang.calendar.CalendarUTCTimeZonesTests
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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class CalendarUTCTimeZonesTests {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(CalendarUTCTimeZonesTests.class.getName());

  private static final String STR_SIMPLE_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
  // 1271674105000 -> "2010/04/19 11:48:25" but should be 12:48:25 ...
  //private static final long lNow = 1271674105000L;

  // 
  private static final long lNow = 1272316521000L;


  

 /**
  * The CalendarUTCTimeZonesTests default constuctor.
  */
  public CalendarUTCTimeZonesTests(){

    Date date = new Date();
    date.setTime(lNow);
    System.out.println("date: "+date.toString()+".");
    System.out.println("date: "+date.toGMTString()+". GMT");
    System.out.println("date: "+date.toLocaleString()+". Locale");


    DateFormat indfm = new SimpleDateFormat("MM/dd/yyyy HH'h'mm");
    indfm.setTimeZone(TimeZone.getTimeZone("Europe/Lisbon"));
    String strNow = indfm.format(date);
    System.out.println("\nNow: "+strNow+".");



    final SimpleDateFormat dateFormat = new SimpleDateFormat(STR_SIMPLE_DATE_FORMAT);
    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Dublin"));
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(lNow);
    String strStart = dateFormat.format(cal.getTime());

    System.out.println("lNow:"+lNow+" -> "+strStart);

    //System.out.println("\n\nisDSTActive()? "+isDSTend(cal)+".");



    Calendar c1 = Calendar.getInstance();
    c1.setTimeInMillis(1271674105000L);

    Calendar c2 = Calendar.getInstance();
    c2.setTimeInMillis(1110174105000L);

    System.out.println("c1.get(Calendar.DST_OFFSET): "+c1.get(Calendar.DST_OFFSET)+". "+new Date(c1.getTimeInMillis()).toString());
    System.out.println("c2.get(Calendar.DST_OFFSET): "+c2.get(Calendar.DST_OFFSET)+". "+new Date(c2.getTimeInMillis()).toString());

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("CalendarUTCTimeZonesTests").append("").toString();
  }

  public static void main(final String[] args){
    final CalendarUTCTimeZonesTests clazz = new CalendarUTCTimeZonesTests();
  }


  /**
   *
   * @param cal
   * @return
   */
  public static boolean isDSTend(final Calendar cal) {
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int date = cal.get(Calendar.DATE);
    TimeZone tz = cal.getTimeZone();

    return isDSTend(year, month, date, tz);
  }

  /**
   *
   * @param year
   * @param month
   * @param date
   * @param tz
   * @return
   */
  public static boolean isDSTend(final int year,
                                 final int month,
                                 final int date,
                                 final TimeZone tz) {

    Calendar cal = new GregorianCalendar(tz);
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(year, month, date, 00, 30, 00);
    cal.add(Calendar.HOUR_OF_DAY, 24);
    return (23 == cal.get(Calendar.HOUR_OF_DAY));
  }
}

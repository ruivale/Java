package exp.calendar;

import java.util.*;
/**
 * @author not attributable
 * @version 1.0
 */
public class CalendarTests {
  public CalendarTests() {
/*
      Valor de time_t: 1113839557
      gmtime: Mon Apr 18 15:52:37 2005
      localtime:      Mon Apr 18 16:52:37 2005
              tzname[0]:      WET
              tzname[1]:      WEST
              timezone:       0
              daylight:       1
*/

    final int intSecs = 1113839557;
    final StringBuffer sb = new StringBuffer().append(intSecs).append("000");

    final long longNow =  System.currentTimeMillis();
    final long longMillis = Long.parseLong(sb.toString());

    System.out.println("Now= "+longNow+".");
    System.out.println("Not= "+longMillis+".");


    final Calendar cal = Calendar.getInstance();
    //cal.setTimeInMillis(longMillis);

    System.out.println("Calendar= "+cal.toString()+".");

    final Date date = new Date(longMillis);
    System.out.println("Date= " + date.toString()+".");

    System.out.println("cal.get(Calendar.SECOND)=" + cal.get(Calendar.SECOND));
    System.out.println("cal.get(Calendar.MINUTE)=" + cal.get(Calendar.MINUTE));
    System.out.println("cal.get(Calendar.HOUR)=" + cal.get(Calendar.HOUR));
    System.out.println("cal.get(Calendar.DAY_OF_MONTH)=" + cal.get(Calendar.DAY_OF_MONTH));
    System.out.println("cal.get(Calendar.MONTH)=" + (cal.get(Calendar.MONTH)+1));
    System.out.println("cal.get(Calendar.YEAR)=" + cal.get(Calendar.YEAR));

  }

  public static void main(String[] args) {
    CalendarTests c = new CalendarTests();
  }
}

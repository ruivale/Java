package exp.calendar;

import java.util.*;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class CalendarFromLong {
  public CalendarFromLong() {
    final long lTime = Long.parseLong("1203110022875");

    //Calendar cal = Calendar.getInstance();
    //cal.setTimeInMillis(lTime);
    final Date date = new Date(lTime);
    System.out.println(date.toString());
  }

  public static void main(String[] args) {
    CalendarFromLong calendarfromlong = new CalendarFromLong();
  }
}

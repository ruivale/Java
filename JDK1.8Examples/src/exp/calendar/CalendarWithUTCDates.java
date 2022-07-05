package exp.calendar;

import java.util.*;
import java.text.SimpleDateFormat;


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
public class CalendarWithUTCDates {

  public static void main(final String[] args) throws Exception {
    String sUTC = "11/08/2006 08:32:45.123456UTC";

    System.out.println("UTC: " + sUTC + ".");

    final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    final Date date = dateFormat.parse(sUTC);
    System.out.println("Date:" + date.toString() + ".");

    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    String strCal = new StringBuffer().append(cal.get(Calendar.YEAR)).append("/").
        append((cal.get(Calendar.MONTH) + 1)).append("/").
        append(cal.get(Calendar.DAY_OF_MONTH)).append(" ").
        append(cal.get(Calendar.HOUR_OF_DAY)).append(":").
        append(cal.get(Calendar.MINUTE)).append(":").
        append(cal.get(Calendar.SECOND)).toString();

    System.out.println("Cal: " + strCal + ".");
  }
}

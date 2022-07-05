package exp.date;

import java.util.*;
import java.text.*;


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
public class SimpleDateFormatTests {

  final static String strDate = "07/23/2007 19:23:34.987654";

  public static void main(final String[] args) {
    try {
      final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      final Date date = dateFormat.parse(strDate);
      System.out.println("Date="+date.toString()+".");

      final Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      String strCal = new StringBuffer().
                      append(cal.get(Calendar.YEAR)).append("/").
                      append((cal.get(Calendar.MONTH)+1)).append("/").
                      append(cal.get(Calendar.DAY_OF_MONTH)).append(" ").
                      append(cal.get(Calendar.HOUR_OF_DAY)).append(":").
                      append(cal.get(Calendar.MINUTE)).append(":").
                      append(cal.get(Calendar.SECOND)).toString();

      System.out.println("Calendar:"+strCal+".");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

package exp.date.doubles;

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

public class DateAndDoubles {
  public static void main(String[] argv)  throws Exception {
    String        decPattern      = "##########.############";
    DecimalFormat decFormat       = new DecimalFormat(decPattern);

    // trying:  39450.583333333336 ==> 2008/01/03 14:00:00
    Date startDate = new Date(108, 0, 3, 14, 0, 0);
    System.out.println("Date: "+startDate.toLocaleString()+".");
    double start = startDate.getTime();
    System.out.println("Start Time: "+decFormat.format(start / 3600000));
    Thread.sleep(1000);

    Date endDate = new Date();
    double end = endDate.getTime();
    System.out.println("End   Time: "+decFormat.format(end / 3600000));
    double totalTime = (end - start) / 3600000;
    System.out.println("Total Time: "+decFormat.format(totalTime));

    Calendar cal = Calendar.getInstance();
    cal.set(2008, 0, 3, 14, 0, 0);
    Date date = new Date(cal.getTimeInMillis());
    System.out.println("Date: "+startDate.toLocaleString()+".");
    double dDate = date.getTime();
    System.out.println("Double date: "+decFormat.format(dDate / 3600000));
  }
}

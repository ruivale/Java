package exp.timezone;

import java.util.TimeZone;


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
public class TimeZoneTests {
  public static void main(final String[] args) {
    TimeZone timeZone = TimeZone.getDefault();

    System.out.println("timeZone=" + timeZone.toString());

    System.out.println("timeZone.getID()=" + timeZone.getID());
  }

}

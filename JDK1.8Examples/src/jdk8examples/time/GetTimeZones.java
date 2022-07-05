
package jdk8examples.time;

import java.util.TimeZone;
import java.util.Date;

/***
 * 
 * @author 2334
 */
public class GetTimeZones {

  public static void main(String[] args) {

    Date today = new Date();
    String[] zoneIds = TimeZone.getAvailableIDs();

    for (int i = 0; i < zoneIds.length; i++) {
      TimeZone tz = TimeZone.getTimeZone(zoneIds[i]);

      String shortName = tz.getDisplayName(tz.inDaylightTime(today), TimeZone.SHORT);
      String longName = tz.getDisplayName(tz.inDaylightTime(today), TimeZone.LONG);

      int rawOffset = tz.getRawOffset();
      int hour = rawOffset / (60 * 60 * 1000);
      int min = Math.abs(rawOffset / (60 * 1000)) % 60;

      boolean hasDST = tz.useDaylightTime();
      boolean inDST = tz.inDaylightTime(today);

      System.out.println(tz.getID() + ' ' + shortName + ' ' + longName + ' ' + hour + ':' + min + ' ' + hasDST + ' ' + inDST);
    }
  }
}

package exp.nio.chat;

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

/**
 * a few static methods (functions) used throughout this code
 * @author PKWooster
 * @version 1.0 June 15,2004
 */
public class Functions {
  private static int debugLevel = 2;

  public static void setDebugLevel(int level) {
    debugLevel = level;
  }

  public static void dout(int level, String s) {
    if (level >= debugLevel) {
      System.out.println(s);
    }
  }

  public static void fail(Exception e, String s) {
    if (e != null) {
      e.printStackTrace();
    }
    if (s != null) {
      System.out.println(s);
    }
    System.exit(0);
  }

  public static int toInt(String s, int er) {
    int i;

    try {
      i = new Integer(s).intValue();
    } catch (NumberFormatException exc) {
      i = er;
    }
    return i;
  }
}

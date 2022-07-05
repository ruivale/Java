package exp.booleans;


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
public class RealBooleansTests {

  private static Boolean booleanIsWinOpened = new Boolean(false);

  public static void showSysConfig() {
    synchronized (booleanIsWinOpened) {
      if (!booleanIsWinOpened.booleanValue()) {
        booleanIsWinOpened = new Boolean(true);
      }else{
        booleanIsWinOpened = new Boolean(false);
      }

      System.out.println("booleanIsWinOpened="+booleanIsWinOpened.booleanValue()+".");
    }
  }

  public static void main(String[] args) {
    RealBooleansTests.showSysConfig();
    RealBooleansTests.showSysConfig();
    RealBooleansTests.showSysConfig();
    RealBooleansTests.showSysConfig();
    RealBooleansTests.showSysConfig();
    RealBooleansTests.showSysConfig();
    RealBooleansTests.showSysConfig();
    RealBooleansTests.showSysConfig();
  }
}

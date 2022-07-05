package exp.log;

import java.util.logging.*;


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
public class LoggingGetNameTests {
  /** This class logger */
  private static final Logger logger = Logger.getLogger(LoggingGetNameTests.class.getName());
  private static final String STR_CLASS = "com.ent.PInt.PInt";

  /**
   *
   */
  public LoggingGetNameTests() {
    logger.setLevel(Level.ALL);
    long lMillisBefore = System.currentTimeMillis();
    logger.entering(
      STR_CLASS,
      "STR_CLASS");
    long lMillisAfter = System.currentTimeMillis();
    long diffs = lMillisAfter-lMillisBefore;
    System.out.println("diffs="+diffs+". - STR_CLASS");

    lMillisBefore = System.currentTimeMillis();
    logger.entering(
      LoggingGetNameTests.class.getName(),
      "com.ent.PInt.PInt.class.getName()");
    lMillisAfter = System.currentTimeMillis();
    diffs = lMillisAfter-lMillisBefore;
    System.out.println("diffs="+diffs+". - com.ent.PInt.PInt.class.getName()");

    lMillisBefore = System.currentTimeMillis();
    logger.entering(
      LoggingGetNameTests.class.getName(),
      "com.ent.PInt.PInt.class.getName()");
    lMillisAfter = System.currentTimeMillis();
    diffs = lMillisAfter-lMillisBefore;
    System.out.println("diffs="+diffs+". - com.ent.PInt.PInt.class.getName()");

    lMillisBefore = System.currentTimeMillis();
    logger.entering(
      STR_CLASS,
      "STR_CLASS");
    lMillisAfter = System.currentTimeMillis();
    diffs = lMillisAfter-lMillisBefore;
    System.out.println("diffs="+diffs+". - STR_CLASS");


    lMillisBefore = System.currentTimeMillis();
    logger.entering(
      LoggingGetNameTests.class.getName(),
      "com.ent.PInt.PInt.class.getName()");
    lMillisAfter = System.currentTimeMillis();
    diffs = lMillisAfter-lMillisBefore;
    System.out.println("diffs="+diffs+". - com.ent.PInt.PInt.class.getName()");



  }

  public static void main(String[] args) {
    LoggingGetNameTests logginggetnametests = new LoggingGetNameTests();
  }
}








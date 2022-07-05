package exp.log.test;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.awt.Rectangle;
import java.util.logging.*;

public class LogTest {

  private static final Logger LOGGER = Logger.getLogger(LogTest.class.getName());

//  private static Logger logger =
  //    Logger.getLogger("exp.log.test");


  private void testMethod(A a, int i, Rectangle rec){


    LOGGER.setLevel(Level.FINER);


    if (LOGGER.isLoggable(Level.FINER)) {
      LOGGER.entering(
          getClass().getName(),
          "testMethod",
          new Object[]{
            a,
            Integer.toString(i),
            rec
      });
    }

  }



  public static void main(String argv[]) {
    Handler fh = null;

    try {
      fh = new FileHandler("logTest.log", 50000, 1);

      // Don't really need this, since XML
      // formatting is the default for FileHandlers>
      fh.setFormatter(new java.util.logging.SimpleFormatter());
          //new java.util.logging.XMLFormatter());

      Handler ch = new ConsoleHandler();

      // Don't really need this, since simple
      // formatting is the default for
      // ConsoleHandlers
      ch.setFormatter(new java.util.logging.SimpleFormatter());

      // Get an instance of the root logger,
      // which is the parent of all loggers,
      // and set the global handlers on it
      Logger rootLogger = Logger.getLogger("");
      rootLogger.addHandler(ch);
      rootLogger.addHandler(fh);

      Logger logger = Logger.getLogger("exp.log");

      logger.setUseParentHandlers(true);


      // Now use the named logger and...

      // log a FINE message
      logger.fine("done");

      // log a SEVERE message
      logger.severe("severe");

      // log a INFO message.
      logger.info("doing stuff");

      // log a WARNING
      logger.log(Level.WARNING, "something may be wrong");

      // we can log a WARNING as follows
      // logger.warning("something may be wrong");


      ch.setLevel(Level.FINER);

      new LogTest().testMethod(new A(), 10, null);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class A{
  public String toString(){
    return "AAAAAAAAAAAAAa";
  }
}
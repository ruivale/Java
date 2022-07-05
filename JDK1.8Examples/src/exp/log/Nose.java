package exp.log;

import java.util.logging.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Nose {
/*
  // Obtain a suitable logger.
  private static Logger logger = Logger.getLogger("exp.logging");

  public static void main(String[] argv) {
    // Log a FINE tracing message
    logger.info("doing stuff");
    try {
      //Wombat.sneeze();
      Integer.parseInt("as");
    } catch (Error ex) {
      // Log the error
      logger.log(Level.WARNING, "trouble sneezing", ex);
    } catch (Exception exc) {
      // Log the error
      logger.log(Level.SEVERE, "Exception ", exc);
    }
    logger.info("done");
  }
  */

   private static Logger theLogger =
     Logger.getLogger(HelloWorld.class.getName());

   public static void main( String[] args ) {

     // The root logger's handlers default to INFO. We have to
     // crank them up. We could crank up only some of them
     // if we wanted, but we will turn them all up.
     Handler[] handlers =
       Logger.getLogger( "" ).getHandlers();
     for ( int index = 0; index < handlers.length; index++ ) {
       handlers[index].setLevel( Level.FINE );
     }

     // We also have to set our logger to log finer-grained
     // messages
     theLogger.setLevel(Level.FINE);
     Nose hello =
       new Nose("Hello world!");
       hello.sayHello();
     }

     private String theMessage;

     public Nose(String message) {
       theMessage = message;
     }

     public void sayHello() {
       theLogger.fine("Hello logging!");
       System.err.println(theMessage);
     }
 }




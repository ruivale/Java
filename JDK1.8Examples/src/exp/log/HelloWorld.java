package exp.log;

import exp.log.a.A;
import exp.log.b.B;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A simple Java Hello World program, in the tradition of
 * Kernighan and Ritchie.
 */
public class HelloWorld implements Runnable{
  private static Logger theLogger = Logger.getLogger(HelloWorld.class.getName());

  A a = null;
  B b = null;

  /**
   *
   * @param message String
   */
  public HelloWorld(){
    final Handler[] handlers = Logger.getLogger("").getHandlers();
    final int intNbrOfHandlers = handlers.length;
    for ( int index = 0; index < intNbrOfHandlers; index++ ) {
      handlers[index].setLevel( Level.ALL );
    }

    sayHello();
    a = new A();
    b = new B();

    try{Thread.sleep(4000);} catch(Exception ex){;}
    new Thread(this).start();

  }

  public void run(){

    byte[] by = new byte[100];
    String strClass = "";
    String strLevel = "";
    Level level = null;

    while(true){
      try{
        by = new byte[100];
        System.out.print("Kal a class a setar: ");
        System.in.read(by);
        strClass = new String(by).trim();
        by = new byte[10];
        System.out.print("Kal o level? ");
        System.in.read(by);
        strLevel = new String(by).trim();
        level = Level.parse(strLevel);


        /*
        Handler[] handlers = Logger.getLogger( "" ).getHandlers();
        for ( int index = 0; index < handlers.length; index++ ) {
          System.out.println("HelloWorld - Setting handler["+index+"]! ("+handlers[index].toString()+")");
          handlers[index].setLevel(level);
        }
        */
        Logger.getLogger(Class.forName(strClass).getName()).setLevel(level);

        /*
        switch(Integer.parseInt(strLevel)){
          case -1:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.ALL);
            break;
          case 0:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.SEVERE);
            break;
          case 1:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.WARNING);
            break;
          case 2:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.INFO);
            break;
          case 3:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.CONFIG);
            break;
          case 4:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.FINE);
            break;
          case 5:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.FINER);
            break;
          case 6:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.FINEST);
            break;
          default:
            Logger.getLogger(Class.forName(strClass).getName()).setLevel(Level.INFO);
        }
        */

      } catch(Exception e){
        e.printStackTrace();
      }

      sayHello();
      a.run();
      b.run();

    }
  }

  /**
   *
   */
  public void sayHello(){
    // use the 'least important' type of message, one at
    // the 'finest' level.
    theLogger.severe("Severe - Hello world!");
    theLogger.warning("Warning - Hello world!");
    theLogger.info("Info - Hello world!");
    theLogger.config("Config - Hello world!");
    theLogger.fine("Fine - Hello world!");
    theLogger.finer("Finer - Hello world!");
    theLogger.finest("Finest - Hello world!");
  }

  public static void main(String[] args){
    HelloWorld hello = new HelloWorld();
  }
}

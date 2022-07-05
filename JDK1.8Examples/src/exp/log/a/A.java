package exp.log.a;


import exp.log.*;
import java.util.logging.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class A implements Runnable{
  Logger theLogger = Logger.getLogger(A.class.getName());

  public A(){
    new Thread(this).start();
  }

  public void run(){
    //while(true){
      theLogger.severe("Severe - AAAAAAAAAAA!");
      theLogger.warning("Warning - AAAAAAAAAA!");
      theLogger.info("Info - AAAAAAAAAA!");
      theLogger.config("Config - AAAAAAAAAA!");
      theLogger.fine("Fine - AAAAAAAAAA!");
      theLogger.finer("Finer - AAAAAAAAAA!");
      theLogger.finest("Finest - AAAAAAAAAA!");
      //try{Thread.sleep(9000);}catch(Exception ex){;}
    //}
  }
}

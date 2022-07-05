package exp.log.b;


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

public class B implements Runnable{
  Logger theLogger = Logger.getLogger(B.class.getName());

  public B(){
    new Thread(this).start();
  }

  public void run(){
    //while(true){
      theLogger.severe("Severe - BBBBBBBBBBB!");
      theLogger.warning("Warning - BBBBBBBBBBB!");
      theLogger.info("Info - BBBBBBBBBBB!");
      theLogger.config("Config - BBBBBBBBBBB!");
      theLogger.fine("Fine - BBBBBBBBBBB!");
      theLogger.finer("Finer - BBBBBBBBBBB!");
      theLogger.finest("Finest - BBBBBBBBBBB!");
      //try{Thread.sleep(7000);}catch(Exception ex){;}
    //}
  }
}

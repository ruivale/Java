package exp;


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

public class C implements Runnable{
  Logger theLogger = Logger.getLogger(C.class.getName());

  public C(){
    new Thread(this).start();
  }

  public void run(){
    //while(true){
      theLogger.severe("Severe - CCCCCCCCCCC!");
      theLogger.warning("Warning - CCCCCCCCCCC!");
      theLogger.info("Info - CCCCCCCCCCC!");
      theLogger.config("Config - CCCCCCCCCCC!");
      theLogger.fine("Fine - CCCCCCCCCCC!");
      theLogger.finer("Finer - CCCCCCCCCCC!");
      theLogger.finest("Finest - CCCCCCCCCCC!");
      //try{Thread.sleep(7000);}catch(Exception ex){;}
    //}
  }
}

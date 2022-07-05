package exp.threads.interthreadcommunication;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.io.*;


public abstract class ProduceData implements Runnable {
  OutputStream os;

  public ProduceData(OutputStream os) {
    this.os = os;

    Thread t = new Thread(this);
    t.start();
  }

  public abstract boolean dataProduction();

  public void run() {
    while (dataProduction())
      ;
  }
}

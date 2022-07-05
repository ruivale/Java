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


public abstract class ConsumeData implements Runnable {
  InputStream is;

  public ConsumeData(InputStream is) {
    this.is = is;

    Thread t = new Thread(this);
    t.start();
  }

  public abstract boolean dataConsumption();

  public void run() {
    while (dataConsumption())
      ;
  }
}

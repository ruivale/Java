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


public class TestThread {
  public static void main(String[] a) {
    try {
      PipedOutputStream os = new PipedOutputStream();
      PipedInputStream is = new PipedInputStream();
      os.connect(is);
      new SendProduction(os);
      new ReceiveProduction(is);
    } catch (Exception e) {
    }
  }
}

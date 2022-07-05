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


public class ReceiveProduction extends ConsumeData {
  InputStream input;

  ReceiveProduction(InputStream is) {
    super(is);
    this.input = is;
  }

  public boolean dataConsumption() {
    int i = 0;

    try {
      for (;;) {
        i = input.read();
        System.out.println("  " + i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true;
  }
}

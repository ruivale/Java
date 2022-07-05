package exp.serialize.xml;

import java.beans.XMLDecoder;
import java.io.*;


/**
 * <p>
 * Title: </p>
 * <p>
 * <p>
 * Description: </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) </p>
 * <p>
 * <p>
 * Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DeserializeTests {

  public DeserializeTests() {

    try {
      XMLDecoder d = new XMLDecoder(
          new BufferedInputStream(
              new FileInputStream(
                  "c:/temp/2340_targetsarea.xml")));

      Object result = d.readObject();

      d.close();

      System.out.println("result=" + result + ".");

    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void main(String[] args) {
    DeserializeTests d = new DeserializeTests();
  }
}

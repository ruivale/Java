package exp.serialize.xml;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


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
public class Serialize2XMLTests {

  /**
   *
   */
  public Serialize2XMLTests() {
    // Create an object and set properties
    MyClass obj = new MyClass();
    obj.setProp(1);
    obj.setProps(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

    Serialize2XMLTests.serialize2xml(obj, "c:/temp/MyClass.xml");
  }

  /**
   *
   * @param o           Object
   * @param strFileName String
   */
  private static void serialize2xml(final Object o,
                                    final String strFileName) {
    try {
      // Serialize object into XML
      XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
          new FileOutputStream(strFileName)));
      encoder.writeObject(o);
      encoder.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    Serialize2XMLTests serialize2xmltests = new Serialize2XMLTests();
  }
}

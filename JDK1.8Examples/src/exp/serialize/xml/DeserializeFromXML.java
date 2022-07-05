package exp.serialize.xml;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


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
public class DeserializeFromXML {

  /**
   *
   */
  public DeserializeFromXML() {
    System.out.println("-------------------------------");

    MyClass obj = (MyClass) DeserializeFromXML.deserializeFromXML("c:/temp/MyClass.xml");

    System.out.println("property=" + obj.getProp() + ".");

    int[] properties = obj.getProps();
    for (int i = 0; i < properties.length; i++) {
      System.out.print(properties[i]);
    }
    System.out.println("-------------------------------");
  }

  /**
   *
   * @param strFileName String
   *
   * @return Object
   */
  private static Object deserializeFromXML(final String strFileName) {
    Object obj = null;
    // Deserialize an object
    try {

      XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
          new FileInputStream(strFileName)));

      if (decoder != null) {
        System.out.println("decoder=" + decoder.toString() + ".");

        obj = decoder.readObject();
        decoder.close();
      }
    } catch (FileNotFoundException e) {
      obj = null;

      e.printStackTrace();
    }

    return obj;
  }

  public static void main(String[] args) {
    DeserializeFromXML deserializefromxml = new DeserializeFromXML();
  }
}

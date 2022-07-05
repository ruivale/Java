package exp.properties.system;

import java.util.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SysProperties {
  public SysProperties() {
    Properties pros = System.getProperties();
    Enumeration enumerationNames = pros.propertyNames();
    while(enumerationNames.hasMoreElements()){
      final String name = (String)enumerationNames.nextElement();
      System.out.println("pros.getProperty("+name+")=" +
                         pros.getProperty(name));
    }

    System.out.println("System.getProperty(\"path\")=" + System.getProperty("jvm.path"));;
  }

  public static void main(String[] args) {
    SysProperties sysproperties = new SysProperties();
  }
}

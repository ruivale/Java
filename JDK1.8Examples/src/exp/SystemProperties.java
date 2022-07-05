package exp;


/**
 * Title: Description: Copyright:    Copyright (c) Company:
 */
import java.lang.*;

import java.util.*;


public class SystemProperties {
  public SystemProperties() {
    Properties  p   = System.getProperties();
    Enumeration e   = p.propertyNames();
    String      aux;
    while(e.hasMoreElements()) {
      aux = ""+e.nextElement();
      System.out.println(aux+": "+p.getProperty(aux)+".");
    }
  }

  public static void main(String[] args) {
    SystemProperties systemProperties1 = new SystemProperties();
  }
}

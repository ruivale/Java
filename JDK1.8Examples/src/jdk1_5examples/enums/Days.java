package jdk1_5examples.enums;

import java.util.*;
/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class Days {

  enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};

  public static void main(String[] args) {
    for(Day d: EnumSet.range(Day.MONDAY, Day.FRIDAY)){
      System.out.println("-> "+d+".");
    }
  }

}

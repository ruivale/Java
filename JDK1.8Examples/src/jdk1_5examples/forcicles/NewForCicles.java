package jdk1_5examples.forcicles;


/**
 * <p>Title: JDK1.5 Examples</p>
 * <p>Description: Examples for the Java5. </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ??????????</p>
 * @author rUI vALE
 * @version 1.0
 */

public class NewForCicles {
  public static void main(String[] args) {
    String[] names = {"A", "B", "C", "D"};

    //    List<String> names;
    for (String name : names) {
      System.out.println("Name:" + name + ".");
    }
  }
}

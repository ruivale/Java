package jdk1_5examples.enums;


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
public class EnumTestsWithAnnotations {

  public enum Colour {
    RED, BLUE, GREEN, BLACK
  }

  /**
   *
   */
  public EnumTestsWithAnnotations() {
    Colour col = Colour.RED;

    //@CompleteEnumSwitch
    switch (col) {
      case RED:
        // do stuff with red
        System.out.println("RED");
        break;

      case BLUE:
        // do stuff with blue
        System.out.println("BLUE");
        break;

      case GREEN:
        // do stuff with green
        System.out.println("GREEN");
        break;

    }
  }

  public static void main(String[] args) {
    EnumTestsWithAnnotations e = new EnumTestsWithAnnotations();
  }
}

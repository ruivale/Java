package exp.argstests;


/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c)
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class AgrsTests {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  A   a = new A();

  /** .. */
  int i = 2;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new AgrsTests object.
   */
  public AgrsTests() {
    System.out.println("ArgsTests.i=" + i + ".");
    System.out.println("ArgsTests.doSomething(i=" + i + ")=" + doSomething(i) +
      ".");
    System.out.println("ArgsTests.i=" + i + ".");
    doSomethingElse(i);
    System.out.println("ArgsTests.i=" + i + ".");
    System.out.println("-------------------------");
    System.out.println("ArgsTests.a.i=" + a.i + ".");
    a.i = 5;
    System.out.println("ArgsTests.a.i=" + a.i + ".");
    doSomethingInAInt(a.i);
    System.out.println("ArgsTests.a.i=" + a.i + ".");
    doSomethingInA(a);
    System.out.println("ArgsTests.a.i=" + a.i + ".");
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    AgrsTests agrsTests1 = new AgrsTests();
  }

  /**
   * Insert doc ...
   *
   * @param i  Insert doc ...
   *
   * @return  Insert doc ...
   */
  private int doSomething(int i) {
    int j = 7;

    return j + i;
  }

  /**
   * Insert doc ...
   *
   * @param i  Insert doc ...
   */
  private void doSomethingElse(int i) {
    i *= 5;
    System.out.println("  inside doSomethingElse(i=" + i + ") and i=" + i +
      ".");
  }

  /**
   * Insert doc ...
   *
   * @param a  Insert doc ...
   */
  private void doSomethingInA(A a) {
    a.i += 230;
  }

  /**
   * Insert doc ...
   *
   * @param i  Insert doc ...
   */
  private void doSomethingInAInt(int i) {
    i += 4;
    System.out.println("  inside doSomethingInAInt(i=" + i + ") and i=" + i +
      ".");
  }

  //~ Inner Classes ////////////////////////////////////////////////////////////

  class A {
    int i = 0;
  }
}

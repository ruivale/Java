package jdk1_5examples.assertions;


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
public class TestAssertions {
  private final int LIGHT_SPEDD = 300000; // Km/h

  public TestAssertions(int speed) {
    /**
    if (i % 3 == 0) {
      System.out.println("0");
    } else if (i % 3 == 1) {
      System.out.println("1");
    } else {
      assert i % 3 == 2:i;
    }
    /**/

    /**/
    final boolean isLess = speed < LIGHT_SPEDD;
    if (isLess){
      return;
    }
    assert isLess; // Execution should never reach this point!
    /**/
  }

  public static void main(String[] args) throws Exception{
    TestAssertions testassertions = new TestAssertions(2000000);

    System.in.read();
  }
}

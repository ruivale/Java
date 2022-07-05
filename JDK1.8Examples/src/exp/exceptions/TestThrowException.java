package exp.exceptions;

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
public class TestThrowException {

  String s = "A";

  public TestThrowException(String _s) {
    s = s+"-"+_s;
    try {
      throw new Exception("The resource bundle is null.");

    } catch (Exception ex) {
      System.out.println(".... DEU EXCEPTION ....");
      ex.printStackTrace();
    }

  }

  public static void main(String[] args) {
    TestThrowException testthrowexception = new TestThrowException("B");
  }
}

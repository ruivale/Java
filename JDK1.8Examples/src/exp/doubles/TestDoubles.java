package exp.doubles;


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
public class TestDoubles {
  public TestDoubles() {
    System.out.println("new Double(123.67).intValue()=" + new Double("123,67").intValue());
  }

  public static void main(String[] a) {
    new TestDoubles();
  }
}

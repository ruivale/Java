package exp.arrays;


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
public class ArrayTests {
  public ArrayTests() {
    int[] a = {1,2};
    System.out.println("before:");
    for (int i = 0; i < a.length; i++) {
      System.out.println("a["+i+"]="+a[i]+".");
    }
    test(a);
    System.out.println("after:");
    for (int i = 0; i < a.length; i++) {
      System.out.println("a["+i+"]="+a[i]+".");
    }

  }

  private void test(int[] a){
    for (int i = 0; i < a.length; i++) {
      a[i] = a[i]*5;
    }
  }
  public static void main(String[] args) {
    ArrayTests arraytests = new ArrayTests();
  }
}

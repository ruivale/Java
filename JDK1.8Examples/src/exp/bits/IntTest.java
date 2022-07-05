package exp.bits;


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
public class IntTest {
  public IntTest() {
    int j = 222901;

    for (int i = 222001; i < 222050; i++) {
      System.out.print("i>>1|j="+((i>>1)|j)+".");
      System.out.println(" i&j="+(i&j)+".");
    }
  }

  public static void main(String[] args) {
    new IntTest();
     //int a = 1 || 2 ^ 3 && 5;
     //System.out.println("a=" + a);
  }
}

package exp.booleans;


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
public class BoolTests {
  public BoolTests() {

    boolean t = true;
    boolean f = false;

    System.out.println("t&f="+(t&f)+".");
    System.out.println("t|f="+(t|f)+".");
    System.out.println("t^f="+(t^f)+".");
    System.out.println("f&t="+(f&t)+".");
    System.out.println("f|t="+(f|t)+".");
    System.out.println("f^t="+(f^t)+".");
    System.out.println("---------------------");
    System.out.println("t&t="+(t&t)+".");
    System.out.println("t|t="+(t|t)+".");
    System.out.println("t^t="+(t^t)+".");
    System.out.println("---------------------");
    System.out.println("f&f="+(f&f)+".");
    System.out.println("f|f="+(f|f)+".");
    System.out.println("f^f="+(f^f)+".");

  }

  public static void main(String[] args) {
    BoolTests booltests = new BoolTests();
  }
}

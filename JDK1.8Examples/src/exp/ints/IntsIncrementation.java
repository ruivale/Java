package exp.ints;


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
public class IntsIncrementation {
  static int lim = 0;

  public static void main(final String[] args) {
    int i = 0;

    System.out.println("BEFORE i=" + i + " and lim=" + lim + ".");
    if (++i > lim) {
      System.out.println("TRUE i=" + i + " and lim=" + lim + ".");
    } else {
      System.out.println("FALSE i=" + i + " and lim=" + lim + ".");
    }

  }
}

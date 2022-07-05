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
public class SystemMillis2Int {
  public static void main(String[] args) {
    long millisDivided = System.currentTimeMillis()%2334544;
    System.out.println("long millisDivided=" + millisDivided);
    int intmillisDivided = (int)millisDivided;
    System.out.println("int millisDivided=" + intmillisDivided);

    System.out.println("System.currentTimeMillis()%2334544=" +
                       ((int)System.currentTimeMillis()%2334544));

  }
}

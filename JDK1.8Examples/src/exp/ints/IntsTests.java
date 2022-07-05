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
public class IntsTests {
  public static void main(final String[] args) {
    long now = System.currentTimeMillis();
    double random = Math.random() * 240978;
    int rest = (int) (now % random);

    System.out.println("now=" + now);
    System.out.println("random=" + random);
    System.out.println("rest=" + rest);

    int iVWId = (int) (System.currentTimeMillis() % 
        (Math.random()*34509787)) + 35652;

    System.out.println("iVWId=" + iVWId);
  }

}

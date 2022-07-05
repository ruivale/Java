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
public class IntCreationUsingTheUTImeMillis {
  public static void main(final String[] args) {

    for (int j = 0; j < 5; j++) {
      long now = System.currentTimeMillis();
      long longNavButtonId = now % 2334544;
      int iNow = (int) longNavButtonId;
      for (int index = 0; index < 10; index++) {
        System.out.println("iNow="+(iNow++)+". now="+now+".");
      }
    }

  }

}

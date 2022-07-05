package exp.strings;


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
public class CompareStringAndStringBufferSPEEDs {
  private static final int INT_LIMIT = 2000000;
  private static final String s = "";
  private static String buffer = "";
  private static long before = 0;
  private static long after = 0;


  public static void main(String[] args) {
    before = System.currentTimeMillis();
    for (int i = 0; i < INT_LIMIT; i++) {
      buffer = "" + i;
    }
    after = System.currentTimeMillis();
    System.out.println("\nConcatenation (\"\"+i) for "+INT_LIMIT+" times took: "+
                       (after-before)+".");

    before = System.currentTimeMillis();
    for (int i = 0; i < INT_LIMIT; i++) {
      buffer = new StringBuffer().append(i).toString();
    }
    after = System.currentTimeMillis();
    System.out.println("New StringBuffers for "+INT_LIMIT+" times took: "+
                       (after-before)+".\n");

  }
}







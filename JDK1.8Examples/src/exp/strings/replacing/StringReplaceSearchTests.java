package exp.strings.replacing;


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
public class StringReplaceSearchTests {
  static final int N = 20;
  static final String S = ".";

  public static void main(final String[] args) {
    String[] str = new String[N];

    for (int i = 0; i < N; i++) {
      str[i] = "07/05/2007 12:34:45.8789"+i;
    }

    long before, after;

    for (int i = 0; i < N; i++) {
      final int iLastIndex = str[i].lastIndexOf(S);

      before = System.currentTimeMillis();
      str[i] = str[i].substring(0,
                                iLastIndex > 0 ?
                                  iLastIndex:
                                  str[i].length());
      after = System.currentTimeMillis();

      System.out.println("took "+(after-before)+" millis - "+i);
     }

  }
}

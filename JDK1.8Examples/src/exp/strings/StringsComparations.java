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
public class StringsComparations {
  public StringsComparations() {
    String s1 = "hello";
    String s2 = "he";

    s2 += "llo"; // s2 is now "hello"

    if (s1 == s2) {
      System.out.println("equal");
    } else {
      System.out.println("not equal");
      System.out.println(s1);
      System.out.println(s2);
    }
  }

  public static void main(String[] args) {
    StringsComparations stringscomparations = new StringsComparations();
  }
}

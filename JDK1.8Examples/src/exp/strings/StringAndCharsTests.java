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
public class StringAndCharsTests {

  public static void main(String[] args) {
    final char[] charsChnFlags =
        {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'};
    charsChnFlags[3] = '1';
    String strFlags = new String(charsChnFlags);

    System.out.println("strFlags=" + strFlags);
  }
}

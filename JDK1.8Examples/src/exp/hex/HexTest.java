package exp.hex;


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
public class HexTest {
  public static void main(String[] args) {
    int x = 0X0001;
    int y = 0x7fffffff;
    int z = 0xDeadCafe;
    System.out.println("x = " + x + " y = " + y + " z = " + z);

    // (max 16 digits non counting the leading 0x ())
    // capital and non capital letters
    //
    // All three integer literals (octal, decimal, and hexadecimal) are
    // defined as int by default, but they may also be specified as long
    // by placing a suffix of L or l after the number:
    long jo = 110599L;
    long so = 0xFFFFl; // Note the lowercase 'l'
  }
}

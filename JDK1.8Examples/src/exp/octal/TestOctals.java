package exp.octal;


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
public class TestOctals {
  public static void main(String[] args) {
    int five = 06; // Equal to decimal 6
    int seven = 07; // Equal to decimal 7
    int eight = 010; // Equal to decimal 8
    int nine = 011; // Equal to decimal 9
    System.out.println("Octal 010 = " + eight);

    int octals[] = {06, 07, 010, 012, 036, 077, 0100, 0111, 0234, 0676, 01000, 05676};
    for (int i = 0; i < octals.length; i++) {
      System.out.println("octals["+i+"]="+octals[i]+".");
    }

    // Explanation (max 21 digits non counting the leading 0 (zero))
    //
    // 0676 stands for:
    // 6*(64=0100) + 7*(8=010) + 6
    //
    // 05676 stands for:
    // 5*(512=01000) + 6*(64=0100) + 7*(8=010) + 6 <=>
    // 2560+384+56+6=3006
    //

  }
}

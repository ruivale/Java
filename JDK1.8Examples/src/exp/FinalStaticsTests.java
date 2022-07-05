package exp;

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
public class FinalStaticsTests {
  String s;
  final int i;

  public FinalStaticsTests() {
    i = 0;
    s = new String("");
    if(s == null){
      s = new String("");
    }
  }

  public static void main(String[] args) {
    FinalStaticsTests finalstaticstests = new FinalStaticsTests();
  }
}

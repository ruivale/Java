package exp.breaking;


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
public class BreakTest {
  public BreakTest() {

    while(true){
      try {
        System.out.println("-");
        Integer.parseInt("uri");
      } catch (Exception ex) {
        System.out.println("exception");
        break;
      } finally {
        System.out.println("finally");
      }
    }
  }

  public static void main(String[] args) {
    BreakTest breaktest = new BreakTest();
  }
}

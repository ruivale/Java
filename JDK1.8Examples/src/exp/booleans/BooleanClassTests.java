package exp.booleans;


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
public class BooleanClassTests {
  public BooleanClassTests() {

    Boolean b1 = new Boolean("true");
    Boolean b2 = new Boolean("false");

    System.out.println("b1="+b1.booleanValue()+".");
    System.out.println("b2="+b2.booleanValue()+".");

    b1 = new Boolean("false");
    b2 = new Boolean("true");

    System.out.println("b1="+b1.booleanValue()+".");
    System.out.println("b2="+b2.booleanValue()+".");


  }

  public static void main(String[] args) {
    BooleanClassTests booleanclasstests = new BooleanClassTests();
  }
}

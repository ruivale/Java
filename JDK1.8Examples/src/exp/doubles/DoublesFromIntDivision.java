package exp.doubles;


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
public class DoublesFromIntDivision {
  public DoublesFromIntDivision() {

    final int int1 = 549;
    final int int2 = 789;

    final double double1 = new Integer(int1).doubleValue() / new Integer(int2).doubleValue();

    System.out.println("double1=" + double1);

    final Double doubleDouble = new Double(double1);
    System.out.println("doubleDouble=" + doubleDouble);

    final Double doubleDouble2 = new Double(int1/int2);
    System.out.println("doubleDouble2=" + doubleDouble2);

    final Float floatFloat = new Float(int1/int2);
    System.out.println("floatFloat=" + floatFloat);

  }

  public static void main(String[] args) {
    DoublesFromIntDivision d = new DoublesFromIntDivision();
  }
}

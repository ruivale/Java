package exp.overriding;


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
public class OverridingTests {
  public OverridingTests() {
  }
  static void method(){
      System.out.println("classA");
  }

  public static void main(String[] args) {
    OverridingTests overridingtests = new OverridingTests();
  }
  class classB extends OverridingTests{
      void _method(){
         System.out.println("classB");
     }
  }
}

package exp.ints;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class IntTests {
  private static int iI = 4567;
  private static int i = 5;
  public IntTests() {
    System.out.println("I i="+i+".");
    new A(i);
    new B(i);
    System.out.println("I i="+i+".");
  }
  public static int getI(){
    return i;
  }
  public static void main(String[] args) {

    System.out.println("System.currentTimeMillis()=" + System.currentTimeMillis());
    int i = (int)System.currentTimeMillis();
    System.out.println("i="+i+".");


    IntTests intTests1 = new IntTests();

    System.out.println("------------------");

    int jh = 312/100;
    System.out.println("jh=" + jh);

  }
  class A{
    public A(int i){
      i = 10;
      System.out.println("A i="+i+".");
      int j = IntTests.getI();
      j += 5;
      System.out.println("A j="+j+".");
    }
  }
}
class B{
  public B(int i){
    i = 15;
    System.out.println("B i="+i+".");
    int j = IntTests.getI();
    j += 10;
    System.out.println("B j="+j+".");
  }
}

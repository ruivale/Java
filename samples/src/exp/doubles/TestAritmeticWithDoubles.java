package exp.doubles;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class TestAritmeticWithDoubles {

  public TestAritmeticWithDoubles() {
    byte byte1[], byte2[];
    double d1, d2, doubleResult;
    int intResult = 0;

    while(true) {
      try {
        byte1 = new byte[1024];
        byte2 = new byte[1024];

        System.out.print("1 double:");
        System.in.read(byte1);
        System.out.print("2 double:");
        System.in.read(byte2);

        d1 = Double.parseDouble(new String(byte1));
        d2 = Double.parseDouble(new String(byte2));

        doubleResult = d1/d2;
        intResult = new Double(doubleResult).intValue()+1;

        System.out.println("d1/d2 = "+doubleResult+".");
        System.out.println("int = "+intResult+".");


      }catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
  public static void main(String[] args) {
    TestAritmeticWithDoubles testAritmeticWithDoubles1 = new TestAritmeticWithDoubles();
  }
}
package exp.random;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MyRandomTests {

  public MyRandomTests() {
  }
  public static void main(String[] args) {
    //MyRandomTests myRandomTests1 = new MyRandomTests();


      byte[] b = new byte[4];

      Random ran1 = new Random();
      System.out.println("number1 int:" + ran1.nextInt());
      ran1.nextBytes(b);
      System.out.println("number1 byte:" + new String(b));

      try {Thread.sleep(1000);}catch (Exception ex) {;}

      b = new byte[4];
      Random ran2 = new Random();
      System.out.println("number2 int:" + ran2.nextInt());
      ran2.nextBytes(b);
      System.out.println("number2 byte:" + new String(b));

  }
}



package exp.collections;


import java.util.*;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class TestingVector {

  public TestingVector() {
    long before = System.currentTimeMillis();
    Vector v = new Vector(1000);
    for (int i=0; i<1000; i++) {
      v.add(new JFrame(""+i));
    }
    long after = System.currentTimeMillis()-before;
    System.out.println("Duration: "+after+".");

  }
  public static void main(String[] args) {
    TestingVector testingVector1 = new TestingVector();
  }
}
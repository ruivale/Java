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

public class TestintLinkedList {
 LinkedList ll;
  public TestintLinkedList() {
    ll = new LinkedList();
    long before = System.currentTimeMillis();

    for (int i=0; i<1000; i++) {
      ll.add(new JFrame(""+i));
    }
    long after = System.currentTimeMillis()-before;
    System.out.println("Duration: "+after+".");


  }
  public static void main(String[] args) {
    TestintLinkedList testintLinkedList1 = new TestintLinkedList();
  }
}
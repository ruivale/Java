package exp.arrays;

import java.util.*;


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
public class ArraysInLists {
  public ArraysInLists() {
    String[] ss = {"A","BB","CCC"};
    byte[] bs0 = ss[0].getBytes();
    byte[] bs1 = ss[1].getBytes();
    byte[] bs2 = ss[2].getBytes();


    List lb = new Vector(3);
    lb.add(bs0);
    lb.add(bs1);
    lb.add(bs2);

    for (int i = 0; i < lb.size(); i++) {
      System.out.println("lb("+i+")="+new String(((byte[])lb.get(i)))+".");
    }
  }

  public static void main(String[] args) {
    ArraysInLists arraysinlists = new ArraysInLists();
  }
}

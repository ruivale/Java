package exp.collections;


import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class TestingHashMap {

  public TestingHashMap() {

    HashMap map = new HashMap(1000);

    map.put(new Integer(123), "111111");
    map.put(new Integer(123), "222222");
    map.put(new Integer(124), "111111");
    map.put(new Integer(125), "111111");

    System.out.println("No map existem "+map.size()+" elementos.");
    System.out.println("Map(123): "+map.get(new Integer(123))+".");
    map.remove(new Integer(123));
    System.out.println("Map(123): "+map.get(new Integer(123))+".");

  }
  public static void main(String[] args) {
    TestingHashMap testingHashMap1 = new TestingHashMap();
  }
}
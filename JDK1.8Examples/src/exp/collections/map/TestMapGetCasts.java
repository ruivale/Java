package exp.collections.map;

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
public class TestMapGetCasts {

  Map m1 = new HashMap(5);

  public TestMapGetCasts() {
    for (int i = 0; i < 10; i++) {
      m1.put(new Integer(i), new HashMap(10));
    }

    Map map = (Map)m1.get(new Integer(20));

    System.out.println("map is null? "+(map == null)+".");

  }

  public static void main(final String[] args) {
    new TestMapGetCasts();
  }

}

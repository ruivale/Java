package exp.collections.set;

import java.util.Set;
import java.util.TreeSet;


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
public class TestTreeSet {
  public TestTreeSet() {
    final Set set= new TreeSet();

    for (int i = 1; i < 5; i++) {
      set.add(new Integer(i));
    }

    System.out.println("set.contains(new Integer(0))=" + set.contains(new Integer(0)));;
    System.out.println("set.contains(new Integer(2))=" + set.contains(new Integer(2)));;
    System.out.println("set.contains(new Integer(5))=" + set.contains(new Integer(5)));;
    System.out.println("set.contains(new Integer(6))=" + set.contains(new Integer(6)));;

  }

  public static void main(String[] args) {
    TestTreeSet testtreeset = new TestTreeSet();
  }
}

package exp.list;

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
public class ListTests {
  public ListTests() {
    final List lAll = new ArrayList(30);
    final List l1 = new ArrayList(10);
    final List l2 = new ArrayList(10);

    for (int i = 0; i < 10; i++) {
      l1.add(new Integer(i));
      l2.add(new Integer(i+10));
    }

    lAll.addAll(l1);
    for (int i = 0; i < lAll.size(); i++) {
      System.out.println("lAll("+i+") = "+((Integer)lAll.get(i)).intValue()+".");
    }
    lAll.addAll(l2);
    for (int i = 0; i < lAll.size(); i++) {
      System.out.println("lAll("+i+") = "+((Integer)lAll.get(i)).intValue()+".");
    }

  }

  public static void main(String[] args) {
    ListTests listtests = new ListTests();
  }
}

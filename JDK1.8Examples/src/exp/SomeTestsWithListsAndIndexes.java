package exp;


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
public class SomeTestsWithListsAndIndexes {
  public SomeTestsWithListsAndIndexes() {

    final int iPartNbr = 7;
    final int nOfInts = 30;

    List list = new ArrayList(nOfInts);

    for (int i = 0; i < nOfInts; i++) {
      list.add(new Integer(i));
    }

    final int n = list.size();
    int from = 0;
    int to = 0;

    for (int i = 0; i < n; ) {
      System.out.println("\nindex(i="+i+").");
      from = i;
      i += iPartNbr;
      to = i < n? i: n;

      printList(list.subList(from, to));

    }

  }

  private void printList(List l){
    for (int i = 0; i < l.size(); i++) {
      System.out.println("\tl("+i+")="+((Integer)l.get(i)).intValue()+".");
    }
  }

  public static void main(String[] args) {
    new SomeTestsWithListsAndIndexes();
  }
}

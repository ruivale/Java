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
public class CollectionToArray {
  public CollectionToArray() {
    List l = new ArrayList(5);

    for (int i = 0; i < 5; i++) {
      l.add(new GI(i));
    }

    Object[] gs = l.toArray();

    for (int i = 0; i < gs.length; i++) {
      System.out.println("gs["+i+"]="+((GI)gs[i]).toString()+".");
    }


  }

  public static void main(String[] args) {
    CollectionToArray collectiontoarray = new CollectionToArray();
  }
}

class GI {
  int i =0;
  public GI(int i){
    this.i = i;
  }

  public String toString(){
    return "GI: "+i+".";
  }
}

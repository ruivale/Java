package exp.util.list;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Java program which shows How to loop over ArrayList in Java using advanced for loop,
 * traditional for loop and How to iterate ArrayList using Iterator in Java
 * advantage of using Iterator for traversing ArrayList is that you can remove
 * elements from Iterator while iterating.
 * <p>
 * @author
 */
public class ArrayListLoopExample {

  public static void main(String args[]) {
    test();
    test();
    test();
    test();
    test();
    test();
  }

  private static void test(){

    final int I_SIZE = 100000;
    //Creating ArrayList to demonstrate How to loop and iterate over ArrayList
    final ArrayList<String> games = new ArrayList<>(I_SIZE);
    for (int i = 0; i < I_SIZE; i++) {
      games.add(Integer.toString(i));
    }

    //System.out.println("original Size of ArrayList : " + games.size());


    //Looping over ArrayList in Java using advanced for loop
    //System.out.println("Looping over ArrayList in Java using advanced for loop");
    long beforeNanos = System.nanoTime();
    long before = System.currentTimeMillis();
    for (String game : games) {
      //System.out.println("\t\t"+game);
    }
    long afterNanos = System.nanoTime();
    long after = System.currentTimeMillis();
    System.out.println("New foreach loop took "+(after-before)+"/"+(afterNanos-beforeNanos)+" millis/nanos.");



    //You can also Loop over ArrayList using traditional for loop
    //System.out.println("Looping ArrayList in Java using simple for loop");
    beforeNanos = System.nanoTime();
    before = System.currentTimeMillis();
    for (int i = 0; i < games.size(); i++) {
      //System.out.println("\t\t"+games.get(i));
    }
    afterNanos = System.nanoTime();
    after = System.currentTimeMillis();
    System.out.println("Old for loop took "+(after-before)+"/"+(afterNanos-beforeNanos)+" millis/nanos.");



    //Iterating over ArrayList in Java
    final Iterator<String> itr = games.iterator();
    //System.out.println("Iterating  over ArrayList in Java using Iterator");
    beforeNanos = System.nanoTime();
    before = System.currentTimeMillis();
    while (itr.hasNext()) {
      itr.next();
      //System.out.println("\t\t" + itr.next());
    }
    afterNanos = System.nanoTime();
    after = System.currentTimeMillis();
    System.out.println("Iterator for loop took "+(after-before)+"/"+(afterNanos-beforeNanos)+" millis/nanos.");

    //System.out.println("final Size of ArrayList : " + games.size()+"\n\n\n");


    System.out.println();
  }

}

package jdk1_6examples.java.util.concurrent.future;


import java.util.*;
import java.util.concurrent.*;
import javax.swing.SwingUtilities;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */

public class CallableExample {

  public static class WordLengthCallable implements Callable {
    private final String word;

    public WordLengthCallable(final String word) {
      this.word = word;
    }

    @Override
    public Integer call() {
      return this.word.length();
    }
  }


  public static void main(String args[]) throws Exception {
    args = new String[]{"index","tool","tomorrow","ya","xpto"};
    ExecutorService pool = Executors.newFixedThreadPool(3);
    Set<Future<Integer>> set = new HashSet<Future<Integer>> (8);

    for (String word : args) {
      @SuppressWarnings("unchecked")
      Callable<Integer> callable = new WordLengthCallable(word);
      Future<Integer> future = pool.submit(callable);
      set.add(future);
    }

    int sum = 0;

    for (Future<Integer> future : set) {
      sum += future.get();
    }

    System.out.printf("The sum of lengths is %s%n", sum);
    System.exit(sum);
  }
}

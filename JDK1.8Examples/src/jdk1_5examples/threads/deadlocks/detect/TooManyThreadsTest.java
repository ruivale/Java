package jdk1_5examples.threads.deadlocks.detect;


import java.lang.management.*;

/**
 * TooManyThreadsTest.java
 *
 * Created on 21/01/2005, 15:20
 *
 * http://www.javaspecialists.co.za/archive/Issue093.html
 *
 */
public class TooManyThreadsTest {
  java.util.Queue d;
  public static void main(String[] args)
      throws InterruptedException {
    ThreadWarningSystem tws = new ThreadWarningSystem(500);
    tws.addListener(new ThreadWarningSystem.Listener() {
      public void deadlockDetected(ThreadInfo thread) {}

      public void thresholdExceeded(ThreadInfo[] threads) {
        System.out.println("Threshold Exceeded");
        System.out.println("threads.length = " + threads.length);
      }
    });
    createBatchOfThreads();
    Thread.sleep(10000);
    System.out.println("We should've dipped below the threshold");
    createBatchOfThreads();
  }

  private static void createBatchOfThreads() {
    for (int i = 0; i < 1000; i++) {
      new Thread() {
        {
          start();
        }

        public void run() {
          try {
            Thread.sleep(5000);
          } catch (InterruptedException e) {}
        }
      };
    }
  }
}

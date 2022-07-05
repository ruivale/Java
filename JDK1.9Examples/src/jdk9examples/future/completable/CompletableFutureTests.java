package jdk9examples.future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


/**
 * From: https://zeroturnaround.com/rebellabs/the-best-java-9-language-and-api-improvements/
 * <p>
 * - Another piece of the API that got a makeover is the CompletableFuture class. There are a couple of
 * interesting additions that make coding correct and beautiful concurrently in Java easier.
 * <p>
 * - One of the things that excited us the most is the copy() method that returns an immutable copy of
 * an existing CompletableFuture. In the code snippet below we construct a CompletableFuture, make a
 * copy of it and show that completing the copy doesn?t influence the original future object.
 * This is incredibly useful when you offer an asynchronous API that returns CompletableFutures.
 * Before you had to jump through hoops to avoid situations where clients can complete the future
 * you returned. Now it?s a matter of calling the copy() method.
 * <p>
 * - The best bit is that the completion value propagates successfully in the normal way.
 * Completing a future will complete all the copies.
 * <p>
 * - Java 9 also delivers timeouts to the CompletableFutures. Dealing with an asynchronous API without a
 * built-in way of specifying a timeout is messy. In Java 9, you can define exactly how you want to
 * complete the future after a user-defined period of time.
 * <p>
 */
public class CompletableFutureTests {

  public CompletableFutureTests() {
    try {

      System.out.println("\nCalling the copy() method.");
      // Before you had to jump through hoops to avoid situations where clients can complete the future
      // you returned. Now it?s a matter of calling the copy() method.
      final CompletableFuture<String> future = new CompletableFuture<>();
      final CompletableFuture<String> futureCopy = future.copy();
      System.out.println("future.isDone()? " + future.isDone());
      System.out.println("futureCopy.isDone()? " + futureCopy.isDone());
      System.out.println("futureCopy.complete(\"JRebel\")?" + futureCopy.complete("JRebel"));
      System.out.println("future.isDone()? " + future.isDone());
      System.out.println("futureCopy.isDone()? " + futureCopy.isDone());

      System.out.println("\nCompleting a future will complete all the copies");
      // The best bit is that the completion value propagates successfully in the normal way.
      // Completing a future will complete all the copies.
      final CompletableFuture<String> futureMain = new CompletableFuture<>();
      final CompletableFuture<String> futureMainCopy = futureMain.copy();
      System.out.println("futureMain.complete(\"XRebel\")? " + futureMain.complete("XRebel"));
      System.out.println("futureMain.isDone()? " + futureMain.isDone());
      System.out.println("futureMainCopy.isDone()? " + futureMainCopy.isDone());

      System.out.println("\nJava 9 also delivers timeouts to the CompletableFutures");
      // Java 9 also delivers timeouts to the CompletableFutures
      final CompletableFuture<String> futureWithTimeout = new CompletableFuture<>();
      final CompletableFuture<String> futureWithTimeoutCopy = futureWithTimeout.completeOnTimeout(
        "Isn't this amazing", 1, TimeUnit.SECONDS);
      System.out.println("futureWithTimeout.isDone()? " + futureWithTimeout.isDone());
      System.out.println("futureWithTimeoutCopy.isDone()? " + futureWithTimeoutCopy.isDone());
      final int secs = 2;
      System.out.println("Will sleep for " + secs + " seconds...");
      Thread.sleep(secs * 1000);
      System.out.println("futureWithTimeout.isDone()? " + futureWithTimeout.isDone());
      System.out.println("futureWithTimeoutCopy.isDone()? " + futureWithTimeoutCopy.isDone());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    final CompletableFutureTests clazz = new CompletableFutureTests();
  }
}

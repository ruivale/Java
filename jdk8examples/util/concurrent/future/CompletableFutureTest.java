package jdk8examples.util.concurrent.future;

//import bbejeck.function.throwing.ThrowingSupplier;
import static java.lang.Math.random;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * User: Bill Bejeck
 * Date: 1/4/16
 * Time: 9:03 PM
 */
public class CompletableFutureTest {

  private static final ExecutorService service = Executors.newCachedThreadPool();
  private final List<String> results = new ArrayList<>(8);

  //Examples of creating simple CompletableFuture objects
  @Test
  public void test_completed_future() throws Exception {
    String expectedValue = "the expected value";
    CompletableFuture<String> alreadyCompleted = CompletableFuture.completedFuture(expectedValue);
    assertThat(alreadyCompleted.get(), is(expectedValue));
  }

  @Test
  public void test_run_async() throws Exception {
    CompletableFuture<Void> runAsync = CompletableFuture.runAsync(()
      -> System.out.println("running async task"), service);
    this.pauseSeconds(1);
    assertThat(runAsync.isDone(), is(true));
  }

  @Test
  public void test_supply_async() throws Exception {
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this.simulatedTask(1,
      "Final Result"), service);
    assertThat(completableFuture.get(), is("Final Result"));
  }

  //Consuming results of CompletableFutures
  @Test
  public void test_accept_result() throws Exception {
    CompletableFuture<String> task = CompletableFuture.
      supplyAsync(this.simulatedTask(1, "add when done"), service);
    CompletableFuture<Void> acceptingTask = task.thenAccept(this.results::add);
    this.pauseSeconds(2);
    assertThat(acceptingTask.isDone(), is(true));
    assertThat(this.results.size(), is(1));
    assertThat(this.results.contains("add when done"), is(true));

  }

  @Test
  public void test_then_run_async() throws Exception {
    Map<String, String> cache = new HashMap<>(8);
    cache.put("key", "value");
    CompletableFuture<String> taskUsingCache = CompletableFuture.supplyAsync(this.simulatedTask(1, cache.
      get("key")), service);
    CompletableFuture<Void> cleanUp = taskUsingCache.thenRunAsync(cache::clear, service);
    cleanUp.get();
    String theValue = taskUsingCache.get();
    assertThat(cache.isEmpty(), is(true));
    assertThat(theValue, is("value"));
  }

  @Test
  public void test_run_after_both() throws Exception {

    CompletableFuture<Void> run1 = CompletableFuture.runAsync(() -> {
      this.pauseSeconds(2);
      this.results.add("first task");
    }, service);

    CompletableFuture<Void> run2 = CompletableFuture.runAsync(() -> {
      this.pauseSeconds(3);
      this.results.add("second task");
    }, service);

    CompletableFuture<Void> finisher = run1.runAfterBothAsync(run2, () -> this.results.add(this.results.
      get(0) + "&" + this.results.get(1)), service);
    this.pauseSeconds(4);
    assertThat(finisher.isDone(), is(true));
    assertThat(this.results.get(2), is("first task&second task"));
  }

  @Test
  public void test_run_after_either() throws Exception {

    CompletableFuture<Void> run1 = CompletableFuture.runAsync(() -> {
      this.pauseSeconds(2);
      this.results.add("should be first");
    }, service);

    CompletableFuture<Void> run2 = CompletableFuture.runAsync(() -> {
      this.pauseSeconds(3);
      this.results.add("should be second");
    }, service);

    CompletableFuture<Void> finisher = run1.runAfterEitherAsync(run2, () -> this.results.add(
      this.results.get(0).toUpperCase()), service);
    this.pauseSeconds(4);
    assertThat(finisher.isDone(), is(true));
    assertThat(this.results.get(1), is("SHOULD BE FIRST"));
  }

  @Test
  public void test_accept_either_async_calling_finishes_first() throws Exception {

    CompletableFuture<String> callingCompletable = CompletableFuture.supplyAsync(this.simulatedTask(1,
      "calling"), service);
    CompletableFuture<String> nestedCompletable = CompletableFuture.supplyAsync(this.simulatedTask(2,
      "nested"), service);

    CompletableFuture<Void> collector = callingCompletable.acceptEither(nestedCompletable,
      this.results::add);

    this.pauseSeconds(2);
    assertThat(collector.isDone(), is(true));
    assertThat(results.size(), is(1));
    assertThat(results.contains("calling"), is(true));
  }

  @Test
  public void test_accept_either_async_nested_finishes_first() throws Exception {

    CompletableFuture<String> callingCompletable = CompletableFuture.supplyAsync(this.simulatedTask(2,
      "calling"), service);
    CompletableFuture<String> nestedCompletable = CompletableFuture.supplyAsync(this.simulatedTask(1,
      "nested"), service);

    CompletableFuture<Void> collector = callingCompletable.acceptEither(nestedCompletable,
      this.results::add);

    this.pauseSeconds(2);
    assertThat(collector.isDone(), is(true));
    assertThat(this.results.size(), is(1));
    assertThat(this.results.contains("nested"), is(true));
  }

  @Test
  public void test_accept_both_async() throws Exception {

    CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(this.simulatedTask(3,
      "first task"), service);
    CompletableFuture<String> secondTask = CompletableFuture.supplyAsync(this.simulatedTask(2,
      "second task"), service);

    BiConsumer<String, String> acceptBothResults = (f, s) -> {
      this.results.add(f);
      this.results.add(s);
    };

    CompletableFuture<Void> bothTasks = firstTask.thenAcceptBothAsync(secondTask, acceptBothResults);
    this.pauseSeconds(4);
    assertThat(bothTasks.isDone(), is(true));
    assertThat(this.results.size(), is(2));
    assertThat(this.results.get(0), is("first task"));
    assertThat(this.results.get(1), is("second task"));

  }

  //Applying functions to CompletableFutures
  @Test
  public void test_apply() throws Exception {
    CompletableFuture<String> task = CompletableFuture.supplyAsync(this.simulatedTask(1, "change me"),
      service).thenApply(String::toUpperCase);
    assertThat(task.get(), is("CHANGE ME"));
  }

  @Test
  public void test_then_combine_async() throws Exception {
    CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(this.simulatedTask(3,
      "combine all"), service);
    CompletableFuture<String> secondTask = CompletableFuture.supplyAsync(this.simulatedTask(2,
      "task results"), service);
    CompletableFuture<String> combined = firstTask.thenCombineAsync(secondTask, (f, s) -> f + " " + s,
      service);

    assertThat(combined.get(), is("combine all task results"));
  }

  @Test
  public void test_then_combine_with_one_supplied_value() throws Exception {
    CompletableFuture<String> asyncComputedValue = CompletableFuture.supplyAsync(this.simulatedTask(2,
      "calculated value"), service);
    CompletableFuture<String> knowValueToCombine = CompletableFuture.completedFuture("known value");

    BinaryOperator<String> calcResults = (f, s) -> "taking a " + f + " then adding a " + s;
    CompletableFuture<String> combined = asyncComputedValue.thenCombine(knowValueToCombine, calcResults);

    assertThat(combined.get(), is("taking a calculated value then adding a known value"));
  }

  @Test
  public void test_then_compose() throws Exception {

    Function<Integer, Supplier<List<Integer>>> getFirstTenMultiples = num -> () -> Stream.iterate(num,
      i -> i + num).limit(10).collect(Collectors.toList());

    Supplier<List<Integer>> multiplesSupplier = getFirstTenMultiples.apply(13);

    CompletableFuture<List<Integer>> getMultiples = CompletableFuture.supplyAsync(multiplesSupplier,
      service);

    Function<List<Integer>, CompletableFuture<Integer>> sumNumbers = multiples -> CompletableFuture.
      supplyAsync(() -> multiples.stream().mapToInt(Integer::intValue).sum());

    CompletableFuture<Integer> summedMultiples = getMultiples.thenComposeAsync(sumNumbers, service);

    assertThat(summedMultiples.get(), is(715));
  }

  @Test
  public void test_several_stage_combinations() throws Exception {
    Function<String, CompletableFuture<String>> upperCaseFunction = s -> CompletableFuture.
      completedFuture(s.toUpperCase());

    CompletableFuture<String> stage1 = CompletableFuture.completedFuture("the quick ");

    CompletableFuture<String> stage2 = CompletableFuture.completedFuture("brown fox ");

    CompletableFuture<String> stage3 = stage1.thenCombine(stage2, (s1, s2) -> s1 + s2);

    CompletableFuture<String> stage4 = stage3.thenCompose(upperCaseFunction);

    CompletableFuture<String> stage5 = CompletableFuture.
      supplyAsync(this.simulatedTask(2, "jumped over"));

    CompletableFuture<String> stage6 = stage4.thenCombineAsync(stage5, (s1, s2) -> s1 + s2, service);

    CompletableFuture<String> stage6_sub_1_slow = CompletableFuture.supplyAsync(this.simulatedTask(4,
      "fell into"));

    CompletableFuture<String> stage7 = stage6.applyToEitherAsync(stage6_sub_1_slow, String::toUpperCase,
      service);

    CompletableFuture<String> stage8 = CompletableFuture.supplyAsync(this.simulatedTask(3,
      " the lazy dog"), service);

    CompletableFuture<String> finalStage = stage7.thenCombineAsync(stage8, (s1, s2) -> s1 + s2, service);

    assertThat(finalStage.get(), is("THE QUICK BROWN FOX JUMPED OVER the lazy dog"));

  }

  static void completedFutureExample() {
    CompletableFuture cf = CompletableFuture.completedFuture("message");
    assertTrue(cf.isDone());
    assertEquals("message", cf.getNow(null));
  }

  static void runAsyncExample() {
    CompletableFuture cf = CompletableFuture.runAsync(() -> {
      assertTrue(Thread.currentThread().isDaemon());
      randomSleep();
    });
    assertFalse(cf.isDone());
    sleepEnough();
    assertTrue(cf.isDone());
  }

  static void thenApplyExample() {
    CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
      assertFalse(Thread.currentThread().isDaemon());
      return s.toUpperCase();
    });
    assertEquals("MESSAGE", cf.getNow(null));
  }

  static void thenApplyAsyncExample() {
    CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
      assertTrue(Thread.currentThread().isDaemon());
      randomSleep();
      return s.toUpperCase();
    });
    assertNull(cf.getNow(null));
    assertEquals("MESSAGE", cf.join());
  }

  static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
    int count = 1;

    @Override
    public Thread newThread(Runnable runnable) {
      return new Thread(runnable, "custom-executor-" + count++);
    }
  });

  static void thenApplyAsyncWithExecutorExample() {
    CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
      assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
      assertFalse(Thread.currentThread().isDaemon());
      randomSleep();
      return s.toUpperCase();
    }, executor);
    assertNull(cf.getNow(null));
    assertEquals("MESSAGE", cf.join());
  }

  static void thenAcceptExample() {
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture("thenAccept message")
      .thenAccept(s -> result.append(s));
    assertTrue("Result was empty", result.length() > 0);
  }

  static void thenAcceptAsyncExample() {
    StringBuilder result = new StringBuilder();
    CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
      .thenAcceptAsync(s -> result.append(s));
    cf.join();
    assertTrue("Result was empty", result.length() > 0);
  }

  static void completeExceptionallyExample() {
    CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(
      String::toUpperCase
      //,CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS) // java9
    );
    CompletableFuture exceptionHandler = cf.handle((s, th) -> {
      return (th != null) ? "message upon cancel" : "";
    });
    cf.completeExceptionally(new RuntimeException("completed exceptionally"));
    assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
    try {
      cf.join();
      fail("Should have thrown an exception");
    } catch (CompletionException ex) { // just for testing
      assertEquals("completed exceptionally", ex.getCause().getMessage());
    }
    assertEquals("message upon cancel", exceptionHandler.join());
  }

  static void cancelExample() {
    CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(
      String::toUpperCase
      //,CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS)// java9
    );
    CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
    assertTrue("Was not canceled", cf.cancel(true));
    assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
    assertEquals("canceled message", cf2.join());
  }

  static void applyToEitherExample() {
    String original = "Message";
    CompletableFuture cf1 = CompletableFuture.completedFuture(original)
      .thenApplyAsync(s -> delayedUpperCase(s));
    CompletableFuture cf2 = cf1.applyToEither(
      CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
      s -> s + " from applyToEither");
    //assertTrue(cf2.join().endsWith(" from applyToEither"));// java9
  }

  static void acceptEitherExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture cf = CompletableFuture.completedFuture(original)
      .thenApplyAsync(s -> delayedUpperCase(s))
      .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
        s -> result.append(s).append("acceptEither"));
    cf.join();
    assertTrue("Result was empty", result.toString().endsWith("acceptEither"));
  }

  static void runAfterBothExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
      CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
      () -> result.append("done"));
    assertTrue("Result was empty", result.length() > 0);
  }

  static void thenAcceptBothExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
      CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
      (s1, s2) -> result.append(s1 + s2));
    assertEquals("MESSAGEmessage", result.toString());
  }

  static void thenCombineExample() {
    String original = "Message";
    CompletableFuture cf = CompletableFuture.completedFuture(original).
      thenApply(s -> delayedUpperCase(s))
      .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s)),
        (s1, s2) -> s1 + s2);
    assertEquals("MESSAGEmessage", cf.getNow(null));
  }

  static void thenCombineAsyncExample() {
    String original = "Message";
    CompletableFuture cf = CompletableFuture.completedFuture(original)
      .thenApplyAsync(s -> delayedUpperCase(s))
      .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
        (s1, s2) -> s1 + s2);
    assertEquals("MESSAGEmessage", cf.join());
  }

  static void thenComposeExample() {
    String original = "Message";
    CompletableFuture cf = CompletableFuture.completedFuture(original).
      thenApply(s -> delayedUpperCase(s))
      .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(
      s -> delayedLowerCase(s))
      .thenApply(s -> upper + s));
    assertEquals("MESSAGEmessage", cf.join());
  }

  static void anyOfExample() {
    // java9
    // java9
    // java9
//    StringBuilder result = new StringBuilder();
//    List messages = Arrays.asList("a", "b", "c");
//    List<CompletableFuture> futures = messages.stream()
//      .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
//      .collect(Collectors.toList());
//    CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete(
//      (res, th) -> {
//        if (th == null) {
//          assertTrue(isUpperCase((String) res));
//          result.append(res);
//        }
//      });
//    assertTrue("Result was empty", result.length() > 0);
  }

  static void allOfExample() {
    // java9
    // java9
    // java9
//    StringBuilder result = new StringBuilder();
//    List messages = Arrays.asList("a", "b", "c");
//    List<CompletableFuture> futures = messages.stream()
//      .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
//      .collect(Collectors.toList());
//    CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete(
//      (v, th) -> {
//        futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
//        result.append("done");
//      });
//    assertTrue("Result was empty", result.length() > 0);
  }

  static void allOfAsyncExample() {
    // java9
    // java9
    // java9
//    StringBuilder result = new StringBuilder();
//    List messages = Arrays.asList("a", "b", "c");
//    List<CompletableFuture> futures = messages.stream()
//      .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
//      .collect(Collectors.toList());
//    CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.
//      size()]))
//      .whenComplete((v, th) -> {
//        futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
//        result.append("done");
//      });
//    allOf.join();
//    assertTrue("Result was empty", result.length() > 0);
  }

  private Supplier<String> simulatedTask(final int numSeconds,
                                         final String taskResult) {
    return () -> {
      try {
        Thread.sleep(numSeconds * 1000);
      } catch (InterruptedException ie) {
      }
      return taskResult;
    };
  }

  private void pauseSeconds(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static boolean isUpperCase(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (Character.isLowerCase(s.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private static String delayedUpperCase(String s) {
    randomSleep();
    return s.toUpperCase();
  }

  private static String delayedLowerCase(String s) {
    randomSleep();
    return s.toLowerCase();
  }



  static Random random = new Random();

  private static void randomSleep() {
    try {
      Thread.sleep(random.nextInt(1000));
    } catch (InterruptedException ex) {

    }
  }



  private static void sleepEnough() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // ...
    }
  }

}

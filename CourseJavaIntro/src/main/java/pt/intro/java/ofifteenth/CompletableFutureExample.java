/**
 * Java intro.
 * 
 * Classname: pt.intro.java.ofifteenth.CompletableFutureExample
 * Copyright (C) 2024 RGV
 * Email: ruivale at gmail dot com
 *
 * This is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */
package pt.intro.java.ofifteenth;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * Description: 
 * 1. What is a Future? 
 * A Future is an interface representing a placeholder for a
 * result that will be available at some point in the future. It is primarily used with
 * ExecutorService to perform asynchronous computations.
 *
 * 1.1 Key Features of Future: 
 *  Blocking Nature: 
 *      You can use the get() method to retrieve the result, but it blocks until the computation is complete. 
 * Limited Functionalities: 
 *      Future provides basic methods like isDone() to check the task’s status, cancel() to stop 
 *      execution, and get() to retrieve the result.
 *
 * 2. What is a CompletableFuture? 
 * CompletableFuture, introduced in Java 8, is an extension of Future. It adds powerful methods to 
 * handle asynchronous programming with non-blocking and composable operations.
 *
 * 2.1 Key Features of CompletableFuture: 
 *  Non-Blocking: 
 *      Use thenApply, thenAccept, or thenRun to process results without blocking. 
 *  Chaining: 
 *      Combine multiple CompletableFutures using methods like thenCompose or thenCombine. 
 *  Exception Handling: 
 *      Built-in support for handling exceptions with methods like exceptionally. 
 *  Manually Complete: 
 *      CompletableFutures can be completed programmatically using complete() or completeExceptionally().
 * 
 * 
 * While Future served its purpose as a foundational abstraction for asynchronous programming in
 * Java, CompletableFuture significantly enhances the developer’s ability to handle asynchronous
 * workflows with non-blocking, composable, and flexible methods. For most modern applications,
 * CompletableFuture is the preferred choice due to its rich API and capabilities, making
 * asynchronous programming in Java both powerful and convenient.
 *
 * For developers transitioning to asynchronous patterns, understanding and leveraging
 * CompletableFuture is an essential step towards building scalable and efficient applications.
 * Whether you’re chaining tasks, handling exceptions, or combining workflows, CompletableFuture
 * provides the tools to meet the demands of today’s reactive systems.
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class CompletableFutureExample {

  
  /**
   * Executes a Future and waits for it termination.
   * 
   * @throws InterruptedException
   * @throws ExecutionException 
   */
  private static void futureExample() throws InterruptedException, ExecutionException {
    
    System.out.println("\nfutureExample:");
    
    ExecutorService executor = Executors.newSingleThreadExecutor();
    
    Future<Integer> future = executor.submit(() -> {
      Thread.sleep(2000); // Simulating a long-running task
      return 42;
    });

    System.out.println("\nWainting for the Future to end...");
    System.out.println(future.get() + "\n"); // Blocks until the result is available
    
    executor.shutdown();
  }

  
  /**
   * 
   */
  private static void completableFutureExample() {
    
    System.out.println("\ncompletableFutureExample:");
    
    CompletableFuture.supplyAsync(() -> {
      try {Thread.sleep(2000);} catch (InterruptedException interruptedException) {}
      return 42; // Simulating a computation
      
    }).thenApply(result -> result * 2) // Transforming the result
      .thenAccept(System.out::println); // Printing the result
  }

  
  /**
   * run multiple tasks and combine their results seamlessly
   */
  private static void combiningMultipleFuturesExample() {
    System.out.println("\ncombiningMultipleFuturesExample:");
    
    CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> 50);
    CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> 70);

    task1.thenCombine(task2, Integer::sum)
      .thenAccept(result -> System.out.println("Sum: " + result));    
  }

  
  /**
   * Handle exceptions without crashing the application
   */
  private static void handlingExceptionsExample() {
    System.out.println("\nhandlingExceptionsExample:");
    
    CompletableFuture.supplyAsync(() -> {
      throw new RuntimeException("Error occurred");
      
    }).exceptionally(ex -> {
      System.out.println("Handled Exception: " + ex.getMessage());
      return 0;
      
    }).thenAccept(System.out::println);    
  }

  
  /**
   * Avoid indefinitely waiting for a result
   */
  private static void timeoutHandlingExample() {
    System.out.println("\ntimeoutHandlingExample:");
    
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
      try {Thread.sleep(5000);} catch (InterruptedException interruptedException) {}
      return 42;
    });
    
    future.orTimeout(2, TimeUnit.SECONDS)
      .exceptionally(ex -> {
        System.out.println("Timeout occurred: " + ex.getMessage());
        return -1;
      })
      .thenAccept(System.out::println);
  }
    
    
  
  
  public static void main(final String[] args) {
    try {
      CompletableFutureExample.futureExample();

    } catch (InterruptedException | ExecutionException interruptedException) {
      interruptedException.printStackTrace();
    }

    try {
      System.out.println("\n\nCalling CompletableFuture...");
      CompletableFutureExample.completableFutureExample();
      System.out.println("... just called the CompletableFuture.\n\n");

      System.out.println("\n\nCalling combiningMultipleFuturesExample...");
      CompletableFutureExample.combiningMultipleFuturesExample();
      System.out.println("... just called the combiningMultipleFuturesExample.\n\n");

      System.out.println("\n\nCalling handlingExceptionsExample...");
      CompletableFutureExample.handlingExceptionsExample();
      System.out.println("... just called the handlingExceptionsExample.\n\n");

      System.out.println("\n\nCalling timeoutHandlingExample...");
      CompletableFutureExample.timeoutHandlingExample();
      System.out.println("... just called the timeoutHandlingExample.\n\n");

      System.out.println("\n\n\nPress any key to finish!");
      System.in.read();
      
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}

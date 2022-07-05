/**
 * <p>
 * Classname: exp.util.concurrent.future.FutureDemo
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package exp.util.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Java program to show how to use Future in Java. Future allows to write
 * asynchronous code in Java, where Future promises result to be available in
 * future
 *
 * @author Javin
 */
public class FutureDemo {

  private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

  /**
   *
   * @param args
   * @throws InterruptedException
   * @throws ExecutionException
   */
  public static void main(String args[]) throws InterruptedException, ExecutionException {
    final int iFactorial = 10;

    final FactorialCalculator task = new FactorialCalculator(iFactorial);

    System.out.println("Submitting Task ...");

    final Future<Long> future = threadpool.submit(task);

    System.out.println("Task is submitted");

    while (!future.isDone()) {
      System.out.println("Task is not completed yet....");
      Thread.sleep(1); //sleep for 1 millisecond before checking again
    }

    System.out.println("Task is completed, let's check result");
    final long factorial = future.get();
    System.out.println("Factorial of " + iFactorial + " is : " + factorial);

    threadpool.shutdown();
  }

  /**
   *
   *
   *
   */
  private static class FactorialCalculator implements Callable<Long> {

    private final int number;

    /**
     *
     * @param number
     */
    FactorialCalculator(int number) {
      this.number = number;
    }

    /**
     *
     * @return
     */
    @Override
    public Long call() {
      long output = 0;

      try {
        output = factorial(number);

      } catch (InterruptedException ex) {
        Logger.getLogger(FutureDemo.class.getName()).log(Level.SEVERE, null, ex);
      }

      return output;
    }

    /**
     *
     * @param number
     * @return
     * @throws InterruptedException
     */
    private long factorial(int number) throws InterruptedException {
      if (number < 0) {
        throw new IllegalArgumentException("Number must be greater than zero");
      }

      long result = 1;

      while (number > 0) {
        Thread.sleep(1); // adding delay for example
        result *= number;
        number--;
      }

      return result;
    }
  }
  private static final Logger LOG = Logger.getLogger(FutureDemo.class.getName());

}

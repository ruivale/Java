/**
 * <p>
 * Classname: jdk8examples.util.concurrent.future.CompletableFutureTests
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
package jdk8examples.util.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Dec 11, 2015, 6:25:16 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class CompletableFutureTests {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(CompletableFutureTests.class.getName());


  private final CompletableFuture<Integer> futureCount = CompletableFuture.supplyAsync(() -> {
    try {
      System.out.println("CompletableFuture.get()...");
      // Simulate long running task
      Thread.sleep(5000);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("... CompletableFuture.get()");

    return 10;
  });

  /**
   * The CompletableFutureTests default constructor.
   */
  public CompletableFutureTests() {
    try {
      System.out.println("futureCount.get()...");
      final int count = futureCount.get();
      System.out.println("... futureCount.get()");
      System.out.println(count);

    } catch (InterruptedException | ExecutionException ex) {
      ex.printStackTrace();
    }
  }




  public static void main(final String[] args) {
    final CompletableFutureTests clazz = new CompletableFutureTests();
  }
}

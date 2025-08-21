/**
 * <p>
 * Classname: jdk23examples.thread.virtual.ExecutorNewVirtualThread
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2024 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package jdk23examples.thread.virtual;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250821
 */
public class ExecutorNewVirtualThread {

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("ExecutorNewVirtualThread").append("").toString();
  }

  public static void main(final String[] args) {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, 1_000).forEach(i -> {
        executor.submit(() -> {
//          System.out.println("This is running in a virtual thread ("+
//              Thread.currentThread()+ " - " + Thread.currentThread().threadId()+")!");
          Thread.sleep(Duration.ofSeconds(1));
          return i;
        });
      });
    }  // executor.close() is called implicitly, and waits
  }
}

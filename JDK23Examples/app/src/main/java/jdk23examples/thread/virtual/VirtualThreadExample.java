/**
 * <p>
 * Classname: jdk23examples.thread.virtual.VirtualThreadExample
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

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 240926
 */
public class VirtualThreadExample {

  public static void main(String[] args) throws InterruptedException {
    // Create and start a virtual thread
    Thread virtualThread = Thread.ofVirtual().start(() -> 
      System.out.println("This is running in a virtual thread ("+
          Thread.currentThread()+ " - " + Thread.currentThread().threadId()+")!")
    );

    // Wait for the virtual thread to complete execution
    virtualThread.join();

    // Creating multiple virtual threads to showcase concurrency
    for (int i = 1; i <= 5; i++) {
      int taskId = i; // Effectively final variable for the lambda expression
      Thread.ofVirtual().start(() -> 
        System.out.println("Task " + taskId + " is running in a virtual thread ("+
          Thread.currentThread()+ " - " + Thread.currentThread().threadId()+")!")
      );
    }

    // Adding sleep to ensure all virtual threads finish before main thread ends
    Thread.sleep(1000); // Delay to allow all threads to finish
  }
}

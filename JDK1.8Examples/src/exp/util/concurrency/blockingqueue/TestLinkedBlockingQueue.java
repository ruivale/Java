/**
 * <p>
 * Classname: exp.util.concurrent.TestLinkedBlockingQueue
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
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
package exp.util.concurrency.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;


public class TestLinkedBlockingQueue {

  LinkedBlockingQueue<Runnable> l = new LinkedBlockingQueue<Runnable>(25);

  TestLinkedBlockingQueue() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        addItens();
      }
    });
    t.start();

    Thread t2 = new Thread(new Runnable() {
      public void run() {
        readItens();
      }
    });
    t2.start();

  }

  /**
   *
   */
  private void addItens() {
    while (true) {
      try {
        System.out.println("P put one more... size: " + l.size() + " space remaining: " + l.remainingCapacity());
        l.put(
            new Runnable() {
              public void run() {
                int sleep = 0;
                try {
                  sleep = ((int) System.currentTimeMillis() % 1000);
                  System.out.println("\t\tThread " + Thread.currentThread().getId() + " will sleep: "
                                     + sleep);
                  Thread.sleep(sleep);
                } catch (Exception e) {
                  e.printStackTrace();
                }
                System.out.println("\t\tThread " + Thread.currentThread().getId() + " finishing");
              }
            }
        );
        int sleep = ((int) System.currentTimeMillis() % 100);
        System.out.println("P will sleep for " + sleep);
        Thread.sleep(sleep);

      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
    }
  }

  /**
   *
   */
  private void readItens() {
    while (true) {
      try {
        final Runnable runnable = l.take();
        //Thread t = new Thread(l.take());
        System.out.println("\tT read one more...");
        //t.start();
        runnable.run();

        int sleep = ((int) System.currentTimeMillis() % 100) * 10;
        System.out.println("\tT will sleep for " + sleep);
        Thread.sleep(sleep);

      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
    }
  }




  public static void main(String[] args) {
    new TestLinkedBlockingQueue();
  }
}

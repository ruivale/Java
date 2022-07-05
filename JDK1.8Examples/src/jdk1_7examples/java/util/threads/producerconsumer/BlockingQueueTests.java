/**
 * <p>
 * Classname: jdk1_7examples.java.util.threads.producerconsumer.BlockingQueueTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich – Ap. 3078
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

package jdk1_7examples.java.util.threads.producerconsumer;


import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Oct 18, 2012, 12:46:09 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class BlockingQueueTests {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(BlockingQueueTests.class.getName());

  private BlockingQueue<Runnable> blockingQueue = new SynchronousQueue(true /* fair */);

  private int iProducer = -1;
  private int iConsumer = 0;

 /**
  * The BlockingQueueTests default constructor.
  */
  public BlockingQueueTests(){



    final Thread threadProducer = new Thread(new Runnable() {
      @Override
      public void run() {

        try {Thread.sleep(2500);} catch (InterruptedException interruptedException) {}

        while(true){
          try {
            final int nProds = iProducer++;
            final Runnable runnable = new Runnable() {
              @Override
              public void run() {
                System.out.println("\t\tProducer is "+nProds);
              }
            };
            System.out.println(nProds+" - Will try to add ... "+System.currentTimeMillis());
            blockingQueue.add(runnable);
            System.out.println(nProds+" - Just added ... "+System.currentTimeMillis());
            Thread.sleep(3467);

          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }, "Producer");
    threadProducer.setDaemon(true);
    threadProducer.start();


    final Thread threadConsumer = new Thread(new Runnable() {
      @Override
      public void run() {

        //try {Thread.sleep(2000);} catch (InterruptedException interruptedException) {}

        while(true){
          try {
            final int nCons = iConsumer++;

            System.out.println("\t"+nCons+" - Will try to take ... "+System.currentTimeMillis());
            final Runnable run = blockingQueue.take();
            System.out.println("\t"+nCons+" - Just took ... "+System.currentTimeMillis());
            run.run();

            Thread.sleep(5367);

          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }, "Consumer");
    threadConsumer.setDaemon(true);
    threadConsumer.start();

  }

 /**
  *
  * @return
  */
  public String toString(){
    return new StringBuffer("FibonacciProducer").append("").toString();
  }

  public static void main(final String[] args){
    final BlockingQueueTests clazz = new BlockingQueueTests();

    System.out.println("Press any key to exit ...");
    try {
      System.in.read();
    } catch (IOException iOException) {
    }

    System.exit(0);
  }
}

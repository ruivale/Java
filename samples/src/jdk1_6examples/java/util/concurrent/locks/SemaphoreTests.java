/**
 * <p>
 * Classname:  jdk1_6examples.java.util.concurrent.locks.SemaphoreTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
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

package jdk1_6examples.java.util.concurrent.locks;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 7/Fev/2011, 10:37:15
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class SemaphoreTests {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(SemaphoreTests.class.getName());
  private static final Semaphore semaphore = new Semaphore(2, true);



 /**
  * The SemaphoreTests default constuctor.
  */
  public SemaphoreTests(){
  }

  /**
   * 
   * @param strCallerThreadName
   * @return
   */
  public boolean process(final String strCallerThreadName) {
    boolean processed = false;

    System.out.println(strCallerThreadName+" has "+semaphore.availablePermits()+" permits.");

    if (semaphore.tryAcquire()) {
      System.out.println("Processing("+strCallerThreadName+") ...");

      try {
        Thread.sleep(2500);
      } catch (InterruptedException interruptedException) {
      }

      System.out.println("... processed("+strCallerThreadName+").");

      semaphore.release();

    } else {
      System.out.println("BLA BLA BLA ("+strCallerThreadName+") ...");
    }

    return processed;
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("SemaphoreTests").append("").toString();
  }

  public static void main(final String[] args){
    final SemaphoreTests clazz = new SemaphoreTests();

    new Thread(new Runnable() {

      @Override
      public void run() {
        clazz.process(Thread.currentThread().getName());
      }
    }, "ThreadName1").start();



    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          Thread.sleep(500);
        } catch (InterruptedException interruptedException) {
        }
        clazz.process(Thread.currentThread().getName());
      }
    }, "ThreadName2").start();



    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          Thread.sleep(2500);
        } catch (InterruptedException interruptedException) {
        }
        clazz.process(Thread.currentThread().getName());
      }
    }, "ThreadName3").start();



    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          Thread.sleep(4000);
        } catch (InterruptedException interruptedException) {
        }
        clazz.process(Thread.currentThread().getName());
      }
    }, "ThreadName4").start();

  }
}

/**
 * <p>
 * Classname:  jdk1_6examples.java.util.concurrent.locks.ReentrantLockWaitAndNotifySample
 * </p>
 *
 * <p>Copyright: Copyright (c) 2024 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
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

package jdk1_6examples.java.util.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;




/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class ReentrantLockWaitAndNotifySample {


  private ReentrantLock reentLock = new ReentrantLock();
  
  private Condition condition = reentLock.newCondition();
  
  private volatile boolean wasFirstFrameReceived = false;


 /**
  * The ReentrantLockWaitAndNotifySample default constructor.
  */
  public ReentrantLockWaitAndNotifySample(){
  }

  
  /**
   * 
   */
  private void startWaiting(final int iMillisToWait) {
    System.out.println("startWaiting...");
    
    reentLock.lock();
    
    System.out.println("reentLock.lock()...");
    
    try {
      System.out.println("Will condition.await("+iMillisToWait+")...");

      condition.await(iMillisToWait, TimeUnit.MILLISECONDS);      

      System.out.println("condition.await()...");
    } catch (Exception exc) {
      exc.printStackTrace();
      
    } finally {
      System.out.println("finally...");

      reentLock.unlock();

      System.out.println("reentLock.unlock()...");
    }
    
    System.out.println("...startWaiting");
  }
  
  
  /**
   * 
   * @param arrived 
   */
  private void firstFrameEventArrived(final boolean arrived) {
    System.out.println("\tfirstFrameEventArrived...");
    
    reentLock.lock();
    
    System.out.println("\treentLock.lock()");

    try {
      //this.wasFirstFrameReceived = arrived;
      
      System.out.println("\tthis.wasFirstFrameReceived = "+arrived+"...");

      condition.signal();
    
      System.out.println("\tcondition.signal()...");
        
    } catch(Exception exc) {
      exc.printStackTrace();
      
    } finally {
      System.out.println("\tfinally...");

      reentLock.unlock();
      
      System.out.println("\treentLock.unlock()...");

    }

    System.out.println("\t...firstFrameEventArrived");

  }



  public static void main(final String[] args){
    final ReentrantLockWaitAndNotifySample clazz = new ReentrantLockWaitAndNotifySample();
    
    final Runnable runnable1 = () -> {
      clazz.startWaiting(2000);
    };

    new Thread(runnable1, "runnable1").start();
    
    
    final Runnable runnable2 = () -> {
      try {
        Thread.sleep(1500);
      } catch (InterruptedException interruptedException) {
      }
      
      clazz.firstFrameEventArrived(true);
    };    
    
    new Thread(runnable2, "runnable2").start();
    
  }
}

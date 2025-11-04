/**
 * <p>
 * Classname:  jdk1_6examples.java.util.concurrent.locks.ReentrantLockInterruptibly
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
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class ReentrantLockInterruptibly {

  
//  private ReentrantLock reentLock = new ReentrantLock();
  
  
  
  
  /**
   * 
   */
  private void startWaiting(final int iMillisToWait) {
    System.out.println("startWaiting...");
    
    try {
      System.out.println("Thread.sleep("+iMillisToWait+")...");

      Thread.sleep(iMillisToWait);
      
      System.out.println("Thread.sleep("+iMillisToWait+") terminated...");
      
    } catch (InterruptedException iexc) {
      iexc.printStackTrace();
      
    } catch (Exception exc) {
      exc.printStackTrace();      
    }
    
    System.out.println("...startWaiting");
  }
  
  
  /**
   * 
   * @param arrived 
   */
  private void firstFrameEventArrived(final boolean arrived, final Thread thread) {
    System.out.println("\tfirstFrameEventArrived...");
    

    try {
      System.out.println("\tWill thread.interrupt()...");

      thread.interrupt();
    
      System.out.println("\tWill thread.interrupt() terminated...");
        
    } catch(Exception exc) {
      exc.printStackTrace();      
    }

    System.out.println("\t...firstFrameEventArrived");

  }    
  
  
  
  
  public static void main(final String[] args){
    final ReentrantLockInterruptibly clazz = new ReentrantLockInterruptibly();

    
    final Runnable runnable1 = () -> {
      clazz.startWaiting(2000);
    };

    final Thread t1 = new Thread(runnable1, "runnable1"); 
    t1.start();
    
    
    final Runnable runnable2 = () -> {
      try {
        Thread.sleep(2500);
      } catch (InterruptedException interruptedException) {
      }
      
      clazz.firstFrameEventArrived(true, t1);
    };    
    
    new Thread(runnable2, "runnable2").start();    
  }
}

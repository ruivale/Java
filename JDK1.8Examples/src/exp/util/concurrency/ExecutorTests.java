/**
 * <p>
 * Classname:  exp.util.concurrency.ExecutorTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2021 EFACEC SE
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

package exp.util.concurrency;


import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
public class ExecutorTests {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(ExecutorTests.class.getName());


 /**
  * The ExecutorTests default constructor.
  */
  public ExecutorTests(){
    
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);    
    
    scheduler.scheduleWithFixedDelay( new TimerTask() {
      long now;
      long then = System.currentTimeMillis();
      
      
      public void run() {
        now = System.currentTimeMillis();

        System.out.println("It took " + (now - then) + " millis.");

        try {
          Thread.sleep(3500);
        } catch (InterruptedException ex) {
        }

        then = System.currentTimeMillis();

        //timer.purge();          
      }
    }, 100, 3000, TimeUnit.MILLISECONDS);
    
  }

  
  
  
  public static void main(final String[] args){
    final ExecutorTests clazz = new ExecutorTests();
  }
}

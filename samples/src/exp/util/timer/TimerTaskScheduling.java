/**
 * <p>
 * Classname: exp.util.timer.TimerTaskScheduling
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

package exp.util.timer;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Apr 11, 2014, 3:26:38 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class TimerTaskScheduling {


  /**
   *
   */
  private void run(){
    final Timer timer = new Timer();

    final TimerTask timerTask = new TimerTask(){
      long now;
      long then = System.currentTimeMillis();
      int i = 1;

      @Override
      public void run() {
        now = System.currentTimeMillis();

        System.out.println("It took "+(now-then)+" millis.");

        try {
          Thread.sleep(3500);
        } catch (InterruptedException ex) {
        }

        then = System.currentTimeMillis();

        timer.purge();
      }
    };
    /**/
    System.out.println("Thread.currentThread().toString()=" +Thread.currentThread().toString());
    timer.schedule(timerTask, 100, 3000);
    System.out.println("Thread.currentThread().toString()=" +Thread.currentThread().toString());

  }


  public static void main(final String[] args){
    new TimerTaskScheduling().run();
  }
}

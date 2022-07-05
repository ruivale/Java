package exp.swing.timers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MyTimerDelayTests {

  long lNow = System.currentTimeMillis();
  int delay = 3000; //milliseconds

  public MyTimerDelayTests() {
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          System.out.println("millis from the last call "+
                             (System.currentTimeMillis()-lNow)+".");
          lNow = System.currentTimeMillis();
        }
    };
    final Timer timer = new Timer(delay, taskPerformer);
    timer.setInitialDelay(0);
    timer.start();

    new Thread(new Runnable() {
      public void run() {
        byte[] b = new byte[1024];
        while(true){
          try {
            System.out.print("Timer delay:");
            System.in.read(b);

            timer.stop();
            timer.setDelay(Integer.parseInt((new String(b)).trim()));
            lNow = System.currentTimeMillis();
            timer.start();

          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    }).start();
  }

  public static void main(String[] args) {
    MyTimerDelayTests mytimerdelaytests = new MyTimerDelayTests();
  }
}

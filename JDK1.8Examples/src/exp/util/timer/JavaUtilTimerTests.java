package exp.util.timer;

import java.util.*;
import javax.swing.SwingUtilities;


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
public class JavaUtilTimerTests{
  Timer timer = new Timer();

  public JavaUtilTimerTests(){

    final TimerTask timerTask = new TimerTask(){
      long now;
      long last = System.currentTimeMillis();
      int i = 1;

      public void run() {
        now = System.currentTimeMillis();

        System.out.println("now : "+now+".");
        System.out.println("last: "+last+".");

        if ((System.currentTimeMillis() - last) >= 1000) {
          System.out.println("took " + (now - last) + " millis.");

          System.out.println("TimerTask Thread.currentThread().toString()=" +
                             Thread.currentThread().toString() +
                             " isEDT?" + SwingUtilities.isEventDispatchThread());
          /***
          try {
            System.out.println("TimerTask Press ENTER to continue ...");
            System.in.read();
            System.out.println("TimerTask ...terminating");

                   } catch (Exception ex) {
            ex.printStackTrace();
          }
          /**/

          if (i++ % 5 == 0) {
            try {
              System.out.println("\tsleeping");
              Thread.sleep(1767);
              System.out.println("\twakeup");
            } catch (Exception ex) {}

          }

        }else{
          System.out.println("SKIPING EXECUTION");
        }

        last = System.currentTimeMillis();

      }
    };
    /**/
    System.out.println("Thread.currentThread().toString()=" +
        Thread.currentThread().toString());
    timer.schedule(
        timerTask,
        1000,
        1000);
    System.out.println("Thread.currentThread().toString()=" +
        Thread.currentThread().toString());

    //timer.schedule(
      //  timerTask,
        //0);
    /**/

    /***
    System.out.println("Before first run ...");
    timerTask.run();
    System.out.println("After first run ...");
    /**/

    /***
    new Thread(
        new Runnable(){
            public void run(){

              System.out.println("\tRunning the thread ... ");

              try{
                int i = 0;
                while(i++<2){
                  System.out.println("\twhile true ...");
                  SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                      System.out.println("\tThread.currentThread().toString()=" +
                          Thread.currentThread().toString());
                      System.out.println("\tSwingUtilities.isEventDispatchThread()=" +
                          SwingUtilities.isEventDispatchThread());
                    }
                  });
                  Thread.sleep(4500);
                }
              } catch(Exception ex){
                ex.printStackTrace();
              }
            }
        }
    ).start();
    /**/

    /***
    try{
      Thread.sleep(4500);
    } catch(Exception ex){
      ex.printStackTrace();
    }
    timerTask.run();
    /**/

    /***
    try {
      System.out.println("Press ENTER to cancel the timer ...");
      System.in.read();
      timer.cancel();
      System.out.println("Timer canceled.");

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    timerTask = new TimerTask(){
          public void run() {
            now = System.currentTimeMillis();
            System.out.println("took " + (now - last) + " millis.");

            System.out.println("TimerTask Thread.currentThread().toString()=" +
                               Thread.currentThread().toString() +
                               " isEDT?" + SwingUtilities.isEventDispatchThread());
            last = now;
          }
    };
    timer = new Timer();
    timer.schedule(
      timerTask,
      0,
      1330);
    /**/


  }
  public static void main(String[] args) throws Exception{
    javax.swing.JFrame f = new javax.swing.JFrame("dssd");
    f.setBounds(100,100,350,200);
    f.setVisible(true);
    JavaUtilTimerTests j = new JavaUtilTimerTests();
    System.out.println("ending ...");
    System.in.read();
    System.out.println("... ended");
  }
}

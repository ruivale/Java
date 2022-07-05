package exp.java.util;

import java.util.*;

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
public class MyTimer {
  private final int iDelayBetweenRuns = 10000;

  private long lMillis = System.currentTimeMillis();

  /**
   *
   */
  public MyTimer() {
    Timer timer = new Timer(true);
    /***/
    timer.schedule(
        new MyTimerTask(),
        iDelayBetweenRuns,
        iDelayBetweenRuns);
    /**/
    /***
    timer.scheduleAtFixedRate(
         new MyTimerTask(),
         3000,
         5000);
    /**/
  }

  public static void main(String[] args) throws Exception {
    MyTimer mytimer = new MyTimer();
    javax.swing.JFrame f = new javax.swing.JFrame("sdf");
    f.setVisible(true);
  }

  class MyTimerTask extends TimerTask{
    int i =0;
    long before = 0;


    public void run(){

      System.out.println("toke " + (System.currentTimeMillis() - lMillis) + " millis.");

      try {
        final byte[] b = new byte[128];
        System.out.println("ENTER ...");
        System.in.read(b);
        final int iSleep = Integer.parseInt(new String(b).trim());
        System.out.println("Sleep for "+iSleep+" millis.");
        Thread.sleep(iSleep);
        System.out.println("... LEAVING sleeping for "+iSleep+".");
      } catch (Exception ex) {
        ex.printStackTrace();

        try {
          System.out.println("Sleep for "+iDelayBetweenRuns+" millis.");
          Thread.sleep(iDelayBetweenRuns);
          System.out.println("... LEAVING sleeping for "+iDelayBetweenRuns+".");
        } catch (InterruptedException interruptedException) {
        }
      }
    


      /**********
      System.out.println(".");
       * 
      if((System.currentTimeMillis()-lMillis) >= iDelayBetweenRuns) {
        System.out.println("toke " + (System.currentTimeMillis() - lMillis) +" millis.");

        if ((i++) % 5 == 0) {
          try {
            System.out.println("ENTER ...");
            System.in.read();
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }

      }else{
        System.out.println("salteite fora");
      }
      /**/

      lMillis = System.currentTimeMillis();

    }
  }
}

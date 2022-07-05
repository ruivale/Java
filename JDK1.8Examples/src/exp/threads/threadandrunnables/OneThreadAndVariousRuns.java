package exp.threads.threadandrunnables;

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
public class OneThreadAndVariousRuns {
  public OneThreadAndVariousRuns() {

    ProcessingThread t = new ProcessingThread();

    /***/
    final Runnable runnable3 = new Runnable() {
      public void run() {
        System.out.println("33333333333333");
      }
    };
    t.start(runnable3);
    /**/
    /***/
    final Runnable runnable1 = new Runnable() {
      public void run() {
        System.out.println("11111111111111");
      }
    };
    t.start(runnable1);
    /**/
    /***/
    final Runnable runnable2 = new Runnable() {
      public void run() {
        System.out.println("22222222222");
      }
    };
    t.start(runnable2);
    /**/

  }

  public static void main(String[] args) {
    OneThreadAndVariousRuns o = new
        OneThreadAndVariousRuns();
  }



  class ProcessingThread extends Thread{
    Object objLockable = new Object();
    Runnable target;

    protected void setRunnable(final Runnable runnable){
      this.target = runnable;
    }

    protected void start(final Runnable runnable){
      this.setRunnable(runnable);

      synchronized(objLockable){
        this.start();
      }
    }

    public void run(){
      synchronized(objLockable){
        if (this.target != null) {
          System.out.println("START - " + this.currentThread().toString());
          this.target.run();
          System.out.println("END - " + this.currentThread().toString());
        }
      }
    }
  }

}

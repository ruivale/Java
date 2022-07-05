package exp.threads;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class SingleThread
  implements Runnable {
  Thread t = new Thread(this, "this");

  /**
   * Creates a new SingleThread object.
   */
  public SingleThread() {
    System.out.println("Starting \"this\" thread!");
    t.start();
    try {
      System.out.println("Main thread sleeping!");
      t.sleep(1000);
      System.out.println("Main thread waking up!");
    } catch(Exception e) {
      e.printStackTrace();
    }
    if(!t.isAlive()) {
      System.out.println("Starting \"this\" thread (2nd)!");
      t.start();
    } else {
      try {
        System.out.println("Thread \"this\" is alive! JOINING");
        t.join(5000);
        try {
          t.start();
        } catch(Exception ex) {
          ex.printStackTrace();
          System.out.println("Creating a new \"this\" thread!");
          t = new Thread(this, "this");
        }
        System.out.println("Starting \"this\" thread (2nd)!");
        t.start();
      } catch(Exception e) {
        e.printStackTrace();
        System.out.println("Exception!");
      }
    }
    System.out.println("Main thread terminating");
  }

  /**
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    SingleThread singleThread1 = new SingleThread();
  }

  /**
   *
   */
  public void run() {
    if(Thread.currentThread()
               .getName()
               .equals("this")) {
      try {
        System.out.println("    Thread \"this\" sleeping!");
        t.sleep(10000);
        System.out.println("    Thread.currentThread().toString(): "+
                           Thread.currentThread().toString());
        System.out.println("    Thread \"this\" terminating!");
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
}

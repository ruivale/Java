package exp.threads;


/**
 * @author $author$
 * @version $Revision$
 */
public class MyThreads implements Runnable {
  Thread t = new Thread(
                        this,
                        "this");

  /**
   * Creates a new MyThreads object.
   */
  public MyThreads() {
    try {
      t.start();

      //    t.join(5000);
      //  System.out.println("JOINOU");
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   *
   */
  public void a() {
    try {
      long milis;
      System.out.println("-> Waiting for the join!");
      milis = System.currentTimeMillis();
      t.join(6000);
      long waited = System.currentTimeMillis()-milis;
      System.out.println("-> Waited for "+waited+"miliseconds!");
      System.out.println("-> Joining");

      //t.interrupt(); // No work
      //t.stop();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    MyThreads myThreads1 = new MyThreads();
  }

  /**
   *
   */
  public void run() {
    if(Thread.currentThread()
               .getName()
               .equals("this")) {
      new Thread(
                 this,
                 "join").start();

      if(true) {
        //while (true) {
        try {
          System.out.println("Sleeping!");
          t.sleep(4000);
          System.out.println("Wake up!");
        } catch(Exception e) {
          System.out.println("Exception while trying to sleep!");
          e.printStackTrace();

          //break;
        }

        //}
      }

      System.out.println("Terminating the thread: this!");
    } else if(Thread.currentThread()
                      .getName()
                      .equals("join")) {
      a();
    }
  }
}

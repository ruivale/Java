package exp.threads.pools;

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
public class UsingThreadPool {
  private static final int ACTIVE_THREADS = 3;

  public UsingThreadPool() {

    // A pool with 2 active threads ...
    final ThreadPool tp = new ThreadPool(ACTIVE_THREADS);

    /**
    tp.addRequest(new MyRunnables("r1", 1000));
    tp.addRequest(new MyRunnables("r2", 1200));
    tp.addRequest(new MyRunnables("r3", 1500));
    tp.addRequest(new MyRunnables("r4", 1700));
    tp.addRequest(new MyRunnables("r5", 1900));
    tp.addRequest(new MyRunnables("r6", 2000));
    /**/

    System.out.println("Now the add request and wait ...");

    new Thread(new Runnable() {
      public void run() {
        try {
          //tp.addRequestAndWait(new NeverEndingRunnable("rw1", 5000));
          tp.addRequest(new MyRunnables("r1", 1000));
          tp.addRequestAndWait(new MyRunnables("rw1", 1450));
          tp.addRequest(new MyRunnables("r2", 2000));
          tp.addRequestAndWait(new MyRunnables("rw2", 750));
          tp.addRequestAndWait(new MyRunnables("rw3", 2600));
          tp.addRequest(new MyRunnables("r3", 1300));
          tp.addRequestAndWait(new MyRunnables("rw4", 1800));
          tp.addRequest(new MyRunnables("r4", 1000));
          tp.addRequestAndWait(new MyRunnables("rw5", 1400));
          tp.addRequestAndWait(new MyRunnables("rw6", 2000));
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();

    System.out.println("Now we'll wait for the Threads stoping ...");

    new Thread(new Runnable() {
      public void run() {
        try {
          while (true) {
            Thread.sleep(100);
            System.out.println("\t\t\tnObjects="+tp.getTotalNumberOfRunnables()+".");
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();

    try {
      Thread.sleep(300);
      tp.waitForAll();
      int intno = tp.getTotalNumberOfRunnables();
      System.out.println("#############################################");
      System.out.println("nObjects="+intno+".");
      System.out.println("stop wainting ...");
      System.out.println("#############################################");

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.out.println("END END END");

  }

  class MyRunnables implements Runnable{
    String name = "r1";
    int sleeping = 1000;

    MyRunnables(final String name, final int sleeping){
      this.name = name;
      this.sleeping = sleeping;
    }
    public void run(){
      System.out.println("Start  "+this.name);
      try {
        Thread.sleep(sleeping);
      } catch (Exception ex) {

      }
      System.out.println("Ending "+this.name);
    }
  }
  class NeverEndingRunnable extends MyRunnables{

    NeverEndingRunnable(final String name, final int sleeping){
      super(name, sleeping);
    }
    public void run(){
      System.out.println("Start  "+name);
      while(true){
        try {
          Thread.sleep(sleeping);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      //System.out.println("Ending "+this.name);
    }
  }

  public static void main(String[] args) {
    UsingThreadPool u = new UsingThreadPool();
  }
}

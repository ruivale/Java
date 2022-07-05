package exp.threads;


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
public class WaitLeak
    implements Runnable {
  protected static Object LOCK = new Object();

  public static void main(String[] args) throws Exception {
    int WAITTIME = 2000;
    int NUMTHREADS = 4;

    (new Thread(new WaitLeakNotifier(WAITTIME))).start();
    for (int i = 0; i < NUMTHREADS; i++) {
      Thread.sleep(1000);
      (new Thread(new WaitLeak())).start();
    }
  }

  public void run() {
    System.out.println("Starting thread " + Thread.currentThread());
    synchronized (LOCK) {
      try {
        LOCK.wait();
      } catch (InterruptedException e) {}
    }
    System.out.println("Terminating thread " + Thread.currentThread());
  }

}


class WaitLeakNotifier
    implements Runnable {
  long waittime;

  public WaitLeakNotifier(long time) {
    waittime = time;
  }

  public void run() {
    long now = System.currentTimeMillis();
    long diff = 0;
    while ((diff = System.currentTimeMillis() - now) < waittime) {
      try {
        Thread.sleep(waittime - diff);
      } catch (InterruptedException e) {}
    }
    synchronized (WaitLeak.LOCK) {
      WaitLeak.LOCK.notifyAll();
    }
  }
}

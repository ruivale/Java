package exp.threads.javatut;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class MutexTest
    implements DeadLockListener {

  protected static Mutex mutex = new Mutex("mutex1");

  /**
   *
   */
  public MutexTest() {
    mutex.addDeadlockListener(this);
    (new TestThread("thread1")).start();
//    (new TestThread("thread2")).start();
//    (new TestThread("thread3")).start();
    mutex.take();
    try {
      Thread.sleep(10000);
    } catch (Exception x) {
    }
    System.out.println("Done pausing");
    mutex.release();
  }

  public static void main(String[] args) {
    new MutexTest();
  }

  /**
   *
   * @param evt DeadlockEvent
   */
  public void deadlocked(DeadlockEvent evt) {
    System.out.println("Deadlocked threads detected: " + evt.getSource());
  }

  /**
   *
   * <p>Title: </p>
   * <p>Description: </p>
   * <p>Copyright: Copyright (c) </p>
   * <p>Company: </p>
   * @author not attributable
   * @version 1.0
   */
  protected class TestThread
      extends Thread {

    protected String name;

    /**
     *
     * @param name String
     */
    public TestThread(String name) {
      super();
      this.name = name;
    }

    public void run() {
      MutexTest.mutex.take();
      MutexTest.mutex.getWaitingThreads();
      try {
        for (int i = 0; i < 5; i++) {
          System.out.println(name + ": " + i);
          Thread.sleep(200);
        }
      } catch (InterruptedException ix) {
        System.out.println(name + ": interrupted!");
      }
      MutexTest.mutex.release();
    }
  }
}

package jdk1_6examples.threads.deadlocks;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
public class DefaultDeadlockListener
    implements
    ThreadDeadlockDetector.Listener {
  public void deadlockDetected(Thread[] threads) {
    System.err.println("Deadlocked Threads:");
    System.err.println("-------------------");
    for (Thread thread : threads) {
      System.err.println(thread);
      for (StackTraceElement ste : thread.getStackTrace()) {
        System.err.println("\t" + ste);
      }
    }
  }
}

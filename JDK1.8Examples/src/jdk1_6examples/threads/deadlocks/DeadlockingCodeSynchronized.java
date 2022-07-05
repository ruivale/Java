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
public class DeadlockingCodeSynchronized
    implements DeadlockingCode {
  private final Object lock = new Object();
  public synchronized void f() {
    synchronized (lock) {
      // do something

    }
  }

  public void g() {
    synchronized (lock) {
      f();
    }
  }
}

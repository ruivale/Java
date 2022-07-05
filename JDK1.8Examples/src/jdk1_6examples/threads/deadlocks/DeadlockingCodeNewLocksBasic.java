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
import java.util.concurrent.locks.*;


public class DeadlockingCodeNewLocksBasic
    implements DeadlockingCode {

  private final Lock lock1 = new ReentrantLock();
  private final Lock lock2 = new ReentrantLock();

  public void f() {
    lock1.lock();
    try {
      lock2.lock();
      try {
        // do something

      } finally {
        lock2.unlock();
      }
    } finally {
      lock1.unlock();
    }
  }

  public void g() {
    lock2.lock();
    try {
      f();
    } finally {
      lock2.unlock();
    }
  }

  public static void main(String[] args) {

  }
}

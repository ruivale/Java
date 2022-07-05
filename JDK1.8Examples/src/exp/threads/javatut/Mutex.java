package exp.threads.javatut;

// Mutex.java

import java.util.LinkedList;
import java.util.Iterator;
import javax.swing.Timer;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

/**
 * Mutex that can monitor waiting threads and detect potential deadlock situations.
 *
 * That actually works really well. Sorry about the lack of comments.
 * To use it, instead of synchronizing using the synchronized keyword,
 * use take() and release(), like so (this is the simplest example):
 *
 * protected static Mutex mutex1 = new Mutex();
 *
 * public void methodThatNeedsSynchronized () {
 *     mutex1.take();
 *     ...
 *     mutex1.release();
 * };
 *
 *
 * public void anotherOneThatNeedsSynchronized () {
 *     mutex1.take();
 *     ...
 *     mutex1.release();
 * };
 *
 * The default potential deadlock threshold is 5000ms. So, if two threads or
 * methods or whatever are waiting on the same mutex for more than 5000ms,
 * a DeadlockEvent will be fired (you'll have to register DeadlockListeners
 * for the mutex's you want to monitor). The DeadlockEvent has some convenient
 * methods in it that let you get an array of Threads waiting on the mutex, and
 * also let you get the Thread currently holding the mutex.
 *
 * I didn't put in a away to disable deadlock detection. Sure, you could always
 * remove all the listeners, but the timer will still be running and stuff.
 *
 * Here's the most recent code I used to test it. It's really crappy, too:
 **/
public class Mutex {

  /** Default deadlock threshold. */
  public static final int DEFAULT_DEADLOCK_TIMEOUT_MS = 5000;

  // These are just for LinkedList.toArray, so we don't have to instantiate a
  // new array each time just to get it's type.
  protected static DeadLockListener[] dlArrayType = new DeadLockListener[0];
  protected static Thread[] thrArrayType = new Thread[0];

  // Protected stuff.
  protected LinkedList waiting = new LinkedList();
  protected Thread holder = null;
  protected LinkedList deadlockListeners = new LinkedList();
  protected Timer deadlockTimer;
  protected String name;

  /** Constructs unnamed mutex, default timeout. */
  public Mutex() {
    this(null, DEFAULT_DEADLOCK_TIMEOUT_MS);
  };

  /** Constructs named mutex, default timeout. */
  public Mutex(String name) {
    this(name, DEFAULT_DEADLOCK_TIMEOUT_MS);
  };

  /** Constructs unnamed mutex, specified timeout. */
  public Mutex(int timeout) {
    this(null, timeout);
  };

  /** Constructs named mutex, specified timeout. */
  public Mutex(String name, int timeout) {
    this.name = name;
    this.deadlockTimer = new Timer(timeout, new AbstractAction() {
      public void actionPerformed(ActionEvent evt) {
        Thread[] threads = getWaitingThreads();
        if (threads.length != 0) {
          fireDeadlocked(threads, holder);
        }
      };
    });
    this.deadlockTimer.setRepeats(false);
  };

  /** Reset deadlock timer. This can be called manually if necessary. */
  public void resetDeadlockTimer() {
    this.deadlockTimer.restart();
  };

  /** Registers a listener to receive deadlock events. */
  public void addDeadlockListener(DeadLockListener dl) {
    deadlockListeners.add(dl);
  };

  /** Removes a registered listener. */
  public void removeDeadlockListener(DeadLockListener dl) {
    deadlockListeners.remove(dl);
  };

  /** Gets an array of registered listeners. */
  public DeadLockListener[] getDeadlockListeners() {
    return (DeadLockListener[]) deadlockListeners.toArray(dlArrayType);
  };

  /** Notifies all listeners of an event. */
  protected void fireDeadlocked(Thread[] threads, Thread holder) {
    DeadlockEvent evt = new DeadlockEvent(this, threads, holder);
    Iterator i = deadlockListeners.iterator();
    while (i.hasNext()) {
      ( (DeadLockListener) i.next()).deadlocked(evt);
    }
  };

  /** Gets an array of threads waiting on this mutex, not including the thread
   *  that is currently holding the mutex. */
  public Thread[] getWaitingThreads() {
    synchronized (waiting) {
      return (Thread[]) waiting.toArray(thrArrayType);
    }
  };

  /** Take mutex. If mutex is already taken, this waits for it to be released. */
  public void take() {
    //System.out.println("Thread " + Thread.currentThread() + " taking mutex held by " + holder);
    synchronized (waiting) {
      waiting.add(Thread.currentThread());
      resetDeadlockTimer();
    }
    ;
    synchronized (this) {
      while (holder != null) {
        Thread.yield();
      }
      holder = Thread.currentThread();
    }
    ;
    synchronized (waiting) {
      waiting.remove(holder);
      resetDeadlockTimer();
    }
    ;
  };

  /** Releases mutex, making it available to other threads. */
  public void release() {
    //System.out.println("Thread " + Thread.currentThread() + " releasing mutex held by " + holder);;
    if (holder == Thread.currentThread()) {
      holder = null;
    }
  };

  /** Gets mutex name. If mutex is unnamed, returns 'null'. */
  public String getName() {
    return name;
  };

  /** String representation of mutex. */
  public String toString() {
    String name = getName();
    return super.toString() + " [" + (name == null ? "unnamed mutex" : name) +
        "]";
  };

};

package exp.threads.pools;

import java.util.Vector;

/**
 * <p>Classname: ThreadPool</p>
 *
 * <p>Description:
 * This class implements a thread pool. Each thread waits for work. When
 * signaled, it simply pulls the first object from the Runnables list and
 * executes it. When execution of that object is finished, the thread must
 * notify the lock associated with it.
 * </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @see BusyFlag
 * @see CondVar
 * @see ThreadPool.ThreadPoolRequest
 * @see ThreadPool.ThreadPoolThread
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version $Revision: 1.2 $
 */
public class ThreadPool {

  private static final int INT_INITIAL_NBR_THREADS = 3;
  //~ Instance fields //////////////////////////////////////////////////////////

  /**
   * The mutually exclusive lock used by the two condition variables,
   * cvAvailable and cvEmpty.
   *
   * @see #cvAvailable
   * @see #cvEmpty
   */
  private BusyFlag cvFlag;

  /** Condition variable which signals that work is available o be performed. */
  private CondVar cvAvailable;

  /** Condition variable which signals that all pending work has been completed. */
  private CondVar cvEmpty;

  /** The Runnables repository. */
  private Vector objects;

  /** This thread pool available threads. */
  private ThreadPoolThread[] poolThreads;

  /**
   * Indicates that this thread pool will not accept more Runnables. Do change
   * this thread pool accepting policy, the waitForAll(boolean) method must be
   * invoked with the true arg.
   *
   * @see #waitForAll(boolean)
   */
  private boolean terminated = false;

  /**
   * Indicates the total number of Runnables, running or pending.
   *
   * @see #getTotalNumberOfRunnables()
   */
  private int nObjects = 0;

  /** Initial number of threads */
  private int intInitialNumberOfThreads = INT_INITIAL_NBR_THREADS;

  /** The real maximum number of threads. */
  private int intMaxNbrOfThreads = intInitialNumberOfThreads * 2;


  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new ThreadPool object.
   *
   * @param intNbrOfThreads the number of available Threads in this thread pool.
   */
  public ThreadPool(final int intNbrOfThreads) {
    this.cvFlag        = new BusyFlag();
    this.cvAvailable   = new CondVar(cvFlag);
    this.cvEmpty       = new CondVar(cvFlag);
    this.objects       = new Vector();
    this.poolThreads   = new ThreadPoolThread[intNbrOfThreads];

    for(int i = 0; i<intNbrOfThreads; i++) {
      this.poolThreads[i] = new ThreadPoolThread(
          this,
          i);
      this.poolThreads[i].start();

System.out.println("Starting the thread["+i+"].");

    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Adds the Runnable to the list of Runnables. It's executed when possible.
   *
   * @param runnableTarget the Runnable to execute.
   * @see #addRequestAndWait(Runnable)
   */
  public void addRequest(final Runnable runnableTarget) {
    add(
      runnableTarget,
      null);
  }

  /**
   * Adds the Runnable to the list of Runnables. It is executed when possible.
   * Runnables not yet started will only run after this one terminates.
   * Be aware that invoking this method with a never ending Runnable will block
   * all the incoming Runnables.
   *
   * @param runnableTarget the Runnable to execute.
   * @throws InterruptedException
   * @see addRequest(Runnable)
   */
  public void addRequestAndWait(final Runnable runnableTarget)
      throws InterruptedException {
    Object lock = new Object();

    synchronized(lock) {
      add(
        runnableTarget,
        lock);
      lock.wait();
    }
  }

  /**
   * When all pending work has been completed, this method returns. When at a
   * given moment in time the number of running Runnables is zero, this method
   * returns. Setting it's arg to true indicates a termination is being request
   * to this pool.
   *
   * @param terminate normally false. If true, the thread pool will not accept
   * more Runnables, the threads will all terminate, we hope, and this class can
   * be GC
   *
   * @throws InterruptedException
   * @see #waitForAll()
   */
  public void waitForAll(final boolean terminate)
      throws InterruptedException {

    try {
      cvFlag.getBusyFlag();

      while(nObjects!=0) {
        cvEmpty.cvWait();
      }

      if(terminate) {
        for(int i = 0; i<poolThreads.length; i++) {
          poolThreads[i].shouldRun = false;
        }

        cvAvailable.cvBroadcast();
        terminated = true;
      }
    } finally {
      cvFlag.freeBusyFlag();
    }
  }

  /**
   * When all pending work has been completed, this method returns. When at a
   * given moment in time the number of running Runnables is zero, this method
   * returns.
   *
   * @throws InterruptedException
   * @see #waitForAll(boolean)
   */
  public void waitForAll()
      throws InterruptedException {

    waitForAll(false);
  }

  /**
   * Total number of Runnables, running or pending.
   *
   * @return int the number of Runnables
   */
  public int getTotalNumberOfRunnables(){
    return this.nObjects;
  }


  /**
   * Adds the Runnable to the list of Runnables. If this thread pool was set to
   * terminate, using the waitForAll(boolean) method, it will throw a Exception,
   * a IllegalStateException.
   *
   * @param target the Runnable
   * @param lock the locking object, if any
   *
   * @see #addRequest(Runnable)
   * @see #addRequestAndWait(Runnable)
   * @see #waitForAll(boolean)
   */
  private void add(
      final Runnable target,
      final Object   lock) {

    try {
      cvFlag.getBusyFlag();

      if(terminated) {
        throw new IllegalStateException("Thread pool has shutdown");
      }

      objects.addElement(new ThreadPoolRequest(
          target,
          lock));
      nObjects++;


      if(nObjects >= this.intMaxNbrOfThreads){
        // We must add more threads to this thread pool.
        raiseNumberOfAvailableThreads();
      }

      cvAvailable.cvSignal();

    } finally {
      cvFlag.freeBusyFlag();
    }
  }

  /**
   * Raises the number of available threads.
   */
  private void raiseNumberOfAvailableThreads() {
    final int intOldNbrOfThreads = this.poolThreads.length;
    final int intNbrOfObjs = this.nObjects;

    if(intOldNbrOfThreads < intNbrOfObjs){
      final ThreadPoolThread[] pThreads = new ThreadPoolThread[intNbrOfObjs];

      // Copying the old threads to the new auxiliar array ...
      System.arraycopy(this.poolThreads, 0, pThreads, 0, this.poolThreads.length);

      // Raising the poolThreads array size ...
      this.poolThreads = new ThreadPoolThread[intNbrOfObjs];
      // .., and copying the new threads to it.
      System.arraycopy(pThreads, 0, this.poolThreads, 0, pThreads.length);

      // Start the threads that were not started ...
      final int intNbrOfThreads = this.poolThreads.length;
      for (int i = intOldNbrOfThreads; i < intNbrOfThreads; i++) {
        this.poolThreads[i] = new ThreadPoolThread(
            this,
            i);
        this.poolThreads[i].start();

        System.out.println("Starting the thread[" + i + "]. new raised size");

      }
    }
  }

  //~ Inner Classes ////////////////////////////////////////////////////////////

  class ThreadPoolRequest {
      final Object   lock;
      final Runnable target;

    ThreadPoolRequest(
      Runnable t,
      Object   l) {
      target   = t;
      lock     = l;
    }
  }


  class ThreadPoolThread
      extends Thread {

    private ThreadPool parent;
    private boolean    shouldRun = true;

    ThreadPoolThread(
        final ThreadPool parent,
        final int        i) {
      super("ThreadPoolThread " + i);
      this.parent = parent;
    }

    public void run() {
      ThreadPoolRequest obj = null;

      while(shouldRun) {
        try {
          parent.cvFlag.getBusyFlag();

          while((obj==null) && shouldRun) {
            try {
              obj = (ThreadPoolRequest)parent.objects.elementAt(0);
              parent.objects.removeElementAt(0);
            } catch(ArrayIndexOutOfBoundsException aiobe) {
              obj = null;
            } catch(ClassCastException cce) {
              System.err.println("Unexpected data");
              obj = null;
            }

            if(obj==null) {
              try {
                parent.cvAvailable.cvWait();
              } catch(InterruptedException ie) {
                return;
              }
            }
          }
        } finally {
          parent.cvFlag.freeBusyFlag();
        }

        if(!shouldRun) {
          return;
        }

        obj.target.run();

        try {
          parent.cvFlag.getBusyFlag();
          nObjects--;

          if(nObjects==0) {
            parent.cvEmpty.cvSignal();
          }
        } finally {
          parent.cvFlag.freeBusyFlag();
        }

        if(obj.lock!=null) {
          synchronized(obj.lock) {
            obj.lock.notify();
          }
        }

        obj = null;
      }
    }
  }
}

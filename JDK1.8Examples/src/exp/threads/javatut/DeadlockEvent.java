package exp.threads.javatut;

// DeadlockEvent.java

/** Describes a deadlock event. */
public class DeadlockEvent
    extends java.util.EventObject {

  // Protected stuff.
  protected Thread[] waiting;
  protected Thread holding;

  /** Constructs a DeadlockEvent with specified source and deadlocked
   *  threads. */
  public DeadlockEvent(Object source, Thread[] waiting, Thread holding) {
    super(source);
    this.waiting = waiting;
    this.holding = holding;
  };

  /** Returns an array of threads that may be deadlocked. */
  public Thread[] getWaitingThreads() {
    return waiting;
  };

  /** Returns thread currently holding the mutex in question. */
  public Thread getHoldingThread() {
    return holding;
  };

};

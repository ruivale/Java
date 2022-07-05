package exp.threads.javatut;

// DeadlockListener.java

/** Listener to receive deadlock events. */
public interface DeadLockListener
    extends java.util.EventListener {

  /** Called when a potential deadlock situation is detected. */
  public void deadlocked(DeadlockEvent evt);

};

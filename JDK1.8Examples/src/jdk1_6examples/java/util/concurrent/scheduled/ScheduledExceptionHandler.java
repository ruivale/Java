package jdk1_6examples.java.util.concurrent.scheduled;


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
public interface ScheduledExceptionHandler {
  /**
   * @return true if the task should be rescheduled;
   *         false otherwise
   */
  boolean exceptionOccurred(Throwable e);
}

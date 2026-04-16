/**
 * <p>
 * Classname: jdk23examples.thread.virtual.inspec.ThreadInspectorDemo
 * </p>
 *
 * Virtual Thread States
 *    I have never seen a new Java feature get adopted as quickly as virtual threads. Major milestones
 * were generics, streams, but even these took years before programmers used them with enthusiasm. 
 * However, even before virtual threads were fully baked, I was receiving requests for consulting 
 * help to iron out some virtual thread issues in production. 
 *    Over the years, they have improved substantially, and the way that I like to show students of 
 * my Mastering Virtual Threads in Java Course what is going on, is to explore the state machine. 
 * It is well documented in the VirtualThread class, and you don't need to visit my course to 
 * understand it - just read the comments in the class. The platform thread states are still the old 
 * NEW, RUNNABLE, etc. that we've always had, but the virtual thread states also have states such as 
 * RUNNING, YIELDING, PARKING, BLOCKING, BLOCKED, etc. Java does not expose the virtual thread states 
 * though, so if we call getState(), we get a matching platform thread state. 
 *    In order to get this detailed information, I wrote a ThreadInspector, which returns a String 
 * containing the virtual thread state, followed by the platform thread state. We use deep reflection 
 * to find all the private static final int fields in the VirtualThread class, plus their values and 
 * field names. We then put them in a Map and use that to return the internal virtual thread state name. 
 * For example, if a virtual thread is busy executing on a carrier thread, it's compound state would 
 * be RUNNING/RUNNABLE. If it is busy yielding with Thread.yield(), it might be YIELDING/RUNNABLE or 
 * YIELDED/RUNNABLE. 
 * 
 * 
 * <p>
 * Copyright: Dr Heinz M. Kabutz <heinz@javaspecialists.eu>
 * </p>
 */
package jdk24examples.thread.virtual.inspec;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
// Use the JVM args --add-opens java.base/java.lang=ALL-UNNAMED

public class ThreadInspectorDemo {

  public static void main(String... args)
    throws InterruptedException, IOException {
    demo(Thread.ofVirtual());
    demo(Thread.ofPlatform());
  }

  private static void demo(Thread.Builder builder)
    throws InterruptedException, IOException {
    System.out.println(builder.getClass().getSimpleName());
    demoUnstarted(builder);
    demoTerminated(builder);
    demoRunning(builder);
    demoWaiting(builder);
    demoBlocked(builder);
    demoSleeping(builder);
    demoWaitingOnIO(builder);
    System.out.println();
  }

  private static void demoUnstarted(Thread.Builder builder) {
    demo("Unstarted", builder.unstarted(() -> {
    }));
  }

  private static void demoTerminated(Thread.Builder builder)
    throws InterruptedException {
    var terminatedThread = builder.start(() -> {
    });
    terminatedThread.join();
    demo("Terminated", terminatedThread);
  }

  private static void demoRunning(Thread.Builder builder)
    throws InterruptedException {
    var running = new AtomicBoolean(true);
    var runningThread = builder.start(() -> {
      while (running.get()) ;
    });
    Thread.sleep(10); // give thread a chance to really start
    demo("Running", runningThread);
    running.set(false);
    runningThread.join();
  }

  private static void demoWaiting(Thread.Builder builder)
    throws InterruptedException {
    var monitor = new Object();
    var waitingThread = builder.start(() -> {
      synchronized (monitor) {
        try {
          monitor.wait();
        } catch (InterruptedException e) {
          throw new CancellationException();
        }
      }
    });
    Thread.sleep(10);
    demo("Waiting", waitingThread);
    synchronized (monitor) {
      monitor.notify();
    }
    waitingThread.join();
  }

  private static void demoBlocked(Thread.Builder builder)
    throws InterruptedException {
    var monitor = new Object();
    synchronized (monitor) {
      var blockedThread = builder.start(() -> {
        synchronized (monitor) {
        }
      });
      Thread.sleep(10);
      demo("Blocked", blockedThread);
    }
  }

  private static void demoSleeping(Thread.Builder builder)
    throws InterruptedException {
    var sleepingThread = builder.start(() -> {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new CancellationException();
      }
    });
    Thread.sleep(10);
    demo("Sleeping", sleepingThread);
  }

  private static void demoWaitingOnIO(Thread.Builder builder)
    throws InterruptedException, IOException {
    var waitingOnIOThread = builder.start(() -> {
      try (var serverSocket
        = new ServerSocket(8080)) {
        serverSocket.accept();
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    });
    Thread.sleep(100); // needs longer wait
    demo("Waiting on IO", waitingOnIOThread);
    new Socket("localhost", 8080);
    waitingOnIOThread.join();
  }

  private static void demo(String description, Thread thread) {
    System.out.printf("%s: %s%n", description,
      ThreadInspector.getCompoundThreadStates(thread));
  }
}

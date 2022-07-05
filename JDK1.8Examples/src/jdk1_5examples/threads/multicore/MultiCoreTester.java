package jdk1_5examples.threads.multicore;


/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
import java.lang.management.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;


public class MultiCoreTester {
  private static final int THREADS = 5;
  private static CountDownLatch ct = new CountDownLatch(THREADS);
  private static AtomicLong total = new AtomicLong();

  public static void main(String[] args)
      throws InterruptedException {
    long elapsedTime = System.nanoTime();
    for (int i = 0; i < THREADS; i++) {
      Thread thread = new Thread() {
        public void run() {
          total.addAndGet(measureThreadCpuTime());
          ct.countDown();
        }
      };
      thread.start();
    }
    ct.await();
    elapsedTime = System.nanoTime() - elapsedTime;
    System.out.println("Total elapsed time " + elapsedTime);
    System.out.println("Total thread CPU time " + total.get());
    double factor = total.get();
    factor /= elapsedTime;
    System.out.printf("Factor: %.2f%n", factor);
  }

  private static long measureThreadCpuTime() {
    ThreadMXBean tm = ManagementFactory.getThreadMXBean();
    long cpuTime = tm.getCurrentThreadCpuTime();
    for (int i = 0; i < 1000 * 1000 * 1000; i++) {
      // keep ourselves busy for a while ...

    }
    cpuTime = tm.getCurrentThreadCpuTime() - cpuTime;
    System.out.println(Thread.currentThread() + ": cpuTime = " + cpuTime);
    return cpuTime;
  }
}

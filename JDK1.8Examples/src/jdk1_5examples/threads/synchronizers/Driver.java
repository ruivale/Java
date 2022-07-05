package jdk1_5examples.threads.synchronizers;

import java.util.concurrent.*;


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
public class Driver {
  private static final int N = 5;

  void main()
      throws InterruptedException {
    CountDownLatch startSignal = new CountDownLatch(1);
    CountDownLatch doneSignal = new CountDownLatch(N);
    for (int i = 0; i < N; ++i) { // create and start threads
      new Thread(new Worker(startSignal, doneSignal)).start();
    }
    //doSomethingElse(); // don't let them run yet
    startSignal.countDown(); // let all threads proceed
    //doSomethingElse();
    doneSignal.await(); // wait for all to finish
  }
}


class Worker
    implements Runnable {
  private final CountDownLatch startSignal;
  private final CountDownLatch doneSignal;
  Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
    this.startSignal = startSignal;
    this.doneSignal = doneSignal;
  }

  public void run() {
    try {
      startSignal.await();
      //doWork();
      doneSignal.countDown();
    } catch (InterruptedException ex) {} // return;
  }
}

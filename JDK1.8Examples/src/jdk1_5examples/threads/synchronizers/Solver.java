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
public class Solver { // Code sketch
/*
  void solve(final Problem p, int nThreads) {
    final CyclicBarrier barrier = new CyclicBarrier(nThreads, new Runnable() {
      public void run() {
        p.checkConvergence();
      }
    });
    for (int i = 0; i < nThreads; ++i) {
      final int id = i;
      Runnable worker = new Runnable() {
        final Segment segment = p.createSegment(id);
        public void run() {
          try {
            while (!p.converged()) {
              segment.update();
              barrier.await();
            }
          } catch (Exception e) {
            return;
          }
        }
      };
      new Thread(worker).start();
    }
  }
 */
}

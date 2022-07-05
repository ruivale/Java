package jdk1_6examples.java.util.concurrent;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class Producer implements Runnable {
  private String name;

  private BlockingDeque<Integer> deque;

  public Producer(String name, BlockingDeque<Integer> deque) {
    this.name = name;
    this.deque = deque;
  }

  public synchronized void run() {

    for (int i = 0; i < 10; i++) {
      try {
        deque.putFirst(i);
        System.out.println(name + " puts " + i);
        Thread.sleep(300);

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}

package exp.threads;


/**
 * Title: Description: Copyright:    Copyright (c) Company:
 *
 * @author
 * @version 1.0
 */
public class RunnableRun {
  public RunnableRun() {
    (new Runnable() {
        public void run() {
          System.out.println("STARTING FOR");
          for(int i = 0; i<1000000000; i++) {
          }
          System.out.println("STOPING FOR");
        }
      }).run();
    System.out.println("AFTER RUNNABLE");

    (new Thread() {
        public void run() {
          System.out.println("STARTING FOR");
          for(int i = 0; i<1000000000; i++) {
          }
          System.out.println("STOPING FOR");
        }
      }).start();
    System.out.println("AFTER THREAD");
  }

  public static void main(String[] args) {
    RunnableRun runnableRun1 = new RunnableRun();
    System.out.println("MAIN");
  }
}

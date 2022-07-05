package exp.runtime;


/**
 * <p>Title: exp.runtime.TestExitHook</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestExitHook {
  public TestExitHook() {

    System.out.println("STARTING ...");

    Runtime.getRuntime().addShutdownHook(new Thread(){
      public void run(){
        System.out.println("ShutdownHook ... EXITING... only after 10 secs ... ahahahahahahah");

        final Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {
            try {Thread.sleep(2500); } catch (InterruptedException e) {}
            System.out.println("Will REALY halt the system ...");

            Runtime.getRuntime().halt(1);

          }
        }, "ThreadName");
        thread.setDaemon(true);
        thread.start();

        try {Thread.sleep(10000); } catch (InterruptedException e) {}

      }
    });

    try {
      System.out.println("... SLEEPING");
      Thread.sleep(2500);
      System.out.println("... WAKING UP.");
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.exit(0);
  }

  public static void main(String[] args) {
    TestExitHook testexithook = new TestExitHook();
  }
}

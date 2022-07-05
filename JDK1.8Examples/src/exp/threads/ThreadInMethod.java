package exp.threads;



/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
/**
 * Com o sleep o interrupt funciona, mas com um método mais elaborado não!
 */
public class ThreadInMethod {
  /**
   * Creates a new ThreadInMethod object.
   */
  public ThreadInMethod() {
    method();
  }

  /**
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    ThreadInMethod threadInMethod1 = new ThreadInMethod();
  }

  /**
   *
   */
  public void method() {
    //   public void method(final Object a, final Object b, final Object c){
    final long TIMEOUT = 3000;
    Runnable   r =
      new Runnable() {
        public void run() { // whatever needs to be done with a,b,c
          try {
            System.out.println("ANTES DE POR A DORMIR!");
            Thread.sleep(10000);

            //makeLargeExecution();
            System.out.println("DEPOIS DE DORMIR!");
          } catch(Exception e) {
            e.printStackTrace();
          }
        }
      };
    Thread t = new Thread(r);
    t.start();
    try {
      t.join(TIMEOUT);
    } catch(InterruptedException ex) {
      // handle interruption of invoking thread
      ex.printStackTrace();
    }
    if(t.isAlive()) {
      System.out.println("IS ALIVE");

      // cleanup the new thread using an appropriate means
      // eg. stop(), interrupt() or whatever
      t.interrupt();
      if(t.isAlive()) {
        System.out.println("STILL ALIVE");
      }
    }
  }

  private String makeLargeExecution() {
    String a = null;
    for(int i = 0; i<1000000000; i++) {
      a = ""+i;
    }

    return a;
  }
}

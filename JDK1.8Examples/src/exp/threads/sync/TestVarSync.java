package exp.threads.sync;


/**
 * <p>Title: </p>
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
public class TestVarSync {

  private static Object obj = new Object();

  public TestVarSync() {

    new Thread(new Runnable() {
      public void run() {
        try {
          for (int i = 0; i < 5; i++) {
            synchronized (obj) {

              System.out.println(
                  "just grabbed the lock in TestVarSync() ... sleeping ...");
              Thread.sleep(776);
              System.out.println("... TestVarSync() waking up.");
            }
            Thread.sleep(1545);
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();

    new AnotherClazz(this);


  }

  public Object getObject(){
    return obj;
  }

  public static void main(String[] args) {
    TestVarSync testvarsync = new TestVarSync();
  }
}

class AnotherClazz{

  AnotherClazz(TestVarSync testVarSync){

    try {
      for (int i = 0; i < 5; i++) {
        synchronized (testVarSync.getObject()) {

          System.out.println(
              "just grabbed the lock in AnotherClazz() ... sleeping ...");
          Thread.sleep(476);
          System.out.println("... AnotherClazz() waking up.");
        }
        Thread.sleep(2345);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}

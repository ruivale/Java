package exp.threads.sync;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MySyncTests {
  public MySyncTests() {
    (new Thread(){
      public void run(){
        syncMethod();
      }
    }).start();
    try {
      Thread.sleep(2000);
    }catch (Exception ex) {
      ex.printStackTrace();
    }
    syncMethod();
    //notSyncMethod("");
  }

  private synchronized void syncMethod(){
    System.out.println("syncMethod()");
    for (int i = 0; i < 4; i++) {
      try {
        Thread.sleep(750);
      }catch (Exception ex) {
        ex.printStackTrace();
      }
      notSyncMethod("    ");
      if(i==2){
        try {
          wait();
        }catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  private void notSyncMethod(final String s){
    for (int i = 0; i < 3; i++) {
      System.out.println(s+"notSyncMethod() i="+i+".");
    }
  }

  public static void main(String[] args) {
    MySyncTests mySyncTests1 = new MySyncTests();
  }

}

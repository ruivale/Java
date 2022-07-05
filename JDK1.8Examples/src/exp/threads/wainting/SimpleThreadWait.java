package exp.threads.wainting;


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
public class SimpleThreadWait {

  static final Object OBJ = new Object();

  public SimpleThreadWait() {

    MySimpleThreadWait mst1 = new MySimpleThreadWait("first");
    MySimpleThreadNotify mst2 = new MySimpleThreadNotify("second");

    mst1.start();
    mst2.start();

  }

  public static void main(String[] args) {
    SimpleThreadWait s = new SimpleThreadWait();
  }


  class MySimpleThreadWait extends Thread{
    String name;

    MySimpleThreadWait(String name){
      this.name = name;
    }


    public void run(){
      try {
        while (true) {
          synchronized (SimpleThreadWait.OBJ) {
            System.out.println("Wainting in thread(" + name + ") wait. " +System.currentTimeMillis());

            SimpleThreadWait.OBJ.wait();

            System.out.println("Waking up in thread(" + name + ") wait. " +System.currentTimeMillis());
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
  class MySimpleThreadNotify extends Thread{
    String name;

    MySimpleThreadNotify(String name){
      this.name = name;
    }


    public void run(){
      try {
        while (true) {
          System.out.println("Press ENTER to try the lock ...");
          System.in.read();

          synchronized (SimpleThreadWait.OBJ) {
            SimpleThreadWait.OBJ.notifyAll();
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }

    }
  }
}

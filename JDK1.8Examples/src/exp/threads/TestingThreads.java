package exp.threads;


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
public class TestingThreads implements Runnable{
  public TestingThreads(){
    new Thread(this, "first").start();
    new Thread(this, "second").start();
    new Thread(this, "third").start();
  }

  public void run(){
    if(Thread.currentThread().getName().equals("first")){
      System.out.print("1st - ");
    }else if(Thread.currentThread().getName().equals("second")){
      System.out.print("2nd - ");
    }else if(Thread.currentThread().getName().equals("third")){
      System.out.print("3th - ");
    }

    System.out.println("toString()=" +
        Thread.currentThread().toString());
  }

  public static void main(String[] args) throws Exception{
    TestingThreads t = new TestingThreads();
    System.in.read();
  }
}

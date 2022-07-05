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
public class TestingThreadsWithGivenRunnable extends Thread{
  public TestingThreadsWithGivenRunnable(){
    this.start();
    this.start();
    this.start();
  }

  public void run(){
    System.out.println("this.toString()=" + this.toString());
  }

  public static void main(String[] args){
    TestingThreadsWithGivenRunnable t = new TestingThreadsWithGivenRunnable();
  }
}

package exp._quiz;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class KickOff {

  public void kickOff()throws InterruptedException, ExecutionException{
    ExecutorService es = Executors.newFixedThreadPool(4);
    Callable<String> c = new Callable<String>() {
      public String call(){
        //try {Thread.sleep(1000);} catch (InterruptedException e) {}
        return "Done";
      }

    };
    String result = es.submit(c).get(); // line n1

    System.out.println("result: "+result);
  }

  public static void main(final String[] args) {
    try {
      final KickOff clazz = new KickOff();
      clazz.kickOff();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }finally{
      System.out.println("finally");
      System.exit(0);
    }
  }
}

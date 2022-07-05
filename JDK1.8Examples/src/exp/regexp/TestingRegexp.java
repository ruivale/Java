package exp.regexp;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

 import java.util.regex.*;


public class TestingRegexp {

  public TestingRegexp() {
    int i = 1;
    boolean b = Pattern.matches("([1-4]|10|16|[20-25])",""+2);

    try{
      System.out.println("b: "+b+".");
      //System.in.read();
      System.out.println("END");

    }catch(Exception e){
      e.printStackTrace();
    }

  }
  public static void main(String[] args) {
    TestingRegexp testingRegexp1 = new TestingRegexp();
  }
}
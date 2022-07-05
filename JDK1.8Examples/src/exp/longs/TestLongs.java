package exp.longs;


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
public class TestLongs {
  public static void main(String[] s){
    long l = Long.parseLong(
        new StringBuffer().append("999999998").append("98888888").toString());
    System.out.println("l="+l+".");
  }
}

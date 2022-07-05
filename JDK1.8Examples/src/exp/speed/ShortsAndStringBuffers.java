package exp.speed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ShortsAndStringBuffers {

  public ShortsAndStringBuffers() {

    short s = 2;

    long t0 = System.currentTimeMillis();

    String strB = new StringBuffer("").append(s).toString();

    long t1 = System.currentTimeMillis();

    String str = new Short(s).toString();

    long t2 = System.currentTimeMillis();

    System.out.println("new StrBu(): "+(t1-t0)+".");
    System.out.println("new Short(): "+(t2-t1)+".");


  }
  public static void main(String[] args) {
    ShortsAndStringBuffers shortsAndStringBuffers1 = new ShortsAndStringBuffers();
  }
}
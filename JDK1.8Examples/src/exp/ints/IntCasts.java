package exp.ints;


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
public class IntCasts {
  public IntCasts() {

    int i = 0;
    System.out.println("i="+i+".");

    i = 2/4;
    System.out.println("2/4 i="+i+".");
    i = 2/3;
    System.out.println("2/3 i="+i+".");
    i = 2/2;
    System.out.println("2/2 i="+i+".");
    i = 2/1;
    System.out.println("2/1 i="+i+".");
  }

  public static void main(String[] args) {
    IntCasts intcasts = new IntCasts();
  }
}

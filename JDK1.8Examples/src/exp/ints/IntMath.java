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
public class IntMath {
  public static void main(String[] s){
    int cols = 3;
    int rows = 3;
    int ww = 350;
    int wh = 280;
    int sw = 1152;
    int sh = 864;

    int x = 0;
    int y = 0;

    for (int i = 0; i < 8; i++) {
      x = ((i%cols))*ww;
      y = ((i/rows))*wh;

      System.out.println("("+x+","+y+")");
    }
  }
}

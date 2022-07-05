package exp.colors;

import java.awt.Color;


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
public class ColorToString {
  public static void main(final String[] args) {
    System.out.println("(new Color(230,240,210)).toString()=" +
                       (new Color(230,240,210)).toString());
    System.out.println("(new Color(230,240,210)).toString()=" +
                       (Color.decode("230,240,210")).toString());
  }

}

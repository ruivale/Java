package exp.properties;


import java.util.*;

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
public class MyResourceBundle {
  public MyResourceBundle() {
    ResourceBundle bundle =
        ResourceBundle.getBundle("exp/properties/property");
    String s = bundle.getString("a.property.to.be.reload");
    System.out.println("s=" + s);
  }

  public static void main(String[] args) {
    MyResourceBundle resourcebundle = new MyResourceBundle();
  }
}

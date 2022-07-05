package exp.locale;


import java.util.Locale;


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
public class Tests {
  public Tests() {
    System.out.println("System.getProperty(\"user.language\")=" +
                       System.getProperty("user.language"));
    System.out.println("System.getProperty(\"user.region\")=" +
                       System.getProperty("user.region"));
    System.out.println("System.getProperty(\"user.country\")=" +
                       System.getProperty("user.country"));
    System.out.println("System.getProperty(\"user.variant\")=" +
                       System.getProperty("user.variant"));


    Locale locale = Locale.getDefault();
    System.out.println("Locale.getDefault()=" + locale);
  }

  public static void main(String[] args) {
    Tests tests = new Tests();
  }
}

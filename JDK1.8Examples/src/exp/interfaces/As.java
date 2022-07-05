package exp.interfaces;

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
public class As
    extends A {
  public As() {
  }

  /**
   * getName
   *
   * @return String
   * @todo Implement this exp.interfaces.AInt method
   */
  public String getName() {
    System.out.println("As - getName");
    return "As";
  }

  public static void main(String[] args) {
    As as = new As();
  }
}

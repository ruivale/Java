package exp.throwsexception;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class ThrowsException {
  /**
   * Creates a new ThrowsException object.
   */
  public ThrowsException() {
    try {
      pop();
      System.out.println("Passou o pop()!");
    } catch(Exception e) {
      System.out.println("catch da class!");
      e.printStackTrace();
    }
  }

  /**
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    ThrowsException throwsException1 = new ThrowsException();
  }

  /**
   *
   */
  public void pop() {
    int _int = 0;

    try {
      if(_int==0) {
        throw new Exception("EXCEPÇÂO");
      }
      System.out.println("Passou o THROW!");

      return;
    } catch(Exception e) {
      System.out.println("catch do pop!");
      e.printStackTrace();
    }
  }
}

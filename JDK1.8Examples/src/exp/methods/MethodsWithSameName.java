package exp.methods;


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
public class MethodsWithSameName {

  public void method(Object o) {
    System.out.println("Object Verion");
  }

  public void method(String s) {
    System.out.println("String Version");
  }

  public static void main(String args[]) {
    MethodsWithSameName question = new MethodsWithSameName();
    question.method(null);
  }
}

package exp.overriding;


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
public class A{

  public static void main(String[] args) {
      A a = new C();
      B b = (B)a;
      a.method();
      b.method();
  }

  public void method(){
      System.out.println("A");
  }
}



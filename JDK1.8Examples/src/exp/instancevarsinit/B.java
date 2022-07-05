package exp.instancevarsinit;


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
public class B extends A{

  static Object obj = new Object();

  public B() {
    super(obj);
  }

  public static void main(String[] args) {
    B b = new B();

    int i = 0x10;
    System.out.println("i=" + i);
  }
}
